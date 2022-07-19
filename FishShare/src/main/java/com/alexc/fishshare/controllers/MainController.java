package com.alexc.fishshare.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.alexc.fishshare.models.Comment;
import com.alexc.fishshare.models.Fish;
import com.alexc.fishshare.models.Like;
import com.alexc.fishshare.models.LoginUser;
import com.alexc.fishshare.models.User;
import com.alexc.fishshare.services.CommentServ;
import com.alexc.fishshare.services.FishServ;
import com.alexc.fishshare.services.LikeServ;
import com.alexc.fishshare.services.UserServ;

@Controller
public class MainController {
	@Autowired
	UserServ userServ;
	
	@Autowired
	FishServ fishServ;
	
	@Autowired
	CommentServ comServ;
	
	@Autowired
	LikeServ likeServ;
	
	@GetMapping("/")
	public String Home() {
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "Login.jsp";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		User user = userServ.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "Login.jsp";
		}

		session.setAttribute("userId", newUser.getId());
		return "redirect:/dashboard";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = userServ.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "Login.jsp";
		}

		session.setAttribute("userId", user.getId());
		return "redirect:/dashboard";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.setAttribute("userId", null);
		return "redirect:/";
	}
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if((Long)session.getAttribute("userId")==null) {
			return "redirect:/logout";
		}
		User user = userServ.findUser((Long) session.getAttribute("userId"));
		List<Fish> fish = fishServ.findAll();
		model.addAttribute("user", user);
		model.addAttribute("fish", fish);
		return "Dashboard.jsp";
	}
	@GetMapping("/fish/add")
	public String addFish(@ModelAttribute("newFish") Fish newFish, Model model, HttpSession session) {
		if((Long)session.getAttribute("userId")==null) {
			return "redirect:/logout";
		}
		return "AddFish.jsp";
	}
	
	//post mapping addfish
	@PostMapping("/fish/new")
	public String newFish(@Valid @ModelAttribute("newFish") Fish newFish, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userServ.findUser(userId);
		if(result.hasErrors()) {
			return "AddFish.jsp";
		}
		newFish.setCreator(user);
		fishServ.createFish(newFish);
		return "redirect:/my/fish";
	}
	@GetMapping("/fish/delete/{id}")
	public String deleteFish(@PathVariable("id") Long id, HttpSession session) {
		if((Long)session.getAttribute("userId")==null) {
			return "redirect:/logout";
		}
		Fish fish = fishServ.findFish(id);
		fishServ.deleteFish(fish);
		return "redirect:/my/fish";
	}
	
	@GetMapping("/fish/edit/{id}")
	public String editFish(@PathVariable("id") Long id, Model model, HttpSession session) {
		if((Long)session.getAttribute("userId")==null) {
			return "redirect:/logout";
		}
		Fish fish = fishServ.findFish(id);
		model.addAttribute("fish", fish);
		return "EditFish.jsp";
	}
	@PutMapping("/fish/edit/{id}")
	public String updateFish(@Valid @ModelAttribute("fish") Fish fish, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "redirect:/fish/edit/" + fish.getId();
		}
		fish.setCreator(userServ.findUser((Long)session.getAttribute("userId")));
		fishServ.updateFish(fish);
		return "redirect:/my/fish";
	}
	
	
	@GetMapping("/my/fish")
	public String myFish( Model model, HttpSession session) {
		if((Long)session.getAttribute("userId")==null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userServ.findUser(userId);
		List<Fish> fish = user.getMyFish();
		model.addAttribute("fish", fish);
		model.addAttribute("user", user); 
		return "UserPage.jsp";
	}
	@GetMapping("/fish/{id}")
	public String fishPage(@PathVariable("id") Long id, @ModelAttribute("newComment") Comment newComment, Model model, HttpSession session) {
		if((Long)session.getAttribute("userId")==null) {
			return "redirect:/logout";
		}
		Fish fish = fishServ.findFish(id);
		User user = userServ.findUser((Long) session.getAttribute("userId"));
		List<Comment> comments = fish.getComments();
		model.addAttribute("user", user);
		model.addAttribute("comments", comments);
 		model.addAttribute("fish", fish);
		return "FishPage.jsp";
	}
	@PostMapping("/fish/{id}/comment")
	public String addComment(@PathVariable("id") Long id, @Valid @ModelAttribute("newComment") Comment newComment, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "redirect:/fish/" + id;
		}
		comServ.createComment(newComment, userServ.findUser((Long)session.getAttribute("userId")), fishServ.findFish(id));
		return "redirect:/fish/" + id;
	}
	@GetMapping("/fish/{id}/like")
	public String addLike(@PathVariable("id") Long id, HttpSession session) {
		User user = userServ.findUser((Long) session.getAttribute("userId"));
		Fish fish = fishServ.findFish(id);
		Like like = new Like();
		likeServ.addLike(like, user, fish);
		return "redirect:/dashboard";
	}
	
}
