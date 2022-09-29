package com.example.gccoffee.Service;

import com.example.gccoffee.Exception.NoSuchProductException;
import com.example.gccoffee.Exception.NotEnoughStockException;
import com.example.gccoffee.Repository.OrderRepository;
import com.example.gccoffee.Repository.ProductRepository;
import com.example.gccoffee.model.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<OrderItem> jsonToOrderItems(Object json) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object o : (List) json) {
            orderItems.add(OrderItem.createOrderItem(
                    productRepository.findById(UUID.fromString(((HashMap<String, ?>) o).get("productId").toString())),
                    Long.valueOf(((HashMap<String, Integer>) o).get("price")),
                    (int) ((HashMap<String, ?>) o).get("quantity")
            ));
        }
        return orderItems;
    }

    @Override
    @Transactional
    public Order order(String email, String address, String postCode , List<OrderItem> orderItems) {
        LocalDateTime now = LocalDateTime.now();
        Optional<Order> targetOrder;
        if(now.isAfter(now.withHour(14).withMinute(0))){
            //2022-09-19_yeoooo : 들어온 주문이 14시 이후인 경우
            targetOrder = orderRepository.findByEmailAndDayOrderAfter(email, now);
        }else{
            targetOrder = orderRepository.findByEmailAndDayOrder(email, now);
        }
        for (OrderItem o : orderItems) {
            Optional<Product> targetProduct = productRepository.findById(o.getProduct().getProductId());
            if (targetProduct.isEmpty()){
                throw new NoSuchProductException("상품이 존재하지 않습니다.");

            }
            if (targetOrder.isEmpty()) {//2022-09-8_yeoooo : 작일 14:00 부터 당일 14:00 까지의 주문이 없으면 새 주문 생성
                targetOrder = Optional.of(Order.createOrder(email, address, postCode, OrderStatus.ACCEPTED, o));
                log.info("New Order Created : {}", targetOrder);
            }
            else {//2022-09-8_yeoooo : 이전 주문이 있으면 해당 주문에 아이템 밀어넣기
                targetOrder.get().addOrderItem(o);
                log.info("Order Already Exists : {}", targetOrder.get().getOrderItems());
            }
            }

        return orderRepository.save(targetOrder.get());

    }


    @Override
    @Transactional
    public void cancelOrder(UUID uuid) {
        Optional<Order> foundOne = orderRepository.findById(uuid);
        if (foundOne.isEmpty()) {
            throw new IllegalStateException("Already deleted Order");
        }else{
            foundOne.get().cancelOrder();
            log.info("order deleted : {}", foundOne);
        }
    }

    @Override
    @Transactional
    public Order changeOrderStatus(Order order, OrderStatus orderStatus) {
        return orderRepository.save(
                order.builder()
                        .dto(OrderDTO.builder()
                .id(order.getId())
                .address(order.getAddress())
                .orderStatus(orderStatus)
                .price(order.getTotalPrice())
                .orderItems(order.getOrderItems())
                .postcode(order.getPostcode())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .email(order.getEmail())
                .build())
                .build());
    }

    @Override
    public List<Order> findByEmail(String email) {
        return orderRepository.findByEmail(email);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }


}
