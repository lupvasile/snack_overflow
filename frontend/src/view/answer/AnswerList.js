import React from "react";
import AnswerDetails from "./AnswerDetails";
import SmartAddAnswer from "./SmartAddAnswer";

const AnswerList = ({answers, loggedUser, onDelete, onEdit, onVote, questionId}) => (
    <div>
        <div className="columns">
            <div className="column is-10">
                {
                    answers.map((answer, index) => (
                        <AnswerDetails answer={answer}
                                       loggedUser={loggedUser}
                                       onDelete={onDelete}
                                       onEdit={onEdit}
                                       onVote={onVote}
                        />
                    ))
                }
            </div>
        </div>
        <SmartAddAnswer questionId={questionId}/>
    </div>
);

export default AnswerList;