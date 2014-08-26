package evolutionary;

import java.util.ArrayList;
import java.util.List;

public class Population {
	public List<Individual> population;
	
	public Population(){
		population = new ArrayList<Individual>();
	}
	
	public Population(List<Individual> individuals){
		population = individuals;
	}
	
	public Population(Individual[] individualArray){
		population = new ArrayList<Individual>();
		for (Individual i : individualArray){
			population.add(i);
		}
	}
	
	public Population(int popSize){
		population = new ArrayList<Individual>();
		for (int i = 0; i < popSize; i++){
			population.add(new Individual());
		}
	}
	
	public Population(int popSize, int indLength){
		population = new ArrayList<Individual>();
		for (int i = 0; i < popSize; i++){
			population.add(new Individual(indLength,indLength));
		}
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
