package evolutionary;

import java.util.LinkedList;
import java.util.List;


public class EvolutionDriver {
	private int maxNumberOfGenerations;
	Population population;
	Mutation mutation;
	Crossover crossover;
	Selection selection;
	static List<Double> allAverages = new LinkedList<Double>();
	static List<Integer> allTimes = new LinkedList<Integer>();
	static double best = Double.MAX_VALUE;
	static Individual bestInd = null;
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
		double bestSolution = Double.MAX_VALUE;
		double worstSolution = Double.MIN_VALUE;
		Individual bestSolInd=null;
		int bestSolIndex=-1;
		int worstFinalIndex=-1;//might not use
		double lastSolution = 0;
		double numberOfRepeats = 0;
		long startTime = System.currentTimeMillis();

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("GEN #     ITER BEST (  POP BEST,    POP AVG,  POP WORST), TIME SINCE ITER START ( OVERALL TIME AVG, OVERALL TIME SUM)");
		while (numberOfGenerations <= maxNumberOfGenerations){

			Population offspring = population.clone();
			
			Config config = Config.getInstance();
			
			/*
			// This will drop the time to compilation
			// A little less accurate in calculating the best value
			// Can make it better by reducing the value of GEN_CHECK_MOD
			// Make this by adding this info into the config
			if (numberOfGenerations % GEN_CHECK_MOD == 0){
				//double curVal = config.calculateMeanPathlength(population);//SHOULD WE BE CALCULATING MEANN!!!!!!!! NEED TO LOOK AT EVERY INDIVIDUAL IN A POPULATION AND TAKE MAXIMUM AND MINIMUM AND AVG!!!
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
					//break;
				}
				lastSolution = bestSolution;
			}
			// System.out.println (String.format("%-10.3f", config.calculateMeanPathlength(population)) + "    (" + String.format("%.3f", bestSolution) + ")");
			*/
			
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
			
			/// calc data store best worst and avg
			//bestF,avgF,worstF,bestInd,worstInd
			Double[] data = population.getStats();

			if (data[0] < bestSolution){
				bestSolution = data[0];
				bestSolInd=population.population.get(data[3].intValue());
			}
			
			if(bestSolution<best){
				best=bestSolution;
				bestInd=population.population.get(data[3].intValue());
			}
			
			if (data[2] > worstSolution){
				worstSolution = data[2];
			}
			
			if (numberOfGenerations%100==0){
				int time = (int) ((System.currentTimeMillis() - startTime) / 1000);

				allTimes.add(time);
				
				int totalTime = 0;
				for (int times : allTimes){
					totalTime += times;
				}
				int avgTime = totalTime / allTimes.size();
				
				System.out.println("G: "+String.format("%5d",numberOfGenerations)+" "+String.format("%10.3f",bestSolution) + " ("+String.format("%10.2f",data[0])+", "+String.format("%10.2f",data[1])+", "+String.format("%10.2f",data[2])+"), "+time+" ("+String.format("%5d",avgTime)+", "+String.format("%5d",totalTime)+")");
			}
		}
		
		System.out.println("--------------------------------------------------------------------------");
		if (best!=Double.MAX_VALUE){
			System.out.println("---------------------- ITERATTION BEST: "+ String.format("%10.3f",bestSolution)+" -----------------------");
			System.out.println(bestSolInd);
		}
		
		System.out.println("--------------------------------------------------------------------------");
		if (best!=Double.MAX_VALUE){
			System.out.println("------------------------ OVERALL BEST: "+ String.format("%10.3f",best)+" ------------------------");
			System.out.println(bestInd);
		}
		System.out.println("--------------------------------------------------------------------------");
		System.out.println();
	}
}
