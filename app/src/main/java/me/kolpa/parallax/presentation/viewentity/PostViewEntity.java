package me.kolpa.parallax.presentation.viewentity;

public class PostViewEntity
{
	public String id;
	public String title;
	public String thumbnailUrl;
	public int upvotes;

	public PostViewEntity(String id, String title, String thumbnailUrl, int upvotes)
	{
		this.id = id;
		this.title = title;
		this.thumbnailUrl = thumbnailUrl;
		this.upvotes = upvotes;
	}
}
