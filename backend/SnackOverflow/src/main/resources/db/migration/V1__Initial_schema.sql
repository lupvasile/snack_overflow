CREATE TABLE IF NOT EXISTS`snack_overflow`.`user` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `username` VARCHAR(45) NOT NULL,
     `password` VARCHAR(512) NOT NULL,
     `is_moderator` BIT(1) NOT NULL,
     `is_banned` BIT(1) NOT NULL,
     PRIMARY KEY (`id`),
     UNIQUE INDEX `username_UNIQUE` (`username` ASC));


CREATE TABLE IF NOT EXISTS `snack_overflow`.`question` (
       `id` INT NOT NULL AUTO_INCREMENT,
       `title` VARCHAR(45) NOT NULL,
       `text` VARCHAR(200) NOT NULL,
       `creation` VARCHAR(45) NULL,
       `author` INT NOT NULL,
       PRIMARY KEY (`id`),
       INDEX `author_idx` (`author` ASC),
       CONSTRAINT `author`
         FOREIGN KEY (`author`)
           REFERENCES `snack_overflow`.`user` (`id`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `snack_overflow`.`answer` (
       `id` INT NOT NULL AUTO_INCREMENT,
       `text` VARCHAR(200) NOT NULL,
       `creation` VARCHAR(45) NULL,
       `author` INT NOT NULL,
       `question` INT NOT NULL,
       PRIMARY KEY (`id`),
       INDEX `author_idx` (`author` ASC),
       INDEX `question_idx` (`question` ASC),
       CONSTRAINT `ans_to_author`
         FOREIGN KEY (`author`)
           REFERENCES `snack_overflow`.`user` (`id`)
           ON DELETE CASCADE
           ON UPDATE CASCADE,
       CONSTRAINT `ans_to_question`
         FOREIGN KEY (`question`)
           REFERENCES `snack_overflow`.`question` (`id`)
           ON DELETE CASCADE
           ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `snack_overflow`.`tag_names` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `snack_overflow`.`question_tag` (
         `id` INT NOT NULL AUTO_INCREMENT,
         `question` INT NOT NULL,
         `tag` INT NOT NULL,
         PRIMARY KEY (`id`),
         INDEX `question_tag_q_idx` (`question` ASC),
         INDEX `question_tag_t_idx` (`tag` ASC),
         CONSTRAINT `question_tag_q`
           FOREIGN KEY (`question`)
             REFERENCES `snack_overflow`.`question` (`id`)
             ON DELETE CASCADE
             ON UPDATE CASCADE,
         CONSTRAINT `question_tag_t`
           FOREIGN KEY (`tag`)
             REFERENCES `snack_overflow`.`tag_names` (`id`)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `snack_overflow`.`vote_question` (
          `id` INT NOT NULL AUTO_INCREMENT,
          `vote_type` BIT(1) NOT NULL,
          `question` INT NOT NULL,
          `user` INT NOT NULL,
          PRIMARY KEY (`id`),
          INDEX `vote_the_question_idx` (`question` ASC),
          INDEX `vote_the_question_user_idx` (`user` ASC),
          CONSTRAINT `vote_the_question`
            FOREIGN KEY (`question`)
              REFERENCES `snack_overflow`.`question` (`id`)
              ON DELETE CASCADE
              ON UPDATE CASCADE,
          CONSTRAINT `vote_the_question_user`
            FOREIGN KEY (`user`)
              REFERENCES `snack_overflow`.`user` (`id`)
              ON DELETE CASCADE
              ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `snack_overflow`.`vote_answer` (
          `id` INT NOT NULL AUTO_INCREMENT,
          `vote_type` BIT(1) NOT NULL,
          `answer` INT NOT NULL,
          `user` INT NOT NULL,
          PRIMARY KEY (`id`),
          INDEX `vote_the_answer_idx` (`answer` ASC),
          INDEX `vote_the_answer_user_idx` (`user` ASC),
          CONSTRAINT `vote_the_answer`
            FOREIGN KEY (`answer`)
              REFERENCES `snack_overflow`.`answer` (`id`)
              ON DELETE CASCADE
              ON UPDATE CASCADE,
          CONSTRAINT `vote_the_answer_user`
            FOREIGN KEY (`user`)
              REFERENCES `snack_overflow`.`user` (`id`)
              ON DELETE CASCADE
              ON UPDATE CASCADE);