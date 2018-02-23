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
					for(Edge edge:edges){
						if(edge.from == current.nodeNo){
							Integer neighborNo = edge.to;
							if(!visited.contains(neighborNo)){
								List<Edge> newPath = new ArrayList<>(current.path);
								newPath.add(edge);
								queue.add(new CompDijsktraPath(neighborNo,current.cost + edge.getWeight(),newPath));
							}
						}
					}
				}
			}
		}
		//Destination was not found... wrong in graph
		System.out.println("Destination was not found, something probably wrong in graph");
		return null;
	}



	public Iterator<E> minimumSpanningTree() {

		PriorityQueue<CompKruskalEdge> pq = new PriorityQueue();

		//The visied nodes

		LinkedList<Integer> [] subtrees = new LinkedList[noOfNodes];

		for (int i = 0; i < noOfNodes; i++){
			subtrees[i] = new LinkedList();
			subtrees[i].add(i);
		}


		List<E> mST = new ArrayList<E>();

		//Lägg in alla bågar i en prioritetskö pq
		for(Edge edge:edges){
			pq.add(new CompKruskalEdge(edge));
		}

		//Så länge pq ej är tom && |cc| > 1 {
		while(!pq.isEmpty() && (!mSTDone(subtrees))){

			//hämta e = (from, to, weight) från pq
			CompKruskalEdge current = pq.poll();

			int from = current.edge.from;
			int to = current.edge.to;

			if(!(subtrees[from].equals(subtrees[to]))){
				if(subtrees[from].size() > subtrees[to].size()){
					subtrees[from].addAll(subtrees[to]);
					mergeSubtree(subtrees[from], subtrees[to], subtrees);
				}
				else {
					subtrees[to].addAll(subtrees[from]);
					mergeSubtree(subtrees[to], subtrees[from], subtrees);
				}
				mST.add((E) current.edge);
			}
		}
		return mST.iterator();
	}

	public boolean mSTDone(LinkedList[] subtrees){
		return (subtrees[0].size() == noOfNodes);
	}

	public void mergeSubtree(LinkedList<Integer> bigger, LinkedList<Integer> smaller, LinkedList[]subtrees){
		for(Integer node: smaller){
			subtrees[node] = bigger;
		}
	}

}