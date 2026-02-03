public class Graph {
    private List<String> cities;
    private List<List<Edge>> adjacencyList;
    private static final int NO_CONNECTION = 99999;

    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public Graph() {
        this.cities = new List<>();
        this.adjacencyList = new List<>();
    }

    public int addCity(String cityName) {
        cities.add(cityName);
        adjacencyList.add(new List<Edge>());
        return cities.size() - 1;
    }

    public int getCityIndex(String cityName) {
        for (int i = 0; i < cities.size(); i++) {
            String currentCity = cities.get(i);
            if (currentCity.equals(cityName)) {
                return i;
            }
        }
        return -1;
    }

    public String getCityName(int index) {
        if (index < 0 || index >= cities.size()) {
            return null;
        }
        return cities.get(index);
    }

    public void addEdge(int from, int to, int weight) {
        if (weight != NO_CONNECTION && weight > 0) {
            List<Edge> neighbors = adjacencyList.get(from);
            Edge newEdge = new Edge(to, weight);
            neighbors.add(newEdge);
        }
    }

    public List<Edge> getNeighbors(int cityIndex) {
        return adjacencyList.get(cityIndex);
    }

    public int getNumCities() {
        return cities.size();
    }

    public int getDistance(int from, int to) {
        List<Edge> neighbors = adjacencyList.get(from);
        for (int i = 0; i < neighbors.size(); i++) {
            Edge edge = neighbors.get(i);
            if (edge.to == to) {
                return edge.weight;
            }
        }
        return NO_CONNECTION;
    }

    public List<String> getCities() {
        return cities;
    }
}
