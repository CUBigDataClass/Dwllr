import React from 'react';
import { render } from 'react-dom';
import { createStore } from 'redux';
import rootReducer from './reducers';
import Root from './components/Root';
import './index.css';
import registerServiceWorker from './registerServiceWorker';

const store = createStore(rootReducer);

render(
  <Root store={store} />,
  document.getElementById('root')
);

registerServiceWorker();
