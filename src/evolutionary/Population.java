package evolutionary;

import java.util.ArrayList;
import java.util.List;

public class Population {
	// TODO change to a list
	public List<Individual> population;
	
	public Population(){
		population = new ArrayList<Individual>();
		// Make a bunch of Individuals
		// Need array of individuals that is the size of the pop size
	}
	
	// OverLoad in case want a different setup? used in selection
	public Population(List<Individual> individuals){
		population = individuals;
	}
	
	public Population(Individual[] individualArray){
		population = new ArrayList<Individual>();
		for (Individual i : individualArray){
			population.add(i);
		}
	}
	
	// OverLoad in case want a different setup?
	public Population(int popSize){
		// Should use a list type
		population = new ArrayList<Individual>();
		for (int i = 0; i < popSize; i++){
			population.add(new Individual());
		}
	}
	
	// OverLoad in case want a different setup
	public Population(int popSize, int indLength){
		population = new ArrayList<Individual>();
		for (int i = 0; i < popSize; i++){
			population.add(new Individual(indLength,indLength));
		}
	}

	public Population(int populationSize, Object object) {
		// TODO Auto-generated constructor stub
	}
	
	public int size(){
		return population.size();
	}
	
	public String toString(){
		String temp = "";
		for (int i = 0; i < population.size(); i++){
			temp += population.get(i).toString();
			temp += System.getProperty("line.separator");
		}
		return temp;
	}
	
	public double calculateMeanFitness(){
		return calculateTotalFitness() / population.size();
	}
	
	public double calculateTotalFitness(){
		double totalFitness = 0;
		for (Individual i : population){
			double indFitness = Config.getInstance().calculateFitness(i);
			// Tempory check to see if the optimal value is ever hit for any ind, remember to remove this...
			if (Math.abs(426 - indFitness) <= 2){
				System.out.println("Success, got within 2 of 426 for an individual");
				// System.exit(0);
			}
			totalFitness += indFitness;
		}
		return totalFitness;
	}
	
	@Override
	public Population clone() {
		List<Individual> newPopList = new ArrayList<Individual>();
		for (Individual i : this.population) {
			newPopList.add(i.clone());
		}
		return new Population(newPopList);
	}
}
