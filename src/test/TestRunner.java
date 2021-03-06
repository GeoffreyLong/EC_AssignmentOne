package test;

import test.TestConfigure.TestOptions;
import evolutionary.Config;
import evolutionary.EvolutionDriver;

// TODO rename to something better
public class TestRunner {
	private static int counter = 1;
	private static int iterations = 3;
	
	public static void main(String[] args){
		TestConfigure test = new TestConfigure(args);
		while(true){ 
			if(counter>iterations)break;
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("------------------------------ ITERATION "+ String.format("%03d",counter)+" -----------------------------");
			run(test.getTestOption());
			counter ++;
		}
	}
	
	public static void run(TestOptions testOption){
		if (testOption == TestOptions.ALL_TESTS){
			for (TestOptions option : TestOptions.values()){
				if (option != TestOptions.ALL_TESTS){
					if (option != TestOptions.PCB442 && option != TestOptions.PR2392){
						runTestingInstance(option);
					}
				}
			}
		}
		else{
			runTestingInstance(testOption);
		}
	}
	
	private static void runTestingInstance(TestOptions test){
		TSPProblem tsp = new TSPProblem(test);
		
		Config config = Config.getInstance();
		
		config.setTestingInstance(test);
		config.setAlleleMap(tsp.getNodes());
		config.setIndividualLength(tsp.getNumberOfNodes());
		
		EvolutionDriver evolutionDriver = new EvolutionDriver();
		evolutionDriver.evolve();
	}
}
