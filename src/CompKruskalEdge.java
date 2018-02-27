public class CompKruskalEdge<E extends Edge> implements Comparable<CompKruskalEdge> {
	//the edge
	public E edge;
	//create a CompKruskalEdge with a specified edge
	public CompKruskalEdge(E edge){
		this.edge = edge;
	}
	//use the compare method from Double to compare the weight of this edge to the weight of another edge
	@Override
	public int compareTo(CompKruskalEdge compKruskalEdge) {
		return Double.compare(this.edge.getWeight(), compKruskalEdge.edge.getWeight());
	}
}
