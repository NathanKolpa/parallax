package me.kolpa.parallaxcore.domain.usecases.guild;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import me.kolpa.parallaxcore.domain.entities.Guild;
import me.kolpa.parallaxcore.domain.repository.GuildRepository;

public class GetGuildsInteractor
{
	private final GuildRepository guildRepository;

	public GetGuildsInteractor(GuildRepository guildRepository)
	{
		this.guildRepository = guildRepository;
	}

	public Flowable<Guild> getGuild(String name)
	{
		return guildRepository.getGuild(name)
				.mergeWith(!guildRepository.hasFetchedGuild(name) ? guildRepository.fetchGuild(name) : Completable.complete());
	}
}
