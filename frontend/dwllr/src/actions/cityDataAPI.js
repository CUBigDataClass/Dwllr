import { makeFetchAction } from 'redux-api-call';
import { createSelector } from 'reselect';
import { flow, get, filter } from 'lodash/fp';

export const cityDataAPI = makeFetchAction('FETCH_ZIP', (zip) => ({ 
  endpoint: `/api/search?city=${zip}`
}));
 
export const cityDataSelector = flow(cityDataAPI.dataSelector, get('data'));

//export const zipSelector = createSelector(resultsSelector, results => {
//
  //if (typeof results === 'undefined') return null;
//  
  //const resultKeys = Object.keys(results);
//  
  //for (let i = 0; i < resultKeys.length; i++) {
    //const result = results[resultKeys[i]];
    //if (result.types.includes('postal_code')) {
      //for (let j = 0; j < result.address_components.length; j++) {
        //const component = result.address_components[j];
        //if (component.types.includes('postal_code')) {
          //let zip = component.long_name;
          //console.log('latlng zip: ', zip);
          //return zip;
        //}
      //}
    //}
  //}
//});
