package test;

import java.util.ArrayList;
import java.util.List;

import evolutionary.Crossover;
import evolutionary.Individual;

public class TestCrossover {
	public static void main(String[] args) {
		
		Individual a = new Individual(5,4);
		Individual b = new Individual(5,4);		
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
	
	
}
