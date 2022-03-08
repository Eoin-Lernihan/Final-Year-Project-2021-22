import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
//
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import { Home } from './Home';
import { Create } from './create';
import { Login } from './login';
import { signUp } from './signUp';

                      



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
						<Route path="/login" component={Login} exact></Route>
                        <Route path="/signUp" component={signUp} exact></Route>
						</Switch>
                </BrowserRouter>
            </div>
        );
    }

   

    onChangeSearchTerm = (e) => { this.setState({ searchTerm: e.target.value }); }
}