CREATE TABLE IF NOT EXISTS`user` (
     `id` INT NOT NULL AUTO_INCREMENT,
     `username` VARCHAR(45) NOT NULL,
     `password` VARCHAR(512) NOT NULL,
     `is_moderator` BIT(1) NOT NULL,
     `is_banned` BIT(1) NOT NULL,
     PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `question` (
       `id` INT NOT NULL AUTO_INCREMENT,
       `title` VARCHAR(45) NOT NULL,
       `text` VARCHAR(200) NOT NULL,
       `creation` VARCHAR(45) NULL,
       `author` INT NOT NULL,
       PRIMARY KEY (`id`),
       CONSTRAINT `author`
         FOREIGN KEY (`author`)
           REFERENCES `user` (`id`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `answer` (
       `id` INT NOT NULL AUTO_INCREMENT,
       `text` VARCHAR(200) NOT NULL,
       `creation` VARCHAR(45) NULL,
       `author` INT NOT NULL,
       `question` INT NOT NULL,
       PRIMARY KEY (`id`),
       CONSTRAINT `ans_to_author`
         FOREIGN KEY (`author`)
           REFERENCES `user` (`id`)
           ON DELETE CASCADE
           ON UPDATE CASCADE,
       CONSTRAINT `ans_to_question`
         FOREIGN KEY (`question`)
           REFERENCES `question` (`id`)
           ON DELETE CASCADE
           ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `tag_names` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `question_tag` (
         `id` INT NOT NULL AUTO_INCREMENT,
         `question` INT NOT NULL,
         `tag` INT NOT NULL,
         PRIMARY KEY (`id`),
         CONSTRAINT `question_tag_q`
           FOREIGN KEY (`question`)
             REFERENCES `question` (`id`)
             ON DELETE CASCADE
             ON UPDATE CASCADE,
         CONSTRAINT `question_tag_t`
           FOREIGN KEY (`tag`)
             REFERENCES `tag_names` (`id`)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `vote_question` (
          `id` INT NOT NULL AUTO_INCREMENT,
          `vote_type` BIT(1) NOT NULL,
          `question` INT NOT NULL,
          `user` INT NOT NULL,
          PRIMARY KEY (`id`),
          CONSTRAINT `vote_the_question`
            FOREIGN KEY (`question`)
              REFERENCES `question` (`id`)
              ON DELETE CASCADE
              ON UPDATE CASCADE,
          CONSTRAINT `vote_the_question_user`
            FOREIGN KEY (`user`)
              REFERENCES `user` (`id`)
              ON DELETE CASCADE
              ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `vote_answer` (
          `id` INT NOT NULL AUTO_INCREMENT,
          `vote_type` BIT(1) NOT NULL,
          `answer` INT NOT NULL,
          `user` INT NOT NULL,
          PRIMARY KEY (`id`),
          CONSTRAINT `vote_the_answer`
            FOREIGN KEY (`answer`)
              REFERENCES `answer` (`id`)
              ON DELETE CASCADE
              ON UPDATE CASCADE,
          CONSTRAINT `vote_the_answer_user`
            FOREIGN KEY (`user`)
              REFERENCES `user` (`id`)
              ON DELETE CASCADE
              ON UPDATE CASCADE);