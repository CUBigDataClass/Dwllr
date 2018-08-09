import React from 'react';
import { connect } from 'react-redux';
import CustomMap from '../components/CustomMap';

const mapStateToProps = (state, ownProps) => ({
  coords: state.citySearchSubmitted.coords,
  ...ownProps,
});

export default connect(mapStateToProps)(CustomMap);