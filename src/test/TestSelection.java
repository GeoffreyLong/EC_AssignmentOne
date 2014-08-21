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
		
		for(int i=0; i<p.population.length; i++){
			System.out.println(p.population[i]+" .. F: "+g.calculateFitness(p.population[i]));
		}
		
		System.out.println("ROULETTE");
		Population n = roulette.select(p, 2, 3);
		for(int i=0; i<n.population.length; i++){
			System.out.println(n.population[i]+" .. F: "+g.calculateFitness(n.population[i]));
		}
		
		System.out.println("SUS");
		n = sus.select(p, 4, 3);

		for(int i=0; i<n.population.length; i++){
			System.out.println(n.population[i]+" .. F: "+g.calculateFitness(n.population[i]));
		}
		
		System.out.println("TOURNAMENT");
		n = tournament.select(p, 5, 3);

		for(int i=0; i<n.population.length; i++){
			System.out.println(n.population[i]+" .. F: "+g.calculateFitness(n.population[i]));
		}
		
		System.out.println("ELITISM");
		n = elitism.select(p, 2, 3);

		for(int i=0; i<n.population.length; i++){
			System.out.println(n.population[i]+" .. F: "+g.calculateFitness(n.population[i]));
		}
		
	}
}
