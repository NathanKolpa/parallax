package me.kolpa.parallaxcore.domain.entities;

public class Guild
{
	private String name;
	private int subscriberCount;

	public Guild(String name, int subscriberCount)
	{
		setName(name);
		setSubscriberCount(subscriberCount);
	}

	public String getName()
	{
		return name;
	}

	public String getFullName()
	{
		return "+" + getName();
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSubscriberCount()
	{
		return subscriberCount;
	}

	public void setSubscriberCount(int subscriberCount)
	{
		this.subscriberCount = subscriberCount;
	}
}
