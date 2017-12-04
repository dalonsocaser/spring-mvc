package es.caser.spring.mvc.controller;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import es.caser.spring.mvc.model.LittleTweet;
import es.caser.spring.mvc.repository.ILittleTwitterRepository;

public class LittleTweetControllerTest {

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		
		
		List<LittleTweet> expectedTweets = creatTweetList(20);
		ILittleTwitterRepository mockRepository = mock(ILittleTwitterRepository.class);
		when(mockRepository.findLittleTweets(Long.MAX_VALUE, 20)).thenReturn(expectedTweets);
		
		
		LittleTweetController controller = new LittleTweetController(mockRepository);		
		MockMvc mockMvc = standaloneSetup(controller)
				.build();
		
		mockMvc.perform(get("/tweets")).andExpect(view().name("tweets"))
				.andExpect(model().attributeExists("tweetsList"))
				.andExpect(model().attribute("tweetsList", hasItems(expectedTweets.toArray())));
	}

	private List<LittleTweet> creatTweetList(int count) {
		List<LittleTweet> spittles = new ArrayList<LittleTweet>();
		for (int i = 0; i < count; i++) {
			spittles.add(new LittleTweet("Tweet " + i, new Date()));
		}
		return spittles;
	}
}
