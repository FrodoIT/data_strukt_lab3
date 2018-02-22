import java.util.*;

public class CompDijsktraPath <E extends Edge>implements Comparable<CompDijsktraPath>{

    //Data strucures for the algorithm;
    public Integer nodeNo;
    public double cost;
    public List<E> path;

    public CompDijsktraPath(Integer nodeNo, double cost, List path) {
        this.nodeNo = nodeNo;
        this.cost = cost;

        if (path != null){
            this.path = path;
        }
        else {
            this.path = new ArrayList<E>();
        }
    }

    @Override
    public int compareTo(CompDijsktraPath compDijsktraPath) {
        return Double.compare(this.cost,compDijsktraPath.cost);
    }
}




