import React from "react";

const AnswerDetails = ({answer, loggedUser, onDelete, onEdit, onVote}) => (
    <div>
        {answer !== undefined ?
            <div>
                <section className="articles">
                    <div className="column">
                        <div className="card article">
                            <div className="card-content">
                                <div className="content article-body">
                                    <p>{answer.text}</p>
                                    <div className="media">
                                        <div className="media-content has-text-centered">
                                            <div className="tags has-addons ">
                                                <span className="tag is-rounded">{answer.author.username}</span>
                                                <span className="tag">score: {answer.author.score}</span>
                                                <span className="tag">voteCount: {answer.voteCount}</span>
                                                {answer.author.id !== loggedUser.id ?
                                                    <span className="tag"><a
                                                        onClick={() => onVote(answer.id, 1)}>Upvote</a></span> : null}
                                                {answer.author.id !== loggedUser.id ?
                                                    <span className="tag"><a
                                                        onClick={() => onVote(answer.id, -1)}>Downvote</a></span> : null}
                                                <span className="tag is-rounded">{answer.creationDateTime}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="media-content">
                                        {loggedUser.id === answer.author.id || loggedUser.isModerator ?
                                            <div>
                                                <button className="button is-info is-light is-small"
                                                        onClick={() => onEdit(answer.id)}>Edit
                                                </button>
                                                &nbsp;
                                                <button className="button is-black is-small"
                                                        onClick={() => onDelete(answer.id)}>Delete
                                                </button>
                                            </div>
                                            : null
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            : null}
    </div>
);

export default AnswerDetails;