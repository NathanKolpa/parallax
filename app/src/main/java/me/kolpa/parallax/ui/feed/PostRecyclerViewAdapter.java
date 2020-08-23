package me.kolpa.parallax.ui.feed;

import android.annotation.SuppressLint;
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

	@SuppressLint("SetTextI18n")
	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
	{
		PostViewEntity post = postViewEntities.get(position);

		holder.title.setText(post.title);
		holder.upvotes.setText(post.upvotes + "");
		holder.comments.setText(post.comments + "");
	}

	@Override
	public int getItemCount()
	{
		return postViewEntities.size();
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder
	{
		public TextView title;
		public TextView upvotes;
		public TextView comments;

		public MyViewHolder(CardView root)
		{
			super(root);
			this.title = root.findViewById(R.id.post_title);
			this.upvotes = root.findViewById(R.id.post_upvotes);
			this.comments = root.findViewById(R.id.post_comments);
		}
	}

}
