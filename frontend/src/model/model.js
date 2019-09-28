import {EventEmitter} from "events";
import WebSocketListener from "../ws/WebSocketListener";
import RestClientFactory from "../rest/RestClientFactory";

const makeUser = (id, username, password, score, isModerator, isBlocked) => ({
    id, username, password, score, isModerator, isBlocked
})

const makeQuestion = (id, author, title, text, creationDateTime, tags, voteCount) => ({
    id, author, title, text, creationDateTime, tags, voteCount
})

const makeAnswer = (id, author, text, creationDateTime, questionId, voteCount) => ({
    id, author, text, creationDateTime, questionId, voteCount
})

class Model extends EventEmitter {
    constructor() {
        super();

        this.state = {
            users: [],

            currentUser: null,

            loginUser: {
                username: "",
                password: ""
            },

            questions: [],

            newQuestion: {
                title: "",
                text: "",
                tagsAsString: ""
            },

            updateQuestion: {
                title: "",
                text: "",
            },

            tags: [],

            questionSearchText: "",

            answers: [],

            newAnswer: {text: ""},

            updateAnswer: {text: ""}
        };
    }

    banUserToLocalState(bannedUserId) {
        var user = this.state.users.find(u => u.id === bannedUserId);
        if (user) user.isBlocked = true;

        if (this.state.currentUser.id === bannedUserId) this.isBlocked = true;
        this.emit("change", this.state);
    }

    listAnswersForQuestion(questionId) {
        return this.state.answers.filter(a => a.questionId === questionId);
    }

    getAnswer(answerId) {
        return this.state.answers.find(a => a.id === answerId);
    }

    sendVoteAnswerToLocalState(votedAnswer) {
        var answer = this.getAnswer(votedAnswer.id);
        answer.voteCount = votedAnswer.voteCount;

        this.emit("change", this.state);
    }

    addAnswerToLocalState(answer) {
        this.state = {
            ...this.state,
            answers: [{...answer}].concat(this.state.answers)
        };

        this.emit("change", this.state);
    }

    updateAnswerTextToLocalState(answerId, newText) {
        var answer = this.getAnswer(answerId);
        if (answer === undefined) {
            return;
        }

        answer.text = newText;

        this.emit("change", this.state);
    }

    deleteAnswerToLocalState(answerId) {
        this.state = {
            ...this.state,
            answers: this.state.answers.filter(a => a.id !== answerId),
        };

        this.emit("change", this.state);
    }

    changeNewAnswerProperty(property, value) {
        this.state = {
            ...this.state,
            newAnswer: {
                ...this.state.newAnswer,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    prepareAnswerForUpdate(answerId) {
        var answer = this.getAnswer(answerId);
        this.state.updateAnswer.text = answer.text;

        this.emit("change", this.state);
    }

    changeUpdateAnswerProperty(property, value) {
        this.state = {
            ...this.state,
            updateAnswer: {
                ...this.state.updateAnswer,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    filterQuestionByTagCommaSeparatedInLocalState(tagText) {
        var tags = tagText.trim().split(',');
        return this.filterQuestionsByTagInLocalState(tags);
    }

    filterQuestionsByTagInLocalState(tags) {
        return this.state.questions.filter(q => tags.every(t => q.tags.includes(t)));
    }

    filterQuestionsByTitleInLocalState(title) {
        return this.state.questions.filter(q => q.title.includes(title));
    }

    getQuestion(id) {
        var res = this.state.questions.find(q => q.id === id);
        return res;
    }

    addQuestionToLocalState(question) {
        this.state = {
            ...this.state,
            questions: [question].concat(this.state.questions)
        };

        this.emit("change", this.state);
    }

    updateQuestionToLocalState(questionId, newTitle, newText) {
        var question = this.getQuestion(questionId);
        if (question === undefined) {
            return;
        }

        question.title = newTitle;
        question.text = newText;

        this.emit("change", this.state);
        ///this does not work???
    }

    prepareQuestionForUpdate(questionId) {
        var question = this.getQuestion(questionId);
        this.state.updateQuestion.title = question.title;
        this.state.updateQuestion.text = question.text;

        this.emit("change", this.state);
    }

    deleteQuestionToLocalState(questionId) {
        this.state = {
            ...this.state,
            questions: this.state.questions.filter(q => q.id !== questionId),
        };
        this.emit("change", this.state);
    }

    sendVoteQuestionToLocalState(votedQuestion) {
        var question = this.getQuestion(votedQuestion.id);
        question.voteCount = votedQuestion.voteCount;

        this.emit("change", this.state);
    }

    changeNewQuestionProperty(property, value) {
        this.state = {
            ...this.state,
            newQuestion: {
                ...this.state.newQuestion,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    changeUpdateQuestionProperty(property, value) {
        this.state = {
            ...this.state,
            updateQuestion: {
                ...this.state.updateQuestion,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    changeModelProperty(property, value) {
        this.state = {
            ...this.state,
            [property]: value
        };
        this.emit("change", this.state);
    }

    makeTagsList() {
        var allTags = this.state.tags.concat.apply([], this.state.questions.map(q => q.tags));
        return Array.from(new Set(allTags)).sort();
    }

    makeLogin(username, password) {
        this.client = new RestClientFactory(username, password);
        return this.client.createLoginClient().loadCurrentLoggedUser().then(user => {
            if (!user) return false;

            if (this.hasOwnProperty("listener")) {
                delete this.listener.client.deactivate();
            }
            this.listener = new WebSocketListener(username, password);
            this.listener.on("event", event => webSocketListener(event));
            this.state = {
                ...this.state,
                currentUser: user
            };

            this.emit("change", this.state);

            return true;
        });
    }

    changeLoginProperty(property, value) {
        this.state = {
            ...this.state,
            loginUser: {
                ...this.state.loginUser,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    updateUserInfo(newUserData) {
        this.state = {
            ...this.state,
            users: this.state.users.map(u => u.id === newUserData.id ? {
                ...u,
                score: newUserData.score,
                username: newUserData.username
            } : {...u}),

            questions: this.state.questions.map(q => q.author.id === newUserData.id ? {
                ...q,
                author: {...newUserData}
            } : {...q}),

            answers: this.state.answers.map(a => a.author.id === newUserData.id ? {
                ...a,
                author: {...newUserData}
            } : {...a}),

            currentUser: this.state.currentUser.id === newUserData.id ? {
                ...this.state.currentUser,
                score: newUserData.score
            } : this.state.currentUser
        };

        this.emit("change", this.state);
    }
}

const model = new Model();

function webSocketListener(event) {
    {
        switch (event.type) {
            case "ANSWER_CREATED":
                model.addAnswerToLocalState(event.answerDTO);
                break;
            case "ANSWER_DELETED":
                model.deleteAnswerToLocalState(event.answerId);
                break;
            case "ANSWER_UPDATED":
                model.updateAnswerTextToLocalState(event.answerDTO.id, event.answerDTO.text);
                break;
            case "QUESTION_CREATED":
                model.addQuestionToLocalState(event.questionDTO);
                break;
            case "QUESTION_DELETED":
                model.deleteQuestionToLocalState(event.questionId);
                break;
            case "QUESTION_UPDATED":
                model.updateQuestionToLocalState(event.questionDTO.id, event.questionDTO.title, event.questionDTO.text);
                break;
            case "USER_UPDATED":
                model.updateUserInfo(event.userDTO);
                break;
            case "VOTED_ANSWER":
                model.sendVoteAnswerToLocalState(event.answerDTO);
                break;
            case "VOTED_QUESTION":
                model.sendVoteQuestionToLocalState(event.questionDTO);
                break;

            case "USER_BANNED":
                model.banUserToLocalState(event.userId);
                break;
        }
    }
}

export default model;