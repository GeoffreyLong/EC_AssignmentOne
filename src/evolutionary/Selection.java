package evolutionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Selection {
	public int param = -1;
	
	public void select(){
		
	}
	
	// Setter that insures field is only set once
	public void setparam(int param)  {
        this.param = this.param == -1 ? param : throw_();
    }

	// Throw error if already set
    public int throw_() {
        throw new RuntimeException("field is already set");
    }
    
    public Population fitnessProportional(Population pop, int outSize){
    	
    	return pop;
    }
    
    public Population tournamentSelection(Population pop, int outSize, int tourSize, double prob){
    	Individual [] subset = new Individual[outSize];
    	int popSize = pop.size();
    	int [] indexes = new int[tourSize];
    	Set<Integer> indexesB = new HashSet<Integer>();    	
    	
    	int outCount = 0;
    	while (outCount<outSize){//until we have the output subset population size
    		int tourCount=0;
    		double bestFitness = 0;
    		int bestIndex = -1;
    		Random rand = new Random(System.currentTimeMillis());
    		while (tourCount<tourSize){//until we have the specified tour size
    			int index = rand.nextInt(popSize);
    			if (!indexesB.contains(index)){			// If index hasn't yet been selected add it
    				indexesB.add(index);// only need if using probability
    				indexes[tourCount]=index;
    				tourCount++;
    				double fitness=Config.getInstance().calculateFitness(pop.population[index]);
    				if(fitness>bestFitness){// fitness of this individual is best
    					bestFitness=fitness;
    					bestIndex=index;
    				}// take best one? or take best one with probability prob (as wiki suggests)
    			}
    		}
    		subset[outCount]=pop.population[bestIndex];
    		outCount++;
    	} 
    	return new Population(subset);
    }
    
    public Population elitism(Population pop, int outSize, double cutOff){//cut percent (rather than number)
    	//sort by fitness
    	//cut off
    	return pop;
    }
}
