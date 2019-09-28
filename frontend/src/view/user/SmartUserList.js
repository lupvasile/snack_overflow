import React, {Component} from "react";
import model from "../../model/model";
import userPresenter from "../../presenter/UserPresenter";
import UserList from "./UserList";

const mapModelStateToComponentState = modelState => ({
    users: modelState.users
});

export default class SmartUserList extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(model.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        model.addListener("change", this.listener);
    }

    componentWillMount() {
        userPresenter.onInit();
    }

    componentWillUnmount() {
        model.removeListener("change", this.listener);
    }

    render() {
        return (
            <UserList onBan={userPresenter.onBan}
                      users={this.state.users}
            />
        );
    }
}
