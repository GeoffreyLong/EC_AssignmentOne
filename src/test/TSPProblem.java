package test;

public class TSPProblem {
	public TSPProblem(String problem){
		// Reads in the problem version to use, pipes out the necessary information
		// How this information is returned is a good question
		String filename = getFileName(problem);
		
	}
	
	private String getFileName(String problem){
		// fileName is the path to the file where the TSP is saved
		String fileName = "../" + problem;
		return fileName;
	}
}
