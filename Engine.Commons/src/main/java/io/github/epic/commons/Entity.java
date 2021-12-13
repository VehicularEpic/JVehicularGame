package io.github.epic.commons;

public class Entity {

    private final String name;

    private double x;
    private double y;
    private double z;

    private double rotationX;
    private double rotationY;
    private double rotationZ;

    public Entity(String name) {
        this.name = name;
    }

    public void translation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void translate(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void rotation(double x, double y, double z) {
        this.rotationX = x;
        this.rotationY = y;
        this.rotationZ = z;
    }

    public void rotate(double x, double y, double z) {
        this.rotationX += x;
        this.rotationY += y;
        this.rotationZ += z;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getRotationX() {
        return rotationX;
    }

    public void setRotationX(double rotationX) {
        this.rotationX = rotationX;
    }

    public double getRotationY() {
        return rotationY;
    }

    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

    public double getRotationZ() {
        return rotationZ;
    }

    public void setRotationZ(double rotationZ) {
        this.rotationZ = rotationZ;
    }

}
