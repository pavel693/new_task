import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CircleIntersectionTest {

    @Test
    public void circleDoNotIntersect1() {
        float result = CircleIntersection
                .getCirclesIntersection(2, 3, 4, 3, 17, 5, 2, 3, -1, -4);
        assertThat((-1f), is(result));
    }

    @Test
    public void circleDoNotIntersect2() {
        float result = CircleIntersection
                .getCirclesIntersection(2, 1, 3, 2, 3, 2, -1, -1, -1, -1);
        assertThat((-1f), is(result));
    }

    @Test
    public void circleDoNotIntersect3() {
        float result = CircleIntersection
                .getCirclesIntersection(1, 1, 2, 2, 4, 4, 0, 2, 0, 2);
        assertThat((-1f), is(result));
    }

    @Test
    public void circleDoNotIntersect4() {
        for (long i = 0; i < 1e7; i++) {
            float r1 = (float) (Math.random() * 1e2f + 1);
            float x1 = (float) (Math.random() * 1e8f + 1);
            float y1 = (float) (Math.random() * 1e8f + 1);
            float vx1 = (float) (Math.random() * 1e2f + 1);
            float vy1 = (float) (Math.random() * 1e2f + 1);
            float coefficient = (float) (Math.random() + 2);
            float result = CircleIntersection
                    .getCirclesIntersection(r1, r1 * coefficient, x1, y1, x1 * coefficient, y1 * coefficient, vx1, vy1, vx1 * coefficient, vy1 * coefficient);
            assertThat((-1f), is(result));
        }
    }

    @Test
    public void circleHaveAlreadyIntersected1() {
        float result = CircleIntersection
                .getCirclesIntersection(1, 2, 2, 2, 5, 2, -1, 1, 1, 1);
        assertThat((0f), is(result));
    }

    @Test
    public void circleHaveAlreadyIntersected2() {
        float result = CircleIntersection
                .getCirclesIntersection(2, 2, 4, 4, 7, 4, 1, 1, -1, -1);
        assertThat((0f), is(result));
    }

    @Test
    public void circleHaveAlreadyIntersected3() {
        float result = CircleIntersection
                .getCirclesIntersection(2, 2, 4, 4, 4, 4, 3, 3, -2, -2);
        assertThat((0f), is(result));
    }

    @Test
    public void circleHaveAlreadyIntersected4() {
        float result = CircleIntersection
                .getCirclesIntersection(3, 1, 3, 3, 5, 3, 0, 1, 0, -1);
        assertThat((0f), is(result));
    }

    @Test
    public void circleInTheMiddleOfOtherIntersect1() {
        float result = CircleIntersection
                .getCirclesIntersection(2, 1, 3, 2, 3, 2, -1, -1, -1, 2);
        assertThat((0.33333f), is(result));
    }

    @Test
    public void circleInTheMiddleOfOtherIntersect2() {
        float result = CircleIntersection
                .getCirclesIntersection(1e4f, 3e4f, 1e4f, 1e4f, 1e3f, 1e2f, 100, 100, -150, -150);
        assertThat((18.7399f), is(result));
    }

    @Test
    public void circleInTheMiddleOfOtherDoNotIntersect() {
        float result = CircleIntersection
                .getCirclesIntersection(4, 1, 3, 3, 4, 4, -1, -1, -1, -1);
        assertThat((-1f), is(result));
    }

    @Test
    public void circleIntersect1() {
        float result = CircleIntersection
                .getCirclesIntersection(1, 3, 3, 4, 4, 4, 0, 1, 0, -2);
        assertThat((0.57735f), is(result));
    }

    @Test
    public void circleIntersect2() {
        float result = CircleIntersection
                .getCirclesIntersection(1, 2, 3, 2, 8, 5, 0, 1, -2, 0);
        assertThat((1.27335f), is(result));
    }

    @Test
    public void circleIntersect3() {
        float result = CircleIntersection
                .getCirclesIntersection(100, 150, 50, 50, 1e8f, 1e8f, 50, 50, -30, -30);
        assertThat((1249999.4f), is(result));
    }

    @Test(expected = Exception.class)
    public void circleException1() {
        float result = CircleIntersection
                .getCirclesIntersection(100, 150, 50, 50, 1e8f, -1e8f, 50, 50, -30, -30);
    }

    @Test(expected = Exception.class)
    public void circleException2() {
        float result = CircleIntersection
                .getCirclesIntersection(100, -150, 50, 50, 1e8f, 1e8f, 50, 50, -30, -30);
    }
}
