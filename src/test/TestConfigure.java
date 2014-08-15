package test;

import java.awt.Point;
import java.util.Map;

import evolutionary.Config;
import evolutionary.EvolutionDriver;
import evolutionary.Mutation;

// Perhaps should run from this class as well?
// If so should rename the class?
public class TestConfigure {
	private TestOptions testOption;
	private int numberOfGenerations;
	private final int SMALLEST_GEN_SIZE = 1;
	private final int LARGEST_GEN_SIZE = 20000;
	
	// Default... this should be instantiated in the algorithm configuration
	private int populationSize = 100;
	
	public enum TestOptions{
		EIL51, 
		EIL76, 
		EIL101, 
		ST70, 
		KROA100, 
		KROC100, 
		KROD100, 
		LIN105,
		PCB442, 
		PR2392,
		ALL_TESTS;
		
		public String getName(){
			return this.name();
		}
	}
	
	public TestConfigure(String[] args){
		if (args.length == 3){
			readTestOption(args[0]);
			readNumberOfGenerations(args[1]);
			readAlgorithmNumber(args[2]);
		}
		else{
			System.out.println("Incorrect number of arguments");
			inputError();
		}
	}
	
	private void readTestOption(String arg){
		switch(readInt(arg)){
			//TODO need to add all the extra cases
			case 1: testOption = TestOptions.EIL51;
					break;
			case 2: testOption = TestOptions.EIL76;
					break;
			default: System.out.println("Not a valid testOption"); 
					inputError();
		}
	}
	private void readNumberOfGenerations(String arg){
		numberOfGenerations = readInt(arg);
		if (numberOfGenerations < SMALLEST_GEN_SIZE){
			System.out.println("Too few generations");
			inputError();
		}
		if (numberOfGenerations > LARGEST_GEN_SIZE){
			System.out.println("Too many generations");
			inputError();
		}
	}
	private void readAlgorithmNumber(String arg){
		switch(readInt(arg)){
		case 1: setUpAlgOne();
				break;
		case 2:	setUpAlgTwo();
				break;
		case 3: setUpAlgThree();
				break;
		default: System.out.println("Not a valid algorithm number");
				inputError();
		}
	}
	
	private int readInt(String arg){
		int returnArg = -1;
		try {
	    	returnArg = Integer.parseInt(arg);
	    } catch (NumberFormatException e) {
	    	System.out.println("One or more parameters is not a number");
	    	inputError();
	    }
		
		return returnArg;
	}
	
	// Each one of these will have set mutation/crossover/selections functions and params that will be used to set the EvolutionDriver object
	// Right now they will either return a fitness level that is put to print, or they could just run print within which might be better
	// They will instantiate a population and call population.evolve, will be if statements to control when the algorithm terminates
	// By controlling termination here we can make it modular to say, if a population doesn't change for a while... makes it easier to switch prob to prob
	// At the end or when you want to print, simply call some sort of .getFitness, or could have fitness eval in here
	
	// TODO add param information print
	// Set the mutation, crossover, and selection types and params
	private void setUpAlgOne(){
		System.out.println("Running Algorithm One");
	}
	private void setUpAlgTwo(){
		System.out.println("Running Algorithm Two");
	}
	private void setUpAlgThree(){
		System.out.println("Running Algorithm Three");
	}
	
	public void run(){
		if (testOption == TestOptions.ALL_TESTS){
			for (TestOptions option : TestOptions.values()){
				if (option != TestOptions.ALL_TESTS){
					runTestingInstance(option);
				}
			}
		}
		else{
			runTestingInstance(testOption);
		}
	}
	
	private void runTestingInstance(TestOptions test){
		// Do running stuff
		TSPProblem tsp = new TSPProblem(TestOptions.EIL101);
		Map<String, Point> nodeMap = tsp.getNodes();
		
		Config.setAlleleMap(nodeMap);
		System.out.println(Config.getAlleleMap());
		
		printData(test);
		EvolutionDriver evolutionDriver = new EvolutionDriver(numberOfGenerations, populationSize);
		evolutionDriver.evolve();
	}
	
	private void inputError(){
		System.out.println("Lol, wrong arguments... sucks to suck");
		// TODO tell the user how to enter information into the system
		System.exit(0);
	}
	
	// Small test for file reader, move into testing suite later
	private void tspReaderTest(){
		TSPProblem tsp = new TSPProblem(TestOptions.EIL101);
		int numberOfNodes = tsp.getNumberOfNodes();
		Map<String, Point> nodeMap = tsp.getNodes();
		
		// Check to see if all the numbers are there
		for (int i = 1; i <= numberOfNodes; i++){
			if (!nodeMap.containsKey(String.valueOf(i))){
				System.out.println("Missing " + i);
			}
			else{
				System.out.println(nodeMap.get(String.valueOf(i)));
			}
		}
	}
	
	// Print for each run
	private void printData(TestOptions testName){
		System.out.println("Running test:" + testName);
		System.out.println("");
	}
}
