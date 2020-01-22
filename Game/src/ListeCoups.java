import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListeCoups {
	private List<Direction> directions;
	private Configuration config;
	private Niveau lvl;
	private Dijkstra dijkstra;
	private int xLVL, yLVL, nbNoeuds;
	private int [][] tableau, graphe, grapheSansCaisses;
		
	public ListeCoups(Configuration config) {
		this.config = config;
		this.lvl = config.niveau;
		
		this.directions = new ArrayList<Direction>();
		this.dijkstra = new Dijkstra(); //algorithme de Dijkstra dans la classe Dijkstra
		
		this.xLVL = lvl.getX() - 2;
		this.yLVL = lvl.getY() - 2;
		this.nbNoeuds = xLVL * yLVL; //nombre de case à l'intérieur des murs du niveau
		
		//tableau représentant le numéro de la case qui est en indice et les coordonnées correspondantes
		this.tableau = new int[nbNoeuds][2];
		this.caseEnTableau();
		
		//tableau représentant les distances entre chaque case du jeu en comptant les caisses
		this.graphe = new int[nbNoeuds][nbNoeuds];
		this.jeuEnTableauAvecCaisses();
		
		//tableau représentant les distances entre chaque case du jeu sans compter les caisses
		this.grapheSansCaisses = new int[nbNoeuds][nbNoeuds];
		this.jeuEnTableauSansCaisses();
	} 
	
	
	//utilisation de l'algorithme de Dijkstra selon les cas
	public List<Integer> cheminLePlusCourt(int depart, int arrivee) {
		List<Integer> chemin = dijkstra.getPath(graphe, depart, arrivee);
		return chemin;
	}
	
	public List<Integer> cheminLePlusCourt(int depart, List<Integer> listeArrivees) {
		List<Integer> chemin = dijkstra.getPath(graphe, depart, listeArrivees);
		return chemin;
	}
	
	public List<Integer> cheminLePlusCourt(List<Integer> listeDeparts, List<Integer> listeArrivees) {
		List<Integer> chemin = dijkstra.getPath(graphe, listeDeparts, listeArrivees);
		return chemin;
	}
	
	//convertis le numéro de la case en position
	public Position caseEnPosition(int c) {
		int x = tableau[c][0];
		int y = tableau[c][1];
		Position p = new Position(x, y);
		
		return p;
	}
	
	//convertis la position du joueur en nuémro de case
	public int caseJoueur() {
		int joueur = 0; 
		
		int x = config.joueur.getPosition().x;
		int y = config.joueur.getPosition().y;
		
		for (int j=0; j<tableau.length; j++) {
			if ( (x == tableau[j][0]) && (y == tableau[j][1]) ) {
				joueur = j;
			}
		}
		
		return joueur; 
	}
	
	//convertis la position des caisses en nuémro de case
	public List<Integer> caseCaisses() {
		ArrayList<Caisse> listCaisse = config.getCaisses();
		int nbCaisses = listCaisse.size();
		Position[] tabPosition = new Position[nbCaisses]; 
		List<Integer> caisses = new ArrayList<Integer>(); 
		
		for (int i=0; i<nbCaisses; i++) {
			//tableau contenant la position de chaque caisse
			tabPosition[i] = listCaisse.get(i).getPosition();
			
			for (int j=0; j<tableau.length; j++) {
				if ( (tabPosition[i].x == tableau[j][0]) && (tabPosition[i].y == tableau[j][1]) ) {
					//tableau contenant le numéro de la case de chaque caisse
					caisses.add(j);
				}
			}
		}
		
		return caisses;
	}
	
	//convertis la position des cibles en nuémro de case
	public List<Integer> caseCibles() {
		ArrayList<Position> listCibles = config.niveau.getCibles();
		int nbCibles = listCibles.size();
		List<Integer> cibles = new ArrayList<Integer>();
		
		for (int i=0; i<nbCibles; i++) {
			for (int j=0; j<tableau.length; j++) {
				if ( (listCibles.get(i).x == tableau[j][0]) && (listCibles.get(i).y == tableau[j][1]) ) {
					//tableau contenant le numéro de la case de chaque cible
					cibles.add(j);
				}
			}
		} 
		
		return cibles;
	}
	
	//renvoie une liste de chemin entre les caisses et la cible la plus proche de la caisse
	public List<List<Integer>> caissesACible() {
		List<List<Integer>> path = new ArrayList<List<Integer>>();
		List<Integer> caisses = this.caseCaisses(); 
		List<Integer> cibles = this.caseCibles(); 
		
		for (int i=0; i<caisses.size(); i++) {
			//ajout du chemin de la caisse i à la cible la plus proche
			path.add(this.cheminLePlusCourt(caisses.get(i), cibles));
		}
		
		return path;
	}
	
	
	//renvoie le chemin le plus court entre le joueur et la caisse la plus proche
	public List<Integer> joueurACaisse() {
		List<Integer> path = new ArrayList<Integer>();
		int joueur = this.caseJoueur(); 
		List<Integer> caisses = this.caseCaisses(); 
		
		path = cheminLePlusCourt(joueur, caisses);
		
		return path;
	}
	
	//renvoie la case de la caisse la plus proche du joueur
	public Integer caisseLaPlusProcheDuJoueur() {
		List<Integer> path = this.joueurACaisse();
		
		int caisse = path.get(path.size()-1);
		
		return caisse;
	}
	
	//renvoie le chemin le plus court entre une caisse et une cible
	public List<Integer> cheminCibleLaPlusProcheDeCaisse() {
		List<Integer> cible = new ArrayList<Integer>();
		List<List<Integer>> path = this.caissesACible();
		
		//prend le chemin le plus court de caisseACible()
		for (int i=0; i<path.size()-1; i++) {
			if (path.get(i).size() < path.get(i+1).size()) {
				for (int j=0; j<path.get(i).size(); j++) {
					cible.add(path.get(i).get(j));
				}
			}
		}
		
		return cible;
	}
	
	//renvoie le chemin le plus court entre la caisse la plus proche du joueur et une cible
	public List<Integer> cheminCaisseLaPlusProcheDuJoueurACible() {
		List<Integer> path = new ArrayList<Integer>();
		int caisse = this.caisseLaPlusProcheDuJoueur();
		List<Integer> cibles = this.caseCibles(); 
		
		path = this.cheminLePlusCourt(caisse, cibles);
		
		return path;
	}
	
	public void infoJeu(SokobanUI sokoban, Solver s) {
		sokoban.show(config);
		System.out.println(config);
		System.out.println(config.getJoueur().getHisto());
		System.out.println(s.getTotalSteps());
	}
	
	//déplacement du joueur selon les données du jeu
	public void deplacementJoueur(SokobanUI sokoban, Solver s) {
		int but=0;
		Position p; 
		int cca, cci;
		
		int joueur = this.caseJoueur(); 
		List<Integer> listCaisses = new ArrayList<Integer>();
		List<Integer> listCaissesDebut = this.caseCaisses();
		List<Integer> listCibles = this.caseCibles();
		Direction dir = Direction.AUCUNE;
		
		List<Integer> listCaissesRestantes = listCaissesDebut;
		List<List<Integer>> path = new ArrayList<List<Integer>>();
		List<Integer> caisse = this.cheminCaisseLaPlusProcheDuJoueurACible();
		
		List<Integer> liste = new ArrayList<Integer>();
		liste.add(0);
		path.add(liste); //initialisation du premier élément pour éviter un bug au moment de path.set
		
		
		System.out.println("listCaissesDebut : "+listCaissesDebut);
		System.out.println("listCibles 0 : "+listCibles);
		System.out.println("caisse 0 : "+caisse);
		while (listCibles != null) {
			for (int i=0; i<caisse.size()-1; i++) {
				//but : prend la case opposé du chemin à suivre pour que le joueur pousse la caisse vers la cible
				//dir : prend la direction vers laquelle le joueur doit pousser la caisse vers la cible une fois que le joueur est au contact de la caisse
				if (caisse.get(i+1) == caisse.get(i)+yLVL) {
					but = caisse.get(i)-yLVL;
					dir = Direction.DROITE;
				}
				else if (caisse.get(i+1) == caisse.get(i)-yLVL) {
					but = caisse.get(i)+yLVL;
					dir = Direction.GAUCHE;
				}
				else if (caisse.get(i+1) == caisse.get(i)-1) {
					but = caisse.get(i)+1;
					dir = Direction.HAUT;
				}
				else if (caisse.get(i+1) == caisse.get(i)+1) {
					but = caisse.get(i)-1;
					dir = Direction.BAS;
				}
				else {
					but = 0;
					dir = Direction.AUCUNE;
				}
				
				p = this.caseEnPosition(but);
				
				//s'il y a un mur à l'endroit où le joueur doit se placer pour pousser la case
				if (lvl.get(p) instanceof Mur) {
					while (lvl.get(p) instanceof Mur) {
						//changement de direction alétoire
						if(dir == Direction.DROITE || dir == Direction.GAUCHE) {
							int random = (int) (Math.random() * 2); // random entre 0 et 2 exclu
							switch (random) {
								case 0:
									but = caisse.get(i)+1;
									dir = Direction.HAUT;
									break;
								default:
									but = caisse.get(i)-1;
									dir = Direction.BAS;
							}
						}
						else {
							int random = (int) (Math.random() * 3); 
							switch (random) {
								case 0:
									but = caisse.get(i)-yLVL;
									dir = Direction.DROITE;
									break;
								default:
									but = caisse.get(i)+yLVL;
									dir = Direction.GAUCHE;
							}
						}
						p = this.caseEnPosition(but);
					}
				}
				
				if (but != 0) {
					//path : chemin le plus court entre le joueur et but
					path.set(0, this.dijkstra.getPath(grapheSansCaisses, joueur, but));
					//utilisation du graphe sans les caisses 
					//car le joueur ne peut pas se placer sur une case où il y a déjà une caisse 
					//car le joueur doit pousser la caisse 
					//donc ne peut pas se mettre dessus
					
					for (int j=0; j<path.size(); j++) {
						for (int k=0; k<path.get(j).size()-1; k++) {
							//directions : convertis le chemin de case en direction
							if (path.get(j).get(k+1) == path.get(j).get(k)+yLVL) {
								directions.add(Direction.DROITE);
							}
							else if (path.get(j).get(k+1) == path.get(j).get(k)-yLVL) {
								directions.add(Direction.GAUCHE);
							}
							else if (path.get(j).get(k+1) == path.get(j).get(k)-1) {
								directions.add(Direction.HAUT);
							}
							else if (path.get(j).get(k+1) == path.get(j).get(k)+1) {
								directions.add(Direction.BAS);
							}
						}//for k
						
						//ajout de la derniere direction pour pousser la case si besoin
						if (j == 0) {
							directions.add(dir);
						}
						
						//le joueur bouge selon les directions qu'il y a dans la liste directions
						for (int d=0; d<directions.size(); d++) {
							config.bougerJoueurVers(directions.get(d));
							s.step(); //step ne contient que le compte total des pas
							this.infoJeu(sokoban, s);
							sokoban.pause(50);
						}
						//mise à jour des graphes, du joueur et des caisses, et réinitialisation de directions 
						this.jeuEnTableauAvecCaisses();
						this.jeuEnTableauSansCaisses();
						listCaisses = this.caseCaisses();
						joueur = this.caseJoueur();
						directions.clear();
						
						//s'il y a une caisse sur une cible
						//on enleve la cible de listCibles et la caisse de listCaissesRestantes
						if( !(listCibles.isEmpty()) || !(listCaisses.isEmpty()) ) {
							for(int ci=0; ci<listCibles.size(); ci++){
								for(int ca=0; ca<listCaisses.size(); ca++) {
									cci = listCibles.get(ci);
									cca = listCaisses.get(ca);
									if(cci == cca) {
										listCibles.remove(ci);
										listCaissesRestantes.remove(ci);
										break;
									}//if
								}//for ca
							}//for ci
						}//if
						
					}//for j
					
				}//else if
				
			}//for i
			
			if (listCibles.isEmpty()) {
				sokoban.endGame(config);
				break;
			}
			else { 
				caisse.clear();
				caisse = this.dijkstra.getPath(graphe, listCaissesRestantes, listCibles);
			}
		
		}//while
		
	}//deplacementJoueur
	
	
	//tableau représentant le numéro de la case qui est en indice et les coordonnées correspondantes
	public void caseEnTableau() {
		for (int i=0, j=1, k=1; i<nbNoeuds; i++, j++) {
			tableau[i][0] = j; //coordonnée x de la case i
			tableau[i][1] = k; //coordonnée y de la case i
			
			if(j%xLVL == 0)
				j=0;
			
			if(j%yLVL == 0)
				k++;
		}
	}
	
	//tableau représentant les données du jeu avec les caisses (par défaut)
	public void jeuEnTableauAvecCaisses() {
		int cpt=0;
		this.caseEnTableau();
		
		//parcours de chaque case
		for (int i=0; i<graphe.length; i++) {
			int xi = tableau[i][0]; 
			int yi = tableau[i][1]; 
			Position pi = new Position(xi,yi); //position de la case i
			
			//on a seulement besoin de remplir qu'un triangle du tableau
			for (int j=cpt; j<graphe[i].length; j++){ 
				int xj = tableau[j][0]; 
				int yj = tableau[j][1]; 
				Position pj = new Position(xj,yj); //position de la case j
			
				if (i == j) {
					graphe[i][j] = 0;
				}
				else if ( !(lvl.get(pi) instanceof Mur) && !(lvl.get(pj) instanceof Mur) && (j==i+yLVL || (j%xLVL!=0 && j==i+1)) ) {
					graphe[i][j] = 1; //la distance vaut 1, car j est une case (non mur) adjacente à i
				}
				else {
					graphe[i][j] = -1; 
				}
			}
			
			cpt++;
			
			//remplissage de l'autre triangle symétriquement au premier 
			for (int j=0; j<cpt-1; j++) {
				graphe[i][j] = graphe[j][i]; 
			}
					
		}
	}
	
	//tableau représentant les données du jeu (sans les caisses)
	public void jeuEnTableauSansCaisses() {
		int cpt=0;
		this.caseEnTableau();
		
		//parcours de chaque case
		for (int i=0; i<graphe.length; i++) {
			int xi = tableau[i][0]; 
			int yi = tableau[i][1]; 
			Position pi = new Position(xi,yi); //position de la case i
			boolean bi = ( !(lvl.get(pi) instanceof Mur) && !(config.estCaisse(pi)) ); 
			
			//on a seulement besoin de remplir qu'un triangle du tableau
			for (int j=cpt; j<graphe[i].length; j++){ 
				int xj = tableau[j][0]; 
				int yj = tableau[j][1]; 
				Position pj = new Position(xj,yj); //position de la case j
				boolean bj = ( !(lvl.get(pj) instanceof Mur) && !(config.estCaisse(pj)) ); 
			
				if (i == j) {
					grapheSansCaisses[i][j] = 0;
				}
				else if ( bi && bj && (j==i+yLVL || (j%xLVL!=0 && j==i+1)) ) {
					grapheSansCaisses[i][j] = 1; //la distance vaut 1, car j est une case (non mur) adjacente à i
				}
				else {
					grapheSansCaisses[i][j] = -1; 
				}
			}
			
			cpt++;
			
			//remplissage de l'autre triangle symétriquement au premier 
			for (int j=0; j<cpt-1; j++) {
				grapheSansCaisses[i][j] = grapheSansCaisses[j][i]; 
			}
					
		}
	}
	
	//affiche le tableau contenant les positions des cases 
	public void printTableau() {
		this.caseEnTableau();
		
		System.out.println("Tableau représentant le numéro de la case qui est en indice et les coordonnées correspondantes : ");
		System.out.printf("%2s %2s %2s ", "n°", "x", "y");
		System.out.println();
		for (int i = 0 ; i < tableau.length; i++ ) {
			System.out.printf("%2d ", i);
			for (int j = 0; j< tableau[i].length; j++) {
				System.out.printf("%2d ",tableau[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//affiche le tableau représentant les données du jeu (avec les caisses)
	public void printGraphe() {
		this.jeuEnTableauAvecCaisses();
		
		System.out.println("Tableau représentant les données du jeu (avec les caisses) : ");
		System.out.printf("%2s ", "*");
		for (int i = 0 ; i < graphe.length; i++ ) {
			System.out.printf("%2d ", i);
		}
		System.out.println();
		for (int i = 0 ; i < graphe.length; i++ ) {
			System.out.printf("%2d ", i);
			for (int j = 0; j< graphe[i].length; j++) {
				System.out.printf("%2d ",graphe[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//affiche le tableau représentant les données du jeu (sans les caisses)
	public void printGrapheSansCaisses() {
		this.jeuEnTableauSansCaisses();
		
		System.out.println("Tableau représentant les données du jeu (sans les caisses) : ");
		System.out.printf("%2s ", "*");
		for (int i = 0 ; i < grapheSansCaisses.length; i++ ) {
			System.out.printf("%2d ", i);
		}
		System.out.println();
		for (int i = 0 ; i < grapheSansCaisses.length; i++ ) {
			System.out.printf("%2d ", i);
			for (int j = 0; j< grapheSansCaisses[i].length; j++) {
				System.out.printf("%2d ",grapheSansCaisses[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	//accesseurs :
	
	public List<Direction> getDirections() {
		return this.directions;
	}
	
	public int getxLVL() {
		return this.xLVL;
	}
	
	public int getyLVL() {
		return this.yLVL;
	}
	
	public int getNbNoeuds() {
		return this.nbNoeuds;
	}
	
	public int [][] getTableau() {
		return this.tableau;
	}
	
	public int [][] getGraphe() {
		return this.graphe;
	}
	public int [][] getGrapheSansCaisses() {
		return this.grapheSansCaisses;
	}
	
}

