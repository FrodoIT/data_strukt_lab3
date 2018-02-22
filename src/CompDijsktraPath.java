import java.util.*;

public class CompDijsktraPath<NO extends NodeObject> implements Comparable<CompDijsktraPath>{

    //Data strucures for the algorithm;
    public NO node;
    public double cost;
    public List<Integer> path;

    public CompDijsktraPath(NO node, double cost, List path) {
        this.node = node;
        this.cost = cost;
        this.path = path;
        this.path.add(node.getNodeNo());
    }

    @Override
    public int compareTo(CompDijsktraPath compDijsktraPath) {
        return Double.compare(this.cost,compDijsktraPath.cost);
    }
}




