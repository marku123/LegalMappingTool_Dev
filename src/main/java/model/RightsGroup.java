package model;

public class RightsGroup {

	//A3 Variables
	private String RightsGroupName;
	private String[][] IntlInstruments;
	private String[][] NatlInstruments;
	
	public String getRightsGroupName() {
		return RightsGroupName;
	}
	public void setRightsGroupName(String rightsGroupName) {
		RightsGroupName = rightsGroupName;
	}
	public String[][] getIntlInstruments() {
		return IntlInstruments;
	}
	public void setIntlInstruments(String[][] intlInstruments) {
		IntlInstruments = intlInstruments;
	}
	public String[][] getNatlInstruments() {
		return NatlInstruments;
	}
	public void setNatlInstruments(String[][] natlInstruments) {
		NatlInstruments = natlInstruments;
	}
	
}
