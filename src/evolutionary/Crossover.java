package evolutionary;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Crossover {

	public void cross(Population p){
		
	}
	
	public List<Individual> orderCross(Individual a, Individual b) {
		int numChromosones = a.genotype.size();
		
		Individual newA = new Individual(numChromosones);
		Individual newB = new Individual(numChromosones);
		
		// Generate random cut interval		
		Random rand = new Random(System.currentTimeMillis());
		int startCut = rand.nextInt(numChromosones - 1);
		int endCut = rand.nextInt(numChromosones - 1);
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
		
		// Copy the remaining chromosones
		// TODO: contains in individual probably won't work, need to implement equals etc
		int chromosonesRemaining = numChromosones - (endCut - startCut) - 1;
		int index = (endCut + 2 > numChromosones) ? 0 : endCut + 1;
		while (chromosonesRemaining > 0) {
			Object chromosone = b.genotype.get(index);
			if (!newA.genotype.contains(chromosone)) {
				newA.genotype.set(index, chromosone);
				chromosonesRemaining--;
			}
			index = (index + 2 > numChromosones) ? 0 : index + 1;
		}
		
		chromosonesRemaining = numChromosones - (endCut - startCut) - 1;
		index = (endCut + 2 > numChromosones) ? 0 : endCut + 1;
		while (chromosonesRemaining > 0) {
			Object chromosone = a.genotype.get(index);
			if (!newB.genotype.contains(chromosone)) {
				newB.genotype.set(index, chromosone);
				chromosonesRemaining--;
			}
			index = (index + 2 > numChromosones) ? 0 : index + 1;
		}
		
		List<Individual> result = new LinkedList<Individual>();
		result.add(newA);
		result.add(newB);
		return result;
	}
}
