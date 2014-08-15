package test;

import java.util.ArrayList;
import java.util.List;

import evolutionary.Crossover;
import evolutionary.Individual;

public class TestCrossover {
	public static void main(String[] args) {
		
		Individual a = new Individual(4,3);
		Individual b = new Individual(5,4);		
		System.out.println("Before orderCrossover");
		System.out.println(a);
		System.out.println(b);
		Crossover cross = new Crossover();
		List<Individual> results = cross.orderCross(a, b);
		System.out.println("After orderCrossover");
		System.out.println(results.get(0));
		System.out.println(results.get(1));
	
		
	}
	
	
}
