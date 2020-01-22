import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

/*
 * Algorithme de Dijkstra generale 
 * pour un tableau de donnees representant un graphe
 */
public class Dijkstra implements DijkstraInterface {
	
	private int[][] graph;
	private int dim;
	private int[] distanceFromStart;
	private int[] precedences;
	private boolean[] activesNodes;
	
	
	@Override
	public List<Integer> getPath(final int[][] graph, final int start, final int end) {
		List<Integer> debut = new ArrayList<Integer>(); 
		debut.add(start);
		
		List<Integer> fin = new ArrayList<Integer>(); 
		fin.add(end);
		
		return this.getPath(graph, debut, fin);
	}
	
	@Override
	public List<Integer> getPath(final int[][] graph, final List<Integer> start, final int ends) {
		List<Integer> fin = new ArrayList<Integer>(); 
		fin.add(ends);
		
		return this.getPath(graph, start, fin);
	}
	
	@Override
	public List<Integer> getPath(final int[][] graph, final int start, final List<Integer> ends) {
		List<Integer> debut = new ArrayList<Integer>(); 
		debut.add(start);
		
		return this.getPath(graph, debut, ends);
	}
	
	@Override
	public List<Integer> getPath(final int[][] graph, final List<Integer> starts, final List<Integer> ends) {
		Collections.sort(ends);

		// initialisation des variables necessaires a la resolution du probleme
		this.init(graph, starts);

		// calcul des distances par rapport au point de depart et recuperation du point d'arrivee
		final int end = this.processDistances(ends);

		// construit et retourne l'itineraire
		return this.buildPath(end);
	}
	
	private void init(final int[][] graph, final List<Integer> start) {
		this.graph = graph;
		this.dim = graph.length;
		this.activesNodes = new boolean[this.dim];

		this.precedences = new int[this.dim];
		Arrays.fill(this.precedences, -1); 
		// -1 = "rien" (choix arbitraire, tant que c'est strictement inferieur a 0)
		// pas de lien avec les autres -1 de la classe ou ceux du graphe de la classe TestDijkstra

		this.distanceFromStart = new int[this.dim];
		Arrays.fill(this.distanceFromStart, Integer.MAX_VALUE);

		for (final int value : start)
			this.activeNode(value, value, 0);
	}
	
	private void activeNode(final int from, final int node, final int distance) {
		this.distanceFromStart[node] = distance;
		this.precedences[node] = from;
		this.activesNodes[node] = true;
	}
	
	private int processDistances(final List<Integer> ends) {
		// selectionne le prochain noeud a analyser (noeud courant)
		final int next = this.selectNextNode();

		// return -1 s'il n'y a plus de noeud a analyser 
		// soit quand il n'existe pas de chemin satisfaisant la recherche
		// (le choix du -1 est arbitraire (tant qu'il est stricetement inferieur a 0) 
		// s'il est modifie, il ne faut pas oublier 
		// de modifier le nextNode et le if de la methode selectNextNode() 
		// et ce -1 n'a pas de lien avec les -1 du graphe dans la classe TestDijkstra)
		if (next == -1)
			return -1;

		// retourne la position du noeud actuel s'il appartient au tableau des destinations possibles
		if (Collections.binarySearch(ends, next) >= 0)
			return next;

		// active les prochains noeuds a analyser a partir du noeud courant
		this.activeAdjacents(next);

		// desactive le noeud courant
		this.activesNodes[next] = false;

		// appel recursif de la methode pour traiter le prochain noeud
		return this.processDistances(ends);
	}

	private int selectNextNode() { 
		int nextNode = -1;
		for (int node = 0; node < this.dim; node++)
			if (this.activesNodes[node] && (nextNode == -1 || this.distanceFromStart[node] < this.distanceFromStart[nextNode]))
				nextNode = node;

		return nextNode;
	}
	
	private void activeAdjacents(final int node) {
		int distanceTo;
		for (int to = 0; to < this.dim; to++)
			if (this.isAdjacent(node, to) && (distanceTo = this.distanceFromStart[node] + this.graph[node][to]) < this.distanceFromStart[to])
				this.activeNode(node, to, distanceTo);
	}

	private boolean isAdjacent(final int from, final int to) {
		return this.graph[from][to] >= 0;
	}

	private List<Integer> buildPath(final int end) {
		final List<Integer> path = new ArrayList<Integer>();
		path.add(end);

		// utilisation d'une boucle do-while pour conserver le point de depart
		// et d'arrivee dans la liste même lorsque le point de depart correspond
		// au point d'arrivee
		int position = end;
		do {
			path.add(0, this.precedences[position]);
			position = path.get(0);
		} while (this.distanceFromStart[position] != 0);

		return path;
	}
	
	public int getDim() {
		return this.dim;
	}

	// affichage des donnees du chemin parcouru 
	// (System.out.printf au lieu de return pour un affichage plus jolie, car formatage de l'affichage)
	public void printDonnees() {
		System.out.printf("%21s", "indice du tableau : ");
		for(int i = 0 ; i < dim; i++ ){
				System.out.printf("%5d ", i);
		}
		System.out.println();
		
		System.out.printf("%21s", "distanceFromStart : ");
		for(int i = 0 ; i < dim; i++ ){
				System.out.printf("%5d ", this.distanceFromStart[i]);
		}
		System.out.println();
		
		System.out.printf("%21s", "precedences : ");
		for(int i = 0 ; i < dim; i++ ){
				System.out.printf("%5d ", this.precedences[i]);
		}
		System.out.println();
		
		System.out.printf("%21s", "activesNodes : ");
		for(int i = 0 ; i < dim; i++ ){
				System.out.printf("%5b ", this.activesNodes[i]);
		}
		System.out.println();
	}

}
