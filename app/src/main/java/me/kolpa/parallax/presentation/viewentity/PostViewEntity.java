package me.kolpa.parallax.presentation.viewentity;

public class PostViewEntity
{
	public String id;
	public String title;
	public String thumbnailUrl;
	public int upvotes;
	public int comments;


	public PostViewEntity(String id, String title, String thumbnailUrl, int upvotes, int comments)
	{
		this.id = id;
		this.title = title;
		this.thumbnailUrl = thumbnailUrl;
		this.upvotes = upvotes;
		this.comments = comments;
	}
}
