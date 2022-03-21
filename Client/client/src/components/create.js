import React, { Component } from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import { Link, Redirect } from 'react-router-dom';
const port = 4000;


export class Create extends Component {

constructor(props) {
  super(props);
  this.onTournamentName = this.onTournamentName.bind(this);
  this.onPlayeramount = this.onPlayeramount.bind(this);
  this.onGame = this.onGame.bind(this);
  this.onChangeSurname = this.onChangeSurname.bind(this);
}

state = {
  tournamentCreationFailed: false,
  tournamentCreated: false,
  tourmentName: '',
  playerAmount: '',
  game: '',
  surname: ''
}

onSubmit = (event) => {
  event.preventDefault();
  axios.post(`http://localhost:${port}/signup/`, {
      name: this.state.tourmentName,
      playersAmount: this.state.playerAmount,
      game: this.state.tournamentType,
      surname: this.state.surname
  })
  .then(response => {
      if (response.statusText === "OK") {
          this.setState({ tournamentCreated: true });
      } else {
          this.setState({ tournamentCreationFailed: true });
      }
  })
  .catch(error => {
      console.log(error);
  });
}


  render() {
        return (
            <div className="Signup topMargin">
                <h5>Existing user? <Link to="/login">Sign In</Link></h5>
                <div className="container-fluid col-lg-8">
                <h2>Create an Account</h2>
                <Form onSubmit={this.onSubmit}>
                    <Form.Row>
                        <Form.Group as={Col}>
                            <Form.Label>TourmentName</Form.Label>
                            <Form.Control type="input" value={this.state.tourmentName} onChange={this.onNewTournamentName} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Game</Form.Label>
                            <Form.Control type="input" value={this.state.game} onChange={this.onNewGame} required></Form.Control>
                        </Form.Group>
                    </Form.Row>
                    <Form.Row>
                        <Form.Group as={Col}>
                            <Form.Label>Amount Of Players</Form.Label>
                            <Form.Control type="input" value={this.state.amountOfPlayers} onChange={this.onChangeFName} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Surname</Form.Label>
                            <Form.Control type="input" value={this.state.surname} onChange={this.onChangeSurname} required></Form.Control>
                        </Form.Group>
                    </Form.Row>                        
                    <Button variant="dark" type="submit">Sign Up</Button>
                </Form>
                {this.tournamentCreationFailedMessage()}
                </div>
                <div className="create">
        <h3>create</h3>
      <button theme="pink" onClick={this.getUser}>
          demo button
        </button>  
        </div>
            </div>
        );
    
    }

  tournamentCreationFailedMessage() {
    if (this.state.accCreationFailed) {
        return (
            <div>
                <hr />
                <h5 className="redText">Sorry toutnament failed to create, Please try again or try later.</h5>
            </div>
        )
    }
}

getUser() {
    // axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/userhttps://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/user`)
    axios.get('https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/admin')  
    .then(response => {
                 console.log(response.data);
                   alert(response.data);
               })
               .catch(error => {
                   console.log(error);
               });
             }

// #region onChange Events
onTournamentName(e) { this.setState({ tourmentName: e.target.value }); }

onPlayeramount(e) { this.setState({ playerAmount: e.target.value }); }

onGame(e) { this.setState({ game: e.target.value }); }

onChangeSurname(e) { this.setState({ surname: e.target.value }); }
}
  

