CREATE TABLE `role` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role` VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE `user_role` (
     `user_id` BIGINT UNSIGNED NOT NULL,
     `role_id` BIGINT UNSIGNED NOT NULL,

     PRIMARY KEY (`user_id`, `role_id`),
     FOREIGN KEY (`user_id`) REFERENCES user(`id`),
     FOREIGN KEY (`role_id`) REFERENCES role(`id`)
);
