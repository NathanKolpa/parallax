package me.kolpa.parallaxcore.domain.usecases.post;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import me.kolpa.parallaxcore.domain.entities.Post;
import me.kolpa.parallaxcore.domain.repository.PostRepository;

public class GetPostsInteractor
{
	private final PostRepository postRepository;

	public GetPostsInteractor(PostRepository postRepository)
	{
		this.postRepository = postRepository;
	}

	public Flowable<List<Post>> getHomeFeed()
	{
		return postRepository.getHomeFeed()
				.mergeWith(!postRepository.hasFetchedHomeFeed() ? postRepository.fetchHomeFeed() : Completable
						.complete());
	}
}
