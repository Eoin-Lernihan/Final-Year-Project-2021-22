import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export class TournamentLoader extends Component {
    
    render() {
        return  this.props.tournamentsGames.map((touraments) => {
                return (
                    <div className="StoreDisplayProducts container-fluid col-lg-10" key={touraments.owner}>
                        <Card id="Card" bg="secondary" border="dark" text="white">
                            <Card.Body>
                                <Row>
                                    <Col>
                                    <p></p>
                                    {touraments.owner}
                                    <p></p>                                   
                                    {touraments.game}
                                    <p></p>
                                    {touraments.gameMode}
                                    <p></p>
                                    {touraments.maxPlayers}
                                    <p></p>
                                    {touraments.time}
                                    <p></p>
                                    </Col>
                                </Row>
                            </Card.Body>
                        </Card>
                        <hr />
                    </div>
                );
            });
    }
}