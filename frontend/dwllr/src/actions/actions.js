export const CITY_SEARCH_SUBMITTED = 'STORE_PLACE';

export function citySearchSubmitted(name, coords) {
  return { type: CITY_SEARCH_SUBMITTED, place: {name, coords}};
}