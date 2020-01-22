import java.util.List;

/*
 * DijkstraInterface permet de trouver le plus court chemin entre
 * deux noeuds d'un graphe. Il est aussi possible de trouver le
 * chemin le plus court entre plusieurs points de depart et plusieurs
 * destinations possibles.
 */
public interface DijkstraInterface {

	/*
	 * @param graph
	 *		graphe representant les donnees du probleme
	 * @param start
	 *		depart
	 * @param end
	 *		destination
	 * @return 
	 *		la liste des points a parcourir (point de depart et d'arrivee inclus), 
	 *		ou null s'il n'existe pas de chemin reliant le point de depart au point d'arrivee
	 */
	public List<Integer> getPath(final int[][] graph, final int start, final int end);

	/*
	 * @param graph
	 *		graphe representant les donnees du probleme
	 * @param start
	 *		tableau des departs possibles
	 * @param end
	 *		destination
	 * @return 
	 *		la liste des points a parcourir (point de depart et destination inclus), 
	 *		telle que le chemin reliant ces deux points est le plus court 
	 *		parmi toutes les combinaisons existantes entre le point de depart 
	 *		et les differentes destinations donnes
	 */
	public List<Integer> getPath(final int[][] graph, final List<Integer> start, final int ends);

	/*
	 * @param graph
	 *		graphe representant les donnees du probleme
	 * @param start
	 *		depart
	 * @param end
	 *		tableau des destinations possibles
	 * @return 
	 *		la liste des points a parcourir (point de depart et destination inclus), 
	 *		telle que le chemin reliant ces deux points est le plus court 
	 *		parmi toutes les combinaisons existantes entre le point de depart 
	 *		et les differentes destinations donnes
	 */
	public List<Integer> getPath(final int[][] graph, final int start, final List<Integer> ends);

	/*
	 * @param graph
	 *		graphe representant les donnees du probleme
	 * @param start
	 *		tableau des departs possibles 
	 * @param end
	 *		tableau des destinations possibles
	 * @return 
	 *		la liste des points a parcourir (point de depart et destination inclus), 
	 *		telle que le chemin reliant ces deux points est le plus court 
	 *		parmi toutes les combinaisons existantes 
	 *		entre les differents points de depart et d'arrivee donnes
	 */
	public List<Integer> getPath(final int[][] graph, final List<Integer> starts, final List<Integer> ends);
}
