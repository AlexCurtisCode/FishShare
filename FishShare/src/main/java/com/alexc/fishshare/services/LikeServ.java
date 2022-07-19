package com.alexc.fishshare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexc.fishshare.models.Fish;
import com.alexc.fishshare.models.Like;
import com.alexc.fishshare.models.User;
import com.alexc.fishshare.repositories.LikeRepo;

@Service
public class LikeServ {
	@Autowired
	LikeRepo likeRepo;
	
	public Like addLike(Like like, User user, Fish fish) {
		int likeCount = fish.getLikeLength();
		likeCount++;
		fish.setLikeLength(likeCount);
		like.setLikingUser(user);
		like.setLikedFish(fish);
		return likeRepo.save(like);
	}
}
