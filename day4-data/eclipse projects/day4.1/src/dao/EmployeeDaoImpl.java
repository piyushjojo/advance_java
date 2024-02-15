package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Employee;
import static utils.DBUtils.*;

public class EmployeeDaoImpl implements IEmployeeDao {
	// state
	private Connection cn;
	private PreparedStatement pst1,pst2;

	// def ctor
	public EmployeeDaoImpl() throws SQLException {
		// open cn
		cn = openConnection();
		// create pst1
		pst1 = cn.prepareStatement("select * from my_emp where deptid=? and join_date > ?");
		pst2=cn.prepareStatement("insert into my_emp values(default,?,?,?,?,?)");
		System.out.println("emp dao created...");
	}

	@Override
	public List<Employee> getEmpDetails(String deptName, Date date) throws SQLException {
		List<Employee> emps = new ArrayList<>();
		// set IN params
		pst1.setString(1, deptName);
		pst1.setDate(2, date);
		// exec method : Exec query ---> RST
		try (ResultSet rst = pst1.executeQuery()) {
			// empId, String name, String address, double salary, String deptId, Date
			// joinDate
			while (rst.next())
				emps.add(new Employee(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4), deptName,
						rst.getDate(6)));

		}
		return emps;
	}
	
	

	@Override
	public String insertEmpDetails(Employee employee) throws SQLException {
		// set IN params
		//name    | addr    | salary | deptid  | join_date
		pst2.setString(1,employee.getName());
		pst2.setString(2,employee.getAddress());
		pst2.setDouble(3, employee.getSalary());
		pst2.setString(4, employee.getDeptId());
		pst2.setDate(5, employee.getJoinDate());
		//exec update
		int updateCount=pst2.executeUpdate();
		if(updateCount == 1)
			return "Emp details inserted successfully!";
		return "Emp details insertion failed !!!!!";
	}

	// add a method to close DB resources
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		// close db conn
		closeConnection();
		System.out.println("emp dao cleaned up !");
	}

}
