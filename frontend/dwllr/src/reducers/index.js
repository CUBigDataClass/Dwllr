import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import {reducers as apiReducers} from 'redux-api-call';
import { citySearchSubmitted } from './reducers';

export default combineReducers({
  routing: routerReducer,
  ...apiReducers,
  citySearchSubmitted
});
