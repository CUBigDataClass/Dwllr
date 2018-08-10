import React from 'react';
import Logo from './Logo';
import SearchByAttributeContainer from '../containers/SearchByAttributeContainer';
import SearchByCityContainer from '../containers/SearchByCityContainer';
import MapOverlay from './MapOverlay';
import CustomMapContainer from '../containers/CustomMapContainer';

export default class PersistentUI extends React.Component {
  render() {
    const pathname = this.props.location.pathname;
    const onResultsPage = pathname.includes('results') ? true : false;
    
    return (
      <React.Fragment>
        <Logo onResultsPage={onResultsPage} />
        <SearchByAttributeContainer onResultsPage={onResultsPage} history={this.props.history} />
        <SearchByCityContainer onResultsPage={onResultsPage} />
        <MapOverlay onResultsPage={onResultsPage} />
        <CustomMapContainer onResultsPage={onResultsPage}/>
      </React.Fragment>
    )
  }
}