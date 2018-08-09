import App from '../components/App';
import { connect } from 'react-redux';
import { cityDataSelector } from '../action/cityDataAPI';

const mapStateToProps = (state, ownProps) => {
  return {
    ...ownProps,
    cityData: cityDataSelector(state),
  }
}

export default connect(mapStateToProps)(App);