import React from 'react';
import CityResultCard from './CityResultCard';
import { BarChart } from 'react-easy-chart';

export default class Gender extends React.Component {
  render() {
    console.log('gender: ', this.props);
    return (
      <CityResultCard name="Gender" type="bar-chart">
        <BarChart 
          axes
          colorBars
          width={478}
          height={150}
          yTickNumber={3}
          data={[
            {x: 'male', y: typeof this.props.male === 'undefined' ? 0 : this.props.male},
            {x: 'female', y: typeof this.props.female === 'undefined' ? 0 : this.props.female},
          ]} 
        />
      </CityResultCard>
    );
  }
}
