package me.kolpa.parallaxcore.domain.repository.impl.reactive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.kolpa.parallaxcore.domain.entities.Post;
import me.kolpa.parallaxcore.domain.repository.PostRepository;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.service.PostService;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.store.ReactiveStore;

public class ReactivePostRepository implements PostRepository
{
	private final ReactiveStore<String, Post> store;
	private final PostService service;

	private Set<String> homeFeedKeys = new HashSet<>();
	private List<String> homeFeedOrder = new ArrayList<>();

	public ReactivePostRepository(PostService service, ReactiveStore<String, Post> store)
	{
		this.service = service;
		this.store = store;
	}


	@Override
	public Flowable<List<Post>> getHomeFeed()
	{
		return store.getAll()
				.mergeWith(Single.just(store.getValues()))
				.map(posts -> Flowable.fromIterable(posts)
						.filter(post -> homeFeedKeys.contains(post.getId()))
						.sorted((left, right) -> homeFeedOrder.indexOf(left.getId()) - homeFeedOrder.indexOf(right.getId()))// TODO: this might be slow
						.toList()
						.blockingGet())
				.toFlowable(BackpressureStrategy.BUFFER);
	}

	@Override
	public Completable fetchHomeFeed()
	{
		return Completable.fromSingle(service.getHomeFeed().doOnSuccess(posts ->
		{
			homeFeedKeys.clear();
			for (Post post : posts)
			{
				homeFeedKeys.add(post.getId());
				homeFeedOrder.add(post.getId());
			}

			store.addAll(posts);

		}).doOnError(throwable ->
		{

		}));
	}

}
