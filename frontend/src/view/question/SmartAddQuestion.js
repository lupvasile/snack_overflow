import React, {Component} from "react";
import model from "../../model/model";
import questionPresenter from "../../presenter/QuestionPresenter";
import AddQuestion from "./AddQuestion";


const mapModelStateToComponentState = modelState => ({
    newQuestion: modelState.newQuestion,
    tags: modelState.tags
});

export default class SmartAddQuestion extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(model.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        model.addListener("change", this.listener);
    }

    componentWillMount() {
        questionPresenter.onInitAddQuestion();
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <AddQuestion
                onCreate={questionPresenter.onCreate}
                onChange={questionPresenter.onChange}
                question={this.state.newQuestion}
                existingTags={this.state.tags}
            />
        );
    }
}
