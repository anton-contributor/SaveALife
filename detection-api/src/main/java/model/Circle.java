package model;

/**
 * Created by anton on 12.08.16.
 */
public class Circle {

    private Double centerX;

    private Double centerY;

    private Double radius;

    public Circle() {
    }

    public Double getCenterX() {
        return centerX;
    }

    public void setCenterX(Double centerX) {
        this.centerX = centerX;
    }

    public Double getCenterY() {
        return centerY;
    }

    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (centerX != null ? !centerX.equals(circle.centerX) : circle.centerX != null) return false;
        if (centerY != null ? !centerY.equals(circle.centerY) : circle.centerY != null) return false;
        return radius != null ? radius.equals(circle.radius) : circle.radius == null;

    }

    @Override
    public int hashCode() {
        int result = centerX != null ? centerX.hashCode() : 0;
        result = 31 * result + (centerY != null ? centerY.hashCode() : 0);
        result = 31 * result + (radius != null ? radius.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "detection.Circle{" +
                "centerX=" + centerX +
                ", centerY=" + centerY +
                ", radius=" + radius +
                '}';
    }
}
