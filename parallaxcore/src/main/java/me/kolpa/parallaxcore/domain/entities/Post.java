package me.kolpa.parallaxcore.domain.entities;

public class Post
{
	private String id;
	private String title;
	private String thumbnailUrl;
	private int upvotes;
	private int comments;

	public Post(String id, String title, String thumbnailUrl, int upvotes, int comments)
	{
		this.comments = comments;
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

	public int getComments()
	{
		return comments;
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

	private void setComments(int comments)
	{
		this.comments = comments;
	}
}
