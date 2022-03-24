
import React, { Component } from 'react';

class MouseTracker extends React.Component {
    
    render() {
        console.log ("hello")
        this.props.tournamentsGames.map(tournament =>  { "console.log({tournament.owner} as the {tournament.game} "} )
        return  "<li> hello </li>" 
    }
  }

