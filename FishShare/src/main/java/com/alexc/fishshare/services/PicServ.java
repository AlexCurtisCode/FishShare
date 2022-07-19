package com.alexc.fishshare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexc.fishshare.models.Fish;
import com.alexc.fishshare.models.Picture;
import com.alexc.fishshare.models.User;
import com.alexc.fishshare.repositories.PicRepo;
import com.alexc.fishshare.repositories.UserRepo;

@Service
public class PicServ {
	@Autowired
	PicRepo picRepo;
	@Autowired
	UserRepo userRepo;

	public void addPic(String url, Fish picturedFish) {
		Picture newPic = new Picture(url, picturedFish);
		picRepo.save(newPic);
	}

	public void addProPic(String url, User user) {
		if (user.getProfilePic() == null) {
			Picture newPic = new Picture(url, user);
			picRepo.save(newPic);
		}
		else {
			Picture newPic = user.getProfilePic();
			newPic.setUrl(url);
			user.setProfilePic(newPic);
			userRepo.save(user);
		}
	}
}
