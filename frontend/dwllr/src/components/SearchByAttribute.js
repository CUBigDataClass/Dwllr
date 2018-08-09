import React from 'react';
import '../styles/SearchByAttribute.css';

export default class SearchByAttribute extends React.Component{
  
  state = {
    width: 0,
    height: 0,
  }

  componentDidMount() {
    this.updateWindowDimensions = this.updateWindowDimensions.bind(this);
    this.updateWindowDimensions();
    window.addEventListener('resize', this.updateWindowDimensions);
  }

  componentWillUnmount() {
    window.removeEventListener('resize', this.updateWindowDimensions);
  }
  
  updateWindowDimensions() {
    this.setState({ width: window.innerWidth, height: window.innerHeight });
  }
  
  render () {
    const onResultsPage = this.props.onResultsPage;
    const translateData = {transform: `translate(-${this.state.width/4 - 255}px, -185px)`};
    console.log(translateData);
    return (
      <div 
        id="SearchByAttribute"
        className={onResultsPage ? 'onResultsPage' : ''}
        style={onResultsPage ?  translateData : {}}
      >
        <h4>Know what makes up your perfect place?</h4>
        <h3>Find the closest matching cities:</h3>
        <div className="form-container">
          <div className="input-group">
            <label>Population <span className="secondary-label"># people</span></label>
            <input placeholder="Enter a number" />
          </div>

          <div className="input-group">
            <label>Average Rent <span className="secondary-label">$ per month</span></label>
            <input placeholder="Enter a number" />
          </div>

          <div className="input-group">
            <label>Average Income <span className="secondary-label">$ per year</span></label>
            <input placeholder="Enter a number" />
          </div>

          <div className="input-group">
            <label>Unemployment <span className="secondary-label">% of people</span></label>
            <input placeholder="Enter a number" />
          </div>

          <button>
            {
              onResultsPage ? 
              'Discover the closest matching cities' :
              'Find cities'
            }
          <div className="send-arrow"></div></button>
        </div>
      </div>
    )
  }
}