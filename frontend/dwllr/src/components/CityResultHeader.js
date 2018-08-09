import React from 'react';
import '../styles/CityResultHeader.css';

export default class CityResultHeader extends React.Component {
  render() {
    console.log('header props: ', this.props);
    return (
      <div id="CityResultHeader">
        <h1>
          {this.props.name && this.props.name.slice(0,-5).replace(/[0-9]/g, '')}
          <span className="zip">{this.props.zip && this.props.zip}</span>
        </h1>
      </div>
    )
  }
}