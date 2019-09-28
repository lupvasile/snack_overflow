export default class RestClientLogin {
    constructor(authorization, BASE_URL) {
        this.authorization = authorization;
        this.BASE_URL = BASE_URL;
    }

    loadCurrentLoggedUser() {
        return fetch(this.BASE_URL + "/me", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            if (!response.ok) return false;
            else return response.json();
        })
    }
}