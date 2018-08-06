import React from 'react';
import { render } from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import { routerMiddleware } from 'react-router-redux';
import createHistory from 'history/createHashHistory';
import rootReducer from './reducers';
import Root from './components/Root';
import './index.css';
import registerServiceWorker from './registerServiceWorker';

const history = createHistory();

const store = createStore(
  rootReducer, 
  undefined, 
  composeWithDevTools(
    applyMiddleware(routerMiddleware(history))
  )
);

const rootProps ={
  store,
  history,
}

render(
  <Root {...rootProps} />,
  document.getElementById('root')
);

registerServiceWorker();
