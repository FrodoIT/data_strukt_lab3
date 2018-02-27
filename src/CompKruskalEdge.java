public class CompKruskalEdge<E extends Edge> implements Comparable<CompKruskalEdge> {

    public E edge;

    public CompKruskalEdge(E edge){
        this.edge = edge;

    }

    @Override
    public int compareTo(CompKruskalEdge compKruskalEdge) {
        return Double.compare(this.edge.getWeight(), compKruskalEdge.edge.getWeight());
    }
}
