import React, { Component } from 'react';
import ReactDOM from 'react-dom';
export class Login extends Component{
render(){
  function checkUser() {
    alert("Sorry this username or password is wrong")
  }
  return (
    <div className="login">
      
      <h3>Login</h3>
      <p>
      <form>
      <label>Enter your username:
        <input type="text" />
      </label>
      <br></br>
      <label>Enter your password:
        <input type="text" />
      </label>
      <br></br>
      <button theme="pink" onClick={checkUser}>enter</button>  
    </form>
          </p>
      </div>
  );
}
}
