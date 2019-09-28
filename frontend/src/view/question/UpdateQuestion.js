import React from "react";
import SmartNavBar from "../user/SmartNavBar";

const UpdateQuestion = ({question, onUpdate, onChange, questionId}) => (
    <div>
        <SmartNavBar/>
        <div className="column is-13">
            <div className="field">
                <label className="label">Title</label>
                <div className="control">
                    <input className="input" type="text" placeholder="Please input title" value={question.title}
                           onChange={e => onChange("title", e.target.value)}/>
                </div>
            </div>

            <div className="field">
                <label className="label">Text</label>
                <div className="control">
                    <textarea className="textarea" placeholder="Textarea" value={question.text}
                              onChange={e => onChange("text", e.target.value)}></textarea>
                </div>
            </div>
            <div className="control">
                <button className="button is-link" onClick={() => onUpdate(questionId)}>Update question</button>
            </div>
        </div>
    </div>
);

export default UpdateQuestion;