package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class TutorialDTO extends BaseDto {
	// id | name | author | publish_date | visits | contents | topic_id
	
	@NotBlank(message = "tut name is required")
	private String tutorialName;
	@NotBlank(message = "author is required")
	private String author;
	
	@NotNull(message = "Publish date must be supplied")
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd" )
	private LocalDate publishDate;
	@NotNull(message = "visits can't be blank")
	private int visits;
	@NotBlank(message = "contents must be supplied")
	private String contents;
	
}
