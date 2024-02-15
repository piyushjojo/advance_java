package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Topic;

public interface ITopicDao extends JpaRepository<Topic, Long>{
	
}
