package es.caser.spring.mvc.controller;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
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
import org.springframework.web.servlet.view.InternalResourceView;

import es.caser.spring.mvc.model.LittleTweet;
import es.caser.spring.mvc.repository.ILittleTwitterRepository;

public class LittleTweetControllerTest {

	@Test
	public void shouldShowRecentTweets() throws Exception {		
		List<LittleTweet> expectedTweets = creatTweetList(20);
		ILittleTwitterRepository mockRepository = mock(ILittleTwitterRepository.class);
		when(mockRepository.findLittleTweets(Long.MAX_VALUE, 20)).thenReturn(expectedTweets);
		
		
		LittleTweetController controller = new LittleTweetController(mockRepository);		
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(
						new InternalResourceView("/WEB-INF/views/tweets.jsp"))
				.build();
		/**
		 * infiere el nombre del tipo y la coleccion
		 */
		mockMvc.perform(get("/tweets")
				.param("max", ""+Long.MAX_VALUE)
				.param("count","20")).andExpect(view().name("tweets"))
		.andExpect(model().attributeExists("littleTweetList"))
		.andExpect(model().attribute("littleTweetList", hasItems(expectedTweets.toArray())));
	}
	@Test
	public void should_ShowRightTweet_whenUsingId() throws Exception {		
		LittleTweet expectedTweet = createTweet(1l);
		ILittleTwitterRepository mockRepository = mock(ILittleTwitterRepository.class);
		when(mockRepository.findOne(1L)).thenReturn(expectedTweet);
		
		
		LittleTweetController controller = new LittleTweetController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/tweets/1"))
		.andExpect(view().name("tweet"))
		.andExpect(model().attributeExists("littleTweet"))
		.andExpect(model().attribute("littleTweet", expectedTweet));
		
	}
	private LittleTweet createTweet(long l) {		
		return new LittleTweet("Tweet " + l, new Date());
	}
	private List<LittleTweet> creatTweetList(int count) {
		List<LittleTweet> spittles = new ArrayList<LittleTweet>();
		for (int i = 0; i < count; i++) {
			spittles.add(createTweet(i));
		}
		return spittles;
	}
}
