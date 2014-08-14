// Will fitnesses only be represented by integers?
// Have mutation in individual and crossover in population or as their own classes?

// Options for running these problems
/* 
 * One:
 * Can instantiate the data for the algorithms in a config object
 * This will be a global object that is used to instantiate the mutation/crossover/selection
 * These classes will be instantiated in population to do direct functions on the pop data
 * 
 * Two:
 * Can pass the information for the objects to the Evolution Driver
 * Evolution driver will instantiate the mutation/crossover/selector
 * These are passed into population and again do direct comps on the pop
 * 
 * Three:
 * Evolution driver
 * Pass info for crossover and selection into pop
 * Pass info for mutation into individual
 * 
 * Four:
 * Can I instantiate the Mutation/Crossover/Selection with final static vars?
 * This would mean each new instance would still contain the previous parameters
 */
// Instantiation of the Individual
/*
 * Needs to be linear uniform random
 */
// Fitness evaluation
/*
 * Of all the functions and methods, this may be the only one that will not be
 * similar throughout different problem representations... 
 * Crossover/mutation/selection all have functions but these will be largely similar
 * from problem to problem...
 *  
 * 
 * One:
 * Can simply do the evaluation of fitness in the TestRunner
 * Pros is that it is easy to do
 * Cons is that fitness eval actually has to be done in many places
 * 
 * Two:
 * Can put the fitness function into the config object and pass in the pop to return fitness
 * 
 * Three:
 * Can pass the fitness function as a lambda to the 
 */
// Crossover and mutation
/*
 * The largest modular parameter for this is determining whether allele values can repeat
 * ie. in TSP they cannot, you cannot have two of the same vertex, but in others you might be able
 * 
 * For crossover:
 * Passed in the population
 * Actual crossing Will take two individuals, so need a function to select them
 * Need number of break points
 * For added modularity if we have time we can do multiple parent crossover
 * 
 * For mutation:
 * Passed in the population
 * Will take in one individual, so need a way to find which individuals to take
 */
// Selection
/*
 * 
 */
package test;

public class TestRunner {
	public enum TestOptions{
		// Has all the different test options so you can just do a case switch function
	}
	
	public static void main(String[] args){
		// Start off asking what test option to run
		// This is a choice of one of the TestOptions enums
		// Then ask how many generations (should we limit the choices for this?
		// Then ask which one of the three test setups
		// This will pipe the TestOption and generation information to one of the runAlg___ methods
		// Could probably make all of these above enums
		
		String testName = "";
		int numberOfGenerations = 0;
		
		
		
	}
	
	// Should probs be the enum testOption, but don't want to change it yet
	private void printData(String testName){
		System.out.println("Running test:" + testName);
		System.out.println("");
	}
	
	// Each one of these will have set mutation/crossover/selections functions and params that will be used to set the EvolutionDriver object
	// Right now they will either return a fitness level that is put to print, or they could just run print within which might be better
	// They will instantiate a population and call population.evolve, will be if statements to control when the algorithm terminates
	// By controlling termination here we can make it modular to say, if a population doesn't change for a while... makes it easier to switch prob to prob
	// At the end or when you want to print, simply call some sort of .getFitness, or could have fitness eval in here
	private void runAlgOne(){
		
	}
	private void runAlgTwo(){
		
	}
	private void runAlgThree(){
		
	}
}
