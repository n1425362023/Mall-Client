-- 用户表（门户+后台）
CREATE TABLE `user` (
                        `user_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
                        `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
                        `password` VARCHAR(255) NOT NULL COMMENT '加密密码',
                        `phone` NOT NULL VARCHAR(20) COMMENT '手机号',
                        `avatar` VARCHAR(255) COMMENT '头像URL',
                        `role` ENUM('customer', 'admin') DEFAULT 'customer' COMMENT '用户角色',
                        `status` TINYINT(1) DEFAULT 1 COMMENT '状态(0=禁用,1=正常)',
                        `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 商品类别表
CREATE TABLE `category` (
                            `category_id` INT AUTO_INCREMENT PRIMARY KEY,
                            `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
                            `parent_id` INT DEFAULT 0 COMMENT '父分类ID(0=顶级)',
                            `sort_order` INT DEFAULT 0 COMMENT '排序权重',
                            `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 商品表
CREATE TABLE `product` (
                           `product_id` INT AUTO_INCREMENT PRIMARY KEY,
                           `product_name` VARCHAR(255) NOT NULL COMMENT '商品名称',
                           `category_id` INT NOT NULL COMMENT '分类ID',
                           `price` DECIMAL(10,2) NOT NULL COMMENT '销售价',
                           `stock` INT NOT NULL DEFAULT 0 COMMENT '库存',
                           `main_images` TEXT NOT NULL COMMENT '主图URL',
                           `sub_images` TEXT COMMENT '子图URL集合(JSON格式)',
                           `detail` TEXT COMMENT '商品详情',
                           `status` ENUM('draft','on_sale','off_sale') DEFAULT 'draft' COMMENT '状态',
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 收货地址表
CREATE TABLE `address` (
                           `address_id` INT AUTO_INCREMENT PRIMARY KEY,
                           `user_id` INT NOT NULL,
                           `receiver` VARCHAR(50) NOT NULL COMMENT '收货人',
                           `phone` VARCHAR(20) NOT NULL COMMENT '手机号',
                           `region` VARCHAR(255) NOT NULL COMMENT '省市区',
                           `detail` VARCHAR(255) NOT NULL COMMENT '详细地址',
                           `is_default` TINYINT(1) DEFAULT 0 COMMENT '默认地址',
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 购物车表
CREATE TABLE `cart` (
                        `cart_id` INT AUTO_INCREMENT PRIMARY KEY,
                        `user_id` INT NOT NULL,
                        `product_id` INT NOT NULL,
                        `quantity` INT NOT NULL DEFAULT 1 COMMENT '商品数量',
                        `selected` TINYINT(1) DEFAULT 1 COMMENT '是否选中',
                        `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
                        FOREIGN KEY (`product_id`) REFERENCES `product`(`product_id`),
                        UNIQUE KEY `user_product` (`user_id`, `product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 订单表
CREATE TABLE `orders` (
                          `order_id` VARCHAR(32) PRIMARY KEY COMMENT '订单号(时间戳+随机数)',
                          `user_id` INT NOT NULL,
                          `address_id` INT NOT NULL,
                          `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总额',
                          `status` ENUM('unpaid','paid','shipped','completed','cancelled') DEFAULT 'unpaid',
                          `payment_method` ENUM('alipay','wechat') COMMENT '支付方式',
                          `payment_time` TIMESTAMP NULL COMMENT '支付时间',
                          `delivery_time` TIMESTAMP NULL COMMENT '发货时间',
                          `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
                          FOREIGN KEY (`address_id`) REFERENCES `address`(`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 订单商品表
CREATE TABLE `order_item` (
                              `item_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                              `order_id` VARCHAR(32) NOT NULL,
                              `product_id` INT NOT NULL,
                              `product_name` VARCHAR(255) NOT NULL COMMENT '商品快照名称',
                              `product_image` VARCHAR(255) NOT NULL COMMENT '商品快照图片',
                              `unit_price` DECIMAL(10,2) NOT NULL COMMENT '下单时单价',
                              `quantity` INT NOT NULL COMMENT '购买数量',
                              `total_price` DECIMAL(10,2) NOT NULL COMMENT '商品总价',
                              FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`),
                              FOREIGN KEY (`product_id`) REFERENCES `product`(`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 支付记录表
CREATE TABLE `payment` (
                           `payment_id` VARCHAR(32) PRIMARY KEY COMMENT '支付流水号',
                           `order_id` VARCHAR(32) NOT NULL,
                           `amount` DECIMAL(10,2) NOT NULL,
                           `platform` ENUM('alipay','wechat') NOT NULL,
                           `status` ENUM('pending','success','failed','refunded') DEFAULT 'pending',
                           `transaction_id` VARCHAR(64) COMMENT '第三方支付ID',
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;