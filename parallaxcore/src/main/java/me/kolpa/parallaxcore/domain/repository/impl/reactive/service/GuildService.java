package me.kolpa.parallaxcore.domain.repository.impl.reactive.service;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import me.kolpa.parallaxcore.domain.entities.Guild;

public interface GuildService
{
	Single<Guild> getGuild(String name);
}
