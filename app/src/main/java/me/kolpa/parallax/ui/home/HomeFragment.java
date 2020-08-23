package me.kolpa.parallax.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import me.kolpa.parallax.R;
import me.kolpa.parallax.di.MyApplication;
import me.kolpa.parallax.presentation.mappers.PostViewEntityMapper;
import me.kolpa.parallax.ui.feed.FeedFragment;
import me.kolpa.parallaxcore.domain.usecases.post.HomeFeedInteractor;

public class HomeFragment extends Fragment
{
	@Inject
	HomeFeedInteractor homeFeedInteractor;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		((MyApplication) getActivity().getApplicationContext()).appComponent.inject(this);
		View inflate = inflater.inflate(R.layout.fragment_home, container, false);
		FeedFragment feedContainer = (FeedFragment) getChildFragmentManager().findFragmentById(R.id.home_feed_fragment);

		HomeViewModel homeViewModel = new HomeViewModel(homeFeedInteractor, new PostViewEntityMapper());// TODO: DI
		homeViewModel.getPostsLiveData().observe(getViewLifecycleOwner(), feedContainer::setPosts);

		return inflate;
	}
}