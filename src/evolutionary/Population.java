package evolutionary;

public class Population {
	// Could alternatively use a link list
	Individual[] population;
	private int generationNumber;
	
	public Population(){
		// Make a bunch of Individuals
		// Need array of individuals that is the size of the pop size
	}
	
	// OverLoad in case want a different setup?
	public Population(int popSize){
		population = new Individual[popSize];
		for (int i = 0; i < popSize; i++){
			population[i] = new Individual();
		}
	}

	public Population(int populationSize, Object object) {
		// TODO Auto-generated constructor stub
	}
	
	// Perhaps should put evolve in EvolutionDriver?
	public void evolve(){
		// TODO how to make the mutation and crossover as modular as possible
		// on each evolve increment the generation number, mutate, crossover, and select new generation
		generationNumber += 1;
	}
	
	public int getGenerationNumber(){
		return generationNumber;
	}
	
	public void incrementGenerationNumber(){
		generationNumber += 1;
	}
}
