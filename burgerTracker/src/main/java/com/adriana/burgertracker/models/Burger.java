package com.adriana.burgertracker.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;



@Entity // declares that it is going to be apart of mysql
@Table(name = "burgers") // gives the table name of the model
public class Burger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // generates an auto incrementing 
	private Long id;
	
	@NotEmpty(message = "Burger name is required!") // validation for strings
	private String name;
	
	@NotEmpty(message = "Restaurant name is required!")
	private String restaurant;
	
	@Min(0) // validation for ints
	private String rating;
	
	@NotEmpty(message = "Add some notes!")
	private String notes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User donor;
	
    @Column(updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

	public Burger() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getDonor() {
		return donor;
	}

	public void setDonor(User donor) {
		this.donor = donor;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@PrePersist // adds the created at date and time to sql on creation 
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate // add the updated at date and time when being updated
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
