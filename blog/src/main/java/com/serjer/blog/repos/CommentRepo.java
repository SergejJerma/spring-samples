package com.serjer.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serjer.blog.domain.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

	Iterable<Comment> findByMessageId(int id);

}
