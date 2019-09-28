import model from "../model/model";
import invoker from "../command/invoker";

class NavBarPresenter {
    onLogout() {
        window.location.assign("#/");
    }

    onAllQuestions() {
        model.changeModelProperty("questionSearchText", "");
        window.location.assign("#/all-questions");
    }

    onAddQuestion() {
        window.location.assign("#/add-question");
    }

    onBanUsers() {
        window.location.assign("#/ban-users");
    }

    onUndo() {
        invoker.undo();
    }

    onRedo() {
        invoker.redo();
    }
}

const navBarPresenter = new NavBarPresenter();

export default navBarPresenter;