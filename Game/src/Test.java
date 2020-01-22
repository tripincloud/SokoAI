import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Niveau lvl = new Niveau(10, 10);
		Position positionjoueur = new Position(8, 4);
		Configuration c = new Configuration(lvl, positionjoueur);
		
		Position p1 = new Position(2, 2);
		Position p2 = new Position(2, 6);
		
		Position p3 = new Position(7, 3);
		Position p4 = new Position(6, 7);
		
		Position p5 = new Position(5, 4);
		Position p6 = new Position(5, 5);
		Position p7 = new Position(5, 3);

		Position p8 = new Position(4, 7);
		Position p9 = new Position(4, 8);
		Position p10 = new Position(4, 6);
		
		c.niveau.addCible(p1);
		c.niveau.addCible(p2);
		
		c.addCaisse(p3);
		c.addCaisse(p4);
		
		c.niveau.addMur(p5);
		c.niveau.addMur(p6);
		c.niveau.addMur(p7);
		c.niveau.addMur(p8);
		c.niveau.addMur(p9);
		//c.niveau.addMur(p10);
		
		ListeCoups listeCoups = new ListeCoups(c);
		
		//listeCoups.printTableau();
		//listeCoups.printGraphe();

		SokobanUI sokoban = new SokobanUI();
		Solver s = new Solver(c);
		listeCoups.infoJeu(sokoban, s);
		listeCoups.deplacementJoueur(sokoban, s);
		/*
		while (true) {
			
			sokoban.show(c);
			s.step();
			System.out.println(c);
			System.out.println(c.getJoueur().getHisto());
			System.out.println(s.getTotalSteps());
			
			listeCoups.infoJeu(sokoban, s);
			listeCoups.deplacementJoueur(sokoban, s);
			
			try {
				Thread.sleep(1000); // 1000 ms
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (s.stop()) {
				break;
			}
		}
		*/
		sokoban.endGame(c);
		
	}
}
