import React from "react";
//id, author, title, text, creationDateTime, tags, voteCount
const UserSummary = ({user, onBan}) => (
    <div>
        <section className="articles">
            <div className="column">
                <div className="card article">
                    <div className="card-content">
                        <div className="content article-body">
                            <div className="columns">
                                <div className="column is-three-quarters">
                                    <div className="media">
                                        <div className="media-content has-text-centered">
                                            <div className="tags has-addons">
                                                <span className="tag is-rounded is-large">{user.username}</span>
                                                <span className="tag is-rounded is-large">score: {user.score}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div className="column">
                                    {
                                        !user.isBlocked ?
                                            <a className="button is-dark" onClick={() => onBan(user.id)}>Ban</a>
                                            : <a className="button is-dark" disabled>Banned</a>
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>


);

export default UserSummary;