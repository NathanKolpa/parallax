package me.kolpa.parallaxcore.domain.repository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import me.kolpa.parallaxcore.domain.entities.Guild;

public interface GuildRepository
{
	Flowable<Guild> getGuild(String name);
	Completable fetchGuild(String name);
}
