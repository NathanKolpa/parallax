package me.kolpa.parallax.di;

import android.app.Application;

public class MyApplication extends Application
{
	public ApplicationComponent appComponent = DaggerApplicationComponent.create();
}
