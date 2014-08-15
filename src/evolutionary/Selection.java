package evolutionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    	int popSize = pop.size();
    	int [] indexes = new int[tourSize];
    	Map<Integer, Integer> indexesB = new HashMap<Integer, Integer>();    	
    	
    	int outCount = 0;
    	while (outCount<outSize){//until we have the output population size
    		int tourCount=0;
    		Random rand = new Random(System.currentTimeMillis());
    		while (tourCount<tourSize){//until we have the specified tour size
    			int index = rand.nextInt(popSize);
    			if (!indexesB.containsKey(index)){			// If index hasn't yet been selected add it
    				indexesB.put(index, index);
    				indexes[tourCount]=index;
    				tourCount++;
    				//need to decide if need to implement take best of as wiki suggests with a particular probability (if takes best then can just check fitness here and keep index and fitness of best rather than putting in tournament array
    			}
    		}
    		
    		//
    		outCount++;
    	} 
    	return pop;
    }
    
    public Population elitism(Population pop, int outSize, int cutOff){//cut off number or percent?
    	//sort by fitness
    	//cut off
    	return pop;
    }
}
