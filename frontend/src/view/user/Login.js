import React from "react";

const Login = ({username, password, onLogin, onChange}) => (
    <section className="hero is-fullheight">
        <div className="hero-body">
            <div className="container has-text-centered">
                <div className="column is-4 is-offset-4">
                    <h3 className="title has-text-grey">Snackoverflow</h3>
                    <p className="subtitle has-text-grey">Please login to proceed.</p>
                    <div className="box">
                        <div className="field">
                            <div className="control">
                                <input className="input is-large" type="username" placeholder="Your Username"
                                       autofocus="" value={username}
                                       onChange={e => onChange("username", e.target.value)}/>
                            </div>
                        </div>

                        <div className="field">
                            <div className="control">
                                <input className="input is-large" type="password" placeholder="Your Password"
                                       value={password}
                                       onChange={e => onChange("password", e.target.value)}/>
                            </div>
                        </div>
                        <button className="button is-block is-info is-large is-fullwidth"
                                onClick={() => onLogin()}>Login
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </section>
);

export default Login;