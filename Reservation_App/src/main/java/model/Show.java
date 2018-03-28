package model;

public class Show {
	private int id;
	
	private String name;

	private int genreId;
	
	private int typeId;
	
	private int length;

	public Show(int id, String name, int genreId, int typeId, int length) {
		super();
		this.id = id;
		this.name = name;
		this.genreId = genreId;
		this.typeId = typeId;
		this.length = length;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public int getShowId() {
		return typeId;
	}

	public void setShowId(int showId) {
		this.typeId = showId;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	
}
