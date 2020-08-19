package me.kolpa.parallax.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.kolpa.parallax.VolleyHttpApiClient;
import me.kolpa.parallaxcore.domain.entities.Guild;
import me.kolpa.parallaxcore.domain.entities.Post;
import me.kolpa.parallaxcore.domain.repository.GuildRepository;
import me.kolpa.parallaxcore.domain.repository.PostRepository;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.ReactiveGuildRepository;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.ReactivePostRepository;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.service.GuildService;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.service.PostService;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.store.InMemoryReactiveStore;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.store.ReactiveStore;
import me.kolpa.parallaxcore.domain.usecases.guild.GetGuildsInteractor;
import me.kolpa.parallaxcore.domain.usecases.post.GetPostsInteractor;
import me.kolpa.parallaxinfrastructure.service.http.HttpApiClient;
import me.kolpa.parallaxinfrastructure.service.http.JsonSerializer;
import me.kolpa.parallaxinfrastructure.service.http.v1.RuqqusApiV1GuildService;
import me.kolpa.parallaxinfrastructure.service.test.TestPostService;

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
	PostService postService()
	{
		return new TestPostService();
	}

	@Provides
	@Singleton
	ReactiveStore<String, Guild> inStringGuildInMemoryReactiveStore()
	{
		return new InMemoryReactiveStore<>(Guild::getName);
	}

	@Provides
	@Singleton
	ReactiveStore<String, Post> inStringPostReactiveStore()
	{
		return new InMemoryReactiveStore<>(Post::getId);
	}


	@Provides
	@Singleton
	GuildRepository guildRepository(GuildService guildService, ReactiveStore<String, Guild> reactiveStore)
	{
		return new ReactiveGuildRepository(guildService, reactiveStore);
	}

	@Provides
	@Singleton
	PostRepository postRepository(PostService postService, ReactiveStore<String, Post> reactiveStore)
	{
		return new ReactivePostRepository(postService, reactiveStore);
	}

	@Provides
	@Singleton
	GetGuildsInteractor getGuildsInteractor(GuildRepository guildRepository)
	{
		return new GetGuildsInteractor(guildRepository);
	}

	@Provides
	@Singleton
	GetPostsInteractor getPostsInteractor(PostRepository postRepository)
	{
		return new GetPostsInteractor(postRepository);
	}
}
