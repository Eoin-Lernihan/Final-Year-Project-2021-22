import { render } from '@testing-library/react';
import React, { Component } from 'react';

export class Home extends Component{
render(){
  return (
    <div className="home">
      
      <h3>Home</h3>
      <p>
          Advertise your touraments here today
          </p>
          <p>

          Keep track of how many players have joined

        </p>
      </div>
  );
}

}
