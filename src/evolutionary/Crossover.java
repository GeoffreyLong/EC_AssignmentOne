package evolutionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class Crossover {

	private static final Random rand = new Random(System.currentTimeMillis());
	public void cross(Population p){
		
	}
	
	public Individual orderCross(Individual a, Individual b) {
		int numChromosomes = a.genotype.size();
		System.out.println(numChromosomes);
		
		List<Object> offspringGenotype = new ArrayList<Object>(Collections.nCopies(numChromosomes, -1));
		
		// Generate random cut interval
		int startCut = rand.nextInt(numChromosomes);
		int endCut = rand.nextInt(numChromosomes);
		if (startCut > endCut) {
			int tmp = startCut;
			startCut = endCut;
			endCut = tmp;
		}
		
		System.out.printf("startCut:%d, endCut:%d\n",startCut,endCut);
		System.out.println("before copy");
		System.out.println(offspringGenotype);
		// Copy the cut sections to the new individuals
		for (int i = startCut; i < endCut + 1; i++) {
			offspringGenotype.set(i,a.genotype.get(i));
		}
		System.out.println("after copy");
		System.out.println(offspringGenotype);
		// Copy the remaining chromosomes
		// TODO: contains in individual probably won't work, need to implement equals etc
		int chromosomesRemaining = numChromosomes - (endCut - startCut) - 1;
		int setIndex = (endCut + 2 > numChromosomes) ? 0 : endCut + 1;
		int getIndex = setIndex;
		while (chromosomesRemaining > 0) {
			Object chromosome = b.genotype.get(getIndex);
			if (!offspringGenotype.contains(chromosome)) {
				offspringGenotype.set(setIndex, chromosome);
				setIndex = (setIndex + 2 > numChromosomes) ? 0 : setIndex + 1;
				chromosomesRemaining--;
			}
			getIndex = (getIndex + 2 > numChromosomes) ? 0 : getIndex + 1;
		}
		
		return new Individual(offspringGenotype);
	}
	
	public Individual pmxCross(Individual a, Individual b) {
		int numChromosomes = a.genotype.size();
		
		List<Object> offspringGenotype = new ArrayList<Object>(Collections.nCopies(numChromosomes, -1));
		
		// Generate random cut interval
		int startCut = rand.nextInt(numChromosomes);
		int endCut = rand.nextInt(numChromosomes);
		if (startCut > endCut) {
			int tmp = startCut;
			startCut = endCut;
			endCut = tmp;
		}
		
		System.out.printf("startCut:%d, endCut:%d\n",startCut,endCut);
		System.out.println("before copy");
		System.out.println(offspringGenotype);
		// Copy the cut sections to the new individuals
		for (int i = startCut; i < endCut + 1; i++) {
			offspringGenotype.set(i,a.genotype.get(i));
		}
		System.out.println("after copy");
		System.out.println(offspringGenotype);
		
		// Construct element index lookup table
		Map<Object, Integer> bLookup = new HashMap<Object, Integer>();
		{int index = 0;
		for (Object chromosome : b.genotype) {
			bLookup.put(chromosome, index);
			index++;
		}}
		
		for (int i = startCut; i <= endCut; i++) {
			Object el1 = b.genotype.get(i);
			if (!offspringGenotype.contains(el1)) {
				boolean placeFound = false;
				int index = i;
				Object el2 = offspringGenotype.get(index);
				while (!placeFound) {					
					// find index of el2 in b
					index = bLookup.get(el2);
					el2 = offspringGenotype.get(index);
					placeFound = (el2 instanceof Integer) ? ((int)el2 == -1) : false;
				}
				offspringGenotype.set(index,el1);
			}
		}
		
		System.out.println("after pmx");
		System.out.println(offspringGenotype);
		
		for (int i = 0; i < offspringGenotype.size(); i++) {
			Object el = offspringGenotype.get(i);
			boolean placeFilled = (el instanceof Integer) ? ((int)el != -1) : true;
			System.out.printf("index: %d, placeFilled:%s, integer:%s\n", i, placeFilled, el instanceof Integer);
			if (!placeFilled) {
				offspringGenotype.set(i, b.genotype.get(i));
			}
		}
		
		System.out.println("after direct mapping");
		System.out.println(offspringGenotype);
		return new Individual(offspringGenotype);
		
	}
	
	public Individual cycleCross(Individual a, Individual b) {
		// Construct element index lookup table
		Map<Object, Integer> aLookup = new HashMap<Object, Integer>();
		{int index = 0;
		for (Object chromosome : a.genotype) {
			aLookup.put(chromosome, index);
			index++;
		}}
		
		// Identify cycles
		List<List<Integer>> cycleList = new ArrayList<List<Integer>>();
		Set<Integer> indexDone = new HashSet<Integer>();
	
		int startIndex = 0;
		while (indexDone.size() < a.genotype.size()) { // Loop until all index's are in a cycle
			if (indexDone.contains(startIndex)) { // find next index
				startIndex++;
				continue;
			}
			System.out.println(startIndex);
			// loop init
			List<Integer> cycle = new ArrayList<Integer>();
			Object cycleStart = a.genotype.get(startIndex);
			int index = startIndex;
					
			do { // follow a cycle, store indexs
				cycle.add(index);
				indexDone.add(index);
				index = aLookup.get(b.genotype.get(index));
				
			} while (!a.genotype.get(index).equals(cycleStart));
			cycleList.add(cycle);
			
			startIndex++;
		}
		
		// Debug cycle indexs
		System.out.println("#####################");
		System.out.println("AGenotype: " + a.genotype);
		System.out.println("BGenotype: " + b.genotype);
		System.out.println("Generated Cycles");
		for (List<Integer> cycle : cycleList) {
			System.out.println(cycle);
		}
		System.out.println("#####################");
		
		
		// Add cycle elements to offspring. Odd cycles add odd elements, even cycles add even elements.
		int numChromosomes = a.genotype.size();
		List<Object> offspringGenotype = new ArrayList<Object>(Collections.nCopies(numChromosomes, -1));
		boolean swap = true;
		for (List<Integer> cycle : cycleList) {
			for (int idx : cycle) {
				Object el = (swap) ? a.genotype.get(idx) : b.genotype.get(idx);
				offspringGenotype.set(idx, el);
			}
			swap = !swap;
		}
		
		
		
		return new Individual(offspringGenotype);
	}
	
	public Individual edgeRecombination(Individual a, Individual b) {
		
		
		return null;
	}
}

