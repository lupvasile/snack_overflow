import model from "../model/model";
import {EventEmitter} from "events";

class AnswerModel extends EventEmitter {
    loadAnswersForQuestion(questionId) {
        return model.client.createAnswerClient().loadAnswersForQuestion(questionId)
            .then(answers => {
                    model.state = {
                        ...model.state,
                        answers
                    }

                    model.emit("change", model.state);
                }
            );
    }


    addAnswer(text, questionId) {
        return model.client.createAnswerClient().createAnswer(text, questionId);
    }

    updateAnswer(answerId, newText) {
        return model.client.createAnswerClient().updateAnswer(answerId, newText);
    }

    deleteAnswer(answerId) {
        return model.client.createAnswerClient().deleteAnswer(answerId);
    }

    voteAnswer(answerId, voteValue) {
        return model.client.createAnswerClient().voteAnswer(answerId, voteValue);
    }
}

const answerModel = new AnswerModel();


export default answerModel;