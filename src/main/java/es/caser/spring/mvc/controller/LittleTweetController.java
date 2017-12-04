package es.caser.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.caser.spring.mvc.repository.ILittleTwitterRepository;

@Controller
@RequestMapping("/tweets")
public class LittleTweetController {
	private ILittleTwitterRepository tweetRepository;

	@Autowired
	public LittleTweetController(ILittleTwitterRepository tweetRepository) {
		this.tweetRepository = tweetRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {
		/**
		 * infiere el nombre del tipo y la coleccion
		 */
		model.addAttribute(tweetRepository.findLittleTweets(Long.MAX_VALUE, 20));
		return "tweets";
	}
}
