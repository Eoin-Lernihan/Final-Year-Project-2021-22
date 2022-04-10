import React, { Component } from 'react';



export class LogOut extends Component {
//A final in conformation if the player wants to sign out
render() {
         return (
             <div className="Login topMargin">
                 <button theme="pink" onClick={this.logOut}><h5>Are your sure</h5></button>    
             </div>
             
         );
        }

    logOut(){
        localStorage.clear();
    }
 }