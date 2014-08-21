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
			
			if (rand < config.mutationChance){
				mutation.mutate(population);
			}
			if (rand < config.crossoverChance){
				//crossover.cross(population);
			}
			

			selection.select(population);
		}
	}
	
	/*
	 * Running inver-over
	 * 
	 * FOR each ind in the population
	 * choose random ind and select a city c from this ind
	 * 
	 * LOOP
	 * if probability is less than p 
	 * 	choose random city c' from ind
	 * 
	 * 
	 * if probability greater than p
	 * 	choose a different ind (only for this if loop, new ind does not replace orig ind)
	 * 		find the city c in this new ind's path
	 * 		find the next city the new ind would travel to
	 * 		set c' to be this city
	 * 
	 * if the cities c and c' are not neighbors in the individual 
	 * 	then mutate inverse between the city after c in ind and c'
	 * 	and set c to be c'
	 * else
	 * 	exit loop
	 * END LOOP
	 * 
	 * if this new individual is better than, or equal to, the old one
	 * 	set the old one to be the new one
	 * 	
	 * END FOR
	 */
}
