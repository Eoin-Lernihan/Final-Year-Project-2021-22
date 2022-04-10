import React, { Component } from 'react';
import {OneTouramentJoin} from './oneTouramentJoin';

export class TournamentLoaderFlase extends Component {
    
    render() { return  this.props.tournamentsGamesFlase.map((touraments) => {
       
            return(
            <div>
              <OneTouramentJoin tourament={touraments} ReloadData={this.props.ReloadData}  >
                  </OneTouramentJoin>
            </div>
            )
        ;
        })
    
    };
}
