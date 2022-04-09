import React, { Component } from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import { Link, Redirect } from 'react-router-dom';

export class Create extends Component {
    constructor(props) {
        super(props);
        this.onChangeTourmentName = this.onChangeTourmentName.bind(this);
        this.onChangePlayerAmount = this.onChangePlayerAmount.bind(this);
        this.onChangeGame = this.onChangeGame.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.onChangeDuration = this.onChangeDuration.bind(this);
        this.onChangeNumRounds = this.onChangeNumRounds.bind(this);
        this.onChangeTime = this.onChangeTime.bind(this);

    }
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

      onSubmit = (event) => {
        event.preventDefault();
        axios.post(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/user`, {
            tourmentName: this.state.tourmentName,
            playerAmount: this.state.playerAmount,
            game: this.state.game,
            description: this.state.description,
            duration: this.state.duration,
            numRounds: this.state.numRounds,
            time: this.state.time
        })
        .then(response => {
            if (response.statusText === "OK") {
                this.setState({ tournamentCreated: true });
                this.props.onLogin(this.state.tourmentName);
            } else {
                this.setState({ tournamentCreationFailed: true });
            }
        })
        .catch(error => {
            console.log(error);
        });
    }

      render() {
        //if (this.state.accountCreated) {
          //  return (<Redirect exact to="/" />);
        //} else {
            return (
                <div className="Signup topMargin">
                   
                    <div className="container-fluid col-lg-8">
                        <h2>Create a public Tournament today</h2>
                        <Form onSubmit={this.onSubmit}>
                        <Form.Group as={Col}>
                            <Form.Label>tourmentName</Form.Label>
                            <Form.Control type="input" value={this.state.tourmentName} onChange={this.onChangeTourmentName} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>playerAmount</Form.Label>
                            <Form.Control type="input" value={this.state.playerAmount} onChange={this.onChangePlayerAmount} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>game</Form.Label>
                            <Form.Control type="input" value={this.state.game} onChange={this.onChangeGame} required></Form.Control>
                        </Form.Group>                           
                        <Form.Group as={Col}>
                            <Form.Label>description</Form.Label>
                            <Form.Control type="input" value={this.state.description} onChange={this.onChangeDescription} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>duration</Form.Label>
                            <Form.Control type="input" value={this.state.duration} onChange={this.onChangeDuration} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>numRounds</Form.Label>
                            <Form.Control type="input" value={this.state.numRounds} onChange={this.onChangeNumRounds} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>time</Form.Label>
                            <Form.Control type="input" value={this.state.time} onChange={this.onChangeTime} required></Form.Control>
                        </Form.Group>                                     
                        <Button variant="dark" type="submit">Sign Up</Button>
                        </Form>
                        {this.tournamentCreationFailedMessage()}
                    </div>
                </div>
            );
        //}
    }

    tournamentCreationFailedMessage() {
        if (this.state.accCreationFailed) {
            return (
                <div>
                    <hr />
                    <h5 className="redText">Sorry account creation failed, Please try again.</h5>
                </div>
            )
        }
    }

    // #region onChange Events
    onChangeTourmentName(e) { this.setState({ tourmentName: e.target.value }); }

    onChangePlayerAmount(e) { this.setState({ playerAmount: e.target.value }); }

    onChangeGame(e) { this.setState({ game: e.target.value }); }

    onChangeDescription(e) { this.setState({ description: e.target.value }); }

    onChangeDuration(e) { this.setState({ duration: e.target.value }); }

    onChangeNumRounds(e) { this.setState({ numRounds: e.target.value }); }

    onChangeTime(e) { this.setState({ time: e.target.value }); }

}