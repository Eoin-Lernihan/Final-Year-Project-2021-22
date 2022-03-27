import React, { Component } from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import { Link, Redirect } from 'react-router-dom';


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