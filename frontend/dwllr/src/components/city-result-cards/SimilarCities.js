import React from 'react';
import SimilarCityContainer from '../../containers/SimilarCityContainer';

export default class SimilarCities extends React.Component {
  render() {
    return (
      <div id="SimilarCities" style={{ marginTop: '-16px', paddingLeft: '4px' }}>
        {this.props.zips && this.props.zips.map((zip, i) => <SimilarCityContainer zip={zip} key={i} /> )}
      </div>
    )
  }
}