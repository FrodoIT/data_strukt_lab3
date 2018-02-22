import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class CompDijsktraPath<NO extends NodeObject> implements Comparable<CompDijsktraPath>{

    //Data strucures for the algorithm;
    public NO node;
    public double cost;
    public List<Integer> prevNode;

    public CompDijsktraPath(NO node, double cost, NO prevNode) {
        this.node = node;
        this.cost = cost;
        this.prevNode = prevNode;
    }

    @Override
    public int compareTo(CompDijsktraPath compDijsktraPath) {
        return 0;
    }
}




