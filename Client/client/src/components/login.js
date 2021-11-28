import React, { Component } from 'react';
import ReactDOM from 'react-dom';
export class Login extends Component{
render(){
  return (
    <div className="login">
      
      <h3>Login</h3>
      <p>
      <form>
      <label>Enter your username:
        <input type="text" />
      </label>
      <button theme="pink">enter</button>  
    </form>
          </p>
      </div>
  );
}
}
