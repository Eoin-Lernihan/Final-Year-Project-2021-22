import React, { Component } from 'react';
import axios from 'axios';
import { TournamentLoaderFlase } from './tournamentLoaderPlayerFlase';

export class Tournaments extends Component {



    constructor() {
        super();
        this.ReloadData = this.ReloadData.bind(this);
    }

    state = { tournamentsGamesTrue: [],
              tournamentsGamesFlase: [] }

    ReloadData() {
            axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`)
            .then(response => {
                this.setState({ tournamentsGamesTrue: response.data.tournaments });
            })
            .catch(error => {
                console.log(error);
            });
            axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`)
            .then(response => {
                this.setState({ tournamentsGamesFlase: response.data.tournaments });
            })
            .catch(error => {
                console.log(error);
            });
    }


    componentDidMount() {
        let user = JSON.parse( localStorage.getItem("user"));
        let userName = user.userName;
        axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`, { params: {
             username: userName,
            // userName: this.state.password,
             inGame: 'flase'
         }})
        .then(response => {
            this.setState({ tournamentsGamesTrue: response.data.tournaments });
        })
        .catch(error => {
            console.log(error);
        });
        axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`)
        .then(response => {
            this.setState({ tournamentsGamesFlase: response.data.tournaments });
        })
        .catch(error => {
            console.log(error);
        });
    }

    render(){
        
        return (
            <div>
               
                <div>

                All Games Avilable
                    <TournamentLoaderFlase
                        tournamentsGamesFlase={this.state.tournamentsGamesFlase} >
                    </TournamentLoaderFlase>
                </div>
            </div>
                );
    }



    
}