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
        <Route exact path="/results" component={Yetti} />
      </div>
    );
  }
}

class Yetti extends React.Component {
  
  state = {
    hide: true,
  }
  
  componentDidMount() {
    setTimeout(() => {
      this.setState({hide: false})
    }, 1500);
  }
  
  render() {
    return (
      <img 
        src="https://i.imgur.com/y4wcil5.png" 
        style={{ position: 'absolute', zIndex:11, left:435, top:118, width: '30px', height: '30px', transition: '0.5s ease' }}
        className={this.state.hide ? 'hide-yetti' : 'show-yetti'}
      />
    )
  }
}

export default App;
