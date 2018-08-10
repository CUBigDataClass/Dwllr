import React from 'react';
import {
  CSSTransition,
  TransitionGroup,
} from 'react-transition-group';
import CityResultHeader from './CityResultHeader';
import CityResultCard from './city-result-cards/CityResultCard';

import SimilarCities from './city-result-cards/SimilarCities';
import Population from './city-result-cards/Population';
import Economy from './city-result-cards/Economy';
import Gender from './city-result-cards/Gender';

import '../styles/CityResult.css';

export default class CityResult extends React.Component {
  
  state = {
    showHeader: false
  }
  
  componentDidMount() {
    this.setState({showHeader:false});
    setTimeout(() => {
      this.setState({showHeader:true})
    }, 0);
  }
  
  componentDidUpdate(prevProps) {
  }
  
  render() {
    console.log('result props: ', this.props);
    
    const similarCities = this.props.cityData && this.props.cityData.similarCities;
    const stats = this.props.cityData && this.props.cityData.stats;
    
    return (
      <div id="CityResult">
        <TransitionGroup className="city-results">
        
          <CityResultHeader name={this.props.name} zip={this.props.zip} className={this.state.showHeader ? 'show-header' : 'hide-header'}/>
        
          <CityResultCard name="Similar Cities">
            <SimilarCities zips={similarCities} />
          </CityResultCard>
          <Population population={stats && stats.population} />
          <Economy 
            avgIncome={stats && stats.averageincome}
            medRent={stats && stats.medianrent}
            unemployment={stats && stats.unemployment}
          />
          <Gender male={stats && stats.malePercentage} female={stats && stats.femalePercentage}/>
          
        </TransitionGroup>
      </div>
    )
  }
}