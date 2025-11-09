package Assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Soil {
    private int n;
    private int[][] soilGrid;
    private UnionFind uf;

    // Constructor to read soil data from file
    public Soil(String filename) throws IOException {
        readSoilData(filename);
        uf = new UnionFind(n * n + 2); // Extra two for virtual top and bottom nodes
        connectSoilCells();
    }

    // Reads soil data from file and initializes the grid
    private void readSoilData(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        n = Integer.parseInt(br.readLine().trim());
        soilGrid = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < n; j++) {
                soilGrid[i][j] = line.charAt(j) - '0';
            }
        }

        br.close();
    }

    // Connects soil cells using Union-Find
    private void connectSoilCells() {
        int topVirtual = n * n;
        int bottomVirtual = n * n + 1;

        for (int i = 0; i < n; i++) {
            if (soilGrid[0][i] == 1) uf.union(topVirtual, i); // Connect top row to virtual top
            if (soilGrid[n - 1][i] == 1) uf.union(bottomVirtual, (n - 1) * n + i); // Connect bottom row
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (soilGrid[i][j] == 1) {
                    if (i > 0 && soilGrid[i - 1][j] == 1) uf.union(i * n + j, (i - 1) * n + j);
                    if (i < n - 1 && soilGrid[i + 1][j] == 1) uf.union(i * n + j, (i + 1) * n + j);
                    if (j > 0 && soilGrid[i][j - 1] == 1) uf.union(i * n + j, i * n + j - 1);
                    if (j < n - 1 && soilGrid[i][j + 1] == 1) uf.union(i * n + j, i * n + j + 1);
                }
            }
        }
    }

    // Determines if the soil allows water to drain
    public boolean doesDrain() {
        int topVirtual = n * n;
        int bottomVirtual = n * n + 1;
        return uf.connected(topVirtual, bottomVirtual);
    }
}
