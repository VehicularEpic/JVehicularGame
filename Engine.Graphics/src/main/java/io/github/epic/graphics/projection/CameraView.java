package io.github.epic.graphics.projection;

import io.github.epic.commons.Camera;
import org.joml.*;

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

        matrix4d = matrix4d.rotate(camera.getRotationX(), 0.d, 0.d, 1.d);
        matrix4d = matrix4d.rotate(camera.getRotationY(), 1.d, 0.d, 0.d);
        matrix4d = matrix4d.rotate(camera.getRotationZ(), 0.d, 1.d, 0.d);

        return matrix4d.get(new float[16]);
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

}
