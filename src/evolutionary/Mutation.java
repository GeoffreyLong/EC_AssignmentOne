package evolutionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Mutation {
	private MutationType mutationType;
	private static Random rand = new Random(System.currentTimeMillis());
	private double[] mutationTypeChance;
	
	public enum MutationType{
		INSERT,SWAP,INVERSION,SCRAMBLE,INVEROVER
	}
	
	public Mutation(double[] mutationTypeChance){
		this.mutationTypeChance = mutationTypeChance;
	}
	
	public Population mutate(Population population){
		Config config = Config.getInstance();
		MutationType mutationType = null;
		double random = rand.nextDouble();
		
		for (int i = 0; i < MutationType.values().length; i++){
			if (random < mutationTypeChance[i]){
				mutationType = MutationType.values()[i];
				break;
			}
		}

		switch(mutationType){
			case INSERT:
				for (int i = 0; i<population.size();i++){
					if (rand.nextDouble() < config.mutationChance){
						population.population.set(i, insert(population.population.get(i)));
					}
				}
				break;
			case SWAP:
				for (int i = 0; i<population.size();i++){
					if (rand.nextDouble() < config.mutationChance){
						population.population.set(i, swap(population.population.get(i)));
					}
				}
				break;		
			case INVERSION:
				for (int i = 0; i<population.size();i++){					
					if (rand.nextDouble() < config.mutationChance){
						population.population.set(i, inversion(population.population.get(i)));
					}
				}
				break;
			case SCRAMBLE:
				for (int i = 0; i<population.size();i++){
					if (rand.nextDouble() < config.mutationChance){
						population.population.set(i, scramble(population.population.get(i)));
					}
				}
				break;
			case INVEROVER:
				newInverOver(population);
				break;
		}
		return population;
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
	
	public Population inverOver(Population p, double prob){
		//System.out.println(p.population.toString());
		for(int popInd = 0; popInd < p.population.size(); popInd++){
			Individual i = p.population.get(popInd);
			Individual iTemp = (Individual) i.clone();//deep clone of individual
			
			int numChromosomes=iTemp.genotype.size();
			int cInd = rand.nextInt(numChromosomes); //c index (index of iTemp (S'))
			String c = (String) iTemp.genotype.get(cInd);//c name
			
			//System.out.println("Individual (S'): " + i.toString());
			//System.out.println("City (c): Index:" +cInd +"   ID: "+ c);
			
			while(true){
				String cb="";
				int cbInd=-1;
				if(rand.nextDouble()<=prob){
					cbInd=rand.nextInt(numChromosomes);
					while (cbInd==cInd){//ensure non-duplicate choice
						cbInd=rand.nextInt(numChromosomes);//c' index
						cb=(String) iTemp.genotype.get(cbInd);//c' name
					}
					//System.out.println("Rand was less than prob, so get c' from S'");
					//System.out.println("City 2 (c'): Index:" +cbInd +"   ID: "+ cb);
				}else{
					//System.out.println("Rand was NOT less than prob, so get c' from random individual");
					int ibInd=rand.nextInt(p.population.size());//can be same individual?
					String label = (String) iTemp.genotype.get(cInd);
					//System.out.println("Individual 2: " + p.population.get(ibInd).toString());
					for(int t=0; t<p.population.get(ibInd).genotype.size();t++){
						String labelb = (String) p.population.get(ibInd).genotype.get(t);
						if(label.equals(labelb)){
							//System.out.println("c is at index " + t + " of individual 2");
							cbInd=(t+1>=p.population.get(ibInd).genotype.size()) ? 0 : t+1;//c' index (index of p.population[ibInd] (separate individual))
							cb=(String) p.population.get(ibInd).genotype.get(cbInd);//c' name
							//System.out.println("City 2 (c'): Index:" +cbInd +"   ID: "+ cb);
							break;
						}
					}
				}
				
				if(cInd==0){
					String next = (String)iTemp.genotype.get(cInd+1);
					if(cb.equals(next)){
						//System.out.println("Same so exit loop");
						break;
					}					
				}else if(cInd==numChromosomes-1){
					String previous = (String)iTemp.genotype.get(cInd-1);
					if(cb.equals(previous)){
						//System.out.println("Same so exit loop");
						break;
					}
				}else{
					String next = (String)iTemp.genotype.get(cInd+1);
					String previous = (String)iTemp.genotype.get(cInd-1);
					if(cb.equals(next)||cb.equals(previous)){
						//System.out.println("Same so exit loop");
						break;
					}
				}
				
				//fix index for c=c' from index of p.population[ibInd] (separate individual) back to S' (ie. find c' string in iTemp (S'))
				for(int t=0; t<i.genotype.size();t++){
					String label = (String) i.genotype.get(t);
					if(cb.equals(label)){
						cbInd=t;//c' index (index of c' in S')
						//System.out.println("City c' is index " +t + " of individual 1: " + i.toString());
						break;
					}
				}
				
				//inversion mutation
				int indexA=(cInd+1)%(numChromosomes);
				int indexB=cbInd%(numChromosomes);
				if (indexA > indexB) {//make sure indexes in ascending order
					int tmp = indexA;
					indexA = indexB;
					indexB = tmp;
				}
				
				//System.out.println("Perform inverson on "+ i.toString()+" from index "+indexA+" to index"+indexB);
				int swaps = (int) (Math.floor(indexB-indexA)/2);//how many swap operations
				
				for (int j = 0; j < swaps; j++) {
					Object temp = iTemp.genotype.get(indexA+j);//store temp
					iTemp.genotype.set(indexA+j,iTemp.genotype.get(indexB-j));
					iTemp.genotype.set(indexB-j,temp);
				}
				
				///c=c'
				//System.out.println("c=c'");
				c=cb;
				cInd=cbInd;				
			}
			
			if(Config.getInstance().calculateFitness(iTemp)>=Config.getInstance().calculateFitness(i)){//compare fitness'
				//System.out.println(i.toString());
				//System.out.println(iTemp.toString());
				p.population.set(popInd, iTemp);
				//System.out.println("Less than");
			}
			//System.out.println("-----------------------------------------------------");
		}		
		//System.out.println(p.population.toString());
		return p;
	}
	
	public void newInverOver(Population p){
		for (int individualIndex = 0; individualIndex < p.size(); individualIndex++){
			Individual originalIndividual = p.population.get(individualIndex);
			Individual clonedIndividual = originalIndividual.clone();
			
			int genotypeSize = clonedIndividual.genotype.size();
			
			int firstCityIndex = rand.nextInt(genotypeSize);
			Object firstCity = clonedIndividual.genotype.get(firstCityIndex);
			
			while(true){
				Object secondCity = firstCity;
				
				if(rand.nextDouble() <= Config.getInstance().inverOverProbability){
					while(secondCity.equals(firstCity)){
						secondCity = clonedIndividual.genotype.get(rand.nextInt(genotypeSize));
					}
				}
				else{
					Individual randomIndividual = p.population.get(rand.nextInt(p.population.size()));
					int index = getIndexOfElement(firstCity, randomIndividual.genotype);
					secondCity = randomIndividual.genotype.get((index + 1) % genotypeSize);
				}
				
				int secondCityIndex = getIndexOfElement(secondCity, clonedIndividual.genotype);
				int indexDifference = Math.abs(firstCityIndex - secondCityIndex);
				if (indexDifference == 1 || indexDifference == genotypeSize){
					break;
				}
				
				//inversion mutation copied straight from other alg
				int indexA = (firstCityIndex+1) % genotypeSize;
				int indexB = secondCityIndex % genotypeSize;
				if (indexA > indexB) {//make sure indexes in ascending order
					int tmp = indexA;
					indexA = indexB;
					indexB = tmp;
				}
				
				int swaps = (int) (Math.floor(indexB-indexA)/2);//how many swap operations
				
				for (int j = 0; j < swaps; j++) {
					Object temp = clonedIndividual.genotype.get(indexA+j);//store temp
					clonedIndividual.genotype.set(indexA+j, clonedIndividual.genotype.get(indexB-j));
					clonedIndividual.genotype.set(indexB-j,temp);
				}
				
				firstCity = secondCity;
				firstCityIndex = secondCityIndex;
			}
			
			if(Config.getInstance().calculateFitness(clonedIndividual)>=Config.getInstance().calculateFitness(originalIndividual)){//compare fitness'
				p.population.set(individualIndex, clonedIndividual);
			}
		}
	}
	
	private int getIndexOfElement(Object element, List<Object> list){
		int index;
		for (index = 0; index < list.size(); index++){
			if (list.get(index).equals(element)){
				break;
			}
		}
		return index;
	}
}
