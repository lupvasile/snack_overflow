export default class RestClientUsers {
    constructor(authorization, BASE_URL) {
        this.authorization = authorization;
        this.BASE_URL = BASE_URL;
    }

    loadAllUsers() {
        return fetch(this.BASE_URL + "/users", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (!response.ok) return [];
            else return response.json();
        })
    }

    banUser(userId) {
        return fetch(this.BASE_URL + "/users/" + userId + "/ban", {
            method: "PUT",
            headers: {
                "Authorization": this.authorization
            }
        });
    }
}