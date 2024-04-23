package ttntupdate;
import java.util.*;
public class GTS1 {    


    public static class TourResult {
        List<Integer> tour;
        int cost;

        public TourResult(List<Integer> tour, int cost) {
            this.tour = tour;
            this.cost = cost;
        }
    }

    public static TourResult findTour(int n, int u, int[][] c) {
        boolean[] visited = new boolean[n];
        int u_c = u - 1; // Vì chỉ số trong mảng c bắt đầu từ 0 
												// nên đỉnh lùi lại 1 đơn vị
        int v = u_c;
        List<Integer> tour = new ArrayList<>();
        tour.add(v+1);
        visited[v] = true;
        int cost = 0;

        for (int i = 0; i < n - 1; i++) {
            int w = -1;
            int minCost = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (c[v][j] < minCost && !visited[j]) {
                    minCost = c[v][j];
                    w = j;
                }
            }

            tour.add(w+1);
            cost += minCost;
            visited[w] = true;
            v = w;
            
        }
    cost += c[v][u_c];
    tour.add(u);
    return new TourResult(tour, cost);
}

public static void main(String[] args) {
    // Cost matrix
    int[][] c = {
        {0, 20, 42, 31, 6, 24},
        {10, 0, 17, 6, 35, 18},
        {25, 5, 0, 27, 14, 9},
        {12, 9, 24, 0, 30, 12},
        {14, 7, 21, 15, 0, 38},
        {40, 15, 16, 5, 20, 0}
    };
    
    Scanner scanner = new Scanner(System.in);
    List<Integer> resultTourMan = new ArrayList<>();
    // System.out.print("Nhập thành phố xuất phát u =  ");
    // int u = scanner.nextInt();

    int u = 2;
    System.out.println("City to start it is: " + u);
    
    TourResult result = findTour(6, u, c);

    resultTourMan = result.tour;
    System.out.println("Cost optimize is: ");
    // System.out.println("Tour: " + result.tour);
    int sizeList = resultTourMan.size();
    int currentIndex = 0;


    // Duyệt đường đi ngắn nhất của ma trận
    for (int x : resultTourMan) {
        if (currentIndex != sizeList - 1) {
        System.out.print(x + " -> ");
        } else {
        System.out.println(x );
        }
        
        currentIndex++;

    }
    
    System.out.printf("\n");

    System.out.println("Cost: " + result.cost);
	}
}





