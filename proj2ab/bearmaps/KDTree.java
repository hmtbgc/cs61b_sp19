package bearmaps;

import java.util.List;

public class KDTree implements PointSet{

    private class Node {
        public Point point;
        public Node left;
        public Node right;

        public Node(Point p) {
            this.point = p;
            left = null;
            right = null;
        }
    }

    private Node add(Node root, Point point, boolean compareX) {
        if (root == null) return new Node(point);
        if (compareX) {
            if (point.getX() >= root.point.getX()) {
                root.right = add(root.right, point, !compareX);
            }
            else {
                root.left = add(root.left, point, !compareX);
            }
        } else {
            if (point.getY() >= root.point.getY()) {
                root.right = add(root.right, point, !compareX);
            }
            else {
                root.left = add(root.left, point, !compareX);
            }
        }
        return root;
    }

    public Node root;

    public KDTree(List<Point> points) {
        root = new Node(points.get(0));
        for (int i = 1; i < points.size(); ++i) {
            root = add(root, points.get(i), true);
        }
    }


    @Override
    public Point nearest(double x, double y) {
        Point object = new Point(x, y);
        Node best = root;
        return nearest(root, object, best, true).point;
    }

    private Node nearest(Node root, Point object, Node best, boolean compareX) {
        if (root == null) return best;
        if (Point.distance(root.point, object) < Point.distance(best.point, object)) {
            best = root;
        }
        Node goodSide, badSide;
        if (compareX) {
            if (root.point.getX() >= object.getX()) {
                goodSide = root.left;
                badSide = root.right;
            }
            else {
                goodSide = root.right;
                badSide = root.left;
            }
        } else {
            if (root.point.getY() >= object.getY()) {
                goodSide = root.left;
                badSide = root.right;
            }
            else {
                goodSide = root.right;
                badSide = root.left;
            }
        }
        best = nearest(goodSide, object, best, !compareX);
//        best = nearest(badSide, object, best, !compareX);
        double bestDistance = Point.distance(object, best.point);
        double currentDeltaX = Math.pow(root.point.getX() - object.getX(), 2);
        double currentDeltaY = Math.pow(root.point.getY() - object.getY(), 2);
        if (compareX) {
            if (currentDeltaX < bestDistance) {
                best = nearest(badSide, object, best, !compareX);
            }
        } else {
            if (currentDeltaY < bestDistance) {
                best = nearest(badSide, object, best, !compareX);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3,3);
        Point p5 = new Point(1,5);
        Point p6 = new Point(4,4);

        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point ret1 = nn.nearest(0, 7);
        Point ret2 = nn.nearest(2, 5);
        Point ret3 = nn.nearest(3, 1);
        Point ret4 = nn.nearest(3, 2);
        Point ret5 = nn.nearest(4, 6);
        System.out.println(ret1);
        System.out.println(ret2);
        System.out.println(ret3);
        System.out.println(ret4);
        System.out.println(ret5);

    }
}
