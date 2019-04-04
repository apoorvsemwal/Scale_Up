import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DriverGraphs {

	private static final Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		Map<Integer, List<Integer>> grap_map = new HashMap<>();
		int start_node = -1;

		Graph graph = new Graph();
		graph.setGraph(grap_map);

		System.out.println("Enter the number of edges.");
		int edge_count = Integer.parseInt(s.nextLine().trim());

		for (int i = 0; i < edge_count; i++) {
			String[] link = s.nextLine().trim().split(" ");
			addEdge(graph, Integer.parseInt(link[0].trim()), Integer.parseInt(link[1].trim()));
		}

		if (graph != null) {
			for (Iterator<Entry<Integer, List<Integer>>> iterator = graph.getGraph().entrySet().iterator(); iterator
					.hasNext();) {
				Entry<Integer, List<Integer>> entry = iterator.next();
				start_node = entry.getKey();
				break;
			}

			if (start_node >= 0) {
				/**
				 * Depth First Traversal of an undirected Graph
				 */
				System.out.println("Depth first traversal is:");
				depthFirstTraversal(graph, start_node);

				/**
				 * Breadth First Traversal of an undirected Graph
				 */
				System.out.println("\nBreadth first traversal is:");
				breadthFirstTraversal(graph, start_node);
			}
		}
	}

	/**
	 * Graph maintained as an Adjacency List using a HashMap Key is a Node Value -
	 * List of Adjacent Nodes UnDirected Graph therefore create link from Dest to
	 * Src as well.
	 * 
	 * @param graph Graph Object
	 * @param src   Source of the edge to be added.
	 * @param dest  Destination of the edge to be added.
	 */
	private static void addEdge(Graph graph, int src, int dest) {

		List<Integer> neighbours;

		if (graph.getGraph().get(src) != null) {

			graph.getGraph().get(src).add(dest);

			if (graph.getGraph().get(dest) != null) {
				graph.getGraph().get(dest).add(src);
			} else {
				neighbours = new ArrayList<>();
				neighbours.add(src);
				graph.getGraph().put(dest, neighbours);
			}

		} else {

			neighbours = new ArrayList<>();
			neighbours.add(dest);

			graph.getGraph().put(src, neighbours);

			if (graph.getGraph().get(dest) != null) {
				graph.getGraph().get(dest).add(src);
			} else {
				neighbours = new ArrayList<>();
				neighbours.add(src);
				graph.getGraph().put(dest, neighbours);
			}
		}
	}

	private static void breadthFirstTraversal(Graph graph, int start_node) {
		Queue<Integer> bfs_print = new LinkedList<>();
		Map<Integer, Boolean> visited = new HashMap<>();
		bfs_print.add(start_node);

		while (bfs_print.size() != 0) {
			int elem = bfs_print.poll();
			if (visited.get(elem) == null || visited.get(elem) != true) {
				System.out.print(elem + " ");
			}
			visited.put(elem, true);
			for (Integer integer : graph.getGraph().get(elem)) {
				if (visited.get(integer) == null || visited.get(integer) != true) {
					bfs_print.add(integer);
				}
			}

		}
	}

	private static void depthFirstTraversal(Graph graph, int start_node) {
		Stack<Integer> dfs_print = new Stack<>();
		Map<Integer, Boolean> visited = new HashMap<>();
		dfs_print.add(start_node);

		while (dfs_print.size() != 0) {
			int elem = dfs_print.pop();
			if (visited.get(elem) == null || visited.get(elem) != true) {
				System.out.print(elem + " ");
			}
			visited.put(elem, true);
			for (Integer integer : graph.getGraph().get(elem)) {
				if (visited.get(integer) == null || visited.get(integer) != true) {
					dfs_print.push(integer);
				}
			}
		}
	}
}

class Graph {
	Map<Integer, List<Integer>> graph;

	public Map<Integer, List<Integer>> getGraph() {
		return graph;
	}

	public void setGraph(Map<Integer, List<Integer>> graph) {
		this.graph = graph;
	}
}