package com.serjer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

@Entity
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(columnDefinition = "TEXT")
	@Size(min = 6, message = "must be atleast 6 characters")
	private String reviewText;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date createDate;

	
	
	public Review() {
		super();
	}

	public Review(String reviewText, Date createDate) {
		super();
		this.reviewText = reviewText;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
