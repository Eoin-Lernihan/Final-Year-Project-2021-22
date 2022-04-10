import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import axios from 'axios';
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';

export class InTournament extends Component {
    constructor(props) {
        super(props);    

        // This binding is necessary to make `this` work in the callback
       // this.updateTournament = this.updateTournament.bind(this);
      }
      state = this.props 
      

    render()  { 
       let stateString = JSON.stringify(this.state)

      let user = JSON.parse( localStorage.getItem("user"));
        let userName = user.userName;

        this.state.tourament.players.indexOf(userName) > -1 ? this.state.tourament.players.pop(userName) : console.log("This item already exists");
        console.log(this.state.tourament.players);
        return (
            <div className="StoreDisplayProducts container-fluid col-lg-10" key={this.state.tourament.owner}>
            <Card id="Card" bg="secondary" border="dark" text="white">
                <Card.Body>
                    <Row>
                        <Col>
                            <p></p>
                            Owner Of Game:&nbsp; {this.state.tourament.owner} &nbsp;
                            <p></p>
                            Game:&nbsp; {this.state.tourament.game}&nbsp;Tournament Type:&nbsp;{this.state.tourament.gameMode}
                            <p></p>
                            Date and Time: &nbsp; {this.state.tourament.time} &nbsp; Max Players:  {this.state.tourament.maxPlayers}
                            <p></p>
                        </Col>
                    </Row>
                   
                    <Button onClick={this.updateTournament} variant="warning">Stop traking Game</Button>{' '}
                    
                </Card.Body>
            </Card>
            <hr />
        </div>
        )
    }

    updateTournament = (event) => {
        event.preventDefault();
       let stateString = JSON.stringify(this.state)
       console.log("here in render"+ stateString)

       axios.put('https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments/' + this.state.tourament.number, {
        number: this.state.tourament.number,
        userName: this.state.tourament.game,
        gameMode: this.state.tourament.gameMode,
        owner: this.state.tourament.owner,
        maxPlayers: this.state.tourament.maxPlayers,
        time: this.state.tourament.time,
        description: this.state.tourament.description,
        players: this.state.tourament.players })
            .then(() => {
                this.props.ReloadData();
            })
            .catch((err) => {
                console.log(err);
            });
    }

}

