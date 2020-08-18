package me.kolpa.parallaxinfrastructure.service.http.v1;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import me.kolpa.parallaxcore.domain.entities.Guild;
import me.kolpa.parallaxcore.domain.repository.impl.reactive.service.GuildService;
import me.kolpa.parallaxinfrastructure.service.http.HttpApiClient;
import me.kolpa.parallaxinfrastructure.service.http.v1.response.GuildResponse;

public class RuqqusApiV1GuildService implements GuildService
{
	private final HttpApiClient client;

	public RuqqusApiV1GuildService(HttpApiClient client)
	{
		this.client = client;
	}

	@Override
	public Single<Guild> getGuild(String name)
	{
		return client.get("api/v1/guild/" + name, GuildResponse.class)
				.map(res -> new Guild(res.name, res.subscriberCount));
	}
}
