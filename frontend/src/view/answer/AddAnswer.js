import React from "react";

const AddAnswer = ({answer, onCreate, onChange, questionId}) => (
    <div className="column is-13">
        <div className="field">
            <div className="control">
                <textarea className="textarea" placeholder="Enter new answer..." value={answer.text}
                          onChange={e => onChange("text", e.target.value)}></textarea>
            </div>
        </div>
        <div className="control">
            <button className="button is-link" onClick={() => onCreate(questionId)}>Add answer</button>
        </div>
    </div>
);

export default AddAnswer;