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

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.TestConfigure.TestOptions;

public class TSPProblem {
	private List<String> data;
	private final int NODE_DATA_OFFSET = 6;
	
	public TSPProblem(TestOptions testName){
		// Reads in the problem version to use, pipes out the necessary information
		// How this information is returned is a good question
		
		try{
			String fileName = getFileName(testName);
			Path path = Paths.get(fileName);
			data = Files.readAllLines(path, StandardCharsets.UTF_8);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String getFileName(TestOptions testName){
		// fileName is the path to the file where the TSP is saved
		String fileName = "lib/" + testName.getName() + ".tsp";
		return fileName;
	}
	
	public int getNumberOfNodes(){
		String numberOfNodes = data.get(3).split(" : ")[1];
		return Integer.valueOf(numberOfNodes);
	}
	
	
	// Each line after the NODE_DATA_OFFSET line corresponds to one node
	// Nodes contain the following info nodeIndex, xCoord, yCoord
	// These are in that order on the line split by a space
	// Simply split the line by spaces and add in the data
	public Map<String, Point> getNodes(){
		Map<String, Point> nodeMap = new HashMap<String, Point>();
		
		// For loop iterates over each node
		for (int i = NODE_DATA_OFFSET; i < data.size() - 1; i++){
			// Get the line containing the next node and split
			String[] nodeValues = data.get(i).split(" ");
			
			// Add in the data gotten by splitting the node line
			String nodeName = String.valueOf(i - NODE_DATA_OFFSET);
			int xCoordinate = Integer.valueOf(nodeValues[1]);
			int yCoordinate = Integer.valueOf(nodeValues[2]);
			
			Point point = new Point(xCoordinate, yCoordinate);
			
			// Add the node representation to the map
			nodeMap.put(nodeName, point);
		}
		
		return nodeMap;
	}
}
