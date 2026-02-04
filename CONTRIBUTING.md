# Contributing to Graph-Based Navigation System

Thank you for your interest in contributing to this project! This guide will help you get started.

## 🤝 Code of Conduct

- Be respectful and inclusive
- Provide constructive feedback
- Focus on what's best for the project and community
- Help others learn and grow

## 🎯 How Can I Contribute?

### Reporting Bugs

Before creating a bug report:
1. Check the [existing issues](https://github.com/alirkal34-jpg/Graph-Based-Navigation-System-Java-/issues)
2. Make sure you're using the latest version
3. Verify the bug is reproducible

**When reporting bugs, include:**
- Java version (`java -version`)
- Operating system
- Steps to reproduce
- Expected vs actual behavior
- Error messages or stack traces

### Suggesting Features

We love new ideas! Before suggesting:
1. Check if it's already been proposed
2. Consider if it fits the project's scope
3. Think about implementation complexity

**Feature request template:**
```markdown
**Problem**: What problem does this solve?
**Proposed Solution**: How would you implement it?
**Alternatives**: What other approaches did you consider?
**Additional Context**: Any relevant information
```

### Code Contributions

#### Areas for Contribution

**High Priority:**
- [ ] A* algorithm implementation
- [ ] Bellman-Ford algorithm for negative weights
- [ ] Performance optimizations
- [ ] Unit tests for all algorithms
- [ ] JavaDoc documentation

**Medium Priority:**
- [ ] Floyd-Warshall all-pairs shortest path
- [ ] Graph export to JSON/GraphML
- [ ] Bi-directional Dijkstra
- [ ] Memory usage profiling

**Nice to Have:**
- [ ] JavaFX GUI visualization
- [ ] Multi-threading for parallel computations
- [ ] REST API wrapper
- [ ] Docker containerization

## 🛠️ Development Setup

### Prerequisites

- JDK 17 or higher
- Git
- A code editor (IntelliJ IDEA, Eclipse, VS Code)

### Getting Started

1. **Fork the repository**
   ```bash
   # Click "Fork" on GitHub, then:
   git clone https://github.com/YOUR_USERNAME/Graph-Based-Navigation-System-Java-.git
   cd Graph-Based-Navigation-System-Java-
   ```

2. **Create a branch**
   ```bash
   git checkout -b feature/your-feature-name
   # or 
   git checkout -b fix/bug-description
   ```

3. **Build the project**
   ```bash
   cd "Graph Project/src"
   javac Main.java
   java Main
   ```

## 📝 Coding Standards

### Java Style Guide

Follow standard Java conventions:

```java
// ✅ Good: Clear naming, proper formatting
public class DijkstraAlgorithm {
    private Graph graph;
    
    public PathResult findShortestPath(String source, String destination) {
        // Implementation
    }
}

// ❌ Bad: Poor naming, no structure
public class dijk {
    Graph g;
    PathResult find(String s, String d) { /* ... */ }
}
```

### Key Principles

1. **No Java Collections Framework**
   - ❌ Don't use: `ArrayList`, `HashMap`, `Stack`, `Queue`
   - ✅ Do use: Custom `List<T>`, `Stack<T>`, `Queue<T>`

2. **Generic Types**
   ```java
   // ✅ Good: Type-safe generics
   public class List<T> {
       public void add(T item) { /* ... */ }
   }
   
   // ❌ Bad: Raw types
   public class List {
       public void add(Object item) { /* ... */ }
   }
   ```

3. **Documentation**
   ```java
   /**
    * Finds the shortest path between two cities using Dijkstra's algorithm.
    * 
    * @param source The starting city name
    * @param destination The target city name
    * @return PathResult containing the route and distance, or null if no path exists
    * @throws IllegalArgumentException if source or destination is null
    */
   public PathResult findShortestPath(String source, String destination) {
       // Implementation
   }
   ```

4. **Error Handling**
   ```java
   // ✅ Good: Explicit validation
   if (source == null || destination == null) {
       throw new IllegalArgumentException("Cities cannot be null");
   }
   
   // ❌ Bad: Silent failures
   if (source == null) return null;
   ```

## 🧪 Testing

### Writing Tests

We need unit tests! Example structure:

```java
public class DijkstraAlgorithmTest {
    private Graph graph;
    private DijkstraAlgorithm dijkstra;
    
    @Before
    public void setUp() {
        graph = new Graph();
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 15);
        dijkstra = new DijkstraAlgorithm(graph);
    }
    
    @Test
    public void testShortestPath() {
        PathResult result = dijkstra.findShortestPath("A", "C");
        assertEquals(25, result.getTotalDistance());
    }
    
    @Test
    public void testNoPath() {
        PathResult result = dijkstra.findShortestPath("A", "Z");
        assertNull(result.getPath());
    }
}
```

### Performance Testing

When adding algorithms, include benchmarks:

```java
long startTime = System.nanoTime();
PathResult result = algorithm.findPath(source, destination);
long endTime = System.nanoTime();
double executionTime = (endTime - startTime) / 1_000_000.0;
System.out.println("Execution time: " + executionTime + " ms");
```

## 📤 Pull Request Process

### Before Submitting

- [ ] Code compiles without errors
- [ ] Follows coding standards
- [ ] Added/updated documentation
- [ ] Tested manually
- [ ] No breaking changes (or clearly documented)

### PR Template

```markdown
## Description
Brief summary of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Performance improvement
- [ ] Documentation update

## Testing
How did you test this?

## Checklist
- [ ] Code follows project style guidelines
- [ ] Self-reviewed the code
- [ ] Commented complex sections
- [ ] Updated documentation
```

### Review Process

1. Submit PR with clear description
2. Wait for maintainer review (usually 2-3 days)
3. Address feedback
4. Once approved, we'll merge!

## 🌟 Recognition

Contributors will be:
- Listed in `CONTRIBUTORS.md`
- Mentioned in release notes
- Credited in relevant documentation

## 📚 Resources

### Learning Materials

- [Dijkstra's Algorithm Explained](https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/)
- [Graph Theory Tutorial](https://www.tutorialspoint.com/data_structures_algorithms/graph_data_structure.htm)
- [Java Generics Guide](https://docs.oracle.com/javase/tutorial/java/generics/)

### Project-Specific

- [Graph Representation Choices](docs/ARCHITECTURE.md)
- [Algorithm Comparison](docs/ALGORITHMS.md)
- [Performance Benchmarks](benchmarks/results.md)

## ❓ Questions?

- Open an issue with the `question` label
- Email: alir.kal34@gmail.com
- Check existing discussions

## 🙏 Thank You!

Every contribution, no matter how small, helps improve this project. We appreciate your time and effort!

---

**Happy Coding!** 🚀
