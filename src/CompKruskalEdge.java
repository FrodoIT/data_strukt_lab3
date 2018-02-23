public class CompKruskalEdge implements Comparable<CompKruskalEdge> {

    public Edge edge;

    public CompKruskalEdge(Edge edge){
        this.edge = edge;

    }

    @Override
    public int compareTo(CompKruskalEdge compKruskalEdge) {
        return Double.compare(this.edge.getWeight(), compKruskalEdge.edge.getWeight());
    }
}
