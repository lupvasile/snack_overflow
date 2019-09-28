import React from "react";
import SmartAnswerList from "../answer/SmartAnswerList";
import SmartNavBar from "../user/SmartNavBar";

const QuestionDetails = ({question, loggedUser, onDelete, onEdit, onVote}) => (
    <div>
        <SmartNavBar/>
        {question !== undefined ?
            <div>
                <section className="articles">
                    <div className="column is-8 is-offset-2">
                        <div className="card article">
                            <div className="card-content">
                                <div className="media">
                                    <div className="media-content has-text-centered">
                                        <p className="title article-title">{question.title}</p>
                                        <div className="tags has-addons level-item">
                                            <span className="tag is-rounded is-info">{question.author.username}</span>
                                            <span className="tag is-info">score: {question.author.score}</span>
                                            <span className="tag">voteCount: {question.voteCount}</span>
                                            {question.author.id !== loggedUser.id ?
                                                <span className="tag"><a
                                                    onClick={() => onVote(question.id, 1)}>Upvote</a></span> : null}
                                            {question.author.id !== loggedUser.id ?
                                                <span className="tag"><a
                                                    onClick={() => onVote(question.id, -1)}>Downvote</a></span> : null}
                                            <span className="tag is-rounded">{question.creationDateTime}</span>
                                        </div>
                                    </div>
                                </div>
                                <div className="content article-body">
                                    <p>{question.text}</p>
                                    <div className="media-content">
                                        <div className="content">
                                            <p>

                                                {
                                                    question.tags.map((tag, index) => (
                                                        <span><span className="tag">{tag} </span> &nbsp;</span>
                                                    ))
                                                }
                                            </p>
                                        </div>
                                        {loggedUser.isModerator ?
                                            <div>
                                                <button className="button is-info is-light is-small"
                                                        onClick={() => onEdit(question.id)}>Edit
                                                </button>
                                                &nbsp;
                                                <button className="button is-black is-small"
                                                        onClick={() => onDelete(question.id)}>Delete
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
                <SmartAnswerList questionId={question.id}/>
            </div>
            : null}
    </div>
);

export default QuestionDetails;