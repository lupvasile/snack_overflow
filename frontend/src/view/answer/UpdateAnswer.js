import React from "react";
import SmartNavBar from "../user/SmartNavBar";

const UpdateAnswer = ({answer, onUpdate, onChange, answerId}) => (
    <div>
        <SmartNavBar/>
        <div className="column is-13">
            <div className="field">
                <div className="control">
                    <textarea className="textarea" placeholder="Enter new answer..." value={answer.text}
                              onChange={e => onChange("text", e.target.value)}></textarea>
                </div>
            </div>
            <div className="control">
                <button className="button is-link" onClick={() => onUpdate(answerId)}>Update answer</button>
            </div>
        </div>
    </div>
);

export default UpdateAnswer;