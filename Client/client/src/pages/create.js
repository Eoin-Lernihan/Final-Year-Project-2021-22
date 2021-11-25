import React from 'react';
import axios from 'axios';
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

const create = () =>{
 return (
    <div>
      <h3>create</h3>
      <button theme="pink" onClick={clickMe}>
          Pink theme
        </button>  
    </div>
    
  );
   
}
export default create;

