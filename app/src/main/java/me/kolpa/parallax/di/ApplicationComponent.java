package me.kolpa.parallax.di;

import javax.inject.Singleton;

import dagger.Component;
import me.kolpa.parallax.ui.MainActivity;
import me.kolpa.parallax.ui.feed.FeedFragment;
import me.kolpa.parallax.ui.home.HomeFragment;

@Singleton
@Component(modules = ImplModule.class)
public interface ApplicationComponent
{
	void inject(MainActivity mainActivity);
	void inject(FeedFragment feedFragment);
	void inject(HomeFragment homeFragment);
}
