package evolutionary;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Crossover {

	public void cross(Population p){
		
	}
	
	public List<Individual> orderCross(Individual a, Individual b) {
		int numChromosomes = a.genotype.size();
		
		Individual newA = new Individual(numChromosomes);
		Individual newB = new Individual(numChromosomes);
		
		// Generate random cut interval		
		Random rand = new Random(System.currentTimeMillis());
		int startCut = rand.nextInt(numChromosomes - 1);
		int endCut = rand.nextInt(numChromosomes - 1);
		if (startCut > endCut) {
			int tmp = startCut;
			startCut = endCut;
			endCut = tmp;
		}
		
		// Copy the cut sections to the new individuals
		for (int i = startCut; i < endCut; i++) {
			newA.genotype.set(i,b.genotype.get(i));
			newB.genotype.set(i,a.genotype.get(i));
		}
		
		// Copy the remaining chromosomes
		// TODO: contains in individual probably won't work, need to implement equals etc
		int chromosomesRemaining = numChromosomes - (endCut - startCut) - 1;
		int index = (endCut + 2 > numChromosomes) ? 0 : endCut + 1;
		while (chromosomesRemaining > 0) {
			Object chromosome = b.genotype.get(index);
			if (!newA.genotype.contains(chromosome)) {
				newA.genotype.set(index, chromosome);
				chromosomesRemaining--;
			}
			index = (index + 2 > numChromosomes) ? 0 : index + 1;
		}
		
		chromosomesRemaining = numChromosomes - (endCut - startCut) - 1;
		index = (endCut + 2 > numChromosomes) ? 0 : endCut + 1;
		while (chromosomesRemaining > 0) {
			Object chromosome = a.genotype.get(index);
			if (!newB.genotype.contains(chromosome)) {
				newB.genotype.set(index, chromosome);
				chromosomesRemaining--;
			}
			index = (index + 2 > numChromosomes) ? 0 : index + 1;
		}
		
		List<Individual> result = new LinkedList<Individual>();
		result.add(newA);
		result.add(newB);
		return result;
	}
}
