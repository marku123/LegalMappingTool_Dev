package model;

public class PersonsOfConcern {

	
	//A3 Variables
	private String PersonOfConcernName;
	private String[][] Obstacles;
	private String[][] obstaclesDocumenation;
	
	
	public String getPersonOfConcernName() {
		return PersonOfConcernName;
	}
	public void setPersonOfConcernName(String personOfConcernName) {
		PersonOfConcernName = personOfConcernName;
	}
	public String[][] getObstacles() {
		return Obstacles;
	}
	public void setObstacles(String[][] obstacles) {
		Obstacles = obstacles;
	}
	public String[][] getObstaclesDocumenation() {
		return obstaclesDocumenation;
	}
	public void setObstaclesDocumenation(String[][] obstaclesDocumenation) {
		this.obstaclesDocumenation = obstaclesDocumenation;
	}
	
}
