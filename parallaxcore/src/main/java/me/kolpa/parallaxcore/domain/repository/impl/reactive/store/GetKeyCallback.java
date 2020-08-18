package me.kolpa.parallaxcore.domain.repository.impl.reactive.store;

public interface GetKeyCallback<Key, Value>
{
	Key getKey(Value value);
}
