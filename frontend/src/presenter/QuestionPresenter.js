import model from "../model/model";
import questionModel from "../model/questionModel";
import answerModel from "../model/answerModel";
import {
    AddQuestionCommand,
    DeleteQuestionCommand,
    UpdateQuestionCommand,
    VoteQuestionCommand
} from "../command/questionCommands";
import invoker from "../command/invoker"

class QuestionPresenter {
    onViewDetails(questionId) {
        answerModel.loadAnswersForQuestion(questionId).then(() =>
            window.location.assign('#/question-details/' + questionId));
    }

    onCreate() {
        var question = model.state.newQuestion;
        var tags = question.tagsAsString.trim().split(',');
        invoker.invoke(new AddQuestionCommand(question.title, question.text, tags)).then(() => {
            model.changeNewQuestionProperty("title", "");
            model.changeNewQuestionProperty("text", "");
            model.changeNewQuestionProperty("tagsAsString", "");
            window.location.assign("#/all-questions");
        });
    }

    onChange(property, value) {
        model.changeNewQuestionProperty(property, value);
    }

    onChangeForUpdate(property, value) {
        model.changeUpdateQuestionProperty(property, value);
    }

    onChangeSearchText(value) {
        model.changeModelProperty("questionSearchText", value);
    }

    onSearchByTitle() {
        if (model.state.questionSearchText === "") {
            questionModel.loadAllQuestions();
            window.location.assign('#/all-questions/');
        } else {
            questionModel.loadFilteredByTitle(model.state.questionSearchText);
            window.location.assign('#all-questions/filterByTitle/' + model.state.questionSearchText);
        }
    }

    onSearchByTag() {
        if (model.state.questionSearchText === "") {
            questionModel.loadAllQuestions();
            window.location.assign('#/all-questions/');
        } else {
            questionModel.loadFilteredByTags(model.state.questionSearchText);
            window.location.assign('#all-questions/filterByTag/' + model.state.questionSearchText);
        }
    }

    onUpdate(questionId) {
        invoker.invoke(new UpdateQuestionCommand(questionId, model.state.updateQuestion.title, model.state.updateQuestion.text)).then(() => {
            model.changeUpdateQuestionProperty("title", "");
            model.changeUpdateQuestionProperty("text", "");
            window.location.assign('#/question-details/' + questionId);
        });
    }

    onDelete(questionId) {
        invoker.invoke(new DeleteQuestionCommand(questionId)).then(() => window.location.assign('#/all-questions/'));
    }

    onEdit(questionId) {
        model.prepareQuestionForUpdate(questionId);
        window.location.assign("#/edit-question/" + questionId);
    }

    onVote(questionId, vote) {
        invoker.invoke(new VoteQuestionCommand(questionId, vote));
    }

    onInitAllQuestions() {
        questionModel.loadAllQuestions();
    }

    onInitFilterByTitle(title) {
        questionModel.loadFilteredByTitle(title);
    }

    onInitFilterByTags(tags) {
        questionModel.loadFilteredByTags(tags);
    }

    onInitDetails(questionId) {
        answerModel.loadAnswersForQuestion(questionId);
    }

    onInitAddQuestion() {
        questionModel.loadAllTags();
    }
}

const questionPresenter = new QuestionPresenter();

export default questionPresenter;