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
	private int maxID;
	public List<Object> genotype;
	
	public Individual(){
		// Uniform random linear time initialization
		genotype = new ArrayList<Object>();
	}
	public Individual(int size, int maxID) {
		this.maxID = maxID;
		genotype = new ArrayList<Object>(size);
		// randomly initialise
		// Assumes numChromosomes is significantly smaller than numID's
		Set<Integer> used = new HashSet<Integer>();
		int count = 0;
		while (count < size) {
			int i = rand.nextInt(maxID + 1);
			if (!used.contains(i)) {
				genotype.add(Integer.toString(i));
				used.add(i);
				count++;
			}
		}
		
		
	}
	
	public String toString() {
		return genotype.toString();
	}
}
