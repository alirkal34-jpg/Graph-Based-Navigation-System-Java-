import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    private Graph graph;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
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
        
        int numCities = graph.getNumCities();
        int[] dist = new int[numCities];
        int[] parent = new int[numCities];
        boolean[] visited = new boolean[numCities];
        
        for (int i = 0; i < numCities; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            visited[i] = false;
        }
        
        dist[sourceIndex] = 0;
        
        PriorityQueue<PQNode> pq = new PriorityQueue<>();
        PQNode startNode = new PQNode(0, sourceIndex);
        pq.add(startNode);
        
        while (!pq.isEmpty()) {
            PQNode current = pq.poll();
            int u = current.cityIndex;
            int currentDist = current.distance;
            
            if (visited[u]) {
                continue;
            }
            
            visited[u] = true;
            
            if (u == destIndex) {
                break;
            }
            
            List<Graph.Edge> neighbors = graph.getNeighbors(u);
            for (int i = 0; i < neighbors.size(); i++) {
                Graph.Edge edge = neighbors.get(i);
                int v = edge.to;
                int weight = edge.weight;
                
                if (!visited[v]) {
                    int newDist = currentDist + weight;
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                        PQNode newNode = new PQNode(newDist, v);
                        pq.add(newNode);
                    }
                }
            }
        }
        
        // check if path exists
        if (dist[destIndex] == Integer.MAX_VALUE) {
            long endTime = System.nanoTime();
            return new PathResult(new List<>(), 0, endTime - startTime);
        }
        
        List<String> path = new List<>();
        int node = destIndex;
        Stack<String> pathStack = new Stack<>();
        
        while (node != -1) {
            String cityName = graph.getCityName(node);
            pathStack.push(cityName);
            node = parent[node];
        }
        
        while (!pathStack.isEmpty()) {
            String city = pathStack.pop();
            path.add(city);
        }
        
        long endTime = System.nanoTime();
        return new PathResult(path, dist[destIndex], endTime - startTime);
    }
    
    private class PQNode implements Comparable<PQNode> {
        int distance;
        int cityIndex;
        
        public PQNode(int distance, int cityIndex) {
            this.distance = distance;
            this.cityIndex = cityIndex;
        }
        
        public int compareTo(PQNode other) {
            return this.distance - other.distance;
        }
    }
}
