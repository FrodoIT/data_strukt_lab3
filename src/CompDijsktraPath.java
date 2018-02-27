import java.util.*;

public class CompDijsktraPath <E extends Edge>implements Comparable<CompDijsktraPath>{
	//the number of the current node
	public Integer nodeNo;
	//the cost
	public double cost;
	//list that holds the path, represented by edges
	public List<E> path;
	//create a CompDijsktraPath with the specified node number, cost and path
	public CompDijsktraPath(Integer nodeNo, double cost, List path) {
		this.nodeNo = nodeNo;
		this.cost = cost;
		//if the specified path is not null, set path to the specified path
		if (path != null){
			this.path = path;
		}
		//else create a new empty path list
		else {
			this.path = new ArrayList<E>();
		}
	}
	//use compare method in Double to compare the cost of this path with another path
	@Override
	public int compareTo(CompDijsktraPath compDijsktraPath) {
		return Double.compare(this.cost,compDijsktraPath.cost);
	}
}




