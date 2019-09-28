import React from "react";
import QuestionSummary from "./QuestionSummary";
import SmartNavBar from "../user/SmartNavBar";
//id, author, title, text, creationDateTime, tags, voteCount
const QuestionList = ({questions, onViewDetails, questionSearchText, onChangeSearchText, onSearchByTitle, onSearchByTag}) => (
    <div>
        <SmartNavBar/>
        <nav className="navbar is-white">
            <div className="container">
                <div className="navbar-menu">
                    <div className="navbar-start">
                        <div className="navbar-item">
                            <input className="input" type="search" placeholder="Search..." value={questionSearchText}
                                   onChange={e => onChangeSearchText(e.target.value)}/>
                        </div>
                        <a className="navbar-item" onClick={onSearchByTitle}>by title</a>
                        <a className="navbar-item" onClick={onSearchByTag}>by tag</a>
                    </div>
                    <div className="navbar-end">
                    </div>
                </div>
            </div>
        </nav>
        <div className="columns">
            <div className="column is-13">
                {
                    questions.map((question, index) => (
                        <QuestionSummary question={question} onViewDetails={onViewDetails}></QuestionSummary>
                    ))
                }
            </div>
        </div>
    </div>
);

export default QuestionList;