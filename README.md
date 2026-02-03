# 🗺️ Graph-Based Navigation System (Java)

> A high-performance routing engine that calculates optimal paths between 81 Turkish cities using **Dijkstra’s Algorithm** and **Custom Data Structures**.

![Java](https://img.shields.io/badge/Language-Java_17-orange) ![Algorithms](https://img.shields.io/badge/Topic-Graph_Theory-blue) ![Performance](https://img.shields.io/badge/Focus-Performance-green)

## 🧠 The Engineering Challenge

Instead of relying on Java's built-in Collections Framework (like `ArrayList`, `Stack`, `HashMap`), this project implements **custom generic data structures** from scratch. 

**Why?** To understand low-level memory management, pointer logic, and the mechanics behind dynamic resizing arrays and linked nodes.

## ⚡ Key Features

* **Shortest Path Calculation:** Implements **Dijkstra’s Algorithm** using a custom Priority Queue logic for $O(E + V \log V)$ efficiency.
* **Algorithm Benchmarking:** Includes a simulation module that compares **DFS (Depth First Search)** vs. **Dijkstra** execution times on complex routes.
* **Custom Generic Data Structures:**
    * `DynamicList<T>`: A lightweight, dynamically resizing array implementation.
    * `CustomStack<T>`: A node-based LIFO structure for path backtracking.
    * `CustomQueue<T>`: FIFO structure for graph traversal.
* **Graph Architecture:** Uses **Adjacency Lists** to represent the map, optimizing space complexity for sparse graphs (like road networks).

## 🔬 Performance Analysis

I conducted a benchmark test comparing DFS and Dijkstra on a dataset of 81 cities.

| Algorithm | Short Path (Time) | Long Path (Time) | Path Optimality |
| :--- | :--- | :--- | :--- |
| **Dijkstra** | ~0.03 ms | ~0.05 ms | **Guaranteed Shortest** |
| DFS | ~0.02 ms | **Timeout / Slow** | Not Guaranteed |

> **Insight:** *Analysis shows that while DFS is fast for direct connections, Dijkstra provides stability and accuracy for complex, multi-node routing scenarios.*

## 📂 Project Structure
<img width="1239" height="512" alt="image" src="https://github.com/user-attachments/assets/ca4fb49d-a984-4257-a97f-cabd60db1f5d" />
