import CityResult from '../components/CityResult';
import { connect } from 'react-redux';
import { zipSelector } from '../actions/latlngToZipAPI';
import { cityDataSelector} from '../actions/cityDataAPI';

const mapStateToProps = (state, ownProps) => {
  return {
    ...ownProps,
    name: state.citySearchSubmitted.name,
    zip: zipSelector(state),
    cityData: cityDataSelector(state),
  }
}

export default connect(mapStateToProps)(CityResult);
