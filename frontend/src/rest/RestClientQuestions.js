export default class RestClientQuestions {
    constructor(authorization, BASE_URL) {
        this.authorization = authorization;
        this.BASE_URL = BASE_URL;
    }

    loadAllQuestions() {
        return fetch(this.BASE_URL + "/questions", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (!response.ok) return [];
            else return response.json();
        })
    }

    createQuestion(title, text, tags) {
        return fetch(this.BASE_URL + "/questions", {
            method: "POST",
            body: JSON.stringify({
                title,
                text,
                tags
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }

    updateQuestion(questionId, newTitle, newText) {
        return fetch(this.BASE_URL + "/questions/" + questionId, {
            method: "PUT",
            body: JSON.stringify({
                id: questionId,
                title: newTitle,
                text: newText
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }

    deleteQuestion(questionId) {
        return fetch(this.BASE_URL + "/questions/" + questionId, {
            method: "DELETE",
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        });
    }

    voteQuestion(questionId, voteValue) {
        return fetch(this.BASE_URL + "/questions/" + questionId + "/votes/" + voteValue, {
            method: "POST",
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        });
    }

    loadFilteredByTags(tagsAsString) {
        return fetch(this.BASE_URL + "/questions/findByTags/" + tagsAsString, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (!response.ok) return [];
            else return response.json();
        })
    }

    loadFilteredByTitle(title) {
        return fetch(this.BASE_URL + "/questions/findByTitle/" + title, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (!response.ok) return [];
            else return response.json();
        })
    }

    loadAllTags() {
        return fetch(this.BASE_URL + "/questions/tags", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (!response.ok) return [];
            else {
                return response.json();
            }
            ;
        }).then(tags => tags.map(t => t.value));
    }
}