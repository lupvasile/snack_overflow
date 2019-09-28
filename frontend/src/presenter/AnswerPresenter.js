import model from "../model/model";
import invoker from "../command/invoker"
import {AddAnswerCommand, DeleteAnswerCommand, UpdateAnswerCommand, VoteAnswerCommand} from "../command/answerCommands";
import answerModel from "../model/answerModel";

class AnswerPresenter {
    onViewDetails(answerId) {
        window.location.assign('#/answer-details/' + answerId)
    }

    onCreate(questionId) {
        var answer = model.state.newAnswer;
        invoker.invoke(new AddAnswerCommand(answer.text, questionId));
        model.changeNewAnswerProperty("text", "");
    }

    onChange(property, value) {
        model.changeNewAnswerProperty(property, value);
    }

    onChangeForUpdate(property, value) {
        model.changeUpdateAnswerProperty(property, value);
    }

    onUpdate(answerId) {
        invoker.invoke(new UpdateAnswerCommand(answerId, model.state.updateAnswer.text));
        model.changeUpdateAnswerProperty("text", "");
        window.location.assign('#/question-details/' + model.getAnswer(answerId).questionId);
    }

    onDelete(answerId) {
        invoker.invoke(new DeleteAnswerCommand(answerId));
    }

    onEdit(answerId) {
        model.prepareAnswerForUpdate(answerId);
        window.location.assign("#/edit-answer/" + answerId);
    }

    onVote(answerId, vote) {
        invoker.invoke(new VoteAnswerCommand(answerId, vote));
    }

    onInit(questionId) {
        answerModel.loadAnswersForQuestion(questionId);
    }
}

const answerPresenter = new AnswerPresenter();

export default answerPresenter;