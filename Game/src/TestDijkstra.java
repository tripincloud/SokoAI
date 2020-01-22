import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDijkstra {
	public static void main(String[] args) {
		/* Donnees du test du jeu Sokoban : 
		(a adapter pour correspondre a l'algo Dijkstra ? mettre les donnees en tableau ?)
		
		int x = 10;
		int y = 10;
		int[][] plateau = new int[x][y]; 
		
		Position positionjoueur = new Position(5, 5);

		Niveau lvl = new Niveau(10, 10);
		Configuration c = new Configuration(lvl, positionjoueur);

		Position p1 = new Position(3, 2);
		Position p2 = new Position(3, 7);
		Position p3 = new Position(2, 3);
		Position p4 = new Position(3, 6);

		c.niveau.addCible(p4);
		c.addCaisse(p3);
		c.niveau.addCible(p1);
		c.addCaisse(p2);
		*/
		
		int[][] graphe = new int[][] { 
			{ 0, -1, -1, 1, -1, -1, -1, -1, -1 },
		 	{ -1, 0, -1, -1, -1, -1, -1, -1, -1 },
		  	{ -1, -1, 0, -1, -1, 1, -1, -1, -1 },
		  	{ 1, -1, -1, 0, 1, -1, 1, -1, -1 }, 
		  	{ -1, -1, -1, 1, 0, 1, -1, 1, -1 }, 
		  	{ -1, -1, 1, -1, 1, 0, -1, -1, 1 }, 
		  	{ -1, -1, -1, 1, -1, -1, 0, 1, -1 }, 
		  	{ -1, -1, -1, -1, 1, -1, 1, 0, 1 }, 
		  	{ -1, -1, -1, -1, -1, 1, -1, 1, 0 } 
		 };
		 
		Dijkstra dijkstra = new Dijkstra();
		int dimension = graphe.length;
		
		System.out.printf("Tableau (%dx%d) représentant les données du graphe : \n", dimension, dimension);
		for(int i = 0 ; i < graphe.length; i++ ){
			for(int j = 0; j< graphe[i].length; j++){
				System.out.printf("%3d ",graphe[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("--------Chemin 1--------");
		List<Integer> actuals1 = dijkstra.getPath(graphe, 2, 7);
		System.out.println("Le chemin le plus court de 2 à 7 est : ");
		System.out.println(actuals1); 
		System.out.println("-Données du chemin 1-");
		dijkstra.printDonnees();
		System.out.println();
		
		/*
		//Test de l'algo Dijkstra avec un tableau de donnees arbitraire : 
		int[][] graphe = new int[][] { 
			{ 0, 85, 217, -1, 173, -1, -1, -1, -1, -1 },
		 	{ 85, 0, -1, -1, -1, 80, -1, -1, -1, -1 },
		  	{ 217, -1, 0, -1, -1, -1, 186, 103, -1, -1 },
		  	{ -1, -1, -1, 0, -1, -1, -1, 183, -1, -1 }, 
		  	{ 173, -1, -1, -1, 0, -1, -1, -1, -1, 502 }, 
		  	{ -1, 80, -1, -1, -1, 0, -1, -1, 250, -1 }, 
		  	{ -1, -1, 186, -1, -1, -1, 0, -1, -1, -1 }, 
		  	{ -1, -1, 103, 183, -1, -1, -1, 0, -1, 167 }, 
		  	{ -1, -1, -1, -1, -1, 250, -1, -1, 0, 84 }, 
		  	{ -1, -1, -1, -1, 502, -1, -1, 167, 84, 0 } 
		 }; // -1 = "rien" (choix arbitraire, tant que c'est strictement inferieur a 0)
		 // pas de lien avec les -1 de la classe Dijkstra
		
		Dijkstra dijkstra = new Dijkstra();
		int dimension = graphe.length;
		
		System.out.printf("Tableau (%dx%d) représentant les données du graphe : \n", dimension, dimension);
		for(int i = 0 ; i < graphe.length; i++ ){
			for(int j = 0; j< graphe[i].length; j++){
				System.out.printf("%3d ",graphe[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("--------Chemin 1--------");
		List<Integer> actuals1 = dijkstra.getPath(graphe, 0, 9);
		System.out.print("Le chemin le plus court de 0 à 9 est : ");
		System.out.println(actuals1); //expected [0, 2, 7, 9]
		System.out.println("-Données du chemin 1-");
		dijkstra.toString();
		System.out.println();
		
		System.out.println("--------Chemin 2--------");
		List<Integer> actuals2 = dijkstra.getPath(graphe, 0, new int[] { 9, 3 });
		System.out.print("Le chemin le plus court de 0 à 9 ou 3 est : ");
		System.out.println(actuals2); //expected [0, 2, 7, 9]
		System.out.println("-Données du chemin 2-");
		dijkstra.toString();
		System.out.println();
		
		System.out.println("--------Chemin 3--------");
		List<Integer> actuals3 = dijkstra.getPath(graphe, new int[] { 3, 1 }, new int[] { 9, 8 });
		System.out.print("Le chemin le plus court de 3 ou 1 à 9 ou 8 est : ");
		System.out.println(actuals3); //expected [1, 5, 8]
		System.out.println("-Données du chemin 3-");
		dijkstra.toString();
		System.out.println();
		*/
		
		
	
	}
	
}
