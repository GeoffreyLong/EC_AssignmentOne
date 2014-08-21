package evolutionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Mutation {
	private MutationType mutationType;
	private static Random rand = new Random(System.currentTimeMillis());
	
	public enum MutationType{
		INSERT,SWAP,INVERSION,SCRAMBLE
	}
	
	public Mutation(MutationType mutationType){
		this.mutationType = mutationType;
	}
	
	public Individual mutate(Individual individual){
		switch(mutationType){
			case INSERT:
					individual=insert(individual);
				break;
			case SWAP:
					individual=swap(individual);
				break;		
			case INVERSION:
					individual=inversion(individual);
				break;
			case SCRAMBLE:
					individual=scramble(individual);
				break;
		}
		return individual;
	}
	
	public Individual insert(Individual i){
		int numChromosomes=i.genotype.size();
		
		int indexA = rand.nextInt(numChromosomes);
		int indexB = rand.nextInt(numChromosomes);
		
		if (indexA > indexB) {//make sure indexes in ascending order
			int tmp = indexA;
			indexA = indexB;
			indexB = tmp;
		}
		//System.out.println(indexA+", "+indexB);//testing
		
		for (int j = indexA+1; j < indexB; j++) {
			Object temp = i.genotype.get(j);
			i.genotype.set(j, i.genotype.get(indexB));
			i.genotype.set(indexB,temp);			
		}

		return i;
	}
	
	public Individual swap(Individual i){
		int numChromosomes=i.genotype.size();
		
		int indexA = rand.nextInt(numChromosomes);
		int indexB = rand.nextInt(numChromosomes);
		//System.out.println(indexA+", "+indexB);//testing
		
		Object temp = i.genotype.get(indexA);//store temp
		i.genotype.set(indexA,i.genotype.get(indexB));
		i.genotype.set(indexB,temp);
		
		return i;	
	}
	
	public Individual inversion(Individual i){
		int numChromosomes=i.genotype.size();
		
		int indexA = rand.nextInt(numChromosomes);
		int indexB = rand.nextInt(numChromosomes);
		if (indexA > indexB) {//make sure indexes in ascending order
			int tmp = indexA;
			indexA = indexB;
			indexB = tmp;
		}
		int swaps = (int) (Math.floor(indexB-indexA+1)/2);//how many swap operations
		//System.out.println(indexA+", "+indexB+", "+swaps);//testing
		
		for (int j = 0; j < swaps; j++) {
			Object temp = i.genotype.get(indexA+j);//store temp
			i.genotype.set(indexA+j,i.genotype.get(indexB-j));
			i.genotype.set(indexB-j,temp);
		}	
		
		return i;	
	}
	
	public Individual scramble(Individual i){
		int numChromosomes=i.genotype.size();
		
		int numberOfScrambles = rand.nextInt(numChromosomes);				// Number of chromosomes to scramble
		int [] indexes = new int[numberOfScrambles];						// Random array of indexes to scramble
		int [] sortedIndexes = new int[numberOfScrambles];					// The same indexes sorted
		Set<Integer> indexesB = new HashSet<Integer>();	// HashSet of the indexes to ensure all are unique
		
		//System.out.println(numberOfScrambles);//testing
		
		int count=0;
		while (count < numberOfScrambles){
			int index = rand.nextInt(numChromosomes);
			if (!indexesB.contains(index)){			// If index hasn't yet been selected add it
				indexesB.add(index);
				indexes[count]=index;
				sortedIndexes[count]=index;
				count++;
			}
		}
		
		Arrays.sort(sortedIndexes);
		
		// Store temp chromosomes
		Map<Integer, Object> temp = new HashMap<Integer, Object>();
		for (int j = 0; j < numberOfScrambles; j++){
			temp.put(sortedIndexes[j],i.genotype.get(sortedIndexes[j]));
			//System.out.print(sortedIndexes[j]+",");//testing
		}
		//System.out.println();//testing
		// Fill in new individual
		for (int j = 0; j < numberOfScrambles; j++){
			i.genotype.set(indexes[j], temp.get(sortedIndexes[j]));
		}
		
		return i;	
	}
}
