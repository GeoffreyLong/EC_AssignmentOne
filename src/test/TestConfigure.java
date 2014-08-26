package test;

import java.awt.Point;
import java.util.Map;

import evolutionary.Config;
import evolutionary.Crossover.CrossoverType;
import evolutionary.Mutation.MutationType;
import evolutionary.Selection.SelectionType;

// TODO rename class
public class TestConfigure {
	private TestOptions testOption;
	private final int SMALLEST_GEN_SIZE = 1;
	private final int LARGEST_GEN_SIZE = 100000;
	
	public enum TestOptions{
		EIL51, 		// 426
		EIL76, 		// 528
		EIL101, 	// 629
		ST70, 		// 675
		KROA100, 	// 21282
		KROC100, 	// 20749
		KROD100, 	// 21294
		LIN105,		// 14379
		PCB442, 	// 50778
		PR2392,		// 378032
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
			case 3: testOption = TestOptions.EIL101;
					break;
			case 4: testOption = TestOptions.ST70;
					break;
			case 5: testOption = TestOptions.KROA100;
					break;
			case 6: testOption = TestOptions.KROC100;
					break;
			case 7: testOption = TestOptions.KROD100;
					break;
			case 8: testOption = TestOptions.LIN105;
					break;
			case 9: testOption = TestOptions.PCB442;
					break;
			case 10: testOption = TestOptions.PR2392;
					break;
			case 11: testOption = TestOptions.ALL_TESTS;
					break;
			default: System.out.println("Not a valid testOption"); 
					inputError();
		}
	}
	private void readNumberOfGenerations(String arg){
		int numberOfGenerations = readInt(arg);
		if (numberOfGenerations < SMALLEST_GEN_SIZE){
			System.out.println("Too few generations");
			inputError();
		}
		if (numberOfGenerations > LARGEST_GEN_SIZE){
			System.out.println("Too many generations");
			inputError();
		}
		
		Config.getInstance().setNumberOfGenerations(numberOfGenerations);
	}
	private void readAlgorithmNumber(String arg){
		switch(readInt(arg)){
		case 1: setUpAlgOne();
				break;
		case 2:	setUpAlgTwo();
				break;
		case 3: setUpAlgThree();
				break;
		case 4: setUpInverOver();
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
		Config config = Config.getInstance();
		System.out.println("Running Algorithm One");
		config.setSelectionType(SelectionType.TOURNAMENT);
		config.setParentSelectionType(SelectionType.TOURNAMENT);
		config.setGenerationMix(false);
		config.setTournamentSize(4);
		config.setMutationChance(1);
		config.setCrossoverChance(1);
		config.setSelectionTypeChance(0, 1, 0, 0);
		config.setCrossoverTypeChance(1, 0, 0, 0);
		config.setMutationTypeChance(1, 0, 0, 0, 0);
	}

	
	
	// With elitism, and tournament parent selection
	// Cycle swap seems to converge around 540 to 600
	// Inversion > Insert > Swap > scramble
	// Scramble seems to be the slowest of these but may be the most likely to get out of a local optimum
	
	// This one converges rather quickly to around 440 - 540
	// 470 - 480 seems to be rather average though
	private void setUpAlgTwo(){
		Config config = Config.getInstance();
		System.out.println("Running Algorithm Two");
		//config.setMutationType(MutationType.INSERT);
		//config.setCrossoverType(CrossoverType.ORDER);
		config.setSelectionType(SelectionType.ELITISM);
		config.setParentSelectionType(SelectionType.TOURNAMENT);
		config.setGenerationMix(true);
		config.setTournamentSize(4);
		config.setMutationChance(1);
		config.setCrossoverChance(1);
		config.setSelectionTypeChance(0, 0, 1, 0);
		config.setCrossoverTypeChance(0, 1, 0, 0);
		config.setMutationTypeChance(0, 0, 1, 0, 0);
	}
	private void setUpAlgThree(){
		Config config = Config.getInstance();
		System.out.println("Running Algorithm Three");
		config.setSelectionType(SelectionType.TOURNAMENT);
		config.setParentSelectionType(SelectionType.SUS);
		config.setGenerationMix(true);
		config.setTournamentSize(3);
		config.setMutationChance(0.1);
		config.setCrossoverChance(0.2);
		config.setCrossoverTypeChance(0.25, 0.25, 0.25, 0.25);
		config.setMutationTypeChance(0.3, 0.1, 0.4, 0.2, 0);
	}
	private void setUpInverOver(){
		Config config = Config.getInstance();
		System.out.println("Running Inver-over");
		//config.setMutationType(MutationType.INVEROVER);
		config.setCrossoverTypeChance(0, 0, 0, 0);
		config.setMutationTypeChance(0, 0, 0, 0, 1);
		config.setGenerationMix(false);
		config.setInverOverProbability(0.02);
	}
	
	
	// Could instead make this a single "enter -h for help" then output this on -h
	private void inputError(){
		System.out.println();
		System.out.println("CORRECT FORMAT");
		System.out.println("program must be called programName x y z");
		System.out.println("  x is the TEST CASE which can be any number between 1 and 11 inclusive");
		for (int i = 0; i < TestOptions.values().length; i++){
			System.out.println("    " + (i+1) + " : " + TestOptions.values()[i]);
		}
		System.out.println("  y is the MAXIMUM NUMBER OF GENERATIONS, which can be between " +
				this.SMALLEST_GEN_SIZE + " and " + this.LARGEST_GEN_SIZE);
		System.out.println("  z is the algorithm number which can be any of {1,2,3,4}");
		System.out.println("    Numbers 1 through 3 are our implementations, 1 is the best of these ");
		System.out.println("    number 4 is the InverOver implementation");
		
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
	
	TestOptions getTestOption(){
		return this.testOption;
	}
}
