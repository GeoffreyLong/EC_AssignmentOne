package test;

import java.util.ArrayList;
import java.util.List;

import evolutionary.Crossover;
import evolutionary.Individual;

public class TestCrossover {
	public static void main(String[] args) {
		System.out.println("wut ");
		Individual a = new Individual(5,5);
		Individual b = new Individual(5,5);
		System.out.println("m8");
		//runOrderCross(a, b);
		//runPmxCross(a, b);
		//runCycleCross(a,b);
		runEdgeRecombination(a,b);
	
		
	}
	
	private static void runOrderCross(Individual a, Individual b) {			
		System.out.println("Before orderCrossover");
		System.out.println(a);
		System.out.println(b);
		Crossover cross = new Crossover();
		Individual offspringA = cross.orderCross(a, b);
		System.out.println("After orderCrossover a b");
		System.out.println(offspringA);
		Individual offspringB = cross.orderCross(b, a);
		System.out.println("After orderCrossover b a");		
		System.out.println(offspringB);		
	}
	
	private static void runPmxCross(Individual a, Individual b) {
		System.out.println("Before pmxCrossover");
		System.out.println(a);
		System.out.println(b);
		Crossover cross = new Crossover();
		Individual offspringA = cross.pmxCross(a, b);
		System.out.println("After pmxCrossover a b");
		System.out.println(offspringA);
		Individual offspringB = cross.pmxCross(b, a);
		System.out.println("After orderCrossover b a");		
		System.out.println(offspringB);		
	}
	private static void runCycleCross(Individual a, Individual b) {
		System.out.println("Before cycleCrossover");
		System.out.println(a);
		System.out.println(b);
		Crossover cross = new Crossover();
		Individual offspringA = cross.cycleCross(a, b);
		System.out.println("After cycleCrossover a b");
		System.out.println(offspringA);
		Individual offspringB = cross.cycleCross(b, a);
		System.out.println("After orderCrossover b a");		
		System.out.println(offspringB);		
	}
	
	private static void runEdgeRecombination(Individual a, Individual b) {
		System.out.println("Before edgeRecombination");
		System.out.println(a);
		System.out.println(b);
		Crossover cross = new Crossover();
		Individual offspringA = cross.edgeRecombination(a, b);
		System.out.println("After edgeRecombination a b");
		System.out.println(offspringA);
		Individual offspringB = cross.edgeRecombination(b, a);
		System.out.println("After edgeRecombination b a");		
		System.out.println(offspringB);		
	}
	
}
