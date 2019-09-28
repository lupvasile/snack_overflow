import React, {Component} from "react";
import model from "../../model/model";
import answerPresenter from "../../presenter/AnswerPresenter";
import AnswerList from "./AnswerList";


const mapModelStateToComponentState = (modelState, props) => ({
    answers: model.listAnswersForQuestion(props.questionId),
    loggedUser: model.state.currentUser,
    questionId: props.questionId
})

export default class SmartAnswerList extends Component {
    constructor(props) {
        super(props);
        this.state = mapModelStateToComponentState(model.state, props);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState, this.props));
        model.addListener("change", this.listener);
    }

    componentDidUpdate(prev) {//do I still need this?
        if (prev.questionId !== this.props.questionId) {
            this.setState(mapModelStateToComponentState(model.state, this.props));
        }
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <AnswerList answers={this.state.answers}
                        loggedUser={this.state.loggedUser}
                        onDelete={answerPresenter.onDelete}
                        onEdit={answerPresenter.onEdit}
                        onVote={answerPresenter.onVote}
                        questionId={this.state.questionId}
            />
        );
    }
}
