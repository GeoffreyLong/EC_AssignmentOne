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
		
		while (numberOfGenerations < maxNumberOfGenerations){
			Population offspring = null;
			numberOfGenerations++;
			
			double rand = Math.random();
			Config config = Config.getInstance();
			
			System.out.println(config.calculateMeanPathlength(population));
			System.out.println(population.size());
			
			if ((1-rand) < config.crossoverChance){
				offspring = crossover.cross(population);
			}
			
			if (offspring == null){
				offspring = population.clone();
			}
			
			if (rand < config.mutationChance){
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
	}
}
