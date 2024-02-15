package beans;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import dao.TutorialDaoImpl;
import pojos.Tutorial;

public class TutorialBean {
	private TutorialDaoImpl tutDao;
	private String name;
	private String author;
	private String pubDate;
	private String contents;
	private int topicId;
	//add message ; as tut bean property 
	private String message;



	//def ctor 
	public TutorialBean() throws SQLException {
		tutDao = new TutorialDaoImpl();
		System.out.println("tut dao created/");
	}

	public TutorialDaoImpl getTutDao() {
		return tutDao;
	}

	public void setTutDao(TutorialDaoImpl tutDao) {
		this.tutDao = tutDao;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	
	public String getMessage() {
		return message;
	}

	//add BL method for validation and insertion. 
	public String validateNInsertTutorial() throws SQLException {
		System.out.println("in validate n insert "+ topicId + " "+ name);
		//validation rules
		long periodInMonth = Period.between(LocalDate.parse(pubDate), LocalDate.now()).toTotalMonths();
		if(contents.length() > 255 || periodInMonth > 6) {
			//validation faliure. : bad request : sc 400 (http status code)
			message="Invalid contents or publish date !!!!";
			return "add_tutorial";
		}
		//valid i/ps -- proceed to inserting tut rec in tuts table
		message = tutDao.insertNewTutorial(new Tutorial(name, author, Date.valueOf(pubDate), contents, topicId));

		return message;
	}
	

}
