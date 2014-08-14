package evolutionary;

import java.util.LinkedList;
import java.util.List;

public class Individual {
	// Perhaps class object of fitness?
	// Class method calculate fitness?
	
	// Make a generic linked list to hold the allele so that it can take many different types of arguments
	List<Object> genotype = new LinkedList<Object>();
	
	public Individual(){
		// Uniform random linear time initialization
	}
	public Individual(int size) {
	}
}
