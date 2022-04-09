import React, { Component } from 'react';



export class LogOut extends Component {

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