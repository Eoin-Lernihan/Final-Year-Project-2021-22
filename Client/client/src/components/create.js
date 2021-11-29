import axios from 'axios';
import React, { Component } from 'react';
const port = 4000;

function clickMe() {
  axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/user`)
            .then(response => {
              console.log(response.data);
                alert(response.data);
            })
            .catch(error => {
                console.log(error);
            });
}

export class Create extends Component{
  render(){
    return (
      <div className="create">
        <h3>create</h3>
      <button theme="pink" onClick={clickMe}>
          demo button
        </button>  
        </div>
    );
  }
}
  

