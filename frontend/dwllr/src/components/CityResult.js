import React from 'react';
import CityResultHeader from './CityResultHeader';
import CityResultCard from './city-result-cards/CityResultCard';
import SimilarCities from '../components/city-result-cards/SimilarCities';
import '../styles/CityResult.css';

export default class CityResult extends React.Component {
  
  render() {
    console.log('result props: ', this.props);
    const cityData = this.props.cityData;
    
    return (
      <div id="CityResult">
        <CityResultHeader name={this.props.name} zip={this.props.zip}/>
        <CityResultCard name="Similar Cities">
          <SimilarCities zips={cityData && cityData.similarCities} />
        </CityResultCard>
      </div>
    )
  }
}