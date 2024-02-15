package dao;

import pojos.Address;

public interface IAddressDao {
	/*
	 * assign user address 
	 * User i/p : user id , address dtls
	 */

	String assignUserAddress(long userId, Address adr);
	//get user + address details by user id(same as adr id)
	Address getUserAddressDetails(long userId);
}
