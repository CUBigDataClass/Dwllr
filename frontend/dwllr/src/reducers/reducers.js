import { CITY_SEARCH_SUBMITTED } from '../actions/actions';

export function citySearchSubmitted(state = {name: null, coords: null}, action) {
  switch(action.type) {
    case CITY_SEARCH_SUBMITTED:
      return {name: action.place.name, coords: action.place.coords};
    default:
      return state;
  }
}
