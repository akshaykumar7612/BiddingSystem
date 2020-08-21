DROP TABLE IF EXISTS tb_auction_item;
 
CREATE TABLE tb_auction_item (
  n_item_code bigint(32) AUTO_INCREMENT PRIMARY KEY,
  d_min_base_price DOUBLE NOT NULL,
  d_step_rate DOUBLE NOT NULL,
  d_highest_bid_price DOUBLE NULL,
  s_status VARCHAR(250) DEFAULT 'RUNNING', 
  dt_created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  n_created_by bigint(32) DEFAULT NULL,
  dt_modified_on TIMESTAMP NULL DEFAULT NULL,
  n_modified_by bigint(32) DEFAULT NULL,
  CHECK (s_status='RUNNING' OR s_status='OVER')
);
 
INSERT INTO tb_auction_item (d_min_base_price, d_step_rate, s_status,d_highest_bid_price) VALUES
  (250, 50, 'RUNNING',1000),
  (500, 100, 'OVER',1000),
  (750, 150, 'RUNNING',1050),
  (1000, 200, 'RUNNING',1600),
  (1250, 250, 'OVER',2000),
  (1500, 250, 'RUNNING',2500),
  (1750, 250, 'OVER',2250),
  (2000, 250, 'OVER',3000),
  (2250, 250, 'OVER',3500),
  (2500, 1000, 'RUNNING',4500);