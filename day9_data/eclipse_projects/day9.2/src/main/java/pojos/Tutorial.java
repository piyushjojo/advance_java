package pojos;

import java.sql.Date;

//id | name        | author | publish_date | visits | contents                                  | topic_id
public class Tutorial {
	private int tutId;
	private String title;
	private String author;
	private Date publishDate;
	private int visits;
	private String contents;
	// FK
	private int topicId;
	public Tutorial() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Tutorial(String title, String author, Date publishDate, String contents, int topicId) {
		super();
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.contents = contents;
		this.topicId = topicId;
	}


	//all args constr
	public Tutorial(int tutId, String title, String author, Date publishDate, int visits, String contents,
			int topicId) {
		super();
		this.tutId = tutId;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.visits = visits;
		this.contents = contents;
		this.topicId = topicId;
	}
	public int getTutId() {
		return tutId;
	}
	

	public void setTutId(int tutId) {
		this.tutId = tutId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
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
	@Override
	public String toString() {
		return "Tutorial [tutId=" + tutId + ", title=" + title + ", author=" + author + ", publishDate=" + publishDate
				+ ", visits=" + visits + ", contents=" + contents + ", topicId=" + topicId + "]";
	}
	

}
