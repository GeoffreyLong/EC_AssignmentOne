// Will fitnesses only be represented by integers?
// Have mutation in individual and crossover in population or as their own classes?
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
