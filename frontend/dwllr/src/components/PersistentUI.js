import React from 'react';
import Logo from './Logo';
import SearchByAttribute from './SearchByAttribute';
import SearchByCity from './SearchByCity';
import MapOverlay from './MapOverlay';

export default class PersistentUI extends React.Component {
  render() {
    const onHomePage = this.props.location.pathname !== 'search' ? true : false;
    
    return (
      <React.Fragment>
        <div>PersistentUI on {this.props.location.pathname}</div>
        <Logo />
        <SearchByAttribute onHomePage={onHomePage} />
        <SearchByCity onHomePage={onHomePage} />
        <MapOverlay />
      </React.Fragment>
    )
  }
}