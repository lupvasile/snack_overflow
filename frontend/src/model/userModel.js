import model from "../model/model";
import {EventEmitter} from "events";

class UserModel extends EventEmitter {
    loadAllUsers() {
        return model.client.createUserClient().loadAllUsers()
            .then(users => {
                    model.state = {
                        ...model.state,
                        users
                    }

                    model.emit("change", model.state);
                }
            );
    }


    banUser(bannedUserId) {
        return model.client.createUserClient().banUser(bannedUserId);
    }
}

const userModel = new UserModel();


export default userModel;