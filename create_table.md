CREATE TABLE `history` (
  `history_key` varchar(45) NOT NULL,
  `item_fk` varchar(45) NOT NULL COMMENT '제품key',
  `action` varchar(45) DEFAULT NULL COMMENT '구매/사용',
  `update_date` datetime DEFAULT NULL COMMENT '수정일',
  `count` int DEFAULT NULL COMMENT '수량',
  `price` int DEFAULT NULL COMMENT '구매금액',
  `product_name` varchar(45) DEFAULT NULL COMMENT '상품명',
  PRIMARY KEY (`history_key`),
  KEY `item_key_idx` (`item_fk`),
  CONSTRAINT `item_key` FOREIGN KEY (`item_fk`) REFERENCES `item` (`item_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `item` (
  `item_key` varchar(45) NOT NULL,
  `item_name` varchar(45) DEFAULT NULL COMMENT '제품명',
  `usage_fk` varchar(45) DEFAULT NULL COMMENT '사용처key',
  `place_fk` varchar(45) DEFAULT NULL COMMENT '위치key',
  `division` varchar(45) DEFAULT NULL COMMENT '구분(소모품/비품)',
  `spot` varchar(45) DEFAULT NULL COMMENT '상세위치',
  `unit` varchar(45) DEFAULT NULL COMMENT '단위수',
  `count` int DEFAULT NULL COMMENT '수량',
  PRIMARY KEY (`item_key`),
  KEY `usage_key_idx` (`usage_fk`),
  KEY `place_key_idx` (`place_fk`),
  CONSTRAINT `place_key` FOREIGN KEY (`place_fk`) REFERENCES `place` (`place_key`),
  CONSTRAINT `usage_key` FOREIGN KEY (`usage_fk`) REFERENCES `usage` (`usage_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `place` (
  `place_key` varchar(45) NOT NULL,
  `area_name` varchar(45) DEFAULT NULL COMMENT '장소명',
  `place_name` varchar(45) DEFAULT NULL COMMENT '위치명',
  `place_photo` varchar(45) DEFAULT NULL COMMENT '위치사진',
  PRIMARY KEY (`place_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usage` (
  `usage_key` varchar(45) NOT NULL,
  `usage_name` varchar(45) DEFAULT NULL COMMENT '사용처명',
  PRIMARY KEY (`usage_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sequences` (
  `NAME` varchar(32) DEFAULT NULL,
  `CURRVAL` bigint unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_sequence`(IN the_name text)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
        DELETE FROM sequences WHERE name = the_name;
        INSERT INTO sequences VALUES(the_name, 0);
   END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(the_name VARCHAR(32)) RETURNS bigint unsigned
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
        DECLARE ret BIGINT UNSIGNED;
        UPDATE sequences SET currval = currval +1 WHERE name = the_name;
        SELECT currval INTO ret FROM sequences WHERE name = the_name LIMIT 1;
        RETURN ret;
   END$$
DELIMITER ;

INSERT INTO `admin`.`usage`
(`usage_key`,
`usage_name`)
VALUES
((SELECT concat('usage',nextval('usage_seq')) FROM DUAL),
'욕실');

INSERT INTO `admin`.`place`
(`place_key`,
`area_name`,
`place_name`)
VALUES
((SELECT concat('place',nextval('place_seq')) FROM DUAL),
'거실',
'책장3');
