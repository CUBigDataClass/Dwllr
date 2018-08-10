import React from 'react';
import '../styles/SearchByAttribute.css';

export default class SearchByAttribute extends React.Component{
  
  state = {
    width: 0,
    height: 0,
    population: '',
    rent: '',
    income: '',
    unemployment: '',
  }

  componentDidMount() {
    this.updateWindowDimensions = this.updateWindowDimensions.bind(this);
    this.updateWindowDimensions();
    window.addEventListener('resize', this.updateWindowDimensions);
  }
  
  componentDidUpdate(prevProps) {
    if (this.props.attributeSearchResults !== null && prevProps.attributeSearchResults !== this.props.attributeSearchResults) {
      this.props.history.push(`/results/attribute`);
    }
  }

  componentWillUnmount() {
    window.removeEventListener('resize', this.updateWindowDimensions);
  }
  
  updateWindowDimensions() {
    this.setState({ width: window.innerWidth, height: window.innerHeight });
  }
  
  handlePopulationChange(e) {
    this.setState({
      population: e.target.value,
    });
  }
  
  handleRentChange(e) {
    this.setState({
      rent: e.target.value,
    });
  }
  
  handleIncomeChange(e) {
    this.setState({
      income: e.target.value,
    });
  }
  
  handleUnemploymentChange(e) {
    this.setState({
      unemployment: e.target.value,
    });
  }
  
  submitSearch() {
    this.props.fetchAttributeSearchResults({population: this.state.population});
  }
  
  render () {
    console.log('attr: ', this.props);
    const onResultsPage = this.props.onResultsPage;
    const translateData = {transform: `translate(-${this.state.width/4 - 255}px, -${this.state.height/5 - 2}px)`};
    
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
            <input placeholder="Enter a number" value={this.state.population} onChange={this.handlePopulationChange.bind(this)} />
          </div>

          <div className="input-group">
            <label>Average Rent <span className="secondary-label">$ per month</span></label>
            <input placeholder="Enter a number" value={this.state.rent} onChange={this.handleRentChange.bind(this)}/>
          </div>

          <div className="input-group">
            <label>Average Income <span className="secondary-label">$ per year</span></label>
            <input placeholder="Enter a number" value={this.state.income} onChange={this.handleIncomeChange.bind(this)} />
          </div>

          <div className="input-group">
            <label>Unemployment <span className="secondary-label">% of people</span></label>
            <input placeholder="Enter a number" value={this.state.unemployment} onChange={this.handleUnemploymentChange.bind(this)}/>
          </div>

          <button onClick={this.submitSearch.bind(this)}>
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