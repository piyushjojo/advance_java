package beans;

import dao.AddressDaoImpl;
import pojos.Address;

public class UserBean {
//state
	private long userId;
	private AddressDaoImpl addressDao;
	
	//ctor
	public UserBean() {
		//create dao instance
		addressDao=new AddressDaoImpl();
		System.out.println("user bean created...");
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public AddressDaoImpl getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDaoImpl addressDao) {
		this.addressDao = addressDao;
	}
	//B.L method to get user + adr details
	public Address getCompleteDetails()
	{
		System.out.println("in B.L "+userId);
		//call dao's method
		return addressDao.getUserAddressDetails(userId);
	}
	
}
