package me.kolpa.parallaxcore.domain.entities;

public class Post
{
	private String id;
	private String title;
	private String thumbnailUrl;
	private int upvotes;

	public Post(String id, String title, String thumbnailUrl, int upvotes)
	{
		setUpvotes(upvotes);
		setId(id);
		setTitle(title);
		setThumbnailUrl(thumbnailUrl);
	}

	public String getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getThumbnailUrl()
	{
		return thumbnailUrl;
	}

	public int getUpvotes()
	{
		return upvotes;
	}

	private void setUpvotes(int upvotes)
	{
		this.upvotes = upvotes;
	}

	private void setTitle(String title)
	{
		this.title = title;
	}

	private void setId(String id)
	{
		this.id = id;
	}

	private void setThumbnailUrl(String thumbnailUrl)
	{
		this.thumbnailUrl = thumbnailUrl;
	}
}
