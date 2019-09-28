import React, {Component} from "react";
import model from "../../model/model";
import answerPresenter from "../../presenter/AnswerPresenter";
import AddAnswer from "./AddAnswer";


const mapModelStateToComponentState = (modelState, props) => ({
    newAnswer: modelState.newAnswer,
    questionId: props.questionId
});

export default class SmartAddAnswer extends Component {
    constructor(props) {
        super(props);
        this.state = mapModelStateToComponentState(model.state, this.props);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState, this.props));
        model.addListener("change", this.listener);
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <AddAnswer
                onCreate={answerPresenter.onCreate}
                onChange={answerPresenter.onChange}
                answer={this.state.newAnswer}
                questionId={this.state.questionId}
            />
        );
    }
}
