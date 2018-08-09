import { createStore, applyMiddleware } from 'redux';
import { middleware as apiMiddleware } from 'redux-api-call';
import { composeWithDevTools } from 'redux-devtools-extension';
import { routerMiddleware } from 'react-router-redux';
import createHistory from 'history/createHashHistory';
import rootReducer from './reducers';

const history = createHistory();

const store = createStore(
  rootReducer, 
  undefined, 
  composeWithDevTools(
    applyMiddleware(
      routerMiddleware(history),
      apiMiddleware,
    )
  )
);

export default store;
export { history };
