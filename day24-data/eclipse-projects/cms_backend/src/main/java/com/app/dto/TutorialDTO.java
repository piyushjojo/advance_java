package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class TutorialDTO extends BaseDto{
	
	@NotBlank(message = "tut title is required")
	private String title;
	@NotNull(message = "author id is required")
	private long authorId;
	@NotNull(message = "topic id is required")
	private long topicId;	
	@NotNull(message = "Publish date must be supplied")
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern ="yyyy-MM-dd" )
	private LocalDate publishDate;
	@NotNull(message = "visits can't be blank")
	private int visits;
	@NotBlank(message = "contents must be supplied")
	private String contents;
	
}
