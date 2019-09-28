import React from "react";
import SmartNavBar from "./SmartNavBar";
import UserSummary from "./UserSummary";

const UserList = ({users, onBan}) => (
    <div>
        <SmartNavBar/>
        <div className="columns is-centered">
            <div className="column is-8 ">
                {
                    users.map((user, index) => (
                        <UserSummary user={user} onBan={onBan}></UserSummary>
                    ))
                }
            </div>
        </div>
    </div>
);

export default UserList;