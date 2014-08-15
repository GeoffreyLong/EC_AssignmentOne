package evolutionary;

public class Selection {
	public int param = -1;
	
	public void select(){
		
	}
	
	// Setter that insures field is only set once
	public void setparam(int param)  {
        this.param = this.param == -1 ? param : throw_();
    }

	// Throw error if already set
    public int throw_() {
        throw new RuntimeException("field is already set");
    }
}
