import RestClientLogin from "./RestClientLogin";
import RestClientQuestions from "./RestClientQuestions";
import RestClientAnswers from "./RestClientAnswers";
import RestClientUsers from "./RestClientUsers";


const BASE_URL = "http://localhost:8080";

export default class RestClientFactory {
    constructor(username, password) {
        this.authorization = "Basic " + btoa(username + ":" + password);

        this.loginClient = new RestClientLogin(this.authorization, BASE_URL);
        this.questionClient = new RestClientQuestions(this.authorization, BASE_URL);
        this.answerClient = new RestClientAnswers(this.authorization, BASE_URL);
        this.userClient = new RestClientUsers(this.authorization, BASE_URL);
    }

    createLoginClient() {
        return this.loginClient;
    }

    createQuestionClient() {
        return this.questionClient;
    }

    createAnswerClient() {
        return this.answerClient;
    }

    createUserClient() {
        return this.userClient;
    }
}