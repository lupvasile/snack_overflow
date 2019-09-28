ALTER TABLE `vote_answer`
  ADD COLUMN `vote_recipient` INT NOT NULL AFTER `user`;
ALTER TABLE `vote_answer`
  ADD CONSTRAINT `vote_the_answer_recipient`
    FOREIGN KEY (`vote_recipient`)
      REFERENCES `user` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE;

ALTER TABLE `vote_question`
  ADD COLUMN `vote_recipient` INT NOT NULL AFTER `user`;
ALTER TABLE `vote_question`
  ADD CONSTRAINT `vote_the_question_recipient`
    FOREIGN KEY (`vote_recipient`)
      REFERENCES `user` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE;

