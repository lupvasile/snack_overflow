import React, {Component} from "react";
import model from "../../model/model";
import questionPresenter from "../../presenter/QuestionPresenter";
import QuestionList from "./QuestionList";

const mapModelStateToComponentState = (modelState, props) => ({
    questions: (!props.match.params.hasOwnProperty("filterText")) ? modelState.questions
        : props.match.params.filterType === "filterByTitle" ? model.filterQuestionsByTitleInLocalState(props.match.params.filterText)
            : model.filterQuestionByTagCommaSeparatedInLocalState(props.match.params.filterText),
    user: modelState.currentUser,
    questionSearchText: modelState.questionSearchText,
});

export default class SmartQuestionList extends Component {
    constructor(props) {
        super(props);
        this.state = mapModelStateToComponentState(model.state, props);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState, this.props));
        model.addListener("change", this.listener);
    }

    componentWillMount() {
        questionPresenter.onInitAllQuestions();
    }

    componentDidUpdate(prev) {
        if (prev.match.params !== this.props.match.params) {
            this.setState(mapModelStateToComponentState(model.state, this.props));
        }
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <QuestionList questions={this.state.questions}
                          onViewDetails={questionPresenter.onViewDetails}
                          questionSearchText={this.state.questionSearchText}
                          onChangeSearchText={questionPresenter.onChangeSearchText}
                          onSearchByTag={questionPresenter.onSearchByTag}
                          onSearchByTitle={questionPresenter.onSearchByTitle}
            />
        );
    }
}
