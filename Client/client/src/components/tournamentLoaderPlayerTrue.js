import React, { Component } from 'react';
import {InTournament} from './inTournament';
export class TournamentLoaderTrue extends Component {
    

    render() { return  this.props.tournamentsGamesTrue.map((touraments) => {
       return(
        <div>
          <InTournament tourament={touraments} >
          </InTournament>
        </div>
        )
    ;
    })

};
}