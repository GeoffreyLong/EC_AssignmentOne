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
		
		population = new Population(populationSize);
	}
	
	public void evolve(){
		while (population.getGenerationNumber() < maxNumberOfGenerations){
			// TODO Could pass the crossover::cross and mutation::mutate in this population::evolve.  That might be good?!?!?!
			// Would have to pass the whole population to these methods though right? 
			// Else might be able to pass the population.pop and set the pop to the return from these methods
			// Or else I could pass the cross/mutate function as params into pop then simply apply them to the pop?
			
			// Would it be an option just to have the function objects read from the problem json?
			// This way the function objects can simply read out of the file on their instantiation which will set the objects up
			population.incrementGenerationNumber();
		}
	}
}
