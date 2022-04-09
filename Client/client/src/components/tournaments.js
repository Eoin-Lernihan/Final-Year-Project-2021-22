import React, { Component } from 'react';
import axios from 'axios';
import { Link, Redirect } from 'react-router-dom';
import { TournamentLoaderFlase } from './tournamentLoaderPlayerFlase';

export class Tournaments extends Component {



    constructor() {
        super();
        this.ReloadData = this.ReloadData.bind(this);
        
    }

    state = { tournamentsGamesTrue: [],
              tournamentsGamesFlase: [],
              loginUser:  localStorage.getItem("user") }

    ReloadData() {
        let user = JSON.parse( localStorage.getItem("user"));
        let userName = user.userName;
            axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`, { params: {
                username: userName,
               // userName: this.state.password,
                inGame: '0'
            }})
            .then(response => {
                this.setState({ tournamentsGamesFlase: response.data.tournaments });
            })
            .catch(error => {
                console.log(error);
            });
          
    }


    componentDidMount() {
        let userName =null;
        let user = JSON.parse( localStorage.getItem("user"));
        if(user == null)
        {
             userName = null;
        }
        else{
             userName = user.userName;
        }
       
        axios.get(`https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments`, { params: {
            // username: this.state.username,
             userName: userName,
             inGame: '0'
         }})
    .then(response => {
        this.setState({ tournamentsGamesFlase: response.data.tournaments });
    })
    .catch(error => {
        console.log(error);
    });
   
    }

    render(){
        
        if (this.state.loginUser == null) {
            return (<Redirect exact to="/" />);
        } else {
            return (
            <div>
                Check
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



}  
