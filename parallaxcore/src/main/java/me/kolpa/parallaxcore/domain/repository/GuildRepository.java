package me.kolpa.parallaxcore.domain.repository;

import java.util.List;

import me.kolpa.parallaxcore.domain.entities.Guild;

public interface GuildRepository
{
	List<Guild> getGuilds(int page);
}
