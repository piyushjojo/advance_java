package dao;

import pojos.Address;
import pojos.Topic;

public interface IAddressDao {
	/*
	 * assign user address 
	 * User i/p : user id , address dtls
	 */

	String assignUserAddress(long userId, Address adr);
}
