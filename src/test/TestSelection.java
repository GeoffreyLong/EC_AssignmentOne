package test;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import evolutionary.Selection;
import evolutionary.Population;
import evolutionary.Config;
import evolutionary.Selection;
import evolutionary.Selection.SelectionType;

public class TestSelection {
	public static void main(String[] args) {
		Population p = new Population(5,10);
		Config g = new Config();
		g=g.getInstance();
		
		Selection roulette = new Selection(SelectionType.ROULETTE);
		Selection sus = new Selection(SelectionType.SUS);
		Selection tournament = new Selection(SelectionType.TOURNAMENT);
		Selection elitism = new Selection(SelectionType.ELITISM);
		
		Map<String, Point> mapp = new HashMap<String, Point>();
		
		for(int i=1; i<=10; i++){
			mapp.put(Integer.toString(i), new Point(0,i));
		}
		
		System.out.println("ORIGINAL POP");
		g.setAlleleMap(mapp);
		
		for(int i=0; i<p.population.size(); i++){
			System.out.println(p.population.get(i)+" .. F: "+g.calculateFitness(p.population.get(i)));
		}
		
		System.out.println("ROULETTE");
		Config.getInstance().setPopulationSize(2);
		Config.getInstance().setTournamentSize(3);
		Population n = roulette.select(p);
		for(int i=0; i<n.population.size(); i++){
			System.out.println(n.population.get(i)+" .. F: "+g.calculateFitness(n.population.get(i)));
		}
		
		System.out.println("SUS");
		Config.getInstance().setPopulationSize(4);
		Config.getInstance().setTournamentSize(3);
		n = sus.select(p);

		for(int i=0; i<n.population.size(); i++){
			System.out.println(n.population.get(i)+" .. F: "+g.calculateFitness(n.population.get(i)));
		}
		
		System.out.println("TOURNAMENT");
		Config.getInstance().setPopulationSize(5);
		Config.getInstance().setTournamentSize(3);
		n = tournament.select(p);

		for(int i=0; i<n.population.size(); i++){
			System.out.println(n.population.get(i)+" .. F: "+g.calculateFitness(n.population.get(i)));
		}
		
		System.out.println("ELITISM");
		Config.getInstance().setPopulationSize(2);
		Config.getInstance().setTournamentSize(3);
		n = elitism.select(p);

		for(int i=0; i<n.population.size(); i++){
			System.out.println(n.population.get(i)+" .. F: "+g.calculateFitness(n.population.get(i)));
		}
		
	}
}
