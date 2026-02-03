import java.io.IOException;
import java.util.Scanner;


public class InteractivePathFinder {
    private Graph graph;
    private DFSAlgorithm dfs;
    private DFSShortestAlgorithm dfsShortest;
    private DijkstraAlgorithm dijkstra;
    private Scanner scanner;

    public InteractivePathFinder(String csvFile) throws IOException {
        System.out.println("Loading...");
        graph = CSVParser.parseCSV(csvFile);
        dfs = new DFSAlgorithm(graph);
        dfsShortest = new DFSShortestAlgorithm(graph);
        dijkstra = new DijkstraAlgorithm(graph);
        scanner = new Scanner(System.in);
    }

    private void displayMainMenu() {
        clearScreen();
        System.out.println("========================================");
        System.out.println("   SHORTEST PATH FINDER");
        System.out.println("========================================");
        System.out.println();
        System.out.println("  [1] DFS");
        System.out.println("  [2] DFS-Shortest");
        System.out.println("  [3] Dijkstra");
        System.out.println("  [4] Compare All");
        System.out.println("  [0] Exit");
        System.out.println();
        System.out.print("  Choice: ");
    }

    private void displayCitySelection(String algorithmName) {
        clearScreen();
        System.out.println("========================================");
        System.out.println("   " + algorithmName);
        System.out.println("========================================");
        System.out.println();
        
        List<String> cities = graph.getCities();
        System.out.println("  Cities:");
        System.out.println();
        
        for (int i = 0; i < cities.size(); i++) {
            int num = i + 1;
            String cityName = cities.get(i);
            System.out.print("  [" + num + "] " + cityName);
            // add spaces for alignment
            int spaces = 15 - cityName.length();
            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }
            if ((i + 1) % 3 == 0 || i == cities.size() - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private int selectCity(String prompt) {
        List<String> cities = graph.getCities();
        int choice;
        
        while (true) {
            System.out.print("  " + prompt + ": ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= cities.size()) {
                    return choice - 1;
                } else {
                    System.out.println("  Invalid! Enter 1-" + cities.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input!");
            }
        }
    }

    private void displayResult(PathResult result, String algorithmName, String source, String destination) {
        clearScreen();
        System.out.println("========================================");
        System.out.println("   RESULT");
        System.out.println("========================================");
        System.out.println();
        System.out.println("  Algorithm: " + algorithmName);
        System.out.println("  From:      " + source);
        System.out.println("  To:        " + destination);
        System.out.println();
        
        if (result.getTotalDistance() == 0 || result.getPath().isEmpty()) {
            System.out.println("  No path found");
        } else {
            System.out.println("  Path: " + result.getPathString());
            System.out.println();
            System.out.println("  Distance: " + result.getTotalDistance() + " km");
            double timeMs = result.getExecutionTime() / 1000000.0;
            System.out.println("  Time:      " + timeMs + " ms");
            System.out.println("  Stops:     " + (result.getPath().size() - 1));
        }
        
        System.out.println();
        System.out.print("  Press Enter to continue...");
        scanner.nextLine();
    }

    private void displayComparison(String source, String destination) {
        clearScreen();
        System.out.println("========================================");
        System.out.println("   ALGORITHM COMPARISON");
        System.out.println("========================================");
        System.out.println();
        System.out.println("  From: " + source);
        System.out.println("  To:   " + destination);
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println();
        
        PathResult dfsResult = dfs.findPath(source, destination);
        PathResult dfsShortestResult = dfsShortest.findShortestPath(source, destination);
        PathResult dijkstraResult = dijkstra.findShortestPath(source, destination);
        
        System.out.println("  [1] DFS");
        if (dfsResult.getTotalDistance() > 0) {
            System.out.println("  Path:     " + dfsResult.getPathString());
            System.out.println("  Distance: " + dfsResult.getTotalDistance() + " km");
            double time1 = dfsResult.getExecutionTime() / 1000000.0;
            System.out.println("  Time:     " + time1 + " ms");
        } else {
            System.out.println("  No path found");
        }
        System.out.println();
        
        System.out.println("  [2] DFS-Shortest");
        if (dfsShortestResult.getTotalDistance() > 0) {
            System.out.println("  Path:     " + dfsShortestResult.getPathString());
            System.out.println("  Distance: " + dfsShortestResult.getTotalDistance() + " km");
            double time2 = dfsShortestResult.getExecutionTime() / 1000000.0;
            System.out.println("  Time:     " + time2 + " ms");
        } else {
            System.out.println("  No path found");
        }
        System.out.println();
        
        System.out.println("  [3] Dijkstra");
        if (dijkstraResult.getTotalDistance() > 0) {
            System.out.println("  Path:     " + dijkstraResult.getPathString());
            System.out.println("  Distance: " + dijkstraResult.getTotalDistance() + " km");
            double time3 = dijkstraResult.getExecutionTime() / 1000000.0;
            System.out.println("  Time:     " + time3 + " ms");
        } else {
            System.out.println("  No path found");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < 50; i++) {
            System.out.print("=");
        }
        System.out.println();
        if (dijkstraResult.getTotalDistance() > 0) {
            int shortest = Math.min(Math.min(
                dfsResult.getTotalDistance() > 0 ? dfsResult.getTotalDistance() : Integer.MAX_VALUE,
                dfsShortestResult.getTotalDistance() > 0 ? dfsShortestResult.getTotalDistance() : Integer.MAX_VALUE),
                dijkstraResult.getTotalDistance());
            
            System.out.println("  Shortest: " + shortest + " km");
        }
        System.out.println();
        System.out.print("  Press Enter to continue...");
        scanner.nextLine();
    }

    private void clearScreen() {
        // print blank lines to clear screen
        for (int i = 0; i < 50; i++) {
                System.out.println();
        }
    }

    public void run() {
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();
            
            if (choice.equals("0")) {
                clearScreen();
                System.out.println("========================================");
                System.out.println("   EXIT");
                System.out.println("========================================");
                System.out.println();
                System.out.println("  Goodbye!");
                System.out.println();
                break;
            }
            
            if (choice.equals("4")) {
                displayCitySelection("COMPARE ALL");
                int sourceIndex = selectCity("Source city");
                int destIndex = selectCity("Destination city");
                
                List<String> cities = graph.getCities();
                String source = cities.get(sourceIndex);
                String destination = cities.get(destIndex);
                
                displayComparison(source, destination);
                continue;
            }
            
            String algorithmName = "";
            PathResult result = null;
            
            switch (choice) {
                case "1":
                    algorithmName = "DFS";
                    displayCitySelection(algorithmName);
                    int source1 = selectCity("Source city");
                    int dest1 = selectCity("Destination city");
                    
                    List<String> cities1 = graph.getCities();
                    result = dfs.findPath(cities1.get(source1), cities1.get(dest1));
                    displayResult(result, algorithmName, cities1.get(source1), cities1.get(dest1));
                    break;
                    
                case "2":
                    algorithmName = "DFS-Shortest";
                    displayCitySelection(algorithmName);
                    int source2 = selectCity("Source city");
                    int dest2 = selectCity("Destination city");
                    
                    List<String> cities2 = graph.getCities();
                    result = dfsShortest.findShortestPath(cities2.get(source2), cities2.get(dest2));
                    displayResult(result, algorithmName, cities2.get(source2), cities2.get(dest2));
                    break;
                    
                case "3":
                    algorithmName = "Dijkstra";
                    displayCitySelection(algorithmName);
                    int source3 = selectCity("Source city");
                    int dest3 = selectCity("Destination city");
                    
                    List<String> cities3 = graph.getCities();
                    result = dijkstra.findShortestPath(cities3.get(source3), cities3.get(dest3));
                    displayResult(result, algorithmName, cities3.get(source3), cities3.get(dest3));
                    break;
                    
                default:
                    System.out.println();
                    System.out.println("  Invalid choice! Enter 0-4");
                    System.out.print("  Press Enter...");
                    scanner.nextLine();
                    break;
            }
        }
        
        scanner.close();
    }

    public static void main(String[] args) {
        try {
            InteractivePathFinder app = new InteractivePathFinder("Turkish cities.csv");
            app.run();
        } catch (IOException e) {
            System.err.println("Error: Cannot read CSV file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
