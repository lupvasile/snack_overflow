import answerModel from "../model/answerModel";
import model from "../model/model"

class AddAnswerCommand {
    constructor(text, questionId) {
        this.text = text;
        this.questionId = questionId;
    }

    execute() {
        return answerModel.addAnswer(this.text, this.questionId).then(ans => {
            this.answerId = ans.id;
            return ans;
        });
    }

    undo() {
        return answerModel.deleteAnswer(this.answerId);
    }
}

class UpdateAnswerCommand {
    constructor(answerId, newText) {
        this.answerId = answerId;
        this.newText = newText;
        this.lastAnswer = {...model.getAnswer(answerId)};
    }

    execute() {
        return answerModel.updateAnswer(this.answerId, this.newText);
    }

    undo() {
        return answerModel.updateAnswer(this.answerId, this.lastAnswer.text);
    }
}

class DeleteAnswerCommand {
    constructor(answerId) {
        this.answerId = answerId;
        this.deletedAnswer = {...model.getAnswer(answerId)};
    }

    execute() {
        return answerModel.deleteAnswer(this.answerId);
    }

    undo() {
        return answerModel.addAnswer(this.deletedAnswer.text, this.deletedAnswer.questionId).then(ans => {
            this.answerId = ans.id;
            return ans;
        });
    }
}

class VoteAnswerCommand {
    constructor(answerId, voteValue) {
        this.answerId = answerId;
        this.voteValue = voteValue;
    }

    execute() {
        return answerModel.voteAnswer(this.answerId, this.voteValue);
    }

    undo() {
        return answerModel.voteAnswer(this.answerId, -this.voteValue);
    }
}

export {AddAnswerCommand, DeleteAnswerCommand, UpdateAnswerCommand, VoteAnswerCommand};