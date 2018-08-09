import SearchByCity from '../components/SearchByCity';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { zipAPI, zipSelector } from '../actions/latlngToZipAPI';
import { cityDataAPI, cityDataSelector } from '../actions/cityDataAPI';
import { citySearchSubmitted } from '../actions/actions';

const {
  isFetchingSelector,
} = zipAPI;

const mapStateToProps = (state, ownProps) => {
  return {
    ...ownProps,
    zip: zipSelector(state),
    isZipFetching: isFetchingSelector(state),
    cityData: cityDataSelector(state),
}};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators({ 
    fetchZip: zipAPI.actionCreator,
    fetchCityData: cityDataAPI.actionCreator,
    citySearchSubmitted,
  }, dispatch);
};

export default connect(mapStateToProps, mapDispatchToProps)(SearchByCity);