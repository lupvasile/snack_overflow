import React from "react";
//id, author, title, text, creationDateTime, tags, voteCount
const QuestionSummary = ({question, onViewDetails}) => (
    <div className="box content">
        <article className="post">
            <h4>
                <a onClick={() => onViewDetails(question.id)}>{question.title}</a>
            </h4>
            <div className="media">
                <div className="media-left">
                    {question.creationDateTime}
                </div>
                <div className="media-content">
                    <div className="content">
                        <p>
                            {question.author.username} score: {question.author.score} &nbsp;
                            {
                                question.tags.map((tag, index) => (
                                    <span><span className="tag">{tag} </span> &nbsp;</span>
                                ))
                            }
                        </p>
                    </div>
                </div>
                <div className="media-right">
                    <span className="has-text-grey-light">{question.voteCount}</span>&nbsp;
                </div>
            </div>
        </article>
    </div>


);

export default QuestionSummary;