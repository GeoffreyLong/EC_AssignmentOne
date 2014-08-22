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
		//crossover = new Crossover(config.getCrossoverType());
		selection = new Selection(config.getSelectionType());
	}
	
	public void evolve(){
		while (population.getGenerationNumber() < maxNumberOfGenerations){
			System.out.println(population.calculateMeanFitness());
			population.incrementGenerationNumber();
			
			double rand = Math.random();
			Config config = Config.getInstance();
			
			if ((1-rand) < config.crossoverChance){
				//crossover.cross(population);
			}
			
			if (rand < config.mutationChance){
				mutation.mutate(population);
			}
			
			selection.select(population);
		}
	}
}
