package evolutionary;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import evolutionary.Mutation.MutationType;

// Make this immutable after instantiating each field
// Needs to be globally accessible else we will be passing it to every class
// Will be an object containing the functions for fitness,
// the params for all the algorithms (cross, mutate, select)
// And anything else we might need

// If we don't want to use a globally accessible object we will have to find
// Another way to instantiate the mutation, crossover

public class Config{
	public int individualLength = -1;
	public Map possibleAlleles;
	public MutationType mutationType;
	
	// Need to instantiate in TestConfigure
	public int populationSize = 100;
	public int numberOfGenerations;
	
	private static Config instance = null;
	public static Config getInstance(){
		if(instance == null){
			instance = new Config();
		}
		return instance;
	}
	
	private void Config(){}
	
	public void setNumberOfGenerations(int generations){
		numberOfGenerations = generations;
	}
	
	public int getNumberOfGenerations(){
		return numberOfGenerations;
	}
	
	public void setPopulationSize(int size){
		populationSize = size;
	}
	
	public int getPopulationSize(){
		return populationSize;
	}
	
	// Setter that insures field is only set once
	// Don't really need if we have the alleleMap
	public void setIndividualLength(int length) {
		individualLength = length;
	}

	public int getIndividualLength(){
		return individualLength;
	}
	
	public void setAlleleMap(Map alleleMap){
		possibleAlleles = alleleMap;
	}
	
	public Map getAlleleMap(){
		return possibleAlleles;
	}
	
	// Definitely test this to ensure it works
	public double calculateFitness(Individual individual){
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
	
	public void setMutationType(MutationType type){
		mutationType = type;
	}
	public MutationType getMutationType(){
		return mutationType;
	}
	/*
	public void setCrossoverType(CrossoverType type){
		crossoverType = type;
	}
	
	public void setSelectionType(SelectionType type){
		selectionType = type;
	}*/
}