package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Population;

public class TestDeepPopCopy {
	public static void main(String[] args) {
		
		// gen pop
		int popSize = 10;
		int indLength = 5;
		Population pop = new Population(popSize,indLength);
		System.out.println("Original population");
		System.out.println(pop);
		
		// deep copy pop
		List<Individual> newPopList = new ArrayList<Individual>();
		for (Individual i : pop.population) {
			newPopList.add(i.clone());
		}
		Population newPop = new Population(newPopList);
		System.out.println("Deep copy of pop before modification");
		System.out.println(newPop);
		
		// modify original pop
		/*
		for (Individual i : pop.population){
			for (Object chromosome : i.genotype) {
				chromosome = (Object)("hello");
			}
		}*/
		for (Individual i : pop.population) {
			for (int j = 0; j < i.genotype.size(); j++) {
				i.genotype.set(j, "hello");
			}
		}
		
		System.out.println("Original population modified");
		System.out.println(pop);
		
		// check if copy has not changed
		System.out.println("Deep copy after modification");
		System.out.println(newPop);
		
		
	}
}
