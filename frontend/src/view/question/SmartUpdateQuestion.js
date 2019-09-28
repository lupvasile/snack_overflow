import React, {Component} from "react";
import model from "../../model/model";
import questionPresenter from "../../presenter/QuestionPresenter";
import UpdateQuestion from "./UpdateQuestion";


const mapModelStateToComponentState = (modelState, props) => ({
    updateQuestion: modelState.updateQuestion,
    updateId: parseInt(props.match.params.questionId)
});

export default class SmartUpdateQuestion extends Component {
    constructor(props) {
        super(props);
        this.state = mapModelStateToComponentState(model.state, props);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState, this.props));
        model.addListener("change", this.listener);
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
            <UpdateQuestion
                onUpdate={questionPresenter.onUpdate}
                onChange={questionPresenter.onChangeForUpdate}
                question={this.state.updateQuestion}
                questionId={this.state.updateId}
            />
        );
    }
}
