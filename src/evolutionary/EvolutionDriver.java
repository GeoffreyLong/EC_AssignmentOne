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
		mutation = new Mutation(config.getMutationTypeChance());
		crossover = new Crossover(config.getCrossoverTypeChance());
		selection = new Selection(config.getSelectionType());
	}
	
	public void evolve(){
		int numberOfGenerations = 0;
		double bestSolution = Double.MAX_VALUE;
		double lastSolution = 0;
		double numberOfRepeats = 0;
		long startTime = System.currentTimeMillis();
		System.out.println();
		System.out.println(Config.getInstance().testingInstance);
		while (numberOfGenerations <= 20000){
			Population offspring = population.clone();
			numberOfGenerations++;
			
			Config config = Config.getInstance();
			
			double curVal = config.calculateMeanPathlength(population);
			if (curVal < bestSolution){
				bestSolution = curVal;
			}
			if (bestSolution == lastSolution){
				numberOfRepeats ++;
			}
			else{
				numberOfRepeats = 0;
			}
			if (numberOfRepeats == 1000){
				break;
			}
			lastSolution = bestSolution;
			//System.out.println (String.format("%-10.3f", curVal) + "    (" + String.format("%.3f", bestSolution) + ")");
			
			offspring = crossover.cross(offspring);
			mutation.mutate(offspring);
			
			if (config.generationMix){
				population.population.addAll(offspring.population);
			}
			else{
				population.population = offspring.population;
			}
			population = selection.select(population);
		}
		if (numberOfGenerations != 20001){
			numberOfGenerations -= 1000;
		}
		System.out.println(bestSolution + ", " + numberOfGenerations + ", " + ((System.currentTimeMillis() - startTime) / 1000));
	}
}