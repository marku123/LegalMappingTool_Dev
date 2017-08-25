package model;

public class Country {

	private String CountryName;
	
	//A1 variables.
	private String CountryRegion;
	private String ConstitutionYesNo;
	private String ConstitutionDate;
	private String ConstitutionAmendYesNo;
	private String ConstitutionAmendDate;
	private String ConstLinkOrig;
	private String ConstLinkFrenEng;
	private String BillLinkOrig;
	private String BillLinkFrenEng;
	private String ConstAppToPoC[][] = new String[5][2];
	
	//A2 variables.
	private String CommonCivilPlural;
	private String PluralText;
	private String FederalState;
	private String[][] JudicialEntityCourt;
	private String[][] AdminEntity;
	private String[][] TradMech;
	private String TradMechComments;	
	private String Comments;	
	
	//A3 Variables
	private String RightsGroup;
	private String[][] IntlInstruments;
	private String[][] NatlInstruments;
	
	//A3 Reporting Variables
	private RightsGroup[] RightsGroups;

	//B Variables
	private String POC;
	private String[][] Obstacles;
	private String[][] obstaclesDocumenation;
	
	//B Reporting Variables
	private PersonsOfConcern[] PersonsOfConcern;
	
	//D Variables
	private String[][] POCDifferencesPriorities;
	private String[][] OtherSubGrps;

	//Analytics Legal Framework
    private String[][] ConsistencyWithInternational; 
    private Boolean ConsistencyWithInternationalDataMissing; 

    
	//Analytics National Instruments
    private String[][] NatInstruPOCDetail; //Average score for each individual POC for each individual rights group.
    private String[] NatInstruPOCSummary; //Average score for all POC for all invididual rights group. 
    private Boolean NatInstruPOCDataMissing; 

    
	//Analytics Obstacles
    private String[][] POCObstaclesDetail; //All information for the POC obstacles table.
    private String[][] POCRightsGrouopsObstacles; //Just the individual POC rights groups obstacles. 
    private String[] AllPOCRightsGrouopsObstacles;//All POC rights groups obstacles. No individual POCs.
    private String[] POCALLRightsGroupsObtacles;//Individual POC rights groups obstacles. No individual rights groups. 
    private String AllPOCALLRightsGroupsObtacles;//All POC rights groups obstacles. No individual POC nor right groups data. 
    private Boolean ObstaclesMissingData; 




	
	
	public PersonsOfConcern[] getPersonsOfConcern() {
		return PersonsOfConcern;
	}
	public void setPersonsOfConcern(PersonsOfConcern[] personsOfConcern) {
		PersonsOfConcern = personsOfConcern;
	}
	public RightsGroup[] getRightsGroups() {
		return RightsGroups;
	}
	public void setRightsGroups(RightsGroup[] rightsGroups) {
		RightsGroups = rightsGroups;
	}
	public Boolean getObstaclesMissingData() {
		return ObstaclesMissingData;
	}
	public void setObstaclesMissingData(Boolean obstaclesMissingData) {
		ObstaclesMissingData = obstaclesMissingData;
	}
	public Boolean getNatInstruPOCDataMissing() {
		return NatInstruPOCDataMissing;
	}
	public void setNatInstruPOCDataMissing(Boolean natInstruPOCSDataMissing) {
		NatInstruPOCDataMissing = natInstruPOCSDataMissing;
	}
	public Boolean getConsistencyWithInternationalDataMissing() {
		return ConsistencyWithInternationalDataMissing;
	}
	public void setConsistencyWithInternationalDataMissing(Boolean consistencyWithInternationalDataMissing) {
		ConsistencyWithInternationalDataMissing = consistencyWithInternationalDataMissing;
	}
	public String[][] getObstaclesDocumenation() {
		return obstaclesDocumenation;
	}
	public void setObstaclesDocumenation(String[][] obstaclesDocumenation) {
		this.obstaclesDocumenation = obstaclesDocumenation;
	}

	public String getTradMechComments() {
		return TradMechComments;
	}
	public void setTradMechComments(String tradMechComments) {
		TradMechComments = tradMechComments;
	}
	public String[][] getNatInstruPOCDetail() {
		return NatInstruPOCDetail;
	}
	public void setNatInstruPOCDetail(String[][] natInstruPOCDetail) {
		NatInstruPOCDetail = natInstruPOCDetail;
	}
	public String[] getNatInstruPOCSummary() {
		return NatInstruPOCSummary;
	}
	public void setNatInstruPOCSummary(String[] natInstruPOCSummary) {
		NatInstruPOCSummary = natInstruPOCSummary;
	}
	public String[][] getOtherSubGrps() {
		return OtherSubGrps;
	}
	public void setOtherSubGrps(String[][] otherSubGrps) {
		OtherSubGrps = otherSubGrps;
	}
	public String getConstitutionAmendYesNo() {
		return ConstitutionAmendYesNo;
	}
	public void setConstitutionAmendYesNo(String constitutionAmendYesNo) {
		ConstitutionAmendYesNo = constitutionAmendYesNo;
	}
	public String[][] getConsistencyWithInternational() {
		return ConsistencyWithInternational;
	}
	public void setConsistencyWithInternational(String[][] consistencyWithInternational) {
		ConsistencyWithInternational = consistencyWithInternational;
	}
	public String getAllPOCALLRightsGroupsObtacles() {
		return AllPOCALLRightsGroupsObtacles;
	}
	public void setAllPOCALLRightsGroupsObtacles(String allPOCALLRightsGroupsObtacles) {
		AllPOCALLRightsGroupsObtacles = allPOCALLRightsGroupsObtacles;
	}
	public String[] getPOCALLRightsGroupsObtacles() {
		return POCALLRightsGroupsObtacles;
	}
	public void setPOCALLRightsGroupsObtacles(String[] pOCALLRightsGroupsObtacles) {
		POCALLRightsGroupsObtacles = pOCALLRightsGroupsObtacles;
	}
	public String[] getAllPOCRightsGrouopsObstacles() {
		return AllPOCRightsGrouopsObstacles;
	}
	public void setAllPOCRightsGrouopsObstacles(String[] allPOCRightsGrouopsObstacles) {
		AllPOCRightsGrouopsObstacles = allPOCRightsGrouopsObstacles;
	}
	public String[][] getPOCRightsGrouopsObstacles() {
		return POCRightsGrouopsObstacles;
	}
	public void setPOCRightsGrouopsObstacles(String[][] pOCRightsGrouopsObstacles) {
		POCRightsGrouopsObstacles = pOCRightsGrouopsObstacles;
	}
	public String[][] getPOCObstaclesDetail() {
		return POCObstaclesDetail;
	}
	public void setPOCObstaclesDetail(String[][] pOCObstaclesDetail) {
		POCObstaclesDetail = pOCObstaclesDetail;
	}


	public String[][] getNatlInstruments() {
		return NatlInstruments;
	}
	public void setNatlInstruments(String[][] natlInstruments) {
		NatlInstruments = natlInstruments;
	}
	public String[][] getPOCDifferencesPriorities() {
		return POCDifferencesPriorities;
	}
	public void setPOCDifferencesPriorities(String[][] pOCDifferencesPriorities) {
		POCDifferencesPriorities = pOCDifferencesPriorities;
	}

	public String getPOC() {
		return POC;
	}
	public void setPOC(String pOC) {
		POC = pOC;
	}
	public String[][] getObstacles() {
		return Obstacles;
	}
	public void setObstacles(String[][] obstacles) {
		Obstacles = obstacles;
	}
	public String[][] getIntlInstruments() {
		return IntlInstruments;
	}
	public void setIntlInstruments(String[][] intlInstruments) {
		IntlInstruments = intlInstruments;
	}
	public String getRightsGroup() {
		return RightsGroup;
	}
	public void setRightsGroup(String rightsGroup) {
		RightsGroup = rightsGroup;
	}

	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	public String[][] getTradMech() {
		return TradMech;
	}
	public void setTradMech(String[][] tradMech) {
		TradMech = tradMech;
	}
	public String[][] getAdminEntity() {
		return AdminEntity;
	}
	public void setAdminEntity(String[][] adminEntity) {
		AdminEntity = adminEntity;
	}
	public String[][] getJudicialEntityCourt() {
		return JudicialEntityCourt;
	}
	public void setJudicialEntityCourt(String[][] judicialEntityCourt) {
		JudicialEntityCourt = judicialEntityCourt;
	}
	public String getCommonCivilPlural() {
		return CommonCivilPlural;
	}
	public void setCommonCivilPlural(String commonCivilPlural) {
		CommonCivilPlural = commonCivilPlural;
	}
	public String getPluralText() {
		return PluralText;
	}
	public void setPluralText(String pluralText) {
		PluralText = pluralText;
	}
	public String getFederalState() {
		return FederalState;
	}
	public void setFederalState(String federalState) {
		FederalState = federalState;
	}

	public String[][] getConstAppToPoC() {
		return ConstAppToPoC;
	}
	public void setConstAppToPoC(String[][] constAppToPoC) {
		ConstAppToPoC = constAppToPoC;
	}
	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public String getCountryRegion() {
		return CountryRegion;
	}
	public void setCountryRegion(String countryRegion) {
		CountryRegion = countryRegion;
	}
	public String getConstitutionYesNo() {
		return ConstitutionYesNo;
	}
	public void setConstitutionYesNo(String constitutionYesNo) {
		ConstitutionYesNo = constitutionYesNo;
	}
	public String getConstitutionDate() {
		return ConstitutionDate;
	}
	public void setConstitutionDate(String constitutionDate) {
		ConstitutionDate = constitutionDate;
	}
	public String getConstitutionAmendDate() {
		return ConstitutionAmendDate;
	}
	public void setConstitutionAmendDate(String constitutionAmendDate) {
		ConstitutionAmendDate = constitutionAmendDate;
	}
	public String getConstLinkOrig() {
		return ConstLinkOrig;
	}
	public void setConstLinkOrig(String constLinkOrig) {
		ConstLinkOrig = constLinkOrig;
	}
	public String getConstLinkFrenEng() {
		return ConstLinkFrenEng;
	}
	public void setConstLinkFrenEng(String constLinkFrenEng) {
		ConstLinkFrenEng = constLinkFrenEng;
	}

	public String getBillLinkFrenEng() {
		return BillLinkFrenEng;
	}
	public void setBillLinkFrenEng(String billLinkFrenEng) {
		BillLinkFrenEng = billLinkFrenEng;
	}
	public String getBillLinkOrig() {
		return BillLinkOrig;
	}
	public void setBillLinkOrig(String billLinkOrig) {
		BillLinkOrig = billLinkOrig;
	}
	
	

}