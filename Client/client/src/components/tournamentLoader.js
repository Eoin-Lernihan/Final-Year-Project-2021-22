import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export class TournamentLoader extends Component {
    
    render() {
        return  this.props.tournamentsGames.map((product) => {
                return (
                    <div className="StoreDisplayProducts container-fluid col-lg-10" key={product.productID}>
                        <Card id="Card" bg="secondary" border="dark" text="white">
                            <Card.Body>
                                <Row>
                                    <Col>
                                       hi
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