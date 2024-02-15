package dependent;

import dependency.Transport;

public class ATMImpl implements ATM {
	private Transport myTransport;// =new HttpTransport();
	//private CustomerNotificationService[] services;

	public ATMImpl(Transport t) {
		this.myTransport=t;
		System.out.println("in cnstr of " + getClass().getName() + " " + myTransport);
	}

	@Override
	public void deposit(double amt) {
		System.out.println("depositing " + amt);
		byte[] data = ("depositing " + amt).getBytes();
		myTransport.informBank(data);

	}

	@Override
	public void withdraw(double amt) {
		System.out.println("withdrawing " + amt);
		byte[] data = ("withdrawing " + amt).getBytes();
		myTransport.informBank(data);
	}
	// custom init method
	public void anyInit() // allowed : throws Exception
	{
		System.out.println("in init " + myTransport);
	}

	// custom destroy method
	public void anyDestroy() // allowed : throws Exception
	{
		System.out.println("in destroy " + myTransport);
	}
	

}
