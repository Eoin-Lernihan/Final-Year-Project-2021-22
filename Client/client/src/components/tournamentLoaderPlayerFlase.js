import React, { Component } from 'react';
import {OneTouramentJoin} from './oneTouramentJoin';

export class TournamentLoaderFlase extends Component {
    
    render() { return  this.props.tournamentsGamesFlase.map((touraments) => {
       
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
}
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
