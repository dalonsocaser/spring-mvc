package es.caser.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.caser.spring.mvc.model.User;
import es.caser.spring.mvc.repository.IUserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	private IUserRepository userRepository;


	@Autowired
	public UserController(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "registerForm";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String showRegistrationForm(User user) {
		userRepository.save(user);
		return "redirect:/users/"+user.getUsername();
	}
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public String getUserProfile(@PathVariable String username, Model model) {
		User user= userRepository.findByUsername(username);
		model.addAttribute(user);
		return "profile";
	}
}
