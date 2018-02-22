import java.util.*;

public class DirectedGraph<E extends Edge> {

	//maybe HashMap for graph
	private List<Edge> edges;
	//Probably need to represent edges
	//private NodeTable nodeTable;


	public DirectedGraph(int noOfNodes) {
		//initialize the data
		edges = new ArrayList<>();
	}

	public void addEdge(E e) {
		if (e != null) edges.add(e);
	}

	public Iterator<E> shortestPath(int from, int to) {

		//visited nodes
		List<Integer> visited = new ArrayList<>();

		//
		PriorityQueue<CompDijsktraPath> queue = new PriorityQueue<>();

		//add first to the queue
		queue.add(new CompDijsktraPath(from, 0, null));

		while (!queue.isEmpty()) {

			CompDijsktraPath current = queue.poll();

			if (!visited.contains(current.nodeNo)) {

				if (current.nodeNo == to) {

					//return the path
					return current.path.iterator();
				} else {
					visited.add(current.nodeNo);
				}
			}
			for (Edge edge:edges)
			{
				if(edge.from == current.nodeNo){
					Integer neighborNo = edge.to;
					if(!visited.contains(neighborNo)){
						List<Edge> newPath = current.path;
						newPath.add(edge);
						queue.add(new CompDijsktraPath(neighborNo,current.cost + edge.getWeight(),newPath));
					}
				}
			}
		}
		//Destination was not found... wrong in graph
		System.out.println("Destination was not found, something probably wrong in graph");
		return null;
	}



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