import SearchByAttribute from '../components/SearchByAttribute';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { attributeSearchAPI, attributeSearchResultsSelector } from '../actions/attributeSearchAPI';

const mapStateToProps = (state, ownProps) => {
  return {
    ...ownProps,
    attributeSearchResults: attributeSearchResultsSelector(state),
  }
};

const mapDispatchToProps = dispatch => {
  return bindActionCreators({
    fetchAttributeSearchResults: attributeSearchAPI.actionCreator,
  }, dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(SearchByAttribute);