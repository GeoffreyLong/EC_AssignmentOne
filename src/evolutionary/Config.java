package evolutionary;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import evolutionary.Crossover.CrossoverType;
import evolutionary.Mutation.MutationType;
import evolutionary.Selection.SelectionType;

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
	public SelectionType selectionType;
	public SelectionType parentSelectionType;
	
	// Need to instantiate in TestConfigure
	public int populationSize = 100;
	public int numberOfGenerations = 10000;
	public double crossoverChance = 1;
	public double crossingChance = 1;
	public double mutationChance = 0.02;
	public int tournamentSize = 5;
	public double inverOverProbability = 0.02;
	public boolean generationMix = true;
	
	public double[] mutationTypeChance = new double[5];
	public double[] crossoverTypeChance = new double[4];
	
	public void setMutationTypeChance(double insert, double swap, double invert, double scramble, double inverOver){
		double probability = 0;
		
		mutationTypeChance[0] = (probability+=insert);
		mutationTypeChance[1] = (probability+=swap);
		mutationTypeChance[2] = (probability+=invert);
		mutationTypeChance[3] = (probability+=scramble);
		mutationTypeChance[4] = (probability+=inverOver);
		if (Math.abs(1 - probability) >= 0.00001){
			throw new IllegalArgumentException("Mutation Chances arguments must add up to one");
		}
	}
	
	public double[] getMutationTypeChance(){
		return this.mutationTypeChance;
	}
	
	public void setCrossoverTypeChance(double order, double pmx, double cycle, double edge){
		double probability = 0;
		
		crossoverTypeChance[0] = (probability+=order);
		crossoverTypeChance[1] = (probability+=pmx);
		crossoverTypeChance[2] = (probability+=cycle);
		crossoverTypeChance[3] = (probability+=edge);
		if (Math.abs(1 - probability) >= 0.00001){
			throw new IllegalArgumentException("Crossover Chances arguments must add up to one");
		}
	}
	
	public double[] getCrossoverTypeChance(){
		return this.crossoverTypeChance;
	}
	
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
			lastPoint = currentPoint;
		}
		
		return 1/fitness;
	}
	
	public double calculateMeanPathlength(Population population){
		return 1/population.calculateMeanFitness();
	}
	public void setGenerationMix(boolean mixGenerations){
		this.generationMix = mixGenerations;
	}
	public void setTournamentSize(int tournamentSize){
		this.tournamentSize = tournamentSize;
	}
	
	public void setSelectionType(SelectionType type){
		selectionType = type;
	}
	public SelectionType getSelectionType(){
		return this.selectionType;
	}
	public void setMutationChance(double chance){
		mutationChance = chance;
	}
	public void setCrossoverChance(double chance){
		crossoverChance = chance;
	}
	public void setInverOverProbability(double chance){
		this.inverOverProbability = chance;
	}
	public void setParentSelectionType(SelectionType parentSelectionType){
		this.parentSelectionType = parentSelectionType;
	}
	public SelectionType getParentSelectionType(){
		return this.parentSelectionType;
	}
}
