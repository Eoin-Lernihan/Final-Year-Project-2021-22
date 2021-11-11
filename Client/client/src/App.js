import logo from './logo.svg';
import './App.css';
import navbar from "./components/navbar";
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
import signUp from "src/components/signUp.js";
import Home from "src\components\Home.js";
import Login from "src\components\login.js";
import create from "src\components\create.js";


function App() {
  
  return (
    <div className="App">
      <header className="App-header">
      <Router>
      <navbar />
      <Routes >
      <Route path='/' exact component={Home} />
        <Route path='/cats' component={Login} />
        <Route path='/sheeps' component={signUp} />
        <Route path='/goats' component={create} />
      </Routes >
    </Router>
        <h1>
         MAJI TOURAMENTS
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
