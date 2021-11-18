/** import React from 'react';
import {  Link } from "react-router-dom";
const navbar= () =>{
  return (
  <div>
    <li>
      <Link to="/">Home</Link>
    </li>
    <li>
      <Link to="/login">login</Link>
    </li>
    <li>
      <Link to="/signUp">signUp</Link>
    </li>
  </div>
  );
}
export default navbar;
import {
Nav,
NavLink,
Bars,
NavMenu,
NavBtn,
NavBtnLink,
} from './NavbarElements';


	{ Second Nav }
		{<NavBtnLink to='/sign-in'>Sign In</NavBtnLink> }
		<NavBtn>
		<NavBtnLink to='/signin'>Sign In</NavBtnLink>
		</NavBtn>
*/

import React from 'react';


const Navbar = () => {
return (
	<>
	<Nav>
		<Bars />

		<NavMenu>
		<NavLink to='/' activeStyle>
      Home
		</NavLink>
		<NavLink to='/events' activeStyle>
			Events
		</NavLink>	
		<NavLink to='/sign-up' activeStyle>
			Sign Up
		</NavLink>
    </NavMenu>
	</Nav>
	</>
);
};

export default Navbar;
