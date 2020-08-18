package me.kolpa.parallaxcore.domain.repository.impl.reactive;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.kolpa.parallaxcore.domain.entities.Guild;
import me.kolpa.parallaxcore.domain.repository.GuildRepository;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.service.GuildService;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.store.ReactiveStore;

public class ReactiveGuildRepository implements GuildRepository
{
	private final GuildService service;
	private final ReactiveStore<String, Guild> store;

	public ReactiveGuildRepository(GuildService service, ReactiveStore<String, Guild> store)
	{
		this.service = service;
		this.store = store;
	}

	@Override
	public Flowable<Guild> getGuild(String name)
	{
		return store.getValue(name).toFlowable(BackpressureStrategy.BUFFER);
	}

	@Override
	public Completable fetchGuild(String name)
	{
		return Completable.fromSingle(service.getGuild(name)
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.computation())
				.doOnSuccess(store::setValue));
	}
}
