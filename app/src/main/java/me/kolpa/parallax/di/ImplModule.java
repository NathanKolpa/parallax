package me.kolpa.parallax.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.kolpa.parallax.VolleyHttpApiClient;
import me.kolpa.parallaxcore.domain.entities.Guild;
import me.kolpa.parallaxcore.domain.repository.GuildRepository;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.ReactiveGuildRepository;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.service.GuildService;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.store.InMemoryReactiveStore;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.store.ReactiveStore;
import me.kolpa.parallaxinfrastructure.service.http.HttpApiClient;
import me.kolpa.parallaxinfrastructure.service.http.JsonSerializer;
import me.kolpa.parallaxinfrastructure.service.http.v1.RuqqusApiV1GuildService;

@Module
public class ImplModule
{
	@Provides
	@Singleton
	HttpApiClient httpApiClient()
	{
		return VolleyHttpApiClient.create("https://ruqqus.com");
	}

	@Provides
	@Singleton
	JsonSerializer jsonSerializer()
	{
		return new JsonSerializer();
	}

	@Provides
	@Singleton
	GuildService guildService(HttpApiClient httpApiClient, JsonSerializer serializer)
	{
		return new RuqqusApiV1GuildService(httpApiClient, serializer);
	}

	@Provides
	@Singleton
	ReactiveStore<String, Guild> inStringGuildInMemoryReactiveStore()
	{
		return new InMemoryReactiveStore<>(Guild::getName);
	}

	@Provides
	@Singleton
	GuildRepository guildRepository(GuildService guildService, ReactiveStore<String, Guild> reactiveStore)
	{
		return new ReactiveGuildRepository(guildService, reactiveStore);
	}
}
