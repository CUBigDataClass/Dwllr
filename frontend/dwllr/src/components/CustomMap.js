import React, { PureComponent } from 'react';
import { compose, withProps } from 'recompose';
import { withScriptjs, withGoogleMap, GoogleMap, Marker } from "react-google-maps";
import '../styles/CustomMap.css';

const defaultCoords = {lat: 40.0902, lng: -98.7129}

const ComposedMap = compose(
  withProps({
    googleMapURL: "null",
    loadingElement: <div style={{ height: `400px`, width: `400px`, background: 'green', color: '#fff' }}>LOADING!</div>,
    containerElement: <div style={{ height: `100%` }} />,
    mapElement: <div style={{ height: `100%` }} />,
  }),
  withScriptjs,
  withGoogleMap
)(props => 
  <GoogleMap
    defaultZoom={5}
    defaultCenter={defaultCoords}
    ref={props.onMapMounted}
    zoom={props.zoom}
  >
    {props.isMarkerShown && <Marker position={props.markerLocation} animation={2} />}
  </GoogleMap>
);

export default class CustomMap extends PureComponent {
  
  state = {
    isMarkerShown: false,
    zoom: 5,
  }
  
  updateLocation(coords) {
    this.mapRef.panTo(coords);
  }
  
  componentDidUpdate(prevProps) {
    console.log('map props: ', this.props);
    if (prevProps.onResultsPage === false && this.props.onResultsPage) {
      setTimeout(() => {
        this.setState((prevState, props) => ({
          zoom: 6.45,
          isMarkerShown: true,
        }));
        this.updateLocation(this.props.coords);
      }, 300);
    }
    else if (prevProps.onResultsPage && !this.props.onResultsPage) { 
      setTimeout(() => {
        this.setState((prevState, props) => ({
          zoom: 5,
          isMarkerShown: false,
        }));
        this.updateLocation(defaultCoords);
      }, 100);
    }
    else if (prevProps.coords !== this.props.coords) {
      this.updateLocation(this.props.coords);
    }
  }
  
  setRef = ref => {
    this.mapRef = ref;
  }
  
  render() {
    return (
      <ComposedMap 
        onMapMounted={ref => this.mapRef=ref} 
        zoom={this.state.zoom} 
        isMarkerShown={this.state.isMarkerShown} 
        markerLocation={this.props.coords}
      />
    )
  }
}