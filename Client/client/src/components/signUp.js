import React, { Component } from 'react';
import { Link, Redirect } from 'react-router-dom';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import axios from 'axios';


export class signUp extends Component {
    constructor(props) {
        super(props);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        this.onChangeFName = this.onChangeFName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangePhonenumber = this.onChangePhonenumber.bind(this);
    }
    state = {
        accCreationFailed: false,
        accountCreated: false,
        username: '',
        email: '',
        password: '',
        firstName: '',
        lastName: '',
        phoneNumber: ''
    }

    onSubmit = (event) => {
        event.preventDefault();
        axios.post(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/user`, {
            userName: this.state.username,
            email: this.state.email,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            password: this.state.password,
            number: this.state.phoneNumber
        })
        .then(response => {
            if (response.statusText === "OK") {
                this.setState({ accountCreated: true });
                this.props.onLogin(this.state.firstName);
            } else {
                this.setState({ accCreationFailed: true });
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
                    <h5>Existing user? <Link to="/login">Sign In</Link></h5>
                    <div className="container-fluid col-lg-8">
                        <h2>Create an Account</h2>
                        <Form onSubmit={this.onSubmit}>
                        <Form.Group as={Col}>
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="input" value={this.state.username} onChange={this.onChangeUsername} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Email Address</Form.Label>
                            <Form.Control type="input" value={this.state.email} onChange={this.onChangeEmail} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="input" value={this.state.password} onChange={this.onChangePassword} required></Form.Control>
                        </Form.Group>                           
                        <Form.Group as={Col}>
                            <Form.Label>First Name</Form.Label>
                            <Form.Control type="input" value={this.state.firstName} onChange={this.onChangeFName} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>lastName</Form.Label>
                            <Form.Control type="input" value={this.state.lastName} onChange={this.onChangeLastName} required></Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>PhoneNumber</Form.Label>
                            <Form.Control type="input" value={this.state.phoneNumber} onChange={this.onChangePhonenumber} required></Form.Control>
                        </Form.Group>                                   
                        <Button variant="dark" type="submit">Sign Up</Button>
                        </Form>
                        {this.accountCreationFailedMessage()}
                    </div>
                </div>
            );
        //}
    }

    accountCreationFailedMessage() {
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
    onChangeEmail(e) { this.setState({ email: e.target.value }); }

    onChangePassword(e) { this.setState({ password: e.target.value }); }

    onChangeFName(e) { this.setState({ firstName: e.target.value }); }

    onChangeLastName(e) { this.setState({ lastName: e.target.value }); }

    onChangeUsername(e) { this.setState({ username: e.target.value }); }

    onChangePhonenumber(e) { this.setState({ phoneNumber: e.target.value }); }
    // #endregion
}