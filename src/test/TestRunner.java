package test;

import test.TestConfigure.TestOptions;
import evolutionary.Config;
import evolutionary.EvolutionDriver;

// TODO rename to something better
public class TestRunner {
	public static void main(String[] args){
		TestConfigure test = new TestConfigure(args);
		run(test.getTestOption());
	}
	
	public static void run(TestOptions testOption){
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
	
	private static void runTestingInstance(TestOptions test){
		// Do running stuff
		TSPProblem tsp = new TSPProblem(test);
		
		Config config = Config.getInstance();
		
		config.setAlleleMap(tsp.getNodes());
		config.setIndividualLength(tsp.getNumberOfNodes());
		
		EvolutionDriver evolutionDriver = new EvolutionDriver();
		evolutionDriver.evolve();
	}
}
