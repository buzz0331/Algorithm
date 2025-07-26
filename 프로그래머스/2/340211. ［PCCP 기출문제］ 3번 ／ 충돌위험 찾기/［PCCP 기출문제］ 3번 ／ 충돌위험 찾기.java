import java.util.*;

class Solution {
    
    private ArrayList<ArrayList<Point>> list;
    
    public int solution(int[][] points, int[][] routes) {    
        int n = points.length; // 포인트 수
        int x = routes.length; // 로봇의 수
        
        list = new ArrayList<>(x);
        for(int i = 0; i < x; i++) {
            ArrayList<Point> posList = new ArrayList<>();
            
            int[] route = routes[i]; // i번 로봇이 방문해야 하는 포인트들
            
            // 시작 위치 먼저 기록
            int[] first = points[route[0] - 1];
            posList.add(new Point(first[0], first[1]));
            
            for(int j = 0; j < route.length - 1; j++) {
                int pointIdx = route[j]; // 방문해야 하는 포인트 인덱스
                int targetPointIdx = route[j + 1];
                int[] startPoint = points[pointIdx - 1];
                int[] targetPoint = points[targetPointIdx - 1];
                posList.addAll(calRoutes(startPoint, targetPoint));
            }
    
            list.add(posList);
        }
        
        return countCorrupt(x);
    }
    
    private int countCorrupt(int x) {
        int count = 0;
        
        int maxCount = list.stream()
            .mapToInt(p -> p.size())
            .max()
            .orElse(0);
        
        int second = 0;
        
        for(int i = 0; second < maxCount; second++) {
            Set<Point> visited = new HashSet<Point>();
            Set<Point> duplicated = new HashSet<>();
            
            
            // 같은 시간(second)에 로봇 위치를 체크
            for (ArrayList<Point> p : list) {
                if (p.size() > second) {
                    Point current = p.get(second);
                    if (!visited.add(current)) {
                        // 이미 있는 좌표면 중복 집합에 추가
                        duplicated.add(current);
                    }
                }
            }
            
            // 중복된 좌표 개수만큼 count 추가
            count += duplicated.size();
        }
        
        return count;
    }
    
    // 최단경로 추적
    private ArrayList<Point> calRoutes(int[] startPoint, int[] targetPoint) {
        ArrayList<Point> posList = new ArrayList<>();
        int startRow = startPoint[0];
        int targetRow = targetPoint[0];
        int startCol = startPoint[1];
        int targetCol = targetPoint[1];
        
        while(startRow < targetRow) {
            posList.add(new Point(startRow + 1, startCol));
            startRow++;
        }
        while(startRow > targetRow) {
            posList.add(new Point(startRow - 1, startCol));
            startRow--;
        }
        while(startCol < targetCol) {
            posList.add(new Point(startRow, startCol + 1));
            startCol++;
        }
        while(startCol > targetCol) {
            posList.add(new Point(startRow, startCol - 1));
            startCol--;
        }
        return posList;
    }
    
    private class Point {
        public int x;
        public int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object obj) {
            Point other = (Point) obj;
            if(this.x == other.x) {
                if(this.y == other.y) {
                    return true;
                }
            }
            
            return false;
        }
        
        @Override
        public int hashCode() {
            return this.x + this.y;
        }
    }
}