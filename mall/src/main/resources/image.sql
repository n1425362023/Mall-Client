CREATE TABLE `product_main_images` (
                                       `id` INT AUTO_INCREMENT PRIMARY KEY,
                                       `product_id` INT NOT NULL COMMENT '商品ID',
                                       `image_url` VARCHAR(255) NOT NULL COMMENT '主图URL',
                                       `sort_order` INT DEFAULT 0 COMMENT '排序字段',
                                       `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       FOREIGN KEY (`product_id`) REFERENCES `product`(`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `product_sub_images` (
                                      `id` INT AUTO_INCREMENT PRIMARY KEY,
                                      `product_id` INT NOT NULL COMMENT '商品ID',
                                      `image_url` VARCHAR(255) NOT NULL COMMENT '子图URL',
                                      `sort_order` INT DEFAULT 0 COMMENT '排序字段',
                                      `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (`product_id`) REFERENCES `product`(`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;