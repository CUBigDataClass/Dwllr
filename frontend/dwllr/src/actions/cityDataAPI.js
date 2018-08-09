import { makeFetchAction } from 'redux-api-call';
import { createSelector } from 'reselect';
import { flow, get, filter } from 'lodash/fp';

export const cityDataAPI = makeFetchAction('FETCH_CITY_DATA', (zip) => ({ 
  endpoint: `http://localhost:3000/api/search?city=${zip}`
}));
 
export const cityDataSelector = flow(cityDataAPI.dataSelector, get('data'));
