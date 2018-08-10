import React from 'react';
import '../../styles/city-result-cards/CityResultCard.css';

export default class CityResultCard extends React.Component {
  render() {
    return (
      <div id="CityResultCard" className={this.props.type}>
        <h1>{this.props.name}</h1>
        {this.props.children}
      </div>
    );
  }
}