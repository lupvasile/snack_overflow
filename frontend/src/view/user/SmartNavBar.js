import React, {Component} from "react";
import model from "../../model/model";
import navBarPresenter from "../../presenter/NavBarPresenter";
import NavBar from "./NavBar";


const mapModelStateToComponentState = (modelState) => ({
    currentUser: modelState.currentUser
});

export default class SmartNavBar extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(model.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        model.addListener("change", this.listener);
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <div>
                <NavBar user={this.state.currentUser}
                        onLogout={navBarPresenter.onLogout}
                        onAddQuestion={navBarPresenter.onAddQuestion}
                        onAllQuestions={navBarPresenter.onAllQuestions}
                        onBanUsers={navBarPresenter.onBanUsers}
                        onUndo={navBarPresenter.onUndo}
                        onRedo={navBarPresenter.onRedo}
                />
            </div>
        );
    }
}
