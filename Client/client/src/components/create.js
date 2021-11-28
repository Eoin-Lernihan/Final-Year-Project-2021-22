import axios from 'axios';
import React, { Component } from 'react';
const port = 4000;

function clickMe() {
  axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/user`)
            .then(response => {
                this.setState({storeProducts: response.data});
            })
            .catch(error => {
                console.log(error);
            });
  alert("You clicked me!");
}

export class Create extends Component{
  render(){
    return (
      <div className="create">
        
        <h3>create</h3>
      <button theme="pink" onClick={clickMe}>
          Pink theme
        </button>  
        </div>
    );
  }
}
  

