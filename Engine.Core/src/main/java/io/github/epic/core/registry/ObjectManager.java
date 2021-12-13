package io.github.epic.core.registry;

public interface ObjectManager<T> {

    T save(String name, T object);

    T get(String name);

}
