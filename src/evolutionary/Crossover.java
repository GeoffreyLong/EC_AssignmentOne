package evolutionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Crossover {

	private int maxID = 5; //TODO
	public void cross(Population p){
		
	}
	
	public List<Individual> orderCross(Individual a, Individual b) {
		int numChromosomes = a.genotype.size();
		System.out.println(numChromosomes);
		
		List<Object> newAGenotype = new ArrayList<Object>(Collections.nCopies(numChromosomes, -1));
		List<Object> newBGenotype = new ArrayList<Object>(Collections.nCopies(numChromosomes, -1));
		
		// Generate random cut interval		
		Random rand = new Random(System.currentTimeMillis());
		int startCut = rand.nextInt(numChromosomes - 1);
		int endCut = rand.nextInt(numChromosomes - 1);
		if (startCut > endCut) {
			int tmp = startCut;
			startCut = endCut;
			endCut = tmp;
		}
		
		System.out.printf("startCut:%d, endCut:%d\n",startCut,endCut);
		System.out.println("before copy");
		System.out.println(newAGenotype);
		System.out.println(newBGenotype);
		// Copy the cut sections to the new individuals
		for (int i = startCut; i < endCut + 1; i++) {
			newAGenotype.set(i,a.genotype.get(i));
			newBGenotype.set(i,b.genotype.get(i));
		}
		System.out.println("after copy");
		System.out.println(newAGenotype);
		System.out.println(newBGenotype);
		// Copy the remaining chromosomes
		// TODO: contains in individual probably won't work, need to implement equals etc
		int chromosomesRemaining = numChromosomes - (endCut - startCut) - 1;
		int setIndex = (endCut + 2 > numChromosomes) ? 0 : endCut + 1;
		int getIndex = setIndex;
		while (chromosomesRemaining > 0) {
			Object chromosome = b.genotype.get(getIndex);
			if (!newAGenotype.contains(chromosome)) {
				newAGenotype.set(setIndex, chromosome);
				setIndex = (setIndex + 2 > numChromosomes) ? 0 : setIndex + 1;
				chromosomesRemaining--;
			}
			getIndex = (getIndex + 2 > numChromosomes) ? 0 : getIndex + 1;
		}
		
		chromosomesRemaining = numChromosomes - (endCut - startCut) - 1;
		setIndex = (endCut + 2 > numChromosomes) ? 0 : endCut + 1;
		getIndex = setIndex;
		while (chromosomesRemaining > 0) {
			Object chromosome = a.genotype.get(getIndex);
			if (!newBGenotype.contains(chromosome)) {
				newBGenotype.set(setIndex, chromosome);
				setIndex = (setIndex + 2 > numChromosomes) ? 0 : setIndex + 1;
				chromosomesRemaining--;
			}
			getIndex = (getIndex + 2 > numChromosomes) ? 0 : getIndex + 1;
		}
		
		List<Individual> result = new LinkedList<Individual>();
		result.add(new Individual(newAGenotype));
		result.add(new Individual(newBGenotype));
		return result;
	}
}
