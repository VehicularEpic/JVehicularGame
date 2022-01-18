package io.github.epic.graphics.projection;

import io.github.epic.commons.Camera;
import org.joml.*;

import java.lang.Math;

public class CameraView {

    private Camera camera;

    public CameraView() {
        this(new Camera());
    }

    public CameraView(Camera camera) {
        this.camera = camera;
    }

    public float[] getMatrix() {
        Matrix4d matrix4d = new Matrix4d();
        matrix4d = matrix4d.translate(-camera.getX(), -camera.getY(), -camera.getZ());

        Quaterniond rotationX = new Quaterniond(
                0.0, 0.0, Math.sin(camera.getRotationX() / 2.0), Math.cos(camera.getRotationX() / 2.0)
        );
        Quaterniond rotationY = new Quaterniond(
                Math.sin(-camera.getRotationY() / 2.0), 0.0, 0.0, Math.cos(-camera.getRotationY() / 2.0)
        );
        Quaterniond rotationZ = new Quaterniond(
                0.0, Math.sin(-camera.getRotationZ() / 2.0), 0.0, Math.cos(-camera.getRotationZ() / 2.0)
        );

        return matrix4d
                .rotateLocal(rotationX)
                .rotateLocal(rotationY)
                .rotateLocal(rotationZ)
                .get(new float[16]);
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

}
