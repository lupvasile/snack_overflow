import React from "react";
//maybe just a href?
const NavBar = ({user, onLogout, onAllQuestions, onAddQuestion, onBanUsers, onUndo, onRedo}) => (
    <div>
        <nav className="navbar is-light topNav">
            <div className="container">
                <div className="navbar-brand">
                    <div className="navbar-burger burger" data-target="topNav">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                </div>
                <div id="topNav" className="navbar-menu">
                    <div className="navbar-start">
                        <div className="navbar-item">
                            <a className="navbar-item" onClick={onAllQuestions}>All questions</a>
                            <a className="navbar-item" onClick={onAddQuestion}>Add question</a>
                            {user.isModerator ? <a className="navbar-item" onClick={onBanUsers}>Ban user</a> : null}
                        </div>
                    </div>

                    <a className="navbar-item" onClick={onUndo}>Undo</a>
                    <a className="navbar-item" onClick={onRedo}>Redo</a>
                    <div className="navbar-end">
                        <div className="navbar-item">


                            <p>
                                <strong>{user.username}</strong> score: {user.score} &nbsp;
                            </p>

                            <p className="control">
                                <a className="button is-small is-outlined" onClick={onLogout}>
                                    <span>Logout</span>
                                </a>
                            </p>

                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
);

export default NavBar;