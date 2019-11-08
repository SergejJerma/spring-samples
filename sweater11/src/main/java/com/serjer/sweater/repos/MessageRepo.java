package com.serjer.sweater.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serjer.sweater.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Integer> {
	
	List<Message> findByTag(String tag);

}
