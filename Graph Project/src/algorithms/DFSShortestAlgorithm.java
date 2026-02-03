public class DFSShortestAlgorithm {
    private Graph graph;

    public DFSShortestAlgorithm(Graph graph) {
        this.graph = graph;
    }

    private static class PathState {
        int node;
        Stack<Integer> path;
        List<Boolean> visited;
        int distance;

        PathState(int node, Stack<Integer> path, List<Boolean> visited, int distance) {
            this.node = node;
            this.path = path;
            this.visited = visited;
            this.distance = distance;
        }
    }

    public PathResult findShortestPath(String source, String destination) {
        long startTime = System.nanoTime();
        
        int sourceIndex = graph.getCityIndex(source);
        int destIndex = graph.getCityIndex(destination);
        
        if (sourceIndex == -1 || destIndex == -1) {
            long endTime = System.nanoTime();
            return new PathResult(new List<>(), 0, endTime - startTime);
        }
        
        if (sourceIndex == destIndex) {
            List<String> path = new List<>();
            path.add(source);
            long endTime = System.nanoTime();
            return new PathResult(path, 0, endTime - startTime);
        }
        
        int bestDistance = Integer.MAX_VALUE;
        List<String> bestPath = null;
        
        Stack<PathState> stateStack = new Stack<>();
        
        Stack<Integer> initialPath = new Stack<>();
        initialPath.push(sourceIndex);
        
        List<Boolean> initialVisited = new List<>();
        int numCities = graph.getNumCities();
        for (int i = 0; i < numCities; i++) {
            initialVisited.add(false);
        }
        initialVisited.set(sourceIndex, true);
        
        PathState initialState = new PathState(sourceIndex, initialPath, initialVisited, 0);
        stateStack.push(initialState);
        
        while (!stateStack.isEmpty()) {
            PathState currentState = stateStack.pop();
            int current = currentState.node;
            Stack<Integer> currentPath = currentState.path;
            List<Boolean> currentVisited = currentState.visited;
            int currentDistance = currentState.distance;
        
            if (current == destIndex) {
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                
                bestPath = new List<>();
                    Stack<Integer> tempStack = currentPath.copy();
                Stack<Integer> reverseStack = new Stack<>();
                
                while (!tempStack.isEmpty()) {
                    int cityIndex = tempStack.pop();
                    reverseStack.push(cityIndex);
                }
                
                while (!reverseStack.isEmpty()) {
                    int cityIndex = reverseStack.pop();
                    String cityName = graph.getCityName(cityIndex);
                    bestPath.add(cityName);
                }
            }
                continue;
        }
        
        List<Graph.Edge> neighbors = graph.getNeighbors(current);
        for (int i = 0; i < neighbors.size(); i++) {
            Graph.Edge edge = neighbors.get(i);
            int neighbor = edge.to;
            
                if (!currentVisited.get(neighbor)) {
                int newDistance = currentDistance + edge.weight;
                    
                if (newDistance < bestDistance) {
                        Stack<Integer> newPath = currentPath.copy();
                        newPath.push(neighbor);
                        
                        List<Boolean> newVisited = new List<>();
                        for (int j = 0; j < numCities; j++) {
                            newVisited.add(currentVisited.get(j));
                        }
                        newVisited.set(neighbor, true);
                        
                        PathState newState = new PathState(neighbor, newPath, newVisited, newDistance);
                        stateStack.push(newState);
                    }
                }
            }
        }
        
        long endTime = System.nanoTime();
        
        if (bestPath == null) {
            return new PathResult(new List<>(), 0, endTime - startTime);
        }
        
        return new PathResult(bestPath, bestDistance, endTime - startTime);
    }
}
