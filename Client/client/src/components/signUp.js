import React, { Component } from 'react';
import { Link, Redirect } from 'react-router-dom';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
const port = 4000;

export class Signup extends Component {
    constructor(props) {
        super(props);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        this.onChangeFName = this.onChangeFName.bind(this);
        this.onChangeSurname = this.onChangeSurname.bind(this);
    }
    state = {
        accCreationFailed: false,
        accountCreated: false,
        email: '',
        password: '',
        firstName: '',
        surname: ''
    }

    onSubmit = (event) => {
        event.preventDefault();
        axios.post(`http://localhost:${port}/signup/`, {
            email: this.state.email,
            pw: this.state.password,
            firstName: this.state.firstName,
            surname: this.state.surname
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
        if (this.state.accountCreated) {
            return (<Redirect exact to="/" />);
        } else {
            return (
                <div className="Signup topMargin">
                    <h5>Existing user? <Link to="/login">Sign In</Link></h5>
                    <div className="container-fluid col-lg-8">
                        <h2>Create an Account</h2>
                        <Form onSubmit={this.onSubmit}>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <Form.Label>Email Address</Form.Label>
                                    <Form.Control type="input" value={this.state.email} onChange={this.onChangeEmail} required></Form.Control>
                                </Form.Group>
                                <Form.Group as={Col}>
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="input" value={this.state.password} onChange={this.onChangePassword} required></Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control type="input" value={this.state.firstName} onChange={this.onChangeFName} required></Form.Control>
                                </Form.Group>
                                <Form.Group as={Col}>
                                    <Form.Label>Surname</Form.Label>
                                    <Form.Control type="input" value={this.state.surname} onChange={this.onChangeSurname} required></Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Button variant="dark" type="submit">Sign Up</Button>
                        </Form>
                        {this.accountCreationFailedMessage()}
                    </div>
                </div>
            );
        }
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

    onChangeSurname(e) { this.setState({ surname: e.target.value }); }
    // #endregion
}