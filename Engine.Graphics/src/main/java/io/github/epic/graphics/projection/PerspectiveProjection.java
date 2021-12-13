package io.github.epic.graphics.projection;

import org.joml.Matrix4d;

public final class PerspectiveProjection implements ProjectionStrategy {

    private double fovY;

    private double zNear;
    private double zFar;

    public PerspectiveProjection() {
        this(70.d);
    }

    public PerspectiveProjection(double fovY) {
        this(fovY, 10.d);
    }

    public PerspectiveProjection(double fovY, double zNear) {
        this(fovY, zNear, 1000.d);
    }

    public PerspectiveProjection(double fovY, double zNear, double zFar) {
        this.fovY = fovY;
        this.zNear = zNear;
        this.zFar = zFar;
    }

    @Override
    public float[] project(int width, int height) {
        float ratio = width / (float) Math.max(height, 1);
        Matrix4d matrix = new Matrix4d()
                .perspectiveLH(fovY, ratio, zNear, zFar);

        return matrix.get(new float[16]);
    }

    public void setFieldOfView(double fovY) {
        this.fovY = fovY;
    }

    public void setNearPlane(double zNear) {
        this.zNear = zNear;
    }

    public void setFarPlane(double zFar) {
        this.zFar = zFar;
    }

}
