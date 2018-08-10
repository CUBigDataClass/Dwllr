import React from 'react';
import { googleMapsUrl, key } from '../../constants/mapsAPI';
import '../../styles/city-result-cards/SimilarCity.css';

export default class SimilarCity extends React.Component {
  
  state = {
    name: '...',
  }
  
  constructor() {
    super();
    this.updateCurrentCity = this.updateCurrentCity.bind(this);
  }
  
  getDataFromGoogle() {
    fetch(`${googleMapsUrl}?address=${this.props.zip}&key=${key}`)
    .then(res => res.json())
    .then(data => {
      
      const result = data.results[0];
    
      const newName = result.formatted_address.slice(0, -11);
      this.setState({name: newName});
      
      this.city = {
        name: result.formatted_address.slice(0, -6),
        coords: {
          lat: result.geometry.location.lat,
          lng: result.geometry.location.lng
        },
        zip: this.zip
      };
    })
    .catch(err => {
      // silently fail lmao
    });
  }
  
  componentDidMount() {
    this.getDataFromGoogle();
  }
  
  componentDidUpdate(prevProps) {
    if (prevProps.zip !== this.props.zip) {
      this.getDataFromGoogle();
    }
  }
  
  updateCurrentCity() {
    this.props.fetchZip(this.city.coords);
    this.props.citySearchSubmitted(this.city.name, this.city.coords);
    this.props.fetchCityData(this.props.zip);
  }
  
  render() {
    return (
      <div id="SimilarCity" onClick={this.updateCurrentCity}>
        <h1>{ this.state.name }</h1>
        <h2>{ this.props.zip }</h2>
      </div>
    )
  }
}