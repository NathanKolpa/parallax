package me.kolpa.parallaxcore.domain.repository.impl.reactive.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class InMemoryReactiveStore<Key, Value> implements ReactiveStore<Key, Value>
{

	private final GetKeyCallback<Key, Value> getKeyCallback;

	private final Map<Key, Value> internalValues = new HashMap<>();
	private final Map<Key, Subject<Value>> subjectMap = new HashMap<>();

	private final Subject<List<Value>> allValuesObservable = PublishSubject.create();

	public InMemoryReactiveStore(GetKeyCallback<Key, Value> getKeyCallback)
	{
		this.getKeyCallback = getKeyCallback;
	}

	@Override
	public void addAll(List<Value> values)
	{
		for (Value value : values)
		{
			Key key = getKeyCallback.getKey(value);

			internalValues.put(key, value);
			if (!subjectMap.containsKey(key))
			{
				createAndStoreNewSubjectForKey(key);
			}
		}

		updateObservable();
	}

	@Override
	public void replaceAll(List<Value> values)
	{
		internalValues.clear();
		addAll(values);

		updateObservable();
	}

	@Override
	public void setValue(Value value)
	{
		Key key = getKeyCallback.getKey(value);

		if (!subjectMap.containsKey(key))
		{
			createAndStoreNewSubjectForKey(key);
		}

		internalValues.put(key, value);

		allValuesObservable.onNext(new ArrayList<>(internalValues.values()));
		subjectMap.get(key).onNext(value);
	}

	@Override
	public boolean hasValue(Key key)
	{
		return internalValues.containsKey(key);
	}

	@Override
	public List<Value> getValues()
	{
		return new ArrayList<>(internalValues.values());
	}

	@Override
	public Observable<Value> getValue(Key key)
	{
		return Observable.defer(() -> createAndStoreNewSubjectForKey(key));
	}

	@Override
	public Observable<List<Value>> getAll()
	{
		return allValuesObservable;
	}

	private void updateObservable()
	{
		allValuesObservable.onNext(new ArrayList<>(internalValues.values()));

		for (Key key : internalValues.keySet())
		{
			final Subject<Value> processor;
			synchronized (subjectMap)
			{
				processor = subjectMap.get(key);
			}

			processor.onNext(internalValues.get(key));
		}
	}

	private Subject<Value> createAndStoreNewSubjectForKey(final Key key)
	{
		final Subject<Value> processor = PublishSubject.<Value>create().toSerialized();
		synchronized (subjectMap)
		{
			subjectMap.put(key, processor);
		}
		return processor;
	}
}
