package es.caser.spring.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.caser.spring.mvc.model.LittleTweet;
import es.caser.spring.mvc.repository.ILittleTwitterRepository;

@Controller
@RequestMapping("/tweets")
public class LittleTweetController {
	private ILittleTwitterRepository tweetRepository;
	private static final String MAX_VALUE_AS_STRING = "" + Long.MAX_VALUE;

	@Autowired
	public LittleTweetController(ILittleTwitterRepository tweetRepository) {
		this.tweetRepository = tweetRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<LittleTweet> spittles(@RequestParam(value = "max", defaultValue = MAX_VALUE_AS_STRING) long max,
			@RequestParam("count") int count) {
		return tweetRepository.findLittleTweets(max, count);
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showTweet(@RequestParam("tweet_id") long tweetId, Model model) {
		model.addAttribute(tweetRepository.findOne(tweetId));
		return "tweet";
	}
}
