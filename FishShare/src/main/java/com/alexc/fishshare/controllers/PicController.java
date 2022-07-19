package com.alexc.fishshare.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexc.fishshare.models.Fish;
import com.alexc.fishshare.models.User;
import com.alexc.fishshare.services.FishServ;
import com.alexc.fishshare.services.PicServ;
import com.alexc.fishshare.services.UserServ;

@Controller
public class PicController {
	@Autowired
	PicServ picServ;
	
	@Autowired
	UserServ userServ;
	
	@Autowired
	FishServ fishServ;
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/images/";
	
	@PostMapping("/pictures/create/{id}")
	public String create(@PathVariable("id") Long id, @RequestParam("url") MultipartFile file, HttpSession session, RedirectAttributes redirectedAttrs) {
		if(file.isEmpty()) {
			redirectedAttrs.addFlashAttribute("message", "Upload field cannot be empty");
			return "redirect:/fish/" + id;
		}
		
		try {
			User user = userServ.findUser((Long) session.getAttribute("userId"));
			Fish fish = fishServ.findFish(id);
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			
			String imageUrl = "/images/" + file.getOriginalFilename();
			picServ.addPic(imageUrl, fish);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/fish/" + id;
	}
	
	@PostMapping("/pictures/profile/add")
	public String newProfPic(@RequestParam("url") MultipartFile file, HttpSession session, RedirectAttributes redirectedAttrs) {
		if(file.isEmpty()) {
			redirectedAttrs.addFlashAttribute("message", "Upload field cannot be empty");
			return "redirect:/my/fish";
		}
		
		try {
			User user = userServ.findUser((Long) session.getAttribute("userId"));

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			
			String imageUrl = "/images/" + file.getOriginalFilename();
			picServ.addProPic(imageUrl, user);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		return "redirect:/my/fish";
	}	
}
