import { makeFetchAction } from 'redux-api-call';
import { createSelector } from 'reselect';
import { flow, get, filter } from 'lodash/fp';
import { FETCH_ATTRIBUTE_SEARCH_RESULTS } from '../constants/actionTypes';

export const attributeSearchAPI = makeFetchAction(FETCH_ATTRIBUTE_SEARCH_RESULTS, params => {
  // reduce would be better ?
  let endpoint = `http://localhost:3000/api/search?`;
  for (let paramName in params) {
    if (params.hasOwnProperty(paramName)) {
      endpoint += `&${paramName}=${params[paramName]}`;
    }
  }
  return {
    endpoint,
  }
});

export const attributeSearchResultsSelector = flow(attributeSearchAPI.dataSelector, get('cities'));
