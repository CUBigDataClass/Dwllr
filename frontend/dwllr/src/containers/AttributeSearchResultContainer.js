import AttributeSearchResult from '../components/AttributeSearchResult';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { attributeSearchAPI, attributeSearchResultsSelector } from '../actions/attributeSearchAPI';

const mapStateToProps = (state, ownProps) => {
  return {
    ...ownProps,
    attributeSearchResults: attributeSearchResultsSelector(state),
  }
}

export default connect(mapStateToProps)(AttributeSearchResult);