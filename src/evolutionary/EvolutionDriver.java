// Perhaps this whole file should simply be removed and changed into the testing package then can call population.evolve from there

package evolutionary;


public class EvolutionDriver {
	private int maxNumberOfGenerations;
	Population population;
	Mutation mutation;
	Selection selection;
	
	
	public EvolutionDriver(){
		this.maxNumberOfGenerations = Config.getInstance().getNumberOfGenerations();
		
		population = new Population(Config.getInstance().getPopulationSize(), Config.getInstance().getIndividualLength());
		mutation = new Mutation(Config.getInstance().getMutationType());
		//selection = new Selection(Config.getInstance().getSelectionType());
	}
	
	public void evolve(){
		while (population.getGenerationNumber() < maxNumberOfGenerations){
			System.out.println(population.calculateMeanFitness());
			population.incrementGenerationNumber();
			mutation.mutate(population);
			//crossover.cross(population);
			//selection.elitism(population,5);
		}
	}
}
