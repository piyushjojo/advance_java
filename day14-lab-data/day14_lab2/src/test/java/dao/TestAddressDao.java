package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pojos.Address;
import utils.HibernateUtils;

class TestAddressDao {
	private static AddressDaoImpl adrDao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		HibernateUtils.getSf();
		adrDao=new AddressDaoImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		HibernateUtils.getSf().close();
	}

	@Test
	void test() {
		Address details = adrDao.getUserAddressDetails(1);
		System.out.println(details);
		System.out.println(details.getOwner());
		assertEquals("Hyderabad", details.getCity());
	}

}
