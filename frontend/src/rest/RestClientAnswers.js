export default class RestClientAnswers {
    constructor(authorization, BASE_URL) {
        this.authorization = authorization;
        this.BASE_URL = BASE_URL;
    }

    loadAnswersForQuestion(questionId) {
        return fetch(this.BASE_URL + "/questions/" + questionId + "/answers", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (!response.ok) return [];
            else return response.json();
        })
    }

    createAnswer(text, questionId) {
        return fetch(this.BASE_URL + "/questions/" + questionId + "/answers", {
            method: "POST",
            body: JSON.stringify({
                questionId,
                text
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }

    updateAnswer(answerId, newText) {
        return fetch(this.BASE_URL + "/questions/" + 0 + "/answers/" + answerId, {
            method: "PUT",
            body: JSON.stringify({
                id: answerId,
                text: newText
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }

    deleteAnswer(answerId) {
        return fetch(this.BASE_URL + "/questions/" + 0 + "/answers/" + answerId, {
            method: "DELETE",
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        });
    }

    voteAnswer(answerId, voteValue) {
        return fetch(this.BASE_URL + "/questions/" + 0 + "/answers/" + answerId + "/votes/" + voteValue, {
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        });
    }
}