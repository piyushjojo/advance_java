package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
//Map this to existing tables "tutorials"
@Table(name = "tutorials")
public class Tutorial extends BaseEntity{
	// name        | author | publish_date | visits | topic_id
	@Column(name="name",length = 50)
	private String tutorialName;
	@Column(length = 30)
	private String author;
	@Column(name="publish_date")
	private LocalDate publishDate;
	private int visits;
	private String contents;
	@ManyToOne
	@JoinColumn(name="topic_id")
	private Topic currentTopic;
	public Tutorial() {
		// TODO Auto-generated constructor stub
	}
	public String getTutorialName() {
		return tutorialName;
	}
	public void setTutorialName(String tutorialName) {
		this.tutorialName = tutorialName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDate publishDate) {
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
	public Topic getCurrentTopic() {
		return currentTopic;
	}
	public void setCurrentTopic(Topic currentTopic) {
		this.currentTopic = currentTopic;
	}
	@Override
	public String toString() {
		return "Tutorial [tutorialName=" + tutorialName + ", author=" + author + ", publishDate=" + publishDate
				+ ", visits=" + visits + ", contents=" + contents + "]";
	}
	
	
}
