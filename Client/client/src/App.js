

/** 
import { Navbar } from 'react-bootstrap'
import { NavDropdown } from 'react-bootstrap'
import { Nav } from 'react-bootstrap'
import { Container } from 'react-bootstrap'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";


import Home from "./components/Home.js"
import Login from "./pages/login.js"
import SignUp from "./pages/signUp.js"
import create from './pages/create.js';
*/
import React, { Component } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { NavigationBar } from './components/navbar';

class App extends Component {   
  state = {
      user: ''
  }

  handleLogin = (uname) => {
      sessionStorage.setItem("LoggedIn", uname);
      this.setState({ user: sessionStorage.getItem("LoggedIn") });
  }

  handleLogout = () => {
      sessionStorage.removeItem("LoggedIn");
  }

  componentDidMount() {
      this.setState({ user: sessionStorage.getItem("LoggedIn") });
  }

  render() {
      return (
          // JSX (JavaScript XML) - Babel(A JavaScript compiler) converts JSX to JavaScript
          <div className="App">
              <NavigationBar
                  handleLogin={this.handleLogin}
                  handleLogout={this.handleLogout}
                  username={this.state.user}>
              </NavigationBar>
             
          </div>
        
         
      );
  }
}

export default App;


/** 
function App() {
  return (
    <div className="App">
       <Container>
        <Navbar bg="light" sticky="top">
            <img src={logo} height= {100}></img>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
        <Nav.Link href="/">Home</Nav.Link>
        <Nav.Link to="src\components\create.js">Link</Nav.Link>
       
      </Nav>
      </Navbar.Collapse>    
      </Navbar>
      </Container>

      <header className="App-header">
        <h1>
        </h1>
        <p>
          Advertise your touraments here today
          </p>
          <p>

          Keep track of how many players have joined

        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
*/

