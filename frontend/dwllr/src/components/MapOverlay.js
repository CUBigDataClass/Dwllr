import React from 'react';
import '../styles/MapOverlay.css';

export default class MapOverlay extends React.Component {
  render () {
    return (
      <div id="MapOverlay" className={this.props.onResultsPage ? 'onResultsPage' : ''}></div>
    )
  }
}