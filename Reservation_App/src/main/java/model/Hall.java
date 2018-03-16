package model;

public class Hall {

	private int Id;
	
	private int InstitutionId;
	
	public Hall(){
		
	}

	public Hall(int institutionId) {
		super();
		InstitutionId = institutionId;
	}

	public int getId() {
		return Id;
	}

	public int getInstitutionId() {
		return InstitutionId;
	}

	public void setInstitutionId(int institutionId) {
		InstitutionId = institutionId;
	}
	
	
}
