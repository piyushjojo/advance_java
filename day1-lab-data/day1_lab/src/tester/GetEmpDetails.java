package tester;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import static utils.DBUtils.openConnection;


public class GetEmpDetails {

	public static void main(String[] args) {
		String sql="select * from my_emp where salary > ? and join_date < ?";
		try(Scanner sc=new Scanner(System.in);
				Connection cn=openConnection();
				PreparedStatement pst=cn.prepareStatement(sql);
				)
		{
			System.out.println("Enter salary n date(yr-mon-day)");
			//set IN params
			pst.setDouble(1,sc.nextDouble());
			pst.setDate(2, Date.valueOf(sc.next()));
			//exec Query : select
			try(ResultSet rst=pst.executeQuery())
			{
				while(rst.next())
					System.out.printf("Emp Id %d Name %s Address %s Salary %.2f Dept %s Joined on %s%n", rst.getInt(1),
							rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5), rst.getDate(6));
		
			}//rst close
		} //pst cn sc : close
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
