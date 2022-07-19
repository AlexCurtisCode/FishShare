package com.alexc.fishshare.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pictures")
public class Picture {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fish_id")
	private Fish picturedFish;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User picturedUser;
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Picture() {}
    public Picture(String url, Fish picturedFish) {
    	this.url = url;
    	this.picturedFish = picturedFish;
    }
    public Picture(String url, User picturedUser) {
    	this.url = url;
    	this.picturedUser = picturedUser;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Fish getPicturedFish() {
		return picturedFish;
	}
	public void setPicturedFish(Fish picturedFish) {
		this.picturedFish = picturedFish;
	}
	public User getPicturedUser() {
		return picturedUser;
	}
	public void setPicturedUser(User picturedUser) {
		this.picturedUser = picturedUser;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
