import { render } from '@testing-library/react';
import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { Carousel } from 'react-responsive-carousel';
import logo1 from './assest/home2.png';
import YuGiOh from './assest/YuGiOh.png';
import logo from './assest/CardsGames.png';
import magic from './assest/Magic.png';
// used https://www.npmjs.com/package/react-responsive-carousel
export class Home extends Component{
  //Simple home page 
render(){
  return (
    <div className="home" >
    <Carousel showArrows={true} >
    <div>
        <img src={logo} alt="Logo" />
        <p className="legend">Legend 1</p>
    </div>
    
    <div>
        <img src={magic} alt="Logo" />
        <p className="legend">Legend 2</p>
    </div>

    <div>
        <img src={YuGiOh} alt="Logo" />
        <p className="legend">Legend 3</p>
    </div>

    <div className="page4">
    <img src={logo} alt="Logo" />
      <h3>Tournaaro Home Page</h3>
      <p>
          Find touraments here today
          </p>
          <p>
        See which type of tourments are open and avilable
        </p>
        <p>
          Never forget about an upcoming tourament
        </p>
       
      </div>
      </Carousel>
     
      </div>
  );  
}
}


