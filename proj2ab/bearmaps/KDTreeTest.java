package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KDTreeTest {


    @Test
    public void testKDTree() {

        int num = 1000;
        int seed = 10;
        Random rand = new Random(seed);
        List<Point> currentPoints = new ArrayList<>();
        List<Point> testPoints = new ArrayList<>();

        for (int i = 0; i < num; ++i) {
            double x = rand.nextDouble() * 10;
            double y = rand.nextDouble() * 10;
            currentPoints.add(new Point(x, y));
        }

        for (int i = 0; i < num; ++i) {
            double x = rand.nextDouble() * 10;
            double y = rand.nextDouble() * 10;
            testPoints.add(new Point(x, y));
        }

        KDTree kd = new KDTree(currentPoints);
        NaivePointSet na = new NaivePointSet(currentPoints);
        List<Point> NaivePointSetAns = new ArrayList<>();
        List<Point> KDTreeAns = new ArrayList<>();

        for (Point p : testPoints) {
            NaivePointSetAns.add(na.nearest(p.getX(), p.getY()));
            KDTreeAns.add(kd.nearest(p.getX(), p.getY()));
        }

        for (int i = 0; i < NaivePointSetAns.size(); ++i) {
//            System.out.println(NaivePointSetAns.get(i));
//            System.out.println(KDTreeAns.get(i));
            assertEquals(NaivePointSetAns.get(i), KDTreeAns.get(i));
        }
    }


}
