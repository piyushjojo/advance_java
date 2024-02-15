package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojos.Tutorial;

import static utils.DBUtils.*;

public class TutorialDaoImpl implements ITuotorialDao {
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3;

	public TutorialDaoImpl() throws SQLException {
		// get cn
		cn = getCn();
		// pst1 : all tut names for the specified topic id
		pst1 = cn.prepareStatement("select name from tutorials where topic_id=?");
		pst2 = cn.prepareStatement("select * from tutorials where name=?");
		pst3 = cn.prepareStatement("update tutorials set visits=visits+1 where name=?");
		System.out.println("tut dao created");
	}

	@Override
	public List<String> getTutorialNamesByTopicId(int topicId) throws SQLException {
		List<String> names = new ArrayList<>();
		// set IN param : topic id
		pst1.setInt(1, topicId);
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				names.add(rst.getString(1));
		}
		return names;
	}

	@Override
	public Tutorial getTutorialDetailsByName(String tutName) throws SQLException {
		// set In param : tut name
		pst2.setString(1, tutName);
		try (ResultSet rst = pst2.executeQuery()) {
			// tutId, String title, String author, Date publishDate, int visits, String
			// contents,
			// int topicId
			if (rst.next())
				return new Tutorial(rst.getInt(1), tutName, rst.getString(3), rst.getDate(4), rst.getInt(5),
						rst.getString(6), rst.getInt(7));
		}
		return null;
	}

	@Override
	public String updateVisits(String tutName) throws SQLException {
		// set In param : tut name
		pst3.setString(1, tutName);
		int updateCount = pst3.executeUpdate();
		if (updateCount == 1)
			return "Visits updated....";
		return "Visits updation failed ";
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		System.out.println("tut dao cleaned up ");
	}

}
