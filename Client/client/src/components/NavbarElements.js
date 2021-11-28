import React, { Component } from 'react';
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
import { Cart4 } from 'react-bootstrap-icons';

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
                            <Navbar.Brand href="/"><img src="DigitronLogo.png" alt="Digitron" width="75" height="40"></img></Navbar.Brand>
                            <Navbar.Toggle aria-controls="basic-navbar-nav" />
                            <Navbar.Collapse>
                                <Nav className="NavLinks">
                                    <Nav.Link href="/">Home</Nav.Link>
                                    <Nav.Link href="/cart">Cart</Nav.Link>
                                    <Nav.Link href="/store">Store</Nav.Link>
                                </Nav>
                                <Form id="leftMargin" inline>
                                    <Form.Control type="input" value={this.state.searchTerm} onChange={this.onChangeSearchTerm} placeholder="Product Search" />
                                    <Link to={"/store/" + this.state.searchTerm} className="btn btn-success" id="navbarButton">Search</Link>
                                </Form>
                            </Navbar.Collapse>
                             <Navbar.Collapse className="justify-content-end">
                                {this.displayUserOrGuest()}
                            </Navbar.Collapse>
                        </Container>
                    </Navbar>
                    <Switch>
                    <Route path="/store" component={Store} exact></Route>
                        <Route path="/" component={MainPage} exact></Route>
                        <Route path="/store" component={Store} exact></Route>
                        <Route path="/store/:search" component={Store} exact></Route>
                        <Route path="/view_product/:id" component={ProductPage} exact></Route>
                        <Route path="/cart" component={Cart} exact></Route>
                        <Route path="/checkout" component={Checkout} exact></Route>
                        <Route 
                            path="/signup" exact 
                            render={() => (
                                <Signup onLogin={this.props.handleLogin} />
                            )}>
                        </Route>
                        <Route
                            path="/login" exact 
                            render={() => (
                                <Login onLogin={this.props.handleLogin} username={this.props.username} />
                            )}>
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
                        <NavDropdown.Item href="/store" eventKey="Logout">Logout</NavDropdown.Item>
                    </NavDropdown>
                    <Nav.Link href="/"><Cart4 color="white" size={24}></Cart4></Nav.Link>
                </Nav>
            );
        } else {
            return (
                <Nav>
                    <Link to="/signup" className="btn btn-outline-light" id="navbarButton">Sign up</Link>
                    <Link to="/login" className="btn btn-outline-light" id="navbarButton">Login</Link>
                </Nav>
            );
        }
    }

    onChangeSearchTerm = (e) => { this.setState({ searchTerm: e.target.value }); }
}