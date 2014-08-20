package evolutionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Individual {
	// Perhaps class object of fitness?
	// Class method calculate fitness?
	
	// Make a generic linked list to hold the allele so that it can take many different types of arguments
	private static final Random rand = new Random(System.currentTimeMillis());
	public List<Object> genotype;
	
	public Individual(){
		// Uniform random linear time initialization
		genotype = new ArrayList<Object>();
	}
	public Individual(int size, int maxID) {
		if (!(maxID + 1 >= size)) {
			throw new IllegalArgumentException("maxID + 1 must be >= size");
		}
		genotype = new ArrayList<Object>(size);
		// randomly initialize
		// Assumes numChromosomes is significantly smaller than numID's
		// Otherwise inefficient
		Set<Integer> used = new HashSet<Integer>();
		int count = 0;
		while (count < size) {
			int i = rand.nextInt(maxID) + 1;
			if (!used.contains(i)) {
				genotype.add(Integer.toString(i));
				used.add(i);
				count++;
			}
		}
	}
	public Individual(List<Object> genotype) {
		this.genotype = genotype;
	}
	
	public String toString() {
		return genotype.toString();
	}
}
