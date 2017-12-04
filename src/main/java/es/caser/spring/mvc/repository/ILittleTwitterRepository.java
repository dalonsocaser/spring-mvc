package es.caser.spring.mvc.repository;

import java.util.List;

import es.caser.spring.mvc.model.LittleTweet;

public interface ILittleTwitterRepository {
	List<LittleTweet> findLittleTweets(long max, int count);
}
