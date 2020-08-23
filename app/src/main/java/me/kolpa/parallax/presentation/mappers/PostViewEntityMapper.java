package me.kolpa.parallax.presentation.mappers;

import java.util.ArrayList;
import java.util.List;

import me.kolpa.parallax.presentation.viewentity.PostViewEntity;
import me.kolpa.parallaxcore.domain.entities.Post;

public class PostViewEntityMapper
{
	public PostViewEntity mapViewEntity(Post post)
	{
		return new PostViewEntity(post.getId(), post.getTitle(), post.getThumbnailUrl(), post.getUpvotes());
	}

	public List<PostViewEntity> mapManyViewEntity(List<Post> posts)
	{
		ArrayList<PostViewEntity> postViewEntities = new ArrayList<>(posts.size());

		for(Post post : posts)
		{
			postViewEntities.add(mapViewEntity(post));
		}

		return postViewEntities;
	}
}
