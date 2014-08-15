package evolutionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Mutation {
	private MutationType mutationType;
	private int mutationParam;
	private static Random rand = new Random(System.currentTimeMillis());
	
	enum MutationType{
		INSERT,SWAP,INVERSION,SCRAMBLE
	}
	/*
	public Mutation(MutationType mutationType, int mutationParam){
		this.mutationType = mutationType;
		this.mutationParam = mutationParam;
	}
	*/
	public void mutate(Population population){
		switch(mutationType){
			case INSERT:
				for (int i = 0; i<population.size();i++){
					population.population[i]=insert(population.population[i]);
				}
				break;
			case SWAP:
				for (int i = 0; i<population.size();i++){
					population.population[i]=swap(population.population[i]);
				}
				break;		
			case INVERSION:
				for (int i = 0; i<population.size();i++){
					population.population[i]=inversion(population.population[i]);
				}
				break;
			case SCRAMBLE:
				for (int i = 0; i<population.size();i++){
					population.population[i]=scramble(population.population[i]);
				}
				break;
		}
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
		System.out.println(indexA+", "+indexB);//testing
		
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
		Map<Integer, Integer> indexesB = new HashMap<Integer, Integer>();	// HashMap of the indexes to ensure all are unique
		
		//System.out.println(numberOfScrambles);//testing
		
		int count=0;
		while (count < numberOfScrambles){
			int index = rand.nextInt(numChromosomes);
			if (!indexesB.containsKey(index)){			// If index hasn't yet been selected add it
				indexesB.put(index, index);
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
