package me.kolpa.parallaxcore.domain.repository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import me.kolpa.parallaxcore.domain.entities.Post;

public interface PostRepository extends Repository
{

	Flowable<List<Post>> getHomeFeed();
	Completable fetchHomeFeed();
}
