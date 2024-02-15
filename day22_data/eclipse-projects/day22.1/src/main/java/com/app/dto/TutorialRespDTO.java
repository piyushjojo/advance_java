package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TutorialRespDTO {
	
	private String tutorialName;
	private String author;
	private LocalDate publishDate;	
	private int visits;	
	private String contents;
	private TopicDTO topic;
}
