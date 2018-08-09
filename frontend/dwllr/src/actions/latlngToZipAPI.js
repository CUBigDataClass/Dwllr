import { makeFetchAction } from 'redux-api-call';
import { createSelector } from 'reselect';
import { flow, get, filter } from 'lodash/fp';

const mapsUrl = 'https://maps.googleapis.com/maps/api/geocode/json';
const key = 'AIzaSyDUphyKwQ4lJqCpQjn8-F_FuGyTxjp7vV8';

export const zipAPI = makeFetchAction('FETCH_ZIP', (params) => ({ 
  endpoint: `${mapsUrl}?latlng=${params.lat},${params.lng}&sensor=false&key=${key}`
}));
 
export const resultsSelector = flow(zipAPI.dataSelector, get('results'));
export const zipSelector = createSelector(resultsSelector, results => {

  if (typeof results === 'undefined') return null;
  
  const resultKeys = Object.keys(results);
  
  for (let i = 0; i < resultKeys.length; i++) {
    const result = results[resultKeys[i]];
    if (result.types.includes('postal_code')) {
      for (let j = 0; j < result.address_components.length; j++) {
        const component = result.address_components[j];
        if (component.types.includes('postal_code')) {
          let zip = component.long_name;
          return zip;
        }
      }
    }
  }
});
