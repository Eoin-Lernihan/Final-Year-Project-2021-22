import React, { Component } from 'react';
import { Link, Redirect } from 'react-router-dom';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
const port = 4000;

export class signUp extends Component {
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
        } else
        {
            return (
                <div className="Signup topMargin">
                <h5>Existing user? <Link to="/login">Sign In</Link></h5>
                <div className="container-fluid col-lg-8">
                    <h2>Create an Account</h2>
                    <Form onSubmit={this.onSubmit}>
                   
                    <form>
        <label>
          Is going:
          <input
            name="isGoing"
            type="checkbox"
            checked={this.state.isGoing}
            onChange={this.handleInputChange} />
        </label>
        <br />
        <label>
          Number of guests:
          <input
            name="numberOfGuests"
            type="number"
            value={this.state.numberOfGuests}
            onChange={this.handleInputChange} />
        </label>
      </form>
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