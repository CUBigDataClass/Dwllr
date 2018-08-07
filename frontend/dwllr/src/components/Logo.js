import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Logo.css';

export default class Logo extends React.Component {
  render () {
    return (
      <Link to="/" id="Logo" className={this.props.onSearchPage ? 'onSearchPage' : ''}>
        <h1>Dwllr.</h1>
        <h2>Find your perfect place</h2>
      </Link>
    )
  }
}