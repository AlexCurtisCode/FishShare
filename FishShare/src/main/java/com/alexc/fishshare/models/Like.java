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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User likingUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fish_id")
	private Fish likedFish;
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Like() {
    
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getLikingUser() {
		return likingUser;
	}
	public void setLikingUser(User likingUser) {
		this.likingUser = likingUser;
	}
	public Fish getLikedFish() {
		return likedFish;
	}
	public void setLikedFish(Fish fish) {
		this.likedFish = fish;
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
