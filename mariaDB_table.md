CREATE TABLE `usage` (
  `usage_key` varchar(45) NOT NULL,
  `usage_name` varchar(45) DEFAULT NULL COMMENT '사용처명',
  PRIMARY KEY (`usage_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `place` (
  `place_key` varchar(45) NOT NULL,
  `area_name` varchar(45) DEFAULT NULL COMMENT '장소명',
  `place_name` varchar(45) DEFAULT NULL COMMENT '위치명',
  `place_photo` varchar(45) DEFAULT NULL COMMENT '위치사진',
  PRIMARY KEY (`place_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `item` (
  `item_key` varchar(45) NOT NULL,
  `item_name` varchar(45) DEFAULT NULL COMMENT '제품명',
  `usage_fk` varchar(45) DEFAULT NULL COMMENT '사용처key',
  `place_fk` varchar(45) DEFAULT NULL COMMENT '위치key',
  `division` varchar(45) DEFAULT NULL COMMENT '구분(소모품/비품)',
  `spot` varchar(45) DEFAULT NULL COMMENT '상세위치',
  `unit` varchar(45) DEFAULT NULL COMMENT '단위수',
  `count` int(11) DEFAULT NULL COMMENT '수량',
  PRIMARY KEY (`item_key`),
  KEY `usage_key_idx` (`usage_fk`),
  KEY `place_key_idx` (`place_fk`),
  CONSTRAINT `place_key` FOREIGN KEY (`place_fk`) REFERENCES `place` (`place_key`),
  CONSTRAINT `usage_key` FOREIGN KEY (`usage_fk`) REFERENCES `usage` (`usage_key`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `history` (
  `history_key` varchar(45) NOT NULL,
  `item_fk` varchar(45) NOT NULL COMMENT '제품key',
  `action` varchar(45) DEFAULT NULL COMMENT '구매/사용',
  `update_date` datetime DEFAULT NULL COMMENT '수정일',
  `count` int(11) DEFAULT NULL COMMENT '수량',
  `price` int(11) DEFAULT NULL COMMENT '구매금액',
  `product_name` varchar(45) DEFAULT NULL COMMENT '상품명',
  PRIMARY KEY (`history_key`),
  KEY `item_key_idx` (`item_fk`),
  CONSTRAINT `item_key` FOREIGN KEY (`item_fk`) REFERENCES `item` (`item_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE SEQUENCE `admin`.`seq_usage`
INCREMENT BY 1                
MAXVALUE 99999999   
START WITH 1;  

CREATE SEQUENCE `admin`.`seq_place`
INCREMENT BY 1                
MAXVALUE 99999999   
START WITH 1;  

CREATE SEQUENCE `admin`.`seq_item`
INCREMENT BY 1                
MAXVALUE 99999999   
START WITH 1;  

CREATE SEQUENCE `admin`.`seq_history`
INCREMENT BY 1                
MAXVALUE 99999999   
START WITH 1;  

INSERT INTO admin.usage (usage_key, usage_name) VALUES ((SELECT concat('usage',nextval(admin.seq_usage)) FROM DUAL), '욕실');
INSERT INTO admin.usage (usage_key, usage_name) VALUES ((SELECT concat('usage',nextval(admin.seq_usage)) FROM DUAL), '부엌');
INSERT INTO admin.usage (usage_key, usage_name) VALUES ((SELECT concat('usage',nextval(admin.seq_usage)) FROM DUAL), '청소');
INSERT INTO admin.usage (usage_key, usage_name) VALUES ((SELECT concat('usage',nextval(admin.seq_usage)) FROM DUAL), '영양제');
INSERT INTO admin.usage (usage_key, usage_name) VALUES ((SELECT concat('usage',nextval(admin.seq_usage)) FROM DUAL), '기타');

INSERT INTO `admin`.`place` (`place_key`, `area_name`, `place_name`) VALUES ((SELECT concat('place',nextval(admin.seq_place)) FROM DUAL), '베란다', '창고');
INSERT INTO `admin`.`place` (`place_key`, `area_name`, `place_name`) VALUES ((SELECT concat('place',nextval(admin.seq_place)) FROM DUAL), '부엌', '냉장고장 아래');

INSERT INTO `admin`.`item` (`item_key`, `item_name`, `usage_fk`, `place_fk`, `division`, `spot`, `unit`, `count`) VALUES ((SELECT concat('item',nextval(admin.seq_item)) FROM DUAL), '샴푸', 'usage1', 'place1', 'somo', '윗칸', '1L', 1); 
INSERT INTO `admin`.`item` (`item_key`, `item_name`, `usage_fk`, `place_fk`,`division`, `spot`, `unit`,`count`)VALUES ((SELECT concat('item',nextval(admin.seq_item)) FROM DUAL), '키친타올','usage2', 'place2', 'somo', '아랫문 맨아래칸 안쪽', '1개', 6);  INSERT INTO `admin`.`item` (`item_key`, `item_name`, `usage_fk`, `place_fk`, `division`, `spot`, `unit`, `count`) VALUES ((SELECT concat('item',nextval(admin.seq_item)) FROM DUAL), '크리스마스트리', 'usage5', 'place1', 'bi', '아래칸', '1개', 1);
INSERT INTO `admin`.`item` (`item_key`, `item_name`, `usage_fk`, `place_fk`, `division`, `spot`, `unit`, `count`) VALUES ((SELECT concat('item',nextval(admin.seq_item)) FROM DUAL), '크리스마스트리', 'usage5', 'place1', 'bi', '아래칸', '1개', 1);

