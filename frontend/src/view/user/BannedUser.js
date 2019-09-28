import React from "react";

const BannedUser = () => (
    <section className="hero is-fullheight is-danger">
        <div className="hero-body">
            <div className="container has-text-centered">
                <h1 className="title">YOU ARE BANNED</h1>
                <p>you can't do any actions</p>
                <br/>
                <a className="button is-warning is-large" href="#/">Logout</a>
            </div>

        </div>

    </section>
);

export default BannedUser;