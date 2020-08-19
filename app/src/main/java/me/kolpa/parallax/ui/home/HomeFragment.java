package me.kolpa.parallax.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import me.kolpa.parallax.R;
import me.kolpa.parallax.di.MyApplication;
import me.kolpa.parallax.ui.feed.FeedFragment;

public class HomeFragment extends Fragment
{
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		((MyApplication) getActivity().getApplicationContext()).appComponent.inject(this);
		View inflate = inflater.inflate(R.layout.fragment_home, container, false);
		FeedFragment feedContainer = (FeedFragment) getChildFragmentManager().findFragmentById(R.id.home_feed_fragment);


		return inflate;
	}
}