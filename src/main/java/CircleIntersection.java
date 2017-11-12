import java.math.BigDecimal;

public class CircleIntersection {

    public static void main(String[] args) {

        float result = getCirclesIntersection(1, 2, 3, 2, 6, 8, 0, 1, -2, -2);
        if (result == -1) {
            System.out.println("This circles don't intersect");
        }
        if (result == 0) {
            System.out.println("This circles have already intersected on start position");
        }
        if (result > 0) {
            System.out.println("Circles will intersect in " + result + " time units.");
        }
    }

    public static float getCirclesIntersection(float r1, float r2, float x1, float y1,
                                               float x2, float y2, float vx1, float vy1,
                                               float vx2, float vy2) {
        // if circles have the same centers coordinates and the same radiuses it is imposition
        if (r1 == r2 && x1 == x2 && y1 == y2) {
            return 0;
        }
        // next three lines we find square distance between centers of the circle and square sum
        // and subtraction of radiuses, square because it is faster then find a square root in formula of
        // distance between two points (sqrt((x2-x1)^2 - (y2-y1)^2))
        float distanceBetweenCentersInSquare = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        float radiusSubtractionInSquare = (r1 - r2) * (r1 - r2);
        float radiusSumInSquare = (r1 + r2) * (r1 + r2);
        // if next conditions is true it means that circles are intersect
        if (radiusSubtractionInSquare <= distanceBetweenCentersInSquare &&
                distanceBetweenCentersInSquare <= radiusSumInSquare) {
            return 0;
        }
        // now we know that circles don't intersect? so if they have the same speed, they won't intersect in future
        if (vx1 == vx2 && vy1 == vy2) {
            return -1;
        }
        // in next few lines we bind to one variable that we will use in future one of two variables that
        // we calculate before, we do it because formula must be changed depends on the next inequalities
        float distanceBetweenCentersIntersectionMomentInSquare;
        if (distanceBetweenCentersInSquare > radiusSumInSquare) {
            distanceBetweenCentersIntersectionMomentInSquare = radiusSumInSquare;
        } else {
            distanceBetweenCentersIntersectionMomentInSquare = radiusSubtractionInSquare;
        }
        // next variables is the free terms in the quadratic equation a*t^2 + b*t + c = 0;
        // we get this equation because we know that our circles will intersect in that moment when the distance
        // between their centers will be equals to sum or subtraction of radiuses
        float a = vx1 * vx1 + vy1 * vy1 + vx2 * vx2 + vy2 * vy2 - 2 * vx1 * vx2 - 2 * vy1 * vy2;
        float b = 2 * x1 * vx1 + 2 * y1 * vy1 + 2 * x2 * vx2 + 2 * y2 * vy2 - 2 * x1 * vx2 - 2 * x2 * vx1 - 2 * y1 * vy2 - 2 * y2 * vy1;
        float c = x1 * x1 + y1 * y1 + x2 * x2 + y2 * y2 - 2 * x1 * x2 - 2 * y1 * y2 - distanceBetweenCentersIntersectionMomentInSquare;
        // calculate discriminant for make some checks before doing next steps
        float discriminant = b * b - 4 * a * c;
        // whe discriminant is zero it means that our equation don't have any solve so that's why circles don't intersect
        if (discriminant < 0) {
            return -1;
        }
        // if discriminant biggest than zero we find two roots of our equation with the formula: ((-b + sqrt(D))/(2*a))
        if (discriminant != 0) {
            float rootOfDiscriminant = (float) Math.sqrt(discriminant);
            float firstRoot = (-b + rootOfDiscriminant) / (2 * a);
            float secondRoot = (-b - rootOfDiscriminant) / (2 * a);
            // if roots less than zero it means that we don't have intersection
            if (firstRoot < 0 && secondRoot < 0) {
                return -1;
            }
            if (firstRoot < secondRoot && firstRoot > 0) {
                return round(firstRoot, 5);
            }
            return secondRoot > 0 ? round(secondRoot, 5) : round(firstRoot, 5);
        }
        // if discriminant is equals to zero we will get only one root? we can get this solve from previous
        // block of code but if we know that discriminants equals to zero we can provide less calculations
        float resultRoot = -b / (2 * a);
        return resultRoot > 0 ? round(resultRoot, 5) : -1;
    }

    //method for rounding of result
    public static float round(float d, int decimalPlace) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(d));
        bigDecimal = bigDecimal.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.floatValue();
    }
}
