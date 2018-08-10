import SimlarCity from '../components/city-result-cards/SimilarCity';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { cityDataAPI } from '../actions/cityDataAPI';
import { zipAPI } from '../actions/latlngToZipAPI.js';
import { citySearchSubmitted } from '../actions/actions';

const mapStateToProps = (state, ownProps) => {
  return {
    ...ownProps,
    isZipFetching: zipAPI.isFetchingSelector(state),
  }
}

const mapDispatchToProps = (dispath) => {
  return bindActionCreators({
    fetchZip: zipAPI.actionCreator,
    fetchCityData: cityDataAPI.actionCreator,
    citySearchSubmitted,
  }, dispath);
};

export default connect(mapStateToProps, mapDispatchToProps)(SimlarCity);