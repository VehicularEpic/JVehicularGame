package io.github.epic.commons;

import java.util.ArrayList;
import java.util.List;

public class World {

    private double gravity = 10.0;
    private final List<Entity> entities = new ArrayList<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

}
