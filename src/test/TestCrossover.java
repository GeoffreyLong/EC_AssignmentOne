package test;

import java.util.LinkedList;
import java.util.List;

import evolutionary.Crossover;
import evolutionary.Individual;

public class TestCrossover {
	public static void main(String[] args) {
		Individual a = new Individual();
		Individual b = new Individual();
		for (int i = 0; i < 5; i++) {
			a.genotype.add(Integer.toString(i));
			b.genotype.add(Integer.toString(4-i));
		}
		
		System.out.println("Before orderCrossover");
		System.out.println(a);
		System.out.println(b);
		Crossover cross = new Crossover();
		List<Individual> result = cross.orderCross(a, b);
		System.out.println()
	
		
	}
}
