import React, { Component } from 'react';
import axios from 'axios';
import { TournamentLoader } from './tournamentLoaderPlayerTrue';
export class Tournaments extends Component {
 


    constructor(){
        super();
        this.ReloadData = this.ReloadData.bind(this);
    }

    state = { tournamentsGames: [] }
    
    ReloadData(){
      axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`)
            .then(response => {
                this.setState({tournamentsGames: response.data.tournaments});
            })
            .catch(error => {
                console.log(error);
            });  
     }


     componentDidMount() {
        axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`)
        .then(response => {
            this.setState({tournamentsGames: response.data.tournaments});
        })
        .catch(error => {
            console.log(error);
        });  
     }

    render() {
        return (        
                <TournamentLoader
                   tournamentsGames={this.state.tournamentsGames} >
                </TournamentLoader>
           
        );
    }

    
}