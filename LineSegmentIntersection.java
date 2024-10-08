import java.util.Scanner;
class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class LineSegmentIntersection {
    static boolean onSegment(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
            q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y)) {
            return true;
        }
        return false;
    }
    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }
    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
        if (o1 != o2 && o3 != o4) return true;
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;
        return false;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coordinates for the first line segment:");
        System.out.print("p1.x: ");
        int p1x = scanner.nextInt();
        System.out.print("p1.y: ");
        int p1y = scanner.nextInt();
        System.out.print("q1.x: ");
        int q1x = scanner.nextInt();
        System.out.print("q1.y: ");
        int q1y = scanner.nextInt();
        System.out.println("Enter coordinates for the second line segment:");
        System.out.print("p2.x: ");
        int p2x = scanner.nextInt();
        System.out.print("p2.y: ");
        int p2y = scanner.nextInt();
        System.out.print("q2.x: ");
        int q2x = scanner.nextInt();
        System.out.print("q2.y: ");
        int q2y = scanner.nextInt();
        Point p1 = new Point(p1x, p1y);
        Point q1 = new Point(q1x, q1y);
        Point p2 = new Point(p2x, p2y);
        Point q2 = new Point(q2x, q2y);
        if (doIntersect(p1, q1, p2, q2)) {
            System.out.println("Line segments are intersecting.");
        } else {
            System.out.println("Line segments are not intersecting.");
        }
        scanner.close();
    }
}
