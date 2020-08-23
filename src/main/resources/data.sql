DROP TABLE IF EXISTS tb_auction_item cascade;
 
CREATE TABLE tb_auction_item (
  n_item_code bigint(32) AUTO_INCREMENT PRIMARY KEY,
  d_min_base_price DOUBLE NOT NULL,
  d_step_rate DOUBLE NOT NULL,
  d_highest_bid_price DOUBLE NULL,
  s_status VARCHAR(250) DEFAULT 'RUNNING',
  n_version bigint(32) NOT NULL DEFAULT 0,
  dt_created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  n_created_by bigint(32) DEFAULT NULL,
  dt_modified_on TIMESTAMP NULL DEFAULT NULL,
  n_modified_by bigint(32) DEFAULT NULL,
  CHECK (s_status='RUNNING' OR s_status='OVER')
);
 
INSERT INTO tb_auction_item (d_min_base_price, d_step_rate, s_status,d_highest_bid_price,n_version) VALUES
  (250, 50, 'RUNNING',300,1),
  (500, 100, 'OVER',1000,4),
  (750, 150, 'RUNNING',1050,1),
  (1000, 200, 'RUNNING',1600,2),
  (1250, 250, 'OVER',2000,2),
  (1500, 250, 'RUNNING',2500,7),
  (1750, 250, 'OVER',2250,1),
  (2000, 250, 'OVER',3000,3),
  (2250, 250, 'OVER',2500,1),
  (2500, 1000, 'RUNNING',4500,1);

DROP TABLE IF EXISTS tb_auction_bid;
  
CREATE TABLE tb_auction_bid (
  n_bid_id bigint(32) AUTO_INCREMENT PRIMARY KEY,
  n_item_code bigint(32), FOREIGN KEY (n_item_code) REFERENCES tb_auction_item,
  d_bid_amount DOUBLE NOT NULL,
  n_user_id bigint(32) NOT NULL,
  s_status VARCHAR(250) NOT NULL DEFAULT 'STATUS_NOT_CHECKED',
  n_version bigint(32) DEFAULT NULL,
  dt_created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  n_created_by bigint(32) DEFAULT NULL,
  dt_modified_on TIMESTAMP NULL DEFAULT NULL,
  n_modified_by bigint(32) DEFAULT NULL,
  CHECK (s_status='STATUS_NOT_CHECKED' OR s_status='ACCEPTED' OR s_status='REJECTED')
);