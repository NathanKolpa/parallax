package me.kolpa.parallax.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.kolpa.parallax.R;
import me.kolpa.parallax.di.MyApplication;
import me.kolpa.parallax.presentation.viewentity.PostViewEntity;

public class FeedFragment extends Fragment
{
	private RecyclerView recyclerView;
	private PostRecyclerViewAdapter postRecyclerViewAdapter;
	private RecyclerView.LayoutManager layoutManager;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		((MyApplication) getActivity().getApplicationContext()).appComponent.inject(this);
		View inflate = inflater.inflate(R.layout.fragment_feed, container, false);

		recyclerView = inflate.findViewById(R.id.feed_recycler_view);
		recyclerView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);

		postRecyclerViewAdapter = new PostRecyclerViewAdapter(new ArrayList<>());
		recyclerView.setAdapter(postRecyclerViewAdapter);

		return inflate;
	}

	public void setPosts(List<PostViewEntity> posts)
	{
		postRecyclerViewAdapter.setPostViewEntities(posts);
	}
}