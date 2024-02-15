package com.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.TutorialDTO;
import com.app.service.TutorialService;

@RestController
@RequestMapping("/topics/{topicId}/tutorials")
public class TutorialController {
	// dep : topic service
	@Autowired
	private TutorialService tutService;

	public TutorialController() {
		System.out.println("in ctro of " + getClass());
	}

	// add REST API end point to add new tut under existing topic
	@PostMapping
	public ResponseEntity<?> addNewTutorial(@PathVariable long topicId, @RequestBody TutorialDTO tut) {
		System.out.println("in add new tut " + topicId + " " + tut);
		return ResponseEntity.status(HttpStatus.CREATED).body(tutService.addNewTutorial(topicId, tut));
	}

	// add REST API end point to get all tut details under existing topic
	@GetMapping
	public ResponseEntity<?> getTutsByTopic(@PathVariable long topicId) {
		System.out.println("in get tuts  " + topicId);
		return ResponseEntity.ok(tutService.getTutDetailsByTopic(topicId));
	}

	// add REST API end point to partially update specific tutorial's details (name
	// or author or anything...) belonging to specific topic
	@PatchMapping("/{tutId}")
	public ResponseEntity<?> partialUpdateTutorial(long topicId, long tutId, @RequestBody Map<String, Object> fields) {
		System.out.println("in partial update " + topicId + " " + tutId + " " + fields);
		return ResponseEntity.ok(tutService.partialUpdateTutorial(topicId, tutId, fields));
	}

	// add REST API end point to Get specific tutorial details by topic id n tut id
	@GetMapping("/{tutId}")
	public ResponseEntity<?> getTutorialDetails(long topicId, long tutId) {
		System.out.println("in get tut dtls " + topicId + " " + tutId);
		return ResponseEntity.ok(tutService.getTutorial(topicId, tutId));
	}
	

}
