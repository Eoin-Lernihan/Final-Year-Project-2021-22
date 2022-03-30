import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import {InTournament} from './inTournament';
export class TournamentLoaderTrue extends Component {
    

    render() { return  this.props.tournamentsGamesTrue.map((touraments) => {
        touraments.players.indexOf("tim") === -1 ? touraments.players.push("tim") : console.log("This item already exists");
        return(
        <div>
          /* Used employee as props name */
          <InTournament tourament={touraments} >
          </InTournament>
        </div>
        )
    ;
    })

};
}