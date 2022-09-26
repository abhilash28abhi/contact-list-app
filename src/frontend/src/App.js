import React, { Component } from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import {Routes} from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import ContactList from "./components/ContactList"

class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <a href="/contacts" className="navbar-brand">
              Contact List App
            </a>
          </nav>

          <div className="container mt-3">
            <Routes>
              <Route exact path={"/"} element={<ContactList/>} />
              <Route exact path={"/contacts"} element={<ContactList/>} />
            </Routes>
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
