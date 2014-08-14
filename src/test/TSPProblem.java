/*
 * Represented as 
 * NAME : <name of file>
 * COMMENT : <Any comments>
 * TYPE : <Type of problem>
 * DIMENSION : <Number of Nodes>
 * EDGE_WEIGHT_TYPE : <ie. EUC_2D>
 * NODE_COORD_SECTION // This is not a field it is just a data separating line
 * <nodeNumber> <x-coord> <y-coord> separated by a space indexed from one
 * ...
 * EOF
 */

// TODO missing ST70, KROA100, and PR2392 from lib
package test;

import test.TestRunner.TestOptions;

public class TSPProblem {
	public TSPProblem(TestOptions testName){
		// Reads in the problem version to use, pipes out the necessary information
		// How this information is returned is a good question
		String filename = getFileName(testName.getName());
	}
	
	private String getFileName(String problem){
		// fileName is the path to the file where the TSP is saved
		String fileName = "../../" + problem;
		return fileName;
	}
}
