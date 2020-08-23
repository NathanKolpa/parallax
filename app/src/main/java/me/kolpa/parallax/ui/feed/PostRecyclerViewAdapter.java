package me.kolpa.parallax.ui.feed;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.kolpa.parallax.R;
import me.kolpa.parallax.presentation.viewentity.PostViewEntity;

public class PostRecyclerViewAdapter
		extends RecyclerView.Adapter<PostRecyclerViewAdapter.MyViewHolder>
{
	private List<PostViewEntity> postViewEntities;

	public PostRecyclerViewAdapter(List<PostViewEntity> postViewEntities)
	{
		this.postViewEntities = postViewEntities;
	}

	public void setPostViewEntities(List<PostViewEntity> posts)
	{
		postViewEntities = posts;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		CardView root = (CardView) LayoutInflater.from(parent.getContext())
				.inflate(R.layout.post_layout, parent, false);

		return new MyViewHolder(root);

	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
	{
		holder.title.setText(postViewEntities.get(position).title);
	}

	@Override
	public int getItemCount()
	{
		return postViewEntities.size();
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder
	{
		public TextView title;

		public MyViewHolder(CardView root)
		{
			super(root);
			this.title = root.findViewById(R.id.post_title);
		}
	}

}
