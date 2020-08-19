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
		posts.add(new Post("1", "dasd", "asdf", 1));
		posts.add(new Post("2", "dasd", "asdf", 2));
		posts.add(new Post("3", "dasd", "asdf", 3));
		posts.add(new Post("4", "dasd", "asdf", 4));

		return Single.just(posts);
	}
}
