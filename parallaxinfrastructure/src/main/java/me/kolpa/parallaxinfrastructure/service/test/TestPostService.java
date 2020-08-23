package me.kolpa.parallaxinfrastructure.service.test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import me.kolpa.parallaxcore.domain.entities.Post;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.service.PostService;

public class TestPostService implements PostService
{
	@Override
	public Single<List<Post>> getHomeFeed()
	{
		List<Post> posts = new ArrayList<>();
		posts.add(new Post("1", "It do be like that", "asdf", 1231, 23));
		posts.add(new Post("2", "Hmm", "asdf", 12, 12));
		posts.add(new Post("3", "Yikes sweaty", "asdf", -32, 321));
		posts.add(new Post("4", "Full frontal clown", "asdf", 123, 12));
		posts.add(new Post("5", "Deuxchad", "asdf", 34, 32));
		posts.add(new Post("6", "turtle", "asdf", 12200, 43));
		posts.add(new Post("7", "Noooo you just can't question the narrative on reddit nooooo", "asdf", 157, 12));
		posts.add(new Post("8", "Flowers of color", "asdf", 45, 21));
		posts.add(new Post("9", "I got banned for saying that everyone deserves a voice", "asdf", 765, 23));
		posts.add(new Post("0", "Fuck commies", "asdf", 10, 1));

		return Single.just(posts);
	}
}
