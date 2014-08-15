package evolutionary;

import java.awt.Point;
import java.util.List;
import java.util.Map;

// Make this immutable after instantiating each field
// Needs to be globally accessible else we will be passing it to every class
// Will be an object containing the functions for fitness,
// the params for all the algorithms (cross, mutate, select)
// And anything else we might need

// If we don't want to use a globally accessible object we will have to find
// Another way to instantiate the mutation, crossover

public class Config {
	private static int numberOfNodes = -1;
	private static Map possibleAlleles;
	
	// Setter that insures field is only set once
	// Don't really need if we have the alleleMap
	public void setNumberOfNodes(int numberOfNodes)  {
        this.numberOfNodes = this.numberOfNodes == -1 ? numberOfNodes : throw_();
    }

	public static void setAlleleMap(Map alleleMap){
		possibleAlleles = alleleMap;
	}
	
	public static Map getAlleleMap(){
		return possibleAlleles;
	}
	
	// Definitely test this to ensure it works
	public static double calculateFitness(Individual individual){
		double fitness = 0;
		List<Object> chromosomes = individual.genotype;
		
		String lastChromosome = (String) chromosomes.get(chromosomes.size() - 1);
		Point lastPoint = (Point) possibleAlleles.get(lastChromosome);
		Point currentPoint;
		
		for (Object chromosome : individual.genotype){
			chromosome = (String) chromosome;
			currentPoint = (Point) possibleAlleles.get(chromosome);
			fitness += currentPoint.distance(lastPoint);
		}
		
		return fitness;
	}
	
	// Throw error if already set
    public int throw_() {
        throw new RuntimeException("field is already set");
    }
}
