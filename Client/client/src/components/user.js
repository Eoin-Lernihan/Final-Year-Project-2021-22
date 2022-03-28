import React, { Component } from 'react';
import axios from 'axios';
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
export class User extends Component {
    state = { loginUser:  localStorage.getItem("user")  }
    
render(){
    let user = JSON.parse( localStorage.getItem("user"));
    let userName = user.userName;
    console.log(user);
    return (
        <div className="StoreDisplayProducts container-fluid col-lg-10">
            <Card id="Card" bg="secondary" border="dark" text="white">
                <Card.Body>
                    <Row>
                        <Col>
                        <p></p>
                        here
                        {this.state.loginUser}
                        <p></p>
                        Test
                        {userName}
                        
                        <p></p>
                        <Button variant="danger" onClick={this.DeleteUser}>Delete Account</Button>
                        </Col>
                    </Row>
                </Card.Body>
            </Card>
            <hr />
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