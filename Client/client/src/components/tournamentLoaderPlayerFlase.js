import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import axios from 'axios';
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import {OneTouramentJoin} from './oneTouramentJoin';

export class TournamentLoaderFlase extends Component {
    
    render() { return  this.props.tournamentsGamesTrue.map((touraments) => {
            touraments.players.indexOf("tim") === -1 ? touraments.players.push("tim") : console.log("This item already exists");
            return(
            <div>
              /* Used employee as props name */
              <OneTouramentJoin tourament={touraments} >
                  </OneTouramentJoin>
            </div>
            )
        ;
        })
    
    };
 /*        return  this.props.tournamentsGamesFlase.map((touraments) => {
                return (
                    <div className="StoreDisplayProducts container-fluid col-lg-10" key={touraments.owner}>
                        <Card id="Card" bg="secondary" border="dark" text="white">
                            <Card.Body>
                                <Row>
                                    <Col>
                                    
                                    <p></p>
                                    Owner Of Game:&nbsp; {touraments.owner} &nbsp;    
                                    <p></p>
                                    Game:&nbsp; {touraments.game}&nbsp;Tournament Type:&nbsp;{touraments.gameMode}
                                    <p></p>
                                    Date and Time: &nbsp; {touraments.time} &nbsp; Max Players:  {touraments.maxPlayers}
                                    <p></p>
                                    Tournament Description
                                    <p></p>
                                    {touraments.description}
                                    <p></p>
                                    </Col>
                                </Row>
                                <Button variant="success" onClick={this.UpdateTournament}>Join Game</Button>{' '}
                            </Card.Body>
                        </Card>
                        <hr />
                    </div>
                );
            }); 
    }

    UpdateTournament(touraments) {
        touraments.preventDefault();
        axios.put('https://cjh1f85qo9.execute-api.us-east-2.amazonaws.com/Develop/touraments/' + touraments.game )
            .then(() => {
                this.props.ReloadData();
            })
            .catch((err) => {
                console.log(err);
            });
    } */
}