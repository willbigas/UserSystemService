CREATE TABLE `usersystem`.`guest`
(
    `id`      BIGINT(11)  NOT NULL,
    `name`    VARCHAR(45) NOT NULL,
    `user_id` BIGINT(11)  NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_guest_user_idx` (`user_id` ASC) INVISIBLE,
    CONSTRAINT `fk_guest_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `usersystem`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
