ALTER TABLE `snack_overflow`.`vote_answer`
  ADD COLUMN `vote_recipient` INT NOT NULL AFTER `user`,
  ADD INDEX `vote_the_answer_recipient_idx` (`vote_recipient` ASC);
ALTER TABLE `snack_overflow`.`vote_answer`
  ADD CONSTRAINT `vote_the_answer_recipient`
    FOREIGN KEY (`vote_recipient`)
      REFERENCES `snack_overflow`.`user` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE;

ALTER TABLE `snack_overflow`.`vote_question`
  ADD COLUMN `vote_recipient` INT NOT NULL AFTER `user`,
  ADD INDEX `vote_the_question_recipient_idx` (`vote_recipient` ASC);
ALTER TABLE `snack_overflow`.`vote_question`
  ADD CONSTRAINT `vote_the_question_recipient`
    FOREIGN KEY (`vote_recipient`)
      REFERENCES `snack_overflow`.`user` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE;

