package evolutionary;

// Crossover and Selection will have a similar setup
public class Mutation {
	private MutationType mutationType;
	private int mutationParam;
	
	enum MutationType{
		
	}
	
	public Mutation(MutationType mutationType, int mutationParam){
		this.mutationType = mutationType;
		this.mutationParam = mutationParam;
	}
	
	public void mutate(Population population){
		switch(mutationType){
			// For each different case run a different method?
		}
	}
}
