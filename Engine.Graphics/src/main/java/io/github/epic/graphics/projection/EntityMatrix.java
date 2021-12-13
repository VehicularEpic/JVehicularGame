package io.github.epic.graphics.projection;

import io.github.epic.commons.Entity;
import org.joml.Matrix4d;

public class EntityMatrix {

    private final Entity entity;

    public EntityMatrix(Entity entity) {
        this.entity = entity;
    }

    public float[] getValues() {
        Matrix4d matrix = new Matrix4d();

        matrix = matrix.translate(entity.getX(), entity.getY(), entity.getZ());

        // TODO: X and Y rotations
        matrix = matrix.rotate(entity.getRotationZ(), 0.f, -1.f, 0.f);

        return matrix.get(new float[16]);
    }

}
