import java.lang.reflect.Array;
import java.util.*;

public class DirectedGraph<E extends Edge> {
	//list with all the edges
	private List<E> edges;
	//list with all the nodes and all their neighbors
	private HashMap<Integer, ArrayList<E>> nodes;
	//number of nodes in the graph
	private int noOfNodes;
	//create a DirectedGraph with the specified number of nodes
	public DirectedGraph(int noOfNodes) {
		edges = new ArrayList<>();
		nodes = new HashMap<>();
		this.noOfNodes = noOfNodes;
	}
	//add a specified, non-null edge to the graph
	public void addEdge(E e) {
		if (e != null) {
			edges.add(e);
			//the from node of the specified edge
			Integer from = e.from;
			//if the from node is not already included in the graph, put it there
			if(!nodes.containsKey(from)){
				nodes.put(from, new ArrayList());
			}
			//add the new edge to the from node
			nodes.get(from).add(e);
		}
	}
	//find the shortest path between a node from and a node to
	public Iterator<E> shortestPath(int from, int to) {
		//list to contain visited nodes
		List<Integer> visited = new ArrayList<>();
		//the queue of edges
		PriorityQueue<CompDijsktraPath> queue = new PriorityQueue<>();
		//add a first path to the queue
		queue.add(new CompDijsktraPath(from, 0, null));
		//until the queue is empty
		while (!queue.isEmpty()) {
			//get current path
			CompDijsktraPath current = queue.poll();
			//if the current node is not visited
			if (!visited.contains(current.nodeNo)) {
				//if the current node is the to node, we've reached the end so we return the path
				if (current.nodeNo == to) {
					return current.path.iterator();
				} else {
					//add the current node to the list of visited nodes
					visited.add(current.nodeNo);
					//get outgoing edges from the current node
					ArrayList<E> outgoingEdges = nodes.get(current.nodeNo);
					//for each outgoing edge from the current node
					for(E outEdge:outgoingEdges){
						//the to node of the outgoing edge
						Integer toNode = outEdge.to;
						//if the to node is not visited
						if(!visited.contains(toNode)){
							//create a new path consisitng of the current path plus the outgoing edge
							List<E> newPath = new ArrayList<>(current.path);
							newPath.add(outEdge);
							//add the new path to the queue
							queue.add(new CompDijsktraPath(toNode, current.cost + outEdge.getWeight(), newPath));
						}
					}
				}
			}
		}
		//didn't find destination
		System.out.println("Destination was not found, something's probably wrong in graph");
		return null;
	}
	//find minimum spanning tree of the graph
	public Iterator<E> minimumSpanningTree() {
		//the queue of edges
		PriorityQueue<CompKruskalEdge<E>> pq = new PriorityQueue();
		//all the subtrees
		LinkedList<Integer> [] subtrees = new LinkedList[noOfNodes];
		//make a new subtree for each node, that contains the node
		for (int i = 0; i < noOfNodes; i++){
			subtrees[i] = new LinkedList();
			subtrees[i].add(i);
		}
		//the minimum spanning tree
		List<E> mST = new ArrayList<>();
		//add the edges to the queue
		for(E edge:edges){
			pq.add(new CompKruskalEdge<E>(edge));
		}
		//while the queue is not empty and the tree is not finished
		while(!pq.isEmpty() && (!mSTDone(subtrees))){
			//the current edge
			CompKruskalEdge<E> current = pq.poll();
			//current subtrees
			LinkedList<Integer> sub1 = subtrees[current.edge.from];
			LinkedList<Integer> sub2 = subtrees[current.edge.to];
			//if the two subtrees are not equeal
			if(!(sub1.equals(sub2))){
				//if subtree 1 is greater than subtree 2
				if(sub1.size() > sub2.size()){
					//add all to the bigger tree
					sub1.addAll(sub2);
					//point all from the lesser tree to the greater tree
					for(Integer node: sub2){
						subtrees[node] = sub1;
					}
				}
				//else the other tree is the greater
				else {
					sub2.addAll(sub1);
					//point all from the lesser tree to the greater tree
					for(Integer node: sub1){
						subtrees[node] = sub2;
					}
				}
				//add the edge to the minimal spanning tree
				mST.add(current.edge);
			}
		}
		return mST.iterator();
	}
	//helper method that returns true if the minimal spanning tree is done
	private boolean mSTDone(LinkedList[] subtrees){
		//when any of the subtrees contains all the nodes, all of them do, and the tree is done
		//check an arbitrary subtree
		return (subtrees[0].size() == noOfNodes);
	}
}
