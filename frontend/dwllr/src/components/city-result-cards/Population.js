import React from 'react';
import CityResultCard from './CityResultCard';
import numberWithCommas from '../../utils/numberWithCommas';
import '../../styles/city-result-cards/Population.css';

export default class Population extends React.Component {
  render() {
    return (
      <CityResultCard name="Population">
        <h1 id="Population">{this.props.population && numberWithCommas(this.props.population) }<span className="sub">people</span></h1>
      </CityResultCard>
    );
  }
}