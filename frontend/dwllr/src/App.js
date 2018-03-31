import React, { Component } from 'react';

import axios from 'axios';

import './App.css';

class App extends Component {

  constructor() {
    super();
    this.state = {
      apiURL: '/search',
      apiParams: {city: "80303"},
      response: 'api not called yet',
      err: 'api not called yet'
    }
    this.callAPI = this.callAPI.bind(this);
  }

  callAPI() {
    axios.get(this.state.apiURL)
    .then(res => {
      this.setState({ err: false, response: JSON.stringify(res.data) });
    })
    .catch(err => {
      this.setState({ err: true, response: JSON.stringify(err) });
    })
  }

  render() {
    return (
      <div className="display-linebreak padding">
        <h1>Dwllr{'\n\n'}</h1>
        <b>URL being called:</b> {'\n'} {this.state.apiURL} {'\n\n\n'}
        <b>with params:</b> {'\n'} {JSON.stringify(this.state.apiParams)} {'\n\n\n'}
        <b>Error:</b> {'\n\n'}
        {this.state.err.toString()} {'\n\n\n'}
        <b>Response:</b> {'\n\n'}
        {this.state.response} {'\n\n\n\n'}
        <button onClick={this.callAPI}>
          Call API
        </button>
      </div>

    );
  }
}

export default App;
