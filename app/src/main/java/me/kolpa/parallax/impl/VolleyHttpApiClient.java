package me.kolpa.parallax.impl;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.StringRequest;

import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.kolpa.parallaxinfrastructure.service.http.HttpApiClient;

public class VolleyHttpApiClient implements HttpApiClient
{
	private final RequestQueue requestQueue;
	private final URL basePath;

	public VolleyHttpApiClient(RequestQueue requestQueue, URL basePath)
	{
		this.requestQueue = requestQueue;
		this.basePath = basePath;
	}

	public static VolleyHttpApiClient create(String basePath)
	{
		RequestQueue queue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack()));

		queue.start();

		try
		{
			return new VolleyHttpApiClient(queue, new URL(basePath));
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public Single<String> get(String path)
	{
		return Single.create((emitter) ->
		{
			StringRequest stringRequest = new StringRequest(
					Request.Method.GET,
					getUrl(path).toString(),
					response -> handleSuccess(emitter, response),
					error -> handleError(emitter, error));

			requestQueue.add(stringRequest);
		});
	}

	private void handleSuccess(SingleEmitter<String> source, String response)
	{
		source.onSuccess(response);
	}

	private void handleError(SingleEmitter<String> source, VolleyError response)
	{
		source.onError(response);
	}

	private URL getUrl(String path)
	{
		try
		{
			return new URL(basePath, path);
		}
		catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}
}
