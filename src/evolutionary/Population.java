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
	
	// OverLoad in case want a different setup
	public Population(int popSize, int indLength){
		population = new Individual[popSize];
		for (int i = 0; i < popSize; i++){
			population[i] = new Individual(indLength,indLength);
		}
	}

	public Population(int populationSize, Object object) {
		// TODO Auto-generated constructor stub
	}
	
	public int getGenerationNumber(){
		return generationNumber;
	}
	
	public void incrementGenerationNumber(){
		generationNumber += 1;
	}
	
	public int size(){
		return population.length;
	}
	
	public String toString(){
		String temp = "";
		for (int i = 0; i < population.length; i++){
			temp += population[i].toString();
			temp += System.getProperty("line.separator");
		}
		return temp;
	}
}
