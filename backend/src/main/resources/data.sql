INSERT INTO PRODUCT(PRODUCT_ID, CREATED_AT, UPDATED_AT, CATEGORY, DESCRIPTION, PRICE, PRODUCT_NAME, QUANTITY)
VALUES (unhex(replace(UUID(),'-','')), '2022-09-07T12:00', '2022-09-08T12:00', 0, '테스트1용 콩.', 100, '테스트콩1', 6),
 (unhex(replace(UUID(),'-','')), '2022-09-07T12:00', '2022-09-08T12:00', 0, '테스트2용 콩.', 100, '테스트콩2', 1),
 (unhex(replace(UUID(),'-','')), '2022-09-07T12:00', '2022-09-08T12:00', 0, '테스트3용 콩.', 100, '테스트콩3', 2),
 (unhex(replace(UUID(),'-','')), '2022-09-07T12:00', '2022-09-08T12:00', 1, '테스트1용 컵.', 100, '테스트컵1', 3),
 (unhex(replace(UUID(),'-','')), '2022-09-07T12:00', '2022-09-08T12:00', 1, '테스트2용 컵.', 100, '테스트컵2', 4);
-- VALUES (unhex(replace(UUID(),'-','')), '2022-09-07T12:00', '2022-09-08T12:00', 0, '이 콩은 두 번째로 맛있습니다.', 100, '커피짱1', 6),
--        (unhex(replace(UUID(),'-','')), '2022-09-08T12:00', '2022-09-08T12:00', 0, '이 콩은 고양이가 먹다 뱉었습니다.', 300, '커피짱2', 7),
--         (unhex(replace(UUID(),'-','')),'2022-09-09T12:00', '2022-09-08T12:00', 0, '이 콩은 고양이가 갖고 놀기 좋아했습니다.', 300, '커피짱3',500),
--         (unhex(replace(UUID(),'-','')),'2022-09-11T12:00', '2022-09-08T12:00', 0, '커피콩6', 300, '커피짱4',500),
--         (unhex(replace(UUID(),'-','')),'2022-09-12T12:00', '2022-09-08T12:00', 1, '고양이가 이 컵은 깨지 않았으면 좋겠습니다.', 500, '컵짱1',700),
--         (unhex(replace(UUID(),'-','')),'2022-09-13T12:00', '2022-09-08T12:00', 1, '깨지는 소리가 특별한 이 컵. 더이상 컵의 기능을 한정짓지 마세요.', 500, '컵짱2',900);


