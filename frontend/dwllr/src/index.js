import React from 'react';
import { render } from 'react-dom';
import store, { history } from './configStore';
import Root from './components/Root';
import './index.css';
import registerServiceWorker from './registerServiceWorker';

const rootProps ={
  store,
  history,
}

render(
  <Root {...rootProps} />,
  document.getElementById('root')
);

registerServiceWorker();
