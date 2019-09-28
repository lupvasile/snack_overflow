import model, {makeQuestion} from "../model/model";
import {EventEmitter} from "events";

class QuestionModel extends EventEmitter {

    loadAllQuestions() {
        return model.client.createQuestionClient().loadAllQuestions()
            .then(questions => {
                    model.state = {
                        ...model.state,
                        questions
                    }

                    model.emit("change", model.state);
                }
            );
    }

    addQuestion(title, text, tags) {
        return model.client.createQuestionClient().createQuestion(title, text, tags);
    }

    updateQuestion(questionId, newTitle, newText) {
        return model.client.createQuestionClient().updateQuestion(questionId, newTitle, newText);
    }

    deleteQuestion(questionId) {
        return model.client.createQuestionClient().deleteQuestion(questionId);
    }

    voteQuestion(questionId, voteValue) {
        return model.client.createQuestionClient().voteQuestion(questionId, voteValue);
    }

    loadFilteredByTags(tagsAsString) {
        return model.client.createQuestionClient().loadFilteredByTags(tagsAsString).then(questions => {
                model.state = {
                    ...model.state,
                    questions
                }

                model.emit("change", model.state);
            }
        );
    }

    loadFilteredByTitle(title) {
        return model.client.createQuestionClient().loadFilteredByTitle(title).then(questions => {
                model.state = {
                    ...model.state,
                    questions
                }

                model.emit("change", model.state);
            }
        );
    }

    loadAllTags() {
        return model.client.createQuestionClient().loadAllTags().then(tags => {
                model.state = {
                    ...model.state,
                    tags
                }

                model.emit("change", model.state);
            }
        );
    }
}

const questionModel = new QuestionModel();


export default questionModel;