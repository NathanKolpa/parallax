package me.kolpa.parallaxinfrastructure.service.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.rxjava3.core.Single;
import sun.misc.IOUtils;

public class HttpApiClient
{
	private final URL basePath;
	private final Gson gson = new GsonBuilder().create();

	public HttpApiClient(String basePath)
	{
		try
		{
			this.basePath = new URL(basePath);
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	public <T> Single<T> get(String path, Class<T> tClass)
	{
		return Single.create((singleSubscriber) ->
		{
			new Thread(() ->
			{
				HttpURLConnection conn = null;
				try
				{
					URI uri = joinUrl(path);
					conn = (HttpURLConnection) uri.toURL().openConnection();

					if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK)
					{
						singleSubscriber.onError(new IOException(
								"Could not establish http connection"));
						return;
					}

					InputStream inputStream = new BufferedInputStream(conn.getInputStream());
					Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");

					String jsonStr = scanner.hasNext() ? scanner.next() : "";
					T object = gson.fromJson(jsonStr, tClass);
					singleSubscriber.onSuccess(object);

					scanner.close();
					inputStream.close();
				}
				catch (Exception e)
				{
					singleSubscriber.onError(e);
				}
				finally
				{
					if (conn != null)
						conn.disconnect();
				}
			}).start();

		});
	}

	private URI joinUrl(String path)
	{
		try
		{
			return new URL(basePath, path).toURI();
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException(e);
		}
	}
}
