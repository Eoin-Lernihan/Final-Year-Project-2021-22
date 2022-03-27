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
                        <Button variant="danger" onClick={this.DeleteBookings}>Delete</Button>
                        </Col>
                    </Row>
                </Card.Body>
            </Card>
            <hr />
        </div>
    );
    }
}