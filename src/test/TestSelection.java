package test;

import evolutionary.Population;
import evolutionary.Config;

public class TestSelection {
	public static void main(String[] args) {
		Population p = new Population(5,10);
		Config g = new Config();
		
		System.out.println("ORIGINAL POP");
		
		for(int i=0; i<p.population.length; i++){
			System.out.println(p.population[i]+" .. F: "+g.calculateFitness(p.population[i]));
		}
			
		
	}
}
