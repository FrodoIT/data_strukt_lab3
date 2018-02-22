
import javax.xml.soap.Node;
import java.util.*;

public class DirectedGraph<E extends Edge, NO extends NodeObject> {

	//maybe HashMap for graph
	private List<Edge> edges;
	private NodeTable<NodeObject> nodes;
	//Probably need to represent edges
	//private NodeTable nodeTable;


	public DirectedGraph(int noOfNodes) {
		//initialize the data
		nodes = new NodeTable<>(noOfNodes);
		edges = new ArrayList<>();
	}

	public void addEdge(E e) {
		if (e != null) edges.add(e);
	}

	public Iterator<E> shortestPath(int from, int to) {

		NodeObject first = nodes.find(from);

		//visited nodes
		List<NodeObject> visited = new ArrayList<>();

		//
		PriorityQueue<CompDijsktraPath> queue = new PriorityQueue<>();

		//add first to the queue
		queue.add(new CompDijsktraPath(first, 0, null));

		while (!queue.isEmpty()) {

			CompDijsktraPath current = queue.poll();
			if (!visited.contains(current.node)) {

				if (current.node.getNodeNo() == to) {

					//return the path
					return null;
				} else {
					visited.add(current.node);
				}
			}
			for (Edge edge:edges)
			{
				if(edge.from == current.node.getNodeNo()){
					NodeObject neighbor = nodes.find(edge.to);
					if(!visited.contains(neighbor)){
						queue.add(new CompDijsktraPath(neighbor,current.cost + edge.getWeight(), current.node));
					}
				}
			}
		}

		return null;
	}



/*
	1: lägg(startnod, 0, tom väg) i Priority queue

            2: While(Priority que is not empty)

            3: (nod, cost, path) = First element in priority queue

            4: If node is not visited

            5: If node is the end, return path

            6: else, add to visited

            7: for every neighbor if not visited add to priority queue

//Very uncertain of what should be here respectively in CompDijsktraPath

*/


	public Iterator<E> minimumSpanningTree() {
		//skapa ett fält cc som för varje nod innehåller en egen tom lista (som skall komma att innehålla bågar sen) (dvs varje nod är i en egen komponent)
		//Lägg in alla bågar i en prioritetskö pq
		//Så länge pq ej är tom && |cc| > 1 {
		//hämta e = (from, to, weight) från pq
		//om from och to inte refererar till samma lista i cc {
		//flytta över alla elementen från den kortare listan till den andra och se till att alla berörda //noder i cc refererar till den påfyllda listan
		//lägg slutligen e i den påfyllda listan
		//} borde den vara här, eller en rad upp?
		//}


		return null;
	}

}