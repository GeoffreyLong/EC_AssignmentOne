// Perhaps this whole file should simply be removed and changed into the testing package then can call population.evolve from there

package evolutionary;

import java.util.Random;


public class EvolutionDriver {
	private int maxNumberOfGenerations;
	Population population;
	Mutation mutation;
	Crossover crossover;
	Selection selection;
	
	
	public EvolutionDriver(){
		Config config = Config.getInstance();
		
		this.maxNumberOfGenerations = config.getNumberOfGenerations();
		
		population = new Population(config.getPopulationSize(), Config.getInstance().getIndividualLength());
		mutation = new Mutation(config.getMutationType());
		crossover = new Crossover(config.getCrossoverType());
		selection = new Selection(config.getSelectionType());
	}
	
	public void evolve(){
		int numberOfGenerations = 0;
		double bestSolution = Double.MAX_VALUE;
		while (numberOfGenerations < maxNumberOfGenerations){
			Population offspring = null;
			numberOfGenerations++;
			
			Config config = Config.getInstance();
			
			double curVal = config.calculateMeanPathlength(population);
			if (curVal < bestSolution){
				bestSolution = curVal;
			}
			System.out.println (String.format("%-10.3f", curVal) + "    (" + String.format("%.3f", bestSolution) + ")");
			
			if (Math.random() < config.crossoverChance){
				offspring = crossover.cross(population);
			}
			
			if (offspring == null){
				offspring = population.clone();
			}
			
			if (Math.random() < config.mutationChance){
				mutation.mutate(offspring);
			}
			
			if (config.generationMix){
				population.population.addAll(offspring.population);
			}
			else{
				population.population = offspring.population;
			}
			
			population = selection.select(population);
		}
		for(Individual i : population.population){
			System.out.println(i.genotype);
			System.out.println(1/Config.getInstance().calculateFitness(i));
		}
	}
}
