package es.caser.spring.mvc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.caser.spring.mvc.model.LittleTweet;
@Repository
public class LittleTweetRepositoryImpl implements ILittleTwitterRepository {
	private List<LittleTweet> tweetList=new ArrayList<>();
	public List<LittleTweet> findLittleTweets(long max, int count) {		
		return tweetList;
	}

}
