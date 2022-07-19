package com.alexc.fishshare.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="fish")
public class Fish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="A title is required")
	private String title;
	
	@NotEmpty(message="You must select a species")
	private String species;
	
	//backlog feature
//	@NotEmpty(message="You must enter a latitude")
//	private String lat;
//	
//	@NotEmpty(message="You must enter a longitude")
//	private String lon;
	
	@NotEmpty(message="You must enter a description of your fish")
	@Size(min=5, max=255, message="your desciription must be between 5 and 255 characters")
	private String description;
	
	@Column(updatable=false)
	@OneToMany(mappedBy="commentedFish")
	private List<Comment> comments;
	
	@Column(updatable=false)
	@OneToMany(mappedBy="likedFish")
	private List<Like> likes;
	
	@Column(updatable=false)
	@OneToMany(mappedBy="picturedFish")
	private List<Picture> pictures;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User creator;
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
    public Fish() {
    	
    }
    private int likeLength;
    
	public int getLikeLength() {
		return likeLength;
	}

	public void setLikeLength(int likeLength) {
		this.likeLength = likeLength;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

//	public String getLat() {
//		return lat;
//	}
//
//	public void setLat(String lat) {
//		this.lat = lat;
//	}
//
//	public String getLon() {
//		return lon;
//	}
//
//	public void setLon(String lon) {
//		this.lon = lon;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes; 
	}



	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
