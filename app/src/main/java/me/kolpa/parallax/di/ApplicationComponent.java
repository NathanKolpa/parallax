package me.kolpa.parallax.di;

import javax.inject.Singleton;

import dagger.Component;
import me.kolpa.parallax.ui.MainActivity;
import me.kolpa.parallax.ui.feed.FeedFragment;
import me.kolpa.parallax.ui.home.HomeFragment;
import me.kolpa.parallax.ui.home.HomeViewModel;

@Singleton
@Component(modules = ImplModule.class)
public interface ApplicationComponent
{
	void inject(MainActivity mainActivity);
	void inject(FeedFragment feedFragment);
	void inject(HomeFragment homeFragment);
	void inject(HomeViewModel homeViewModel);
}
