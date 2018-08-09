import React from 'react';
import Autocomplete from 'react-google-autocomplete';
import { withRouter } from 'react-router-dom';
import '../styles/SearchByCity.css';

class SearchByCity extends React.Component {
  
  state = {
    width: 0,
    height: 0,
  }

  componentDidMount() {
    this.updateWindowDimensions = this.updateWindowDimensions.bind(this);
    this.updateWindowDimensions();
    window.addEventListener('resize', this.updateWindowDimensions);
  }

  componentWillUnmount() {
    window.removeEventListener('resize', this.updateWindowDimensions);
  }
  
  componentDidUpdate(prevProps) {
    console.log('props: ', this.props);
    // move this route change to App.js
    if (this.props.zip !== null && prevProps.zip !== this.props.zip) {
      this.props.history.push(`/results`);
    }
    if (
      !this.props.isZipFetching && 
      this.props.zip != null &&
      prevProps.zip !== this.props.zip
    ) {
      // fetch city data from custom api
      this.props.fetchCityData(this.props.zip);
    }
  }
  
  updateWindowDimensions() {
    this.setState({ width: window.innerWidth, height: window.innerHeight });
  }
  
  citySearchSubmitted(place) {
    const coords = {
      lat: place.geometry.location.lat(), 
      lng: place.geometry.location.lng()
    }
    this.props.citySearchSubmitted(place.formatted_address, coords);
    this.props.fetchZip(coords);
  }
  
  render () {
    const onResultsPage = this.props.onResultsPage;
    const translateData = {transform: `translate(-${(3*this.state.width)/4 - 255}px, ${278}px)`};
    
    return (
      <div 
        id="SearchByCity" 
        className={onResultsPage ? 'onResultsPage' : ''}
        style={onResultsPage ? translateData : {}}
      >
        <h4>Already fond of a particular city?</h4>
        <h3>Find a city by name:</h3>
        <div className="autocomplete-container">
          <Autocomplete className="autocomplete"
            onPlaceSelected={place => this.citySearchSubmitted(place)} 
          />
        </div>
      </div>
    )
  }
}

export default withRouter(SearchByCity);