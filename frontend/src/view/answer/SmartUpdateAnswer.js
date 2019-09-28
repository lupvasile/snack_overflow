import React, {Component} from "react";
import model from "../../model/model";
import answerPresenter from "../../presenter/AnswerPresenter";
import UpdateAnswer from "./UpdateAnswer";


const mapModelStateToComponentState = (modelState, props) => ({
    updateAnswer: modelState.updateAnswer,
    updateId: parseInt(props.match.params.answerId)
});

export default class SmartUpdateAnswer extends Component {
    constructor(props) {
        super(props);
        this.state = mapModelStateToComponentState(model.state, props);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState, this.props));
        model.addListener("change", this.listener);
    }

    componentDidUpdate(prev) {
        if (prev.match.params.answerId !== this.props.match.params.answerId) {
            this.setState(mapModelStateToComponentState(model.state, this.props));
        }
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <UpdateAnswer
                onUpdate={answerPresenter.onUpdate}
                onChange={answerPresenter.onChangeForUpdate}
                answer={this.state.updateAnswer}
                answerId={this.state.updateId}
            />
        );
    }
}
