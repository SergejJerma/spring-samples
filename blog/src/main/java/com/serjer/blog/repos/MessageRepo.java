package com.serjer.blog.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serjer.blog.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Integer> {
	
	List<Message> findByTag(String tag);

}
