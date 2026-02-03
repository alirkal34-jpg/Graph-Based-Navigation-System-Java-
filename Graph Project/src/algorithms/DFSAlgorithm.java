public class DFSAlgorithm {
    private Graph graph;

    public DFSAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public PathResult findPath(String source, String destination) {
        long startTime = System.nanoTime();
        
        int sourceIndex = graph.getCityIndex(source);
        int destIndex = graph.getCityIndex(destination);
        
        // check if cities exist
        if (sourceIndex == -1 || destIndex == -1) {
            long endTime = System.nanoTime();
            return new PathResult(new List<>(), 0, endTime - startTime);
        }
        
        // same city
        if (sourceIndex == destIndex) {
            List<String> path = new List<>();
            path.add(source);
            long endTime = System.nanoTime();
            return new PathResult(path, 0, endTime - startTime);
        }
        
        Stack<Integer> stack = new Stack<>();
        List<Boolean> visited = new List<>();
        List<Integer> parent = new List<>();
        
        int numCities = graph.getNumCities();
        for (int i = 0; i < numCities; i++) {
            visited.add(false);
            parent.add(-1);
        }
        
        stack.push(sourceIndex);
        visited.set(sourceIndex, true);
        
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current == destIndex) {
                List<String> path = new List<>();
                int node = destIndex;
                
                while (node != -1) {
                    String cityName = graph.getCityName(node);
                    path.add(0, cityName);
                    node = parent.get(node);
                }
                
                int totalDistance = 0;
                for (int i = 0; i < path.size() - 1; i++) {
                    String fromCity = path.get(i);
                    String toCity = path.get(i + 1);
                    int fromIndex = graph.getCityIndex(fromCity);
                    int toIndex = graph.getCityIndex(toCity);
                    int distance = graph.getDistance(fromIndex, toIndex);
                    totalDistance = totalDistance + distance;
                }
                
                long endTime = System.nanoTime();
                return new PathResult(path, totalDistance, endTime - startTime);
            }
            
            // explore neighbors
            List<Graph.Edge> neighbors = graph.getNeighbors(current);
            for (int i = 0; i < neighbors.size(); i++) {
                Graph.Edge edge = neighbors.get(i);
                int neighbor = edge.to;
                
                if (!visited.get(neighbor)) {
                    visited.set(neighbor, true);
                    parent.set(neighbor, current);
                    stack.push(neighbor);
                }
            }
        }
        
        // no path 
        long endTime = System.nanoTime();
        return new PathResult(new List<>(), 0, endTime - startTime);
    }
}
