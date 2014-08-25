// Perhaps this whole file should simply be removed and changed into the testing package then can call population.evolve from there

package evolutionary;

import java.util.Random;

import evolutionary.Crossover.CrossoverType;
import evolutionary.Mutation.MutationType;
import evolutionary.Selection.SelectionType;


public class EvolutionDriver {
	private int maxNumberOfGenerations;
	Population population;
	Mutation mutation;
	Crossover crossover;
	Selection selection;
	
	
	public EvolutionDriver(){
		Config config = Config.getInstance();
		
		this.maxNumberOfGenerations = config.getNumberOfGenerations();
		
		population = new Population(config.getPopulationSize(), Config.getInstance().getIndividualLength());
		mutation = new Mutation(config.getMutationType());
		crossover = new Crossover(config.getCrossoverType());
		selection = new Selection(config.getSelectionType());
	}
	
	public void masterTest(){
		SelectionType[] selectionTypes = {SelectionType.TOURNAMENT, SelectionType.ELITISM};
		
		Config config = Config.getInstance();
		for (MutationType mutationType : MutationType.values()){
			for (CrossoverType crossType : CrossoverType.values()){
				for (SelectionType selectionType : selectionTypes){
					for (SelectionType parentSelectionType : SelectionType.values()){
						if (crossType != CrossoverType.EDGE){
							config.setMutationType(mutationType);
							config.setSelectionType(selectionType);
							config.setCrossoverType(crossType);
							mutation = new Mutation(config.getMutationType());
							crossover = new Crossover(config.getCrossoverType());
							selection = new Selection(config.getSelectionType());
							config.setParentSelectionType(parentSelectionType);
							for (int i = 1; i < 7; i++){
								double crossChance = Math.random();
								double mutationChance = Math.random();
								
								switch(i){
								case 1:
									crossChance = 0;
									mutationChance = 1;
									break;
								case 2:
									crossChance = 1;
									mutationChance = 0;
									break;
								case 3:
									crossChance = 0.3;
									mutationChance = 0.7;
									break;
								case 4:
									crossChance = 0.7;
									mutationChance = 0.3;
									break;
								case 5:
									crossChance = 1;
									mutationChance = 1;
									break;
								case 6:
									break;
								}
								
								if (mutationType == MutationType.INVEROVER && i < 4) {
									crossChance = 0;
									config.setGenerationMix(false);
								}
								else{
									config.setGenerationMix(true);
								}
								config.setTournamentSize(i*2);
								config.setCrossoverChance(crossChance);
								config.setMutationChance(mutationChance);
								System.out.println(mutationType + ", " + crossType + ", " + selectionType + ", " + parentSelectionType + 
										" crossingChance: " + String.format("%.2f", crossChance) +
										" mutationChance: " + String.format("%.2f", mutationChance) +
										" tournamentSize: " + i*2 + " generationMix: " + config.generationMix);
								for (int j = 0; j < 3; j++){
									population = new Population(Config.getInstance().getPopulationSize(), Config.getInstance().getIndividualLength());
									evolve();
								}
							}
							System.out.println();
							System.out.println();
						}
					}
				}
			}
		}
	}
	
	public void evolve(){
		int numberOfGenerations = 0;
		double bestSolution = Double.MAX_VALUE;
		long startTime = System.currentTimeMillis();
		int bestValCount = 0;
		double lastSolution = 0;
		
		while (numberOfGenerations <= /*maxNumberOfGenerations*/ 10000){
			if (bestValCount >= 1000){
				break;
			}
			if (bestSolution == lastSolution){
				bestValCount ++;
			}
			else{
				bestValCount = 0;
			}
			lastSolution = bestSolution;
			
			Population offspring = population.clone();
			numberOfGenerations++;
			
			Config config = Config.getInstance();
			
			double curVal = config.calculateMeanPathlength(population);
			if (curVal < bestSolution){
				bestSolution = curVal;
			}
			
			offspring = crossover.cross(offspring);
			mutation.mutate(offspring);
			
			if (config.generationMix){
				population.population.addAll(offspring.population);
			}
			else{
				population.population = offspring.population;
			}
			population = selection.select(population);
		}
		long timeToExecute = System.currentTimeMillis() - startTime;
		System.out.println (String.format("%.3f", bestSolution) + " in " + timeToExecute/1000 + " seconds with " + 
				(numberOfGenerations-1000) + " generations");
	}
}
