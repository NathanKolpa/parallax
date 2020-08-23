package me.kolpa.parallax.ui.home;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.kolpa.parallax.presentation.mappers.PostViewEntityMapper;
import me.kolpa.parallax.presentation.viewentity.PostViewEntity;
import me.kolpa.parallaxcore.domain.usecases.post.HomeFeedInteractor;

public class HomeViewModel extends ViewModel
{
	private CompositeDisposable disposables = new CompositeDisposable();
	private final HomeFeedInteractor homeFeedInteractor;
	private final PostViewEntityMapper postViewEntityMapper;

	private final MutableLiveData<List<PostViewEntity>> postsLiveData = new MutableLiveData<>();

	@Inject
	public HomeViewModel(HomeFeedInteractor homeFeedInteractor, PostViewEntityMapper postViewEntityMapper)
	{
		this.homeFeedInteractor = homeFeedInteractor;
		this.postViewEntityMapper = postViewEntityMapper;

		disposables.add(configureHomeFeed());
	}

	private Disposable configureHomeFeed()
	{
		return homeFeedInteractor.getHomeFeed()
				.map(postViewEntityMapper::mapManyViewEntity)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(postsLiveData::setValue);
	}


	@Override
	protected void onCleared()
	{
		super.onCleared();
		disposables.dispose();
	}

	public LiveData<List<PostViewEntity>> getPostsLiveData()
	{
		return postsLiveData;
	}
}
