import React, { Component } from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import { Link, Redirect } from 'react-router-dom';

export class Create extends Component {

    state = {
        tournamentCreationFailed: false,
        tournamentCreated: false,
        tourmentName: '',
        playerAmount: '',
        game: '',
        description: '',
        duration: '',
        numRounds: '',
        time: ''
      }

render(){
    return(
       <div>
       hi
       <Form>
       <Form.Group as={Col}>
                    <Form.Label>Date</Form.Label>
                    <input type='date'
                            className='form-control'
                            value={this.state.Date}
                            onChange={this.onChangeDate}></input>
        </Form.Group>
       </Form>
        </div>
      
    );
}

onSubmit(){

}
}