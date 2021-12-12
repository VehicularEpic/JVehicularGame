package io.github.epic.commons;

public class Entity {

    private final String name;

    private double x;
    private double y;
    private double z;

    private double xy;
    private double xz;
    private double zy;

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

    public void rotation(double xy, double xz, double zy) {
        this.xy = Math.toRadians(xy);
        this.xz = Math.toRadians(xz);
        this.zy = Math.toRadians(zy);
    }

    public void rotate(double xy, double xz, double zy) {
        this.xy += xy;
        this.xz += xz;
        this.zy += zy;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getXy() {
        return xy;
    }

    public double getXz() {
        return xz;
    }

    public double getZy() {
        return zy;
    }

}
