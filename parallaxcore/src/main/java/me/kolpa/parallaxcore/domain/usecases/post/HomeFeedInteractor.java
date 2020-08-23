package me.kolpa.parallaxcore.domain.usecases.post;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import me.kolpa.parallaxcore.domain.entities.Post;
import me.kolpa.parallaxcore.domain.repository.PostRepository;

public class HomeFeedInteractor
{
	private final PostRepository postRepository;
	private boolean hasFetchedHomeFeed = false;
	private int currentPage = 0;

	public HomeFeedInteractor(PostRepository postRepository)
	{
		this.postRepository = postRepository;
	}

	public Flowable<List<Post>> getHomeFeed()
	{
		return postRepository.getHomeFeed()
				.mergeWith(!hasFetchedHomeFeed ? postRepository.fetchHomeFeed().doOnComplete(() ->
				{
					hasFetchedHomeFeed = true;
				}) : Completable.complete());
	}

	public void setPage(int page)
	{
		if(page > currentPage)
		{
			// load new data
		}
	}
}
