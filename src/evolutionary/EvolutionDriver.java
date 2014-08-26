// Perhaps this whole file should simply be removed and changed into the testing package then can call population.evolve from there

package evolutionary;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class EvolutionDriver {
	private int maxNumberOfGenerations;
	Population population;
	Mutation mutation;
	Crossover crossover;
	Selection selection;
	static List<Double> allScores = new LinkedList<Double>();
	static List<Integer> allGenerations = new LinkedList<Integer>();
	static List<Integer> allTimes = new LinkedList<Integer>();
	static double best = -1;
	private final int GEN_CHECK_MOD = 25;
	
	public EvolutionDriver(){
		Config config = Config.getInstance();
		
		this.maxNumberOfGenerations = config.getNumberOfGenerations();
		
		population = new Population(config.getPopulationSize(), Config.getInstance().getIndividualLength());
		mutation = new Mutation(config.getMutationTypeChance());
		crossover = new Crossover(config.getCrossoverTypeChance());
		selection = new Selection(config.getSelectionType());
	}
	
	public void evolve(){
		int numberOfGenerations = 0;
		int maxGenerations = Config.getInstance().getNumberOfGenerations();
		double bestSolution = Double.MAX_VALUE;
		double lastSolution = 0;
		double numberOfRepeats = 0;
		long startTime = System.currentTimeMillis();
		// System.out.println();
		// System.out.println(Config.getInstance().testingInstance);
		while (numberOfGenerations <= maxNumberOfGenerations){

			Population offspring = population.clone();
			
			Config config = Config.getInstance();
			
			
			// This will drop the time to compilation
			// A little less accurate in calculating the best value
			// Can make it better by reducing the value of GEN_CHECK_MOD
			// Make this by adding this info into the config
			if (numberOfGenerations % GEN_CHECK_MOD == 0){
				double curVal = config.calculateMeanPathlength(population);
				if (curVal < bestSolution){
					bestSolution = curVal;
				}
				if (bestSolution == lastSolution){
					numberOfRepeats ++;
				}
				else{
					numberOfRepeats = 0;
				}
				if (numberOfRepeats == 15){
					break;
				}
				lastSolution = bestSolution;
			}
			// System.out.println (String.format("%-10.3f", config.calculateMeanPathlength(population)) + "    (" + String.format("%.3f", bestSolution) + ")");
			
			offspring = crossover.cross(offspring);
			offspring = mutation.mutate(offspring);
			
			if (config.generationMix){
				population.population.addAll(offspring.population);
			}
			else{
				population.population = offspring.population;
			}
			population = selection.select(population);
			numberOfGenerations++;
		}

		if (numberOfGenerations != maxNumberOfGenerations + 1){
			numberOfGenerations -= 1000;
		}
		int time = (int) ((System.currentTimeMillis() - startTime) / 1000);

		//System.out.println(bestSolution + ", " + numberOfGenerations + ", " + (time));

		//System.out.println(String.format("%.3f", bestSolution) + ", " + numberOfGenerations + ", " + (time));

		allTimes.add(time);
		allScores.add(bestSolution);
		allGenerations.add(numberOfGenerations);
		if(best<0 || best>bestSolution)best=bestSolution;
		
		int totalTime = 0;
		for (int times : allTimes){
			totalTime += times;
		}
		int avgTime = totalTime / allTimes.size();
		
		double totalScore = 0;
		for (double scores : allScores){
			totalScore += scores;
		}
		double avgScore = totalScore / allScores.size();
		double stdDevScore = 0;
		for (double scores : allScores){
			stdDevScore += (avgScore-scores)*(avgScore-scores);
		}
		stdDevScore=Math.sqrt(stdDevScore);
		int totalGens = 0;
		for (int gens : allGenerations){
			totalGens += gens;
		}
		int avgGens = totalGens / allGenerations.size();
		
		System.out.println(String.format("%10.3f",bestSolution) + " ("+String.format("%10.2f",best)+", "+String.format("%10.2f",avgScore)+", "+String.format("%10.2f",stdDevScore)+"), "+String.format("%5d",numberOfGenerations)+" ("+String.format("%5d",avgGens)+"), "+time+" ("+String.format("%5d",avgTime)+", "+String.format("%5d",totalTime)+")");
	}
}
