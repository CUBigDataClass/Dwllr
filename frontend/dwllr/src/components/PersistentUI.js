import React from 'react';
import Logo from './Logo';
import SearchByAttribute from './SearchByAttribute';
import SearchByCity from './SearchByCity';
import MapOverlay from './MapOverlay';

export default class PersistentUI extends React.Component {
  render() {
    console.log(this.props);
    const onSearchPage = this.props.location.pathname === '/search' ? true : false;
    
    return (
      <React.Fragment>
        <Logo onSearchPage={onSearchPage} />
        <SearchByAttribute onSearchPage={onSearchPage} />
        <SearchByCity onSearchPage={onSearchPage} />
        <MapOverlay onSearchPage={onSearchPage} />
      </React.Fragment>
    )
  }
}