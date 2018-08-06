import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import './App.css';

class Test extends Component {
  render() {
    return (
      <div>
        on search page
      </div>
    );
  }
}

class App extends Component {
  render() {
    return (
      <div className="App">
        hello
        <Route exact path="/search" component={Test} />
      </div>
    );
  }
}

export default App;
