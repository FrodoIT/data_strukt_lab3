import java.lang.reflect.Array;
import java.util.*;

public class DirectedGraph<E extends Edge> {

	//maybe HashMap for graph
	private List<Edge> edges;
	private int noOfNodes;
	//Probably need to represent edges
	//private NodeTable nodeTable;


	public DirectedGraph(int noOfNodes) {
		//initialize the data
		edges = new ArrayList<>();
		this.noOfNodes = noOfNodes;
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

		PriorityQueue<Edge> pq = new PriorityQueue();

		//The visied nodes
		ArrayList<ArrayList<Integer>> subtrees = new ArrayList<>(noOfNodes);


		//skapa ett fält cc som för varje nod innehåller en egen tom lista (som skall komma att innehålla bågar sen) (dvs varje nod är i en egen komponent)
		List<E> mST = new ArrayList<E>();

		//Lägg in alla bågar i en prioritetskö pq
		pq.addAll(edges);

		//Så länge pq ej är tom && |cc| > 1 {
		while(!pq.isEmpty() && subtrees.size() > 1){

			//hämta e = (from, to, weight) från pq
			Edge e = pq.poll();

			ArrayList<Integer> subtreefrom = new ArrayList<>();
			ArrayList<Integer> subtreeto = new ArrayList<>();

			for (ArrayList subtree:subtrees)
			{
					if (subtree.contains(e.from)){
						subtreefrom = subtree;
					}
					else if(subtree.contains(e.to)){
						subtreeto = subtree;
					}
			}

			if(!subtreefrom.equals(subtreeto)){
				ArrayList<Integer> newSubtree = new ArrayList<>();
				if(subtreefrom.size() > subtreeto.size()){
					subtreefrom.addAll(subtreeto);
					newSubtree = subtreefrom;
				}
				else{
					subtreeto.addAll(subtreefrom);
					newSubtree = subtreefrom;
				}
				subtrees.remove(subtreeto);
				subtrees.remove(subtreefrom);
				subtrees.add(newSubtree);
				mST.add((E) e);
			}
			else if(subtreefrom.isEmpty() && subtreeto.isEmpty()){
				subtreefrom.add(e.from);
				subtreefrom.add(e.to);
				mST.add((E) e);
			}

		}
		
		return mST.iterator();
	}

}