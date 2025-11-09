import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Soil {
    private int[][] grid;
    private int n;
    private UnionFind uf;
    private int virtualTop;
    private int virtualBottom;

    public Soil(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        
        String firstLine = scanner.nextLine();
        String[] firstRow = firstLine.trim().split("\\s+");
        n = firstRow.length;
       
        grid = new int[n][n];
        
        // Process first row
        for (int j = 0; j < n; j++) {
            grid[0][j] = Integer.parseInt(firstRow[j]);
        }
        
        // Process remaining rows
        for (int i = 1; i < n; i++) {
            String line = scanner.nextLine();
            String[] row = line.trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(row[j]);
            }
        }
        scanner.close();
        
        // Initialize Union find with n*n + 2 (for virtual top and bottom)
        uf = new UnionFind(n * n + 2);
        virtualTop = n * n;
        virtualBottom = n * n + 1;
        
        // Connect virtual top to top row's draining cells
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                uf.union(virtualTop, j);
            }
        }
        
        // Connect virtual bottom to bottom row's draining cells
        for (int j = 0; j < n; j++) {
            if (grid[n-1][j] == 1) {
                uf.union(virtualBottom, (n - 1) * n + j);
            }
        }
        
        // Connect adjacent draining cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // Check right neighbor
                    if (j < n - 1 && grid[i][j+1] == 1) {
                        uf.union(i * n + j, i * n + j + 1);
                    }
            
                    // Check bottom neighbor
                    if (i < n-1 && grid[i+1][j] == 1) {
                        uf.union(i * n + j, (i+1) * n + j);
                    }
                }
            }
        }
    }

    public boolean doesDrain() {
        return uf.connected(virtualTop, virtualBottom);
    }
}