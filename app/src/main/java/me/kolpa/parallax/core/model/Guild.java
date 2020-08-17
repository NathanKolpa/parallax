package me.kolpa.parallax.core.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Guild extends ViewModel
{
	private MutableLiveData<String> name = new MutableLiveData<>(), fullName = new MutableLiveData<>();
	private MutableLiveData<Integer> subscriberCount = new MutableLiveData<>();

	public Guild(String name, int subscriberCount)
	{
		setName(name);
		setSubscriberCount(subscriberCount);
	}

	public LiveData<String> getName()
	{
		return name;
	}

	public LiveData<String> getFullName()
	{
		return fullName;
	}

	public void setName(String name)
	{
		this.name.setValue(name);
		fullName.setValue("+" + name);
	}

	public LiveData<Integer> getSubscriberCount()
	{
		return subscriberCount;
	}

	public void setSubscriberCount(int subscriberCount)
	{
		this.subscriberCount.setValue(subscriberCount);
	}
}
