import java.util.*;
import java.io.*;

class Solution
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Point[] points;
    private static PriorityQueue<Edge> pq;
    private static int[] parents;
    private static int N;

    public static void main(String args[]) throws Exception
    {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");

            N = Integer.parseInt(br.readLine());
            points = new Point[N];
            parents = new int[N];
            pq = new PriorityQueue<>();

            for(int i = 0; i < N; i++) {
                parents[i] = i;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                points[i] = new Point(Double.parseDouble(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                points[i].y = Double.parseDouble(st.nextToken());
                for(int j = 0; j < i; j++) {
                    double xDistance = Math.abs(points[i].x - points[j].x);
                    double yDistance = Math.abs(points[i].y - points[j].y);

                    double distance = xDistance * xDistance + yDistance * yDistance;
                    pq.offer(new Edge(i, j, distance));
                }
            }

            double sum = kruskal();
            double E = Double.parseDouble(br.readLine());
            sb.append(Math.round(sum * E));



            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static double kruskal() {
        int count = 0;
        double sum = 0;

        while(!pq.isEmpty()) {
            Edge current = pq.poll();

            if(count == N - 1) break;

            boolean result = union(current.a, current.b);
            if(result) {
                count++;
                sum += current.distance;
            }
        }

        return sum;
    }

    private static int find(int x) {
        if(parents[x] == x) return parents[x];

        return parents[x] = find(parents[x]);
    }

    private static boolean union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX == parentY) return false;

        if(parentX <= parentY) {
            parents[parentY] = parentX;
        } else {
            parents[parentX] = parentY;
        }

        return true;
    }

    private static class Point {
        public double x, y;

        public Point(double x) {
            this.x = x;
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int a, b;
        public double distance;

        public Edge(int a, int b, double distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}