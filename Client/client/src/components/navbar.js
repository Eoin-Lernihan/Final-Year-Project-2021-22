import React, { Component } from 'react';
import {  Link } from "react-router-dom";
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import { MainPage } from './mainPage';
import { Store } from './store';
import { Checkout } from './checkout';
import { ProductPage } from './productPage';
import { Cart } from './cart';
import { Login } from './login';
import { Signup } from './signup';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Form from 'react-bootstrap/Form';

const navbar= () =>{
  return (
    <div>
    <li>
      <Link to="/">Home</Link>
    </li>
    <li>
      <Link to="/create">Create</Link>
    </li>
    <li>
      <Link to="/login">Login</Link>
    </li>
    <li>
      <Link to="/signUp">Sign Up</Link>
    </li>
  </div>
  );
}

export class navbar extends Component {
    state = { searchTerm: '' }

    handleSelect = (eventKey) => {
        if (eventKey === "Logout") {
            this.props.handleLogout();
        }
    }

    render() {
        return (
            <div className="navbar">
                <BrowserRouter>
                    <Navbar className="d-flex align-items-center" bg="dark" variant="dark" sticky="top" expand="md">
                        <Container>
                            <Navbar.Brand href="/"><img src="DigitronLogo.png" alt="Digitron" width="75" height="40"></img></Navbar.Brand>
                            <Navbar.Toggle aria-controls="basic-navbar-nav" />
                            <Navbar.Collapse>
                                <Nav className="NavLinks">
                                    <Nav.Link href="/">Home</Nav.Link>
                                    <Nav.Link href="/cart">Cart</Nav.Link>
                                    <Nav.Link href="/store">Store</Nav.Link>
                                </Nav>

                            </Navbar.Collapse>
                            
                        </Container>
                    </Navbar>
                    <Switch>

                    <Route path="/store" component={Store} exact></Route>
                        <Route path="/" component={MainPage} exact></Route>
                    </Switch>
                </BrowserRouter>
            </div>
        );
    }
