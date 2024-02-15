package beans;

import java.sql.SQLException;
import java.util.List;

import dao.TopicDaoImpl;
import pojos.Topic;

public class TopicBean {
	private TopicDaoImpl topicDao;

	public TopicBean() throws SQLException {
		topicDao = new TopicDaoImpl();
		System.out.println("topic bean created.");
	}

	public TopicDaoImpl getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDaoImpl topicDao) {
		this.topicDao = topicDao;
	}
	
	//add BL method to ret all topics to the caller (JSP)
	public List<Topic> getAllTopics() throws SQLException{
		return topicDao.getAllTopics();
	}
	
}
