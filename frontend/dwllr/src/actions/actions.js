import { CITY_SEARCH_SUBMITTED } from '../constants/actionTypes';

export function citySearchSubmitted(name, coords) {
  return { type: CITY_SEARCH_SUBMITTED, place: {name, coords}};
}