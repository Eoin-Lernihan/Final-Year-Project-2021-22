import React, { Component } from 'react';
import axios from 'axios';
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import { TournamentLoaderTrue } from './tournamentLoaderPlayerTrue';

export class User extends Component {
   
    constructor() {
        super();
        this.ReloadData = this.ReloadData.bind(this);
        
    }

    state = { loginUser:  localStorage.getItem("user") ,
              tournamentsGamesTrue: [],
              tournamentsGamesFlase: [] }

    ReloadData() {
        let user = JSON.parse( localStorage.getItem("user"));
        let userName = user.userName;
            axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`, { params: {
                // username: this.state.username,
                 userName: userName,
                 inGame: '1'
             }})
            .then(response => {
                this.setState({ tournamentsGamesTrue: response.data.tournaments });
            })
            .catch(error => {
                console.log(error);
            });
           
    }


    componentDidMount() {
        let user = JSON.parse( localStorage.getItem("user"));
        let userName = user.userName;
            axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`, { params: {
                // username: this.state.username,
                 userName: userName,
                 inGame: '1'
             }})
        .then(response => {
            this.setState({ tournamentsGamesTrue: response.data.tournaments });
        })
        .catch(error => {
            console.log(error);
        });
       
    }
render(){
    let user = JSON.parse( localStorage.getItem("user"));
    let userName = user.userName;
    let firstName = user.firstName;
    let lastName = user.lastName;
    let phoneNumber = user.phoneNumber;
    let email = user.email;
    console.log(user);
    return (
        <div style={{ width: '100%' } }>
             <div>
            <Card id="Card" bg="secondary" border="dark" text="white">
                <Card.Body variant="secondary">
                    <Row>
                        <Col>
                        <p></p>
                        User Profile 
                        <p></p>
                        User Name :&nbsp; {userName}
                        <p></p>
                        Full Name :&nbsp; {firstName} {lastName}
                        <p></p>
                        PhoneNumber :&nbsp; {phoneNumber}
                        <p></p>
                        Email :&nbsp; {email}
                        <p></p>
                        <Button variant="primary" onClick={this.DeleteUser}>Update User</Button>&nbsp; &nbsp; <Button variant="danger" onClick={this.DeleteUser}>Delete Account</Button>
                        </Col>
                    </Row>
                </Card.Body>
            </Card>
            </div>
            <hr />
            <div>
                 <h4 fontWeight='bold'>Games joined</h4>   
                <TournamentLoaderTrue
                        tournamentsGamesTrue={this.state.tournamentsGamesTrue} >
                    </TournamentLoaderTrue>
                </div>
        </div>
    );
    }

    DeleteUser(userName) {
        userName.preventDefault();
        axios.delete('https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/user/${this.state.userName}' )
            .then(() => {
                this.props.ReloadData();
            })
            .catch((err) => {
                console.log(err);
            });
    }

    
      
}