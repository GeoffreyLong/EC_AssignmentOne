package test;

import java.util.List;

import evolutionary.Crossover;
import evolutionary.Individual;

public class TestCrossover {
	public static void main(String[] args) {		
		Individual a = new Individual(5,5);
		Individual b = new Individual(5,5);		
		System.out.println("Before orderCrossover");
		System.out.println(a);
		System.out.println(b);
		Crossover cross = new Crossover();
		List<Individual> result = cross.orderCross(a, b);
		System.out.println("After orderCrossover");
		System.out.println(a);
		System.out.println(b);
	
		
	}
	
	
}
