import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import CustomMap from './CustomMap';
import './App.css';

const Home = () => (
  <div>react router on home page</div>
)

const Search = () => (
  <div>react router on search page</div>
)

class App extends Component {
  render() {
    return (
      <div className="App">
        hello
        <Route exact path="/" component={Home} />
        <Route exact path="/search" component={Search} />
        <CustomMap />
      </div>
    );
  }
}

export default App;
