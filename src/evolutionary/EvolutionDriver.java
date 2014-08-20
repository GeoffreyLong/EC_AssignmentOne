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
		
		population = new Population(populationSize, Config.getIndividualLength());
		mutation = new Mutation(Config.getMutationType());
	}
	
	public void evolve(){
		while (population.getGenerationNumber() < maxNumberOfGenerations){
			population.incrementGenerationNumber();
			mutation.mutate(population);
		}
	}
}
