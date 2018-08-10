import React from 'react';
import SimilarCities from './city-result-cards/SimilarCities';
import '../styles/AttributeSearchResult.css';

export default class AttributeSearchResult extends React.Component {
  
  render() {
    console.log('attr results: ', this.props);
    return (
      <div id="AttributeSearchResult">
        <h1>Closest Matching Cities</h1>
        <SimilarCities zips={this.props.attributeSearchResults} />
      </div>
    );
  }
}
