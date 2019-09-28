import React, {Component} from "react";
import model from "../../model/model";
import questionPresenter from "../../presenter/QuestionPresenter";
import QuestionDetails from "./QuestionDetails";
import answerModel from "../../model/answerModel";


const mapModelStateToComponentState = (modelState, props) => ({
    question: model.getQuestion(parseInt(props.match.params.questionId)),
    loggedUser: model.state.currentUser
})

export default class SmartQuestionDetails extends Component {
    constructor(props) {
        super(props);
        this.state = mapModelStateToComponentState(model.state, props);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState, this.props));
        model.addListener("change", this.listener);
    }

    componentWillMount() {
        answerModel.loadAnswersForQuestion(this.state.question.id);
    }

    componentDidUpdate(prev) {
        if (prev.match.params.questionId !== this.props.match.params.questionId) {
            this.setState(mapModelStateToComponentState(model.state, this.props));
        }
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <QuestionDetails
                question={this.state.question}
                loggedUser={this.state.loggedUser}
                onEdit={questionPresenter.onEdit}
                onDelete={questionPresenter.onDelete}
                onVote={questionPresenter.onVote}
            />
        );
    }
}
