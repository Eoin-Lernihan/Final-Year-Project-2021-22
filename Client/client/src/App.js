import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import { BrowserRouter as Router, Switch, Route  } from 'react-router-dom';

/** 
import { Navbar } from 'react-bootstrap'
import { NavDropdown } from 'react-bootstrap'
import { Nav } from 'react-bootstrap'
import { Container } from 'react-bootstrap'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

*/
import Home from "./pages/Home.js"
import Login from "./pages/login.js"
import SignUp from "./pages/signUp.js"
import create from './pages/create.js';


function App() {
  return (
    <Router>
      <navbar />
      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/signUp" component={Login} />
        <Route path="/login" component={SignUp} />
        <Route path="/create" component={create} />

      </Switch>
    </Router>
    
  );
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

