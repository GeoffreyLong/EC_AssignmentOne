// Perhaps this whole file should simply be removed and changed into the testing package then can call population.evolve from there

package evolutionary;


public class EvolutionDriver {
	private int maxNumberOfGenerations;
	private int populationSize;
	Population population;
	Mutation mutation;
	
	
	public EvolutionDriver(int maxNumberOfGenerations, int populationSize){
		this.maxNumberOfGenerations = maxNumberOfGenerations;
		this.populationSize = populationSize;
		
		population = new Population(populationSize, Config.getInstance().getIndividualLength());
		mutation = new Mutation(Config.getInstance().getMutationType());
	}
	
	public void evolve(){
		while (population.getGenerationNumber() < maxNumberOfGenerations){
			population.incrementGenerationNumber();
			mutation.mutate(population);
			//crossover.cross(population);
			//selection.select(population);
		}
	}
}
