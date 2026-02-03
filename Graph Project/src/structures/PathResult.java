public class PathResult {
    private List<String> path;
    private int totalDistance;
    private long executionTime; // nanoseconds

    public PathResult(List<String> path, int totalDistance, long executionTime) {
        this.path = path;
        this.totalDistance = totalDistance;
        this.executionTime = executionTime;
    }

    public List<String> getPath() {
        return path;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public String getPathString() {
        if (path == null || path.isEmpty()) {
            return "No path found";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i < path.size() - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }
}
