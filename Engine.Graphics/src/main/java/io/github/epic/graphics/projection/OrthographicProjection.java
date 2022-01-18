package io.github.epic.graphics.projection;

import org.joml.Matrix4d;

public final class OrthographicProjection implements ProjectionStrategy {

    private double zNear;
    private double zFar;

    private double scale = 1.0;

    public OrthographicProjection() {
        this(1000.d);
    }

    public OrthographicProjection(double zNear) {
        this(-zNear, +zNear);
    }

    public OrthographicProjection(double zNear, double zFar) {
        this.zNear = zNear;
        this.zFar = zFar;
    }

    @Override
    public float[] project(int width, int height) {
        Matrix4d matrix = new Matrix4d().orthoLH(
                -width, width, -height, height, zNear, zFar
        );

        matrix = matrix.scale(scale);
        return matrix.get(new float[16]);
    }

    public void setNearPlane(double zNear) {
        this.zNear = zNear;
    }

    public void setFarPlane(double zFar) {
        this.zFar = zFar;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

}
