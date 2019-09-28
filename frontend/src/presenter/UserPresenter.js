import userModel from "../model/userModel";
import invoker from "../command/invoker";
import {BanUserCommand} from "../command/userCommands";

class UserPresenter {
    onBan(bannedUserId) {
        invoker.invoke(new BanUserCommand(bannedUserId));
    }

    onInit() {
        userModel.loadAllUsers();
    }
}

const userPresenter = new UserPresenter();

export default userPresenter;