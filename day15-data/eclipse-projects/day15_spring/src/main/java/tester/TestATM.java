package tester;

import dependency.SoapTransport;
import dependent.ATMImpl;

public class TestATM {

	public static void main(String[] args) {
		ATMImpl atm=new ATMImpl();
		atm.setMyTransport(new SoapTransport());
		atm.withdraw(1000);

	}

}
