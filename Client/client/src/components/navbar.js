import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import { Home } from './home';
import { Create } from './create';
import { Login } from './login';
import { SignUp } from './signUp';
import { User } from './user';
import { LogOut } from './logOut';
import { Tournaments } from './tournaments';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Form from 'react-bootstrap/Form';

//import NavDropdown from 'react-bootstrap/NavDropdown';
//import Form from 'react-bootstrap/Form';

export class NavigationBar extends Component {
    state = {  loginUser:  localStorage.getItem("user") }

    handleSelect = (eventKey) => {
        if (eventKey === "Logout") {
            this.props.handleLogout();
        }
    }

    render() {
        return (
            <div className="NavigationBar">
                <BrowserRouter>
                    <Navbar className="d-flex align-items-center" bg="dark" variant="dark" sticky="top" expand="md">
                        <Container>
                            <Navbar.Brand href="/">Tournaaro</Navbar.Brand>
                            <Navbar.Toggle aria-controls="basic-navbar-nav" />
                            <Navbar.Collapse className="justify-content-start">
                                <Nav className="NavLinks">
                                    <Nav.Link href="/">Home</Nav.Link>
                                    <Nav.Link href="/newTournament">Create Tournament</Nav.Link>
                                    <Nav.Link href="/tournaments" >Tournament</Nav.Link>                                   
                                </Nav>
                            </Navbar.Collapse>
                            <Navbar.Collapse className="justify-content-end" color='white'>
                            {this.LoggedInOrNot()}
                            
                            
                            
                           
                            </Navbar.Collapse>
                        </Container>
                    </Navbar>
					<Switch>
                        <Route path="/" component={Home} exact></Route>

                        <Route path="/newTournament" component={Create} exact></Route>

                        <Route path="/tournaments" component={Tournaments} exact></Route>
                        <Route path="/user" component={User} exact></Route>
                        <Route path="/logOut" component={LogOut} exact></Route>
                        <Route  path="/signUp" component={SignUp} exact></Route>
                        <Route
                             path="/login" exact 
                             render={() => (
                                 <Login onLogin={this.props.handleLogin} username={this.props.username} />
                        )}
                            //path="/login" component={Login} exact
                            >
                        </Route>
                       
						</Switch>
                </BrowserRouter>
            </div>

            
        );
    }
    LoggedInOrNot() {
        if (this.state.loginUser != null) {
            return (
                <Nav onSelect={this.handleSelect}>
                    <Nav.Link href="/user" >User</Nav.Link>
                            <Nav.Link href="/logOut" >Log Out</Nav.Link>
                 </Nav>
            );
        } else {
            return (
                <Nav>
                    <Nav.Link href="/login" >Login</Nav.Link>
                    <Nav.Link href="/signUp" >Sing Up</Nav.Link>
                </Nav>
            );
        }
    }   

    onChangeSearchTerm = (e) => { this.setState({ searchTerm: e.target.value }); }
}