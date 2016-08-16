package calculation;

import model.Circle;
import model.Point;

/**
 * Created by anton on 15.08.16.
 */
public class DetectionNearby implements Detection<Circle, Point> {

    @Override
    public boolean detect(final Circle circle, final Point point) {
        return (Math.pow(circle.getCenterX() - point.getX(), 2)
                + Math.pow(circle.getCenterY() - point.getY(), 2)
                <= Math.pow(circle.getRadius(), 2));
    }
}
