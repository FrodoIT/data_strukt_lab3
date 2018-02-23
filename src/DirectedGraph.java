import java.lang.reflect.Array;
import java.util.*;

public class DirectedGraph<E extends Edge> {

	//all the edges
	private List<Edge> edges;
	//list of nodes and all their neighbors
	private HashMap<Integer,ArrayList<Edge>> nodes;

	private int noOfNodes;

	public DirectedGraph(int noOfNodes) {
		//initialize the data
		edges = new ArrayList<>();
		nodes = new HashMap<>();
		this.noOfNodes = noOfNodes;
	}

	public void addEdge(E e) {
		if (e != null) {
			edges.add(e);

			Integer from = e.from;

			if(!nodes.containsKey(from)){
				nodes.put(from,new ArrayList());
			}
			nodes.get(from).add(e);
		}
	}

	public Iterator<E> shortestPath(int from, int to) {

		//visited nodes
		List<Integer> visited = new ArrayList<>();

		//the queue of edges
		PriorityQueue<CompDijsktraPath> queue = new PriorityQueue<>();

		//add first to the queue
		queue.add(new CompDijsktraPath(from, 0, null));

		//until the queue is empty
		while (!queue.isEmpty()) {

			//current path
			CompDijsktraPath current = queue.poll();

			if (!visited.contains(current.nodeNo)) {

				if (current.nodeNo == to) {

					//if at the end return the path
					return current.path.iterator();

				} else {
					visited.add(current.nodeNo);

					//neighbors of the current node
					ArrayList<Edge> neighbors = nodes.get(current.nodeNo);

					for(Edge neighbor:neighbors){
						Integer neighborNo = neighbor.to;
						if(!visited.contains(neighborNo)){
							List<Edge> newPath = new ArrayList<>(current.path);
							newPath.add(neighbor);
							queue.add(new CompDijsktraPath(neighborNo,current.cost + neighbor.getWeight(),newPath));
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

		//The queue of edges
		PriorityQueue<CompKruskalEdge> pq = new PriorityQueue();

		//All the subtrees
		LinkedList<Integer> [] subtrees = new LinkedList[noOfNodes];

		for (int i = 0; i < noOfNodes; i++){
			subtrees[i] = new LinkedList();
			subtrees[i].add(i);
		}

		//the Minimal Spanning Tree
		List<E> mST = new ArrayList<E>();

		//Add edges to the queue
		for(Edge edge:edges){
			pq.add(new CompKruskalEdge(edge));
		}

		//while the queue is not empty and the tree is not finished
		while(!pq.isEmpty() && (!mSTDone(subtrees))){

			//current edge
			CompKruskalEdge current = pq.poll();

			//Current subtrees
			LinkedList<Integer> sub1 = subtrees[current.edge.from];
			LinkedList<Integer> sub2 = subtrees[current.edge.to];

			if(!(sub1.equals(sub2))){
				if(sub1.size() > sub2.size()){

					//add all to the bigger tree
					sub1.addAll(sub2);

					//point all from the smaller tree to the bigger tree
					for(Integer node: sub2){
						subtrees[node] = sub1;
					}
				}
				else {

					//add all to the bigger tree
					sub2.addAll(sub1);

					//point all from the smaller tree to the bigger tree
					for(Integer node: sub1){
						subtrees[node] = sub2;
					}
				}

				//Add the edge to the minimal spanning tree
				mST.add((E) current.edge);
			}
		}
		return mST.iterator();
	}

	private boolean mSTDone(LinkedList[] subtrees){
		return (subtrees[0].size() == noOfNodes);
	}
}