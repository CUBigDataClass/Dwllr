import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { CSSTransition } from 'react-transition-group';
import PersistentUI from './PersistentUI';
import Divider from './Divider';
import CityResultContainer from '../containers/CityResultContainer';
import AttributeSearchResultContainer from '../containers/AttributeSearchResultContainer';
import '../styles/App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Route path="/(|results|results/attribute)" component={PersistentUI} />
        <Route exact path="/" component={Divider} />
        <Route exact path="/results" component={CityResultContainer} />
        <Route path="/results/attribute" component={AttributeSearchResultContainer}/>
      </div>
    );
  }
}

export default App;
