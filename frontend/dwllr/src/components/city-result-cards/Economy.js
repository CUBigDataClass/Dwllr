import React from 'react';
import CityResultCard from './CityResultCard';
import numberWithCommas from '../../utils/numberWithCommas';
import '../../styles/city-result-cards/Economy.css';

export default class Economy extends React.Component {
  
  render() {
    return (
      <CityResultCard name="Economy">
        <div id="Economy">
          <div className="third">
            <h1><span className="data">$</span>{this.props.avgIncome && numberWithCommas(this.props.avgIncome) }</h1>
            <h2 className="sub">average income</h2>
          </div>
          <div className="third">
            <h1><span className="data">$</span>{this.props.medRent && numberWithCommas(this.props.medRent) }</h1>
            <h2 className="sub">median rent</h2>
          </div>
          <div className="third">
            <h1><span className="data"></span>{ this.props.unemployment }<span style={{paddingLeft: '4px', color: 'rgba(0,0,0,0.58)'}}>%</span></h1>
            <h2 className="sub">unemployment</h2>
          </div>
        </div>
      </CityResultCard>
    );
  }
}