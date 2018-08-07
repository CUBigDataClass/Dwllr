import React from 'react';
import '../styles/Divider.css';

const Divider = () => (
  <div id="Divider">
    <div className="vertical-line top-half" />
    <div className="vertical-line bottom-half" />
    <div className="text">or</div>
  </div>
);

export default Divider;