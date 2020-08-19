package me.kolpa.parallax.di;

import javax.inject.Singleton;

import dagger.Component;
import me.kolpa.parallax.ui.MainActivity;
import me.kolpa.parallaxcore.domain.repository.GuildRepository;
import me.kolpa.parallaxinfrastructure.service.http.HttpApiClient;

@Singleton
@Component(modules = ImplModule.class)
public interface ApplicationComponent
{
	void inject(MainActivity mainActivity);
}
