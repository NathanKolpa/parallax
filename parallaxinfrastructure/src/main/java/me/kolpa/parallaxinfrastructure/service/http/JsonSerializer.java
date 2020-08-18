package me.kolpa.parallaxinfrastructure.service.http;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonSerializer
{
	private Gson gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.create();

	public <T> T createFromJson(String json, Class<T> tClass)
	{
		return gson.fromJson(json, tClass);
	}
}
