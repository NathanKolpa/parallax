package me.kolpa.parallaxinfrastructure.service.http.v1.response;

public class GuildResponse
{
	public String name;
	public String color;

	public int modsCount;
	public int subscriberCount;

	public String description;
	public String descriptionHtml;

	public String bannerUrl;
	public String profileUrl;

	public boolean isBanned;
	public boolean isPrivate;
	public boolean isRestricted;
	public boolean over18;
}
