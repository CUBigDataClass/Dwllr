import React, { PureComponent } from 'react';
import { compose, withProps } from 'recompose';
import { withScriptjs, withGoogleMap, GoogleMap, Marker } from "react-google-maps";
import '../styles/CustomMap.css';

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
    defaultCenter={{lat: 40.0902, lng: -98.7129}}
    ref={props.onMapMounted}
  >
    {props.isMarkerShown && <Marker position={{ lat: -34.397, lng: 150.644 }} onClick={props.onMarkerClick} />}
  </GoogleMap>
);

export default class CustomMap extends PureComponent {
  state = {
    isMarkerShown: false,
  }
  
  componentDidMount() {
  }
  
  pan = () => {
    setTimeout(() => {
      console.log('pan');
      //this.mapRef.panTo({lat: 44.0902, lng: -102.7129});
    }, 3000);
  }
  
  setRef = ref => {
    console.log('ref: ', ref);
    this.mapRef = ref;
    this.pan();
    //this.mapRef.setOptions({draggable: false});
  }
  
  render() {
    return (
      <ComposedMap onMapMounted={this.setRef} isMarkerShown={this.state.isMarkerShown} panTo={this.state.panLoc}/>
    )
  }
}