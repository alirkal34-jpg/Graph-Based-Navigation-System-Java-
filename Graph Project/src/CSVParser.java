import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVParser {
    private static final int NO_CONNECTION = 99999;

    public static Graph parseCSV(String filename) throws IOException {
        Graph graph = new Graph();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        String headerLine = reader.readLine();
        if (headerLine == null) {
            reader.close();
            return graph;
        }
        
        String[] headers = headerLine.split(",");
        int numCities = headers.length - 1;
        String[] cityNames = new String[numCities];
        
        for (int i = 1; i < headers.length; i++) {
            String cityName = headers[i].trim();
            cityNames[i - 1] = cityName;
            graph.addCity(cityName);
        }
        
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                break;
            }
            
            String[] values = line.split(",");
            String fromCity = values[0].trim();
            
            int fromIndex = graph.getCityIndex(fromCity);
            if (fromIndex == -1) {
                continue;
            }
            
            for (int i = 1; i < values.length && i - 1 < cityNames.length; i++) {
                try {
                    String distanceStr = values[i].trim();
                    int distance = Integer.parseInt(distanceStr);
                    int toIndex = i - 1;
                    
                    if (distance != NO_CONNECTION && distance > 0 && fromIndex != toIndex) {
                        graph.addEdge(fromIndex, toIndex, distance);
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        
        reader.close();
        return graph;
    }
}
