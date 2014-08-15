package evolutionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


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
				while (!placeFound) {
					Object el2 = offspringGenotype.get(index);
					// find index of el2 in b
					index = bLookup.get(el2);
					placeFound = ((int)offspringGenotype.get(index) == -1);					
				}
				offspringGenotype.set(index,el1);
			}
		}
		
		for (int i = 0; i < offspringGenotype.size(); i++) {
			if ((int)offspringGenotype.get(i) == -1) {
				offspringGenotype.set(i, b.genotype.get(i));
			}
		}
		return new Individual(offspringGenotype);
		
	}
}

