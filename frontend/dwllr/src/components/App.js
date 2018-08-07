import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import CustomMapContainer from '../containers/CustomMapContainer';
import PersistentUI from './PersistentUI';
import '../styles/App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        hello
        <Route exact path="/(|search)" component={PersistentUI} />
        <CustomMapContainer />
      </div>
    );
  }
}

export default App;
