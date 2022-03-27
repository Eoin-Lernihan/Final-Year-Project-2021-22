import React, { Component } from 'react';
import { Link, Redirect } from 'react-router-dom';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

export class Login extends Component {
    constructor(props) {
        super(props);
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
    }

    state = {
        username: '',
        password: '',
        invalidLogin: false,
        loginUser:  localStorage.getItem("user")
    }

    onSubmit = (event) => {
        
        let concetion = `https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/user/${this.state.username}`;
        event.preventDefault();
        axios.get(concetion, {
            params: {
               // username: this.state.username,
                password: this.state.password
            }
        })
        .then(response => {
            if (response.data != null) {
                console.log("if");
                this.props.onLogin( localStorage.setItem('user', JSON.stringify(response.data.users)));
            } else {
                console.log("hit");
                this.setState({ username: '', password: '', invalidLogin: true });
            }
        })
        .catch(error => {
            console.log(error);
        });   
    }

    

    render() {
        if (this.state.loginUser != null) {
            return (<Redirect exact to="/" />);
        } else {
            return (
                <div className="Login topMargin">
                    <h5>Don't have an account? <Link to="/signUp">Sign Up</Link></h5>
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
                    <div className="create">
        <h3>create</h3>
      <button theme="pink" onClick={this.getUser}>
          demo button
        </button>  
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

    getUser() {

        
        //
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
}