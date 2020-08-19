package me.kolpa.parallaxcore.domain.repository.impl.reactive.store;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface ReactiveStore<Key, Value>
{
	void addAll(List<Value> values);
	void replaceAll(List<Value> values);
	void setValue(Value value);

	boolean hasValue(Key key);

	Observable<Value> getValue(Key key);
	Observable<List<Value>> getAll();
}
