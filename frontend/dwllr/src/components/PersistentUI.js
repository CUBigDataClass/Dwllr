import React from 'react';
import Logo from './Logo';
import SearchByAttribute from './SearchByAttribute';
import SearchByCityContainer from '../containers/SearchByCityContainer';
import MapOverlay from './MapOverlay';

export default class PersistentUI extends React.Component {
  render() {
    const onResultsPage = this.props.location.pathname === '/results' ? true : false;
    
    return (
      <React.Fragment>
        <Logo onResultsPage={onResultsPage} />
        <SearchByAttribute onResultsPage={onResultsPage} />
        <SearchByCityContainer onResultsPage={onResultsPage} />
        <MapOverlay onResultsPage={onResultsPage} />
      </React.Fragment>
    )
  }
}