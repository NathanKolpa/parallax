package me.kolpa.parallaxinfrastructure.service.http;

import io.reactivex.rxjava3.core.Single;

public interface HttpApiClient
{
	Single<String> get(String path);
}
