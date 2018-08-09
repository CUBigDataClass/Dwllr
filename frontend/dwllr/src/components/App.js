import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import CustomMapContainer from '../containers/CustomMapContainer';
import PersistentUI from './PersistentUI';
import Divider from './Divider';
import '../styles/App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Route path="/(|results)" component={PersistentUI} />
        <Route exact path="/" component={Divider}/>
        <CustomMapContainer />
      </div>
    );
  }
}

export default App;
