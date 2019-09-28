import React from "react";
import SmartNavBar from "../user/SmartNavBar";

const AddQuestion = ({question, existingTags, onCreate, onChange}) => (
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
            <div className="field">
                <label className="label">Tags</label>
                <div className="control">
                    <input className="input" type="text" placeholder="Please input tags, comma separated: tag1,tag2"
                           value={question.tagsAsString} onChange={e => onChange("tagsAsString", e.target.value)}/>
                </div>
            </div>
            <div className="media-content">
                <div className="content">

                    <p>
                        existing tags: &nbsp;
                        {
                            existingTags ? existingTags.map((tag, index) => (
                                <span><span className="tag">{tag} </span> &nbsp;</span>
                            )) : null
                        }
                    </p>
                </div>
                <br/>
            </div>
            <div className="control">


                <button className="button is-link" onClick={onCreate}>Add question</button>
            </div>


        </div>
    </div>
);

export default AddQuestion;