
import java.util.*;

public class DirectedGraph<E extends Edge> {

	//maybe HashMap for graph
	//private HashMap<> graph;

	//Probably need to represent edges


	public DirectedGraph(int noOfNodes) {
		//initialize the data

	}

	public void addEdge(E e) {
		;
	}

	public Iterator<E> shortestPath(int from, int to) {
		//perform the Dijsktra algorithm
		/*

		1: lägg(startnod, 0, tom väg) i Priority queue

		2: While(Priority que is not empty)

		3: (nod, cost, path) = First element in priority queue

		4: If node is not visited

		5: If node is the end, return path

		6: else, add to visited

		7: for every neighbor if not visited add to priority queue

		//Very uncertain of what should be here respectively in CompDijsktraPath


		 */
		return null;
	}
		
	public Iterator<E> minimumSpanningTree() {
		//skapa ett fält cc som för varje nod innehåller en egen tom lista (som skall komma att innehålla bågar sen) (dvs varje nod är i en egen komponent)
		//Lägg in alla bågar i en prioritetskö pq
		//Så länge pq ej är tom && |cc| > 1 {
		//hämta e = (from, to, weight) från pq
		//om from och to inte refererar till samma lista i cc {
		//flytta över alla elementen från den kortare listan till den andra och se till att alla berörda //noder i cc refererar till den påfyllda listan
		//lägg slutligen e i den påfyllda listan
		//} borde den vara här, eller en rad upp?
		//}


		return null;
	}

}
  
