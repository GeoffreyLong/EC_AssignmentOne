package evolutionary;

// Make this immutable after instantiating each field
// Needs to be globally accessible else we will be passing it to every class
// Will be an object containing the functions for fitness,
// the params for all the algorithms (cross, mutate, select)
// And anything else we might need

// If we don't want to use a globally accessible object we will have to find
// Another way to instantiate the mutation, crossover

public class Config {
	private static int numberOfNodes = -1;
	
	// Setter that insures field is only set once
	public void setNumberOfNodes(int numberOfNodes)  {
        this.numberOfNodes = this.numberOfNodes == -1 ? numberOfNodes : throw_();
    }

	// Throw error if already set
    public int throw_() {
        throw new RuntimeException("field is already set");
    }
}
