package io.github.epic.core.registry;

import java.util.HashMap;
import java.util.Map;

public class BaseObjectManager<T> implements ObjectManager<T> {

    final Map<String, T> registry = new HashMap<>();

    @Override
    public final T save(String name, T object) {
        if (registry.containsKey(name))
            throw new IllegalArgumentException(
                    String.format("Registry with the name '%s' already exists!", name)
            );

        registry.put(name, object);
        return registry.get(name);
    }

    @Override
    public final T get(String name) {
        return registry.get(name);
    }

}
