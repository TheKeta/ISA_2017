package model;

public class Hall {

	private int id;
	
	private int institutionId;
	
	public Hall(){
		
	}

	public Hall(int id, int institutionId) {
		super();
		this.id = id;
		this.institutionId = institutionId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}

	
	
	
}
