import SimlarCity from '../components/city-result-cards/SimilarCity';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { cityDataAPI } from '../actions/cityDataAPI';
import { citySearchSubmitted } from '../actions/actions';

const mapStateToProps = (state, ownProps) => ownProps;

const mapDispatchToProps = (dispath) => {
  return bindActionCreators({
    fetchCityData: cityDataAPI.actionCreator,
    citySearchSubmitted,
  }, dispath);
};

export default connect(mapStateToProps, mapDispatchToProps)(SimlarCity);