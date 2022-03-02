import React, { Component } from 'react';
import { Link, Redirect } from 'react-router-dom';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
const port = 4000;

export class Login extends Component {
    constructor(props) {
        super(props);
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
    }

    state = {
        username: '',
        password: '',
        invalidLogin: false
    }

    onSubmit = (event) => {
        event.preventDefault();
        axios.get(`http://localhost:${port}/loginattempt/`, {
            params: {
                user: this.state.username,
                pw: this.state.password
            }
        })
        .then(response => {
            if (response.data.length > 0) {
                this.props.onLogin(response.data[0].firstName);
            } else {
                this.setState({ username: '', password: '', invalidLogin: true });
            }
        })
        .catch(error => {
            console.log(error);
        });   
    }

    render() {
        if (this.props.username != null) {
            return (<Redirect exact to="/store" />);
        } else {
            return (
                <div className="Login topMargin">
                    <h5>Don't have an account? <Link to="/signup">Sign Up</Link></h5>
                    <div className="container-fluid col-lg-4">
                        <h2>Sign In</h2>
                        <Form onSubmit={this.onSubmit}>
                            <Form.Group>
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="input" value={this.state.username} onChange={this.onChangeUsername} placeholder="Enter Username" required></Form.Control>
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="input" value={this.state.password} onChange={this.onChangePassword} placeholder="Enter Password" required></Form.Control>
                            </Form.Group>
                            <Button variant="dark" type="submit">Sign In</Button>
                        </Form>
                        {this.incorrectLoginMessage()}
                    </div>
                </div>
            );
        }
    }

    incorrectLoginMessage() {
        if (this.state.invalidLogin) {
            return (
                <div>
                    <hr />
                    <h5 className="redText">Incorrect password, Please try again.</h5>
                </div>
            )
        }
    }

    // #region onChange Events
    onChangeUsername(e) { this.setState({ username: e.target.value }); }

    onChangePassword(e) { this.setState({ password: e.target.value }); }
    // #endregion
}