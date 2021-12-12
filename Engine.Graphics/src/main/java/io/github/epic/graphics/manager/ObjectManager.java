package io.github.epic.graphics.manager;

public interface ObjectManager<T> {

    T save(String name, T object);

    T get(String name);

}
