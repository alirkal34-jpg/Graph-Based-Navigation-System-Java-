import java.io.IOException;
import java.util.Random;

// main class to compare algorithms
public class ShortestPathComparison {
    private Graph graph;
    private DFSAlgorithm dfs;
    private DFSShortestAlgorithm dfsShortest;
    private DijkstraAlgorithm dijkstra;

    public ShortestPathComparison(String csvFile) throws IOException {
        graph = CSVParser.parseCSV(csvFile);
        dfs = new DFSAlgorithm(graph);
        dfsShortest = new DFSShortestAlgorithm(graph);
        dijkstra = new DijkstraAlgorithm(graph);
    }

    // check if two cities are directly connected
    private boolean areDirectlyConnected(String city1, String city2) {
        int index1 = graph.getCityIndex(city1);
        int index2 = graph.getCityIndex(city2);
        if (index1 == -1 || index2 == -1) {
            return false;
        }
        int distance = graph.getDistance(index1, index2);
        return distance != 99999 && distance > 0;
    }

    // select 8 pairs of cities that are not directly connected
    private String[][] selectCityPairs() {
        List<String> cities = graph.getCities();
        String[][] pairs = new String[8][2];
        int count = 0;
        Random random = new Random(42);
        
        while (count < 8) {
            int index1 = random.nextInt(cities.size());
            int index2 = random.nextInt(cities.size());
            
            if (index1 != index2) {
                String city1 = cities.get(index1);
                String city2 = cities.get(index2);
                
                if (!areDirectlyConnected(city1, city2)) {
                    pairs[count][0] = city1;
                    pairs[count][1] = city2;
                    count++;
                }
            }
        }
        
        return pairs;
    }

    // helper method to print line of equals
    private static void printEqualsLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    // run all three algorithms on a city pair
    private void runComparison(String source, String destination) {
        System.out.println();
        printEqualsLine(100);
        System.out.println("COMPARISON: " + source + " -> " + destination);
        printEqualsLine(100);
        
        PathResult dfsResult = dfs.findPath(source, destination);
        PathResult dfsShortestResult = dfsShortest.findShortestPath(source, destination);
        PathResult dijkstraResult = dijkstra.findShortestPath(source, destination);
        
        System.out.println("\n1. DFS (Path Finder):");
        System.out.println("   Path: " + dfsResult.getPathString());
        if (dfsResult.getTotalDistance() > 0) {
            System.out.println("   Distance: " + dfsResult.getTotalDistance() + " km");
        } else {
            System.out.println("   Distance: No path");
        }
        double dfsTime = dfsResult.getExecutionTime() / 1000000.0;
        System.out.println("   Execution Time: " + dfsTime + " ms");
        
        System.out.println("\n2. Modified DFS (Shortest Path Finder):");
        System.out.println("   Path: " + dfsShortestResult.getPathString());
        if (dfsShortestResult.getTotalDistance() > 0) {
            System.out.println("   Distance: " + dfsShortestResult.getTotalDistance() + " km");
        } else {
            System.out.println("   Distance: No path");
        }
        double dfsShortestTime = dfsShortestResult.getExecutionTime() / 1000000.0;
        System.out.println("   Execution Time: " + dfsShortestTime + " ms");
        
        System.out.println("\n3. Dijkstra's Algorithm:");
        System.out.println("   Path: " + dijkstraResult.getPathString());
        if (dijkstraResult.getTotalDistance() > 0) {
            System.out.println("   Distance: " + dijkstraResult.getTotalDistance() + " km");
        } else {
            System.out.println("   Distance: No path");
        }
        double dijkstraTime = dijkstraResult.getExecutionTime() / 1000000.0;
        System.out.println("   Execution Time: " + dijkstraTime + " ms");
    }

    // generate comparison table
    private void generateComparisonTable(String[][] pairs) {
        System.out.println();
        System.out.println();
        printEqualsLine(150);
        System.out.println("COMPREHENSIVE COMPARISON TABLE");
        printEqualsLine(150);
        
        System.out.println("Source               | Destination          | DFS Distance    | DFS Time(ms) | DFS-Short Dist  | DFS-S Time(ms) | Dijkstra Dist   | Dijkstra Time(ms)");
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();
        
        long totalDFSTime = 0;
        long totalDFSShortestTime = 0;
        long totalDijkstraTime = 0;
        int validPaths = 0;
        
        for (int p = 0; p < pairs.length; p++) {
            String source = pairs[p][0];
            String destination = pairs[p][1];
            
            PathResult dfsResult = dfs.findPath(source, destination);
            PathResult dfsShortestResult = dfsShortest.findShortestPath(source, destination);
            PathResult dijkstraResult = dijkstra.findShortestPath(source, destination);
            
            String dfsDist = "N/A";
            if (dfsResult.getTotalDistance() > 0) {
                dfsDist = String.valueOf(dfsResult.getTotalDistance());
            }
            
            String dfsShortestDist = "N/A";
            if (dfsShortestResult.getTotalDistance() > 0) {
                dfsShortestDist = String.valueOf(dfsShortestResult.getTotalDistance());
            }
            
            String dijkstraDist = "N/A";
            if (dijkstraResult.getTotalDistance() > 0) {
                dijkstraDist = String.valueOf(dijkstraResult.getTotalDistance());
            }
            
            double dfsTime = dfsResult.getExecutionTime() / 1000000.0;
            double dfsShortestTime = dfsShortestResult.getExecutionTime() / 1000000.0;
            double dijkstraTime = dijkstraResult.getExecutionTime() / 1000000.0;
            
            // print row manually
            System.out.print(source);
            for (int i = source.length(); i < 20; i++) {
                System.out.print(" ");
            }
            System.out.print(" | ");
            System.out.print(destination);
            for (int i = destination.length(); i < 20; i++) {
                System.out.print(" ");
            }
            System.out.print(" | ");
            System.out.print(dfsDist);
            for (int i = dfsDist.length(); i < 15; i++) {
                System.out.print(" ");
            }
            System.out.print(" | ");
            System.out.print(dfsTime);
            for (int i = String.valueOf(dfsTime).length(); i < 12; i++) {
                System.out.print(" ");
            }
            System.out.print(" | ");
            System.out.print(dfsShortestDist);
            for (int i = dfsShortestDist.length(); i < 15; i++) {
                System.out.print(" ");
            }
            System.out.print(" | ");
            System.out.print(dfsShortestTime);
            for (int i = String.valueOf(dfsShortestTime).length(); i < 12; i++) {
                System.out.print(" ");
            }
            System.out.print(" | ");
            System.out.print(dijkstraDist);
            for (int i = dijkstraDist.length(); i < 15; i++) {
                System.out.print(" ");
            }
            System.out.print(" | ");
            System.out.println(dijkstraTime);
            
            if (dijkstraResult.getTotalDistance() > 0) {
                validPaths++;
                totalDFSTime += dfsResult.getExecutionTime();
                totalDFSShortestTime += dfsShortestResult.getExecutionTime();
                totalDijkstraTime += dijkstraResult.getExecutionTime();
            }
        }
        
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();
        
        System.out.println("\nAverage Execution Times (for valid paths):");
        if (validPaths > 0) {
            double avgDFS = (totalDFSTime / 1000000.0) / validPaths;
            double avgDFSShortest = (totalDFSShortestTime / 1000000.0) / validPaths;
            double avgDijkstra = (totalDijkstraTime / 1000000.0) / validPaths;
            System.out.println("  DFS:           " + avgDFS + " ms");
            System.out.println("  DFS-Shortest:  " + avgDFSShortest + " ms");
            System.out.println("  Dijkstra:      " + avgDijkstra + " ms");
        }
    }

    // generate time chart
    private void generateTimeChart(String[][] pairs) {
        System.out.println();
        System.out.println();
        printEqualsLine(100);
        System.out.println("EXECUTION TIME COMPARISON (Bar Chart)");
        printEqualsLine(100);
        
        for (int p = 0; p < pairs.length; p++) {
            String source = pairs[p][0];
            String destination = pairs[p][1];
            
            PathResult dfsResult = dfs.findPath(source, destination);
            PathResult dfsShortestResult = dfsShortest.findShortestPath(source, destination);
            PathResult dijkstraResult = dijkstra.findShortestPath(source, destination);
            
            double dfsTime = dfsResult.getExecutionTime() / 1000000.0;
            double dfsShortestTime = dfsShortestResult.getExecutionTime() / 1000000.0;
            double dijkstraTime = dijkstraResult.getExecutionTime() / 1000000.0;
            
            double maxTime = dfsTime;
            if (dfsShortestTime > maxTime) {
                maxTime = dfsShortestTime;
            }
            if (dijkstraTime > maxTime) {
                maxTime = dijkstraTime;
            }
            if (maxTime == 0) {
                maxTime = 1;
            }
            
            int maxBarLength = 60;
            
            System.out.println("\n" + source + " -> " + destination + ":");
            System.out.print("  DFS:           " + dfsTime + " ms |");
            int bar1 = (int)(dfsTime / maxTime * maxBarLength);
            for (int i = 0; i < bar1; i++) {
                System.out.print("█");
            }
            System.out.println();
            
            System.out.print("  DFS-Shortest:  " + dfsShortestTime + " ms |");
            int bar2 = (int)(dfsShortestTime / maxTime * maxBarLength);
            for (int i = 0; i < bar2; i++) {
                System.out.print("█");
            }
            System.out.println();
            
            System.out.print("  Dijkstra:      " + dijkstraTime + " ms |");
            int bar3 = (int)(dijkstraTime / maxTime * maxBarLength);
            for (int i = 0; i < bar3; i++) {
                System.out.print("█");
            }
            System.out.println();
        }
    }

    // generate distance chart
    private void generateDistanceChart(String[][] pairs) {
        System.out.println();
        System.out.println();
        printEqualsLine(100);
        System.out.println("PATH DISTANCE COMPARISON");
        printEqualsLine(100);
        
        for (int p = 0; p < pairs.length; p++) {
            String source = pairs[p][0];
            String destination = pairs[p][1];
            
            PathResult dfsResult = dfs.findPath(source, destination);
            PathResult dfsShortestResult = dfsShortest.findShortestPath(source, destination);
            PathResult dijkstraResult = dijkstra.findShortestPath(source, destination);
            
            int dfsDist = dfsResult.getTotalDistance();
            int dfsShortestDist = dfsShortestResult.getTotalDistance();
            int dijkstraDist = dijkstraResult.getTotalDistance();
            
            if (dfsDist == 0 && dfsShortestDist == 0 && dijkstraDist == 0) {
                continue;
            }
            
            int maxDist = dfsDist;
            if (dfsDist <= 0) {
                maxDist = 0;
            }
            if (dfsShortestDist > maxDist) {
                maxDist = dfsShortestDist;
            }
            if (dijkstraDist > maxDist) {
                maxDist = dijkstraDist;
            }
            if (maxDist == 0) {
                continue;
            }
            
            int maxBarLength = 60;
            
            System.out.println("\n" + source + " -> " + destination + ":");
            if (dfsDist > 0) {
                System.out.print("  DFS:           " + dfsDist + " km |");
                int bar1 = (int)((double)dfsDist / maxDist * maxBarLength);
                for (int i = 0; i < bar1; i++) {
                    System.out.print("█");
                }
                System.out.println();
            }
            if (dfsShortestDist > 0) {
                System.out.print("  DFS-Shortest:  " + dfsShortestDist + " km |");
                int bar2 = (int)((double)dfsShortestDist / maxDist * maxBarLength);
                for (int i = 0; i < bar2; i++) {
                    System.out.print("█");
                }
                System.out.println();
            }
            if (dijkstraDist > 0) {
                System.out.print("  Dijkstra:      " + dijkstraDist + " km |");
                int bar3 = (int)((double)dijkstraDist / maxDist * maxBarLength);
                for (int i = 0; i < bar3; i++) {
                    System.out.print("█");
                }
                System.out.println();
            }
        }
    }

    // generate analysis summary
    private void generateAnalysis(String[][] pairs) {
        System.out.println();
        System.out.println();
        printEqualsLine(100);
        System.out.println("ALGORITHM ANALYSIS SUMMARY");
        printEqualsLine(100);
        
        int dfsPathsFound = 0;
        int dfsShortestPathsFound = 0;
        int dijkstraPathsFound = 0;
        
        int totalDFSDistance = 0;
        int totalDFSShortestDistance = 0;
        int totalDijkstraDistance = 0;
        
        long totalDFSTime = 0;
        long totalDFSShortestTime = 0;
        long totalDijkstraTime = 0;
        
        for (int p = 0; p < pairs.length; p++) {
            PathResult dfsResult = dfs.findPath(pairs[p][0], pairs[p][1]);
            PathResult dfsShortestResult = dfsShortest.findShortestPath(pairs[p][0], pairs[p][1]);
            PathResult dijkstraResult = dijkstra.findShortestPath(pairs[p][0], pairs[p][1]);
            
            if (dfsResult.getTotalDistance() > 0) {
                dfsPathsFound++;
                totalDFSDistance += dfsResult.getTotalDistance();
                totalDFSTime += dfsResult.getExecutionTime();
            }
            
            if (dfsShortestResult.getTotalDistance() > 0) {
                dfsShortestPathsFound++;
                totalDFSShortestDistance += dfsShortestResult.getTotalDistance();
                totalDFSShortestTime += dfsShortestResult.getExecutionTime();
            }
            
            if (dijkstraResult.getTotalDistance() > 0) {
                dijkstraPathsFound++;
                totalDijkstraDistance += dijkstraResult.getTotalDistance();
                totalDijkstraTime += dijkstraResult.getExecutionTime();
            }
        }
        
        System.out.println("\n1. Path Finding Success Rate:");
        double dfsPercent = dfsPathsFound * 100.0 / pairs.length;
        System.out.println("   DFS:           " + dfsPathsFound + "/" + pairs.length + " paths found (" + dfsPercent + "%)");
        double dfsShortestPercent = dfsShortestPathsFound * 100.0 / pairs.length;
        System.out.println("   DFS-Shortest:  " + dfsShortestPathsFound + "/" + pairs.length + " paths found (" + dfsShortestPercent + "%)");
        double dijkstraPercent = dijkstraPathsFound * 100.0 / pairs.length;
        System.out.println("   Dijkstra:     " + dijkstraPathsFound + "/" + pairs.length + " paths found (" + dijkstraPercent + "%)");
        
        System.out.println("\n2. Average Path Distance (for found paths):");
        if (dfsPathsFound > 0) {
            double avgDFS = (double) totalDFSDistance / dfsPathsFound;
            System.out.println("   DFS:           " + avgDFS + " km");
        }
        if (dfsShortestPathsFound > 0) {
            double avgDFSShortest = (double) totalDFSShortestDistance / dfsShortestPathsFound;
            System.out.println("   DFS-Shortest:  " + avgDFSShortest + " km");
        }
        if (dijkstraPathsFound > 0) {
            double avgDijkstra = (double) totalDijkstraDistance / dijkstraPathsFound;
            System.out.println("   Dijkstra:      " + avgDijkstra + " km");
        }
        
        System.out.println("\n3. Average Execution Time:");
        if (dfsPathsFound > 0) {
            double avgTime1 = (totalDFSTime / 1000000.0) / dfsPathsFound;
            System.out.println("   DFS:           " + avgTime1 + " ms");
        }
        if (dfsShortestPathsFound > 0) {
            double avgTime2 = (totalDFSShortestTime / 1000000.0) / dfsShortestPathsFound;
            System.out.println("   DFS-Shortest:  " + avgTime2 + " ms");
        }
        if (dijkstraPathsFound > 0) {
            double avgTime3 = (totalDijkstraTime / 1000000.0) / dijkstraPathsFound;
            System.out.println("   Dijkstra:      " + avgTime3 + " ms");
        }
        
        System.out.println("\n4. Algorithm Characteristics:");
        System.out.println("   DFS:           Finds any path, not necessarily shortest. Fast but may find suboptimal paths.");
        System.out.println("   DFS-Shortest:  Explores all paths to find shortest. Can be slow for large graphs.");
        System.out.println("   Dijkstra:      Guaranteed shortest path. Efficient with O((V+E)log V) complexity.");
    }

    // main method
    public static void main(String[] args) {
        try {
            String csvFile = "Turkish cities.csv";
            ShortestPathComparison comparison = new ShortestPathComparison(csvFile);
            
            printEqualsLine(100);
            System.out.println("SHORTEST PATH ALGORITHM COMPARISON");
            System.out.println("Turkish Cities Network Analysis");
            printEqualsLine(100);
            
            String[][] pairs = comparison.selectCityPairs();
            
            System.out.println("\nSelected City Pairs (not directly connected):");
            for (int i = 0; i < pairs.length; i++) {
                System.out.println("  " + (i + 1) + ". " + pairs[i][0] + " -> " + pairs[i][1]);
            }
            
            for (int i = 0; i < pairs.length; i++) {
                comparison.runComparison(pairs[i][0], pairs[i][1]);
            }
            
            comparison.generateComparisonTable(pairs);
            comparison.generateTimeChart(pairs);
            comparison.generateDistanceChart(pairs);
            comparison.generateAnalysis(pairs);
            
            System.out.println();
            System.out.println();
            printEqualsLine(100);
            System.out.println("ANALYSIS COMPLETE");
            printEqualsLine(100);
            
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
