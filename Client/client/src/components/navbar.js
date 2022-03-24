import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import { Home } from './Home';
import { Create } from './create';
import { Login } from './login';
import { signUp } from './signUp';
import { Tournaments } from './tournaments';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Form from 'react-bootstrap/Form';

//import NavDropdown from 'react-bootstrap/NavDropdown';
//import Form from 'react-bootstrap/Form';

export class NavigationBar extends Component {
    state = { searchTerm: '' }

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
                                    <Nav.Link href="/create">Create</Nav.Link>
                                    <Nav.Link href="/tournaments" >Tournament</Nav.Link>                                   
                                </Nav>
                            </Navbar.Collapse>
                            <Navbar.Collapse className="justify-content-end" color='white'>
                            <Nav.Link href="/login" >Login</Nav.Link>
                            <Nav.Link href="/signUp" >Sing Up</Nav.Link>
                           
                            </Navbar.Collapse>
                        </Container>
                    </Navbar>
					<Switch>
                        <Route path="/" component={Home} exact></Route>
                        <Route path="/create" component={Create} exact></Route>
                        <Route path="/tournaments" component={Tournaments} exact></Route>
                        <Route 
                            // path="/signUp" exact 
                            // render={() => (
                                // <signUp onLogin={this.props.handleLogin} />
                            // )}
                            path="/signUp" component={signUp} exact
                        >
                        </Route>
                        <Route
                            // path="/login" exact 
                            // render={() => (
                                // <Login onLogin={this.props.handleLogin} username={this.props.username} />
                            // )}
                            path="/login" component={Login} exact
                            >
                        </Route>
                       
						</Switch>
                </BrowserRouter>
            </div>

            
        );
    }
    displayUserOrGuest() {
        if (this.props.username != null) {
            return (
                <Nav onSelect={this.handleSelect}>
                    <NavDropdown title={this.props.username}>
                        <NavDropdown.Item href="/">Your Orders</NavDropdown.Item>
                        <NavDropdown.Item href="/" eventKey="Logout">Logout</NavDropdown.Item>
                    </NavDropdown>
                                    </Nav>
            );
        } else {
            return (
                <Nav>
                    <Link to="/signUp" className="btn btn-outline-light" id="navbarButton">Sign up</Link>
                    <Link to="/login" className="btn btn-outline-light" id="navbarButton">Login</Link>
                </Nav>
            );
        }
    }   

    onChangeSearchTerm = (e) => { this.setState({ searchTerm: e.target.value }); }
}