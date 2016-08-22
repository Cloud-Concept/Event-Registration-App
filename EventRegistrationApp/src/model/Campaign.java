package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bibo on 12/27/15.
 */
public class Campaign {

    @SerializedName("Id")
    private String ID;

    @SerializedName("IsActive")
    private String IsActive;

    @SerializedName("ActualCost")
    private String ActualCost;

    @SerializedName("BudgetedCost")
    private String BudgetedCost;

    @SerializedName("CurrencyIsoCode")
    private String CurrencyIsoCode;
    @SerializedName("CampaignMemberRecordType")
    private String CampaignMemberRecordType;

    @SerializedName("Name")
    private String Name;

    @SerializedName("NumberOfConvertedLeads")
    private int NumberOfConvertedLeads;

    @SerializedName("Description")
    private String Description;

    private User Owner;

    @SerializedName("EndDate")
    private String EndDate;

    @SerializedName("ExpectedResponse")
    private String ExpectedResponse;

    @SerializedName("ExpectedRevenue")
    private String ExpectedRevenue;
    @SerializedName("NumberSent")
    private String NumberSent;

    @SerializedName("NumberOfOpportunities")
    private String NumberOfOpportunities;
    @SerializedName("NumberOfWonOpportunities")
    private String NumberOfWonOpportunities;
    @SerializedName("StartDate")
    private String StartDate;
    @SerializedName("Status")
    private String Status;
    @SerializedName("HierarchyActualCost")
    private String HierarchyActualCost;
    @SerializedName("HierarchyBudgetedCost")
    private String HierarchyBudgetedCost;
    @SerializedName("NumberOfContacts")
    private String NumberOfContacts;
    @SerializedName("HierarchyNumberOfContacts")
    private String HierarchyNumberOfContacts;
    @SerializedName("HierarchyNumberOfConvertedLeads")
    private String HierarchyNumberOfConvertedLeads;
    @SerializedName("HierarchyExpectedRevenue")

    private String HierarchyExpectedRevenue;
    @SerializedName("NumberOfLeads")
    private String NumberOfLeads;
    @SerializedName("HierarchyNumberOfLeads")
    private String HierarchyNumberOfLeads;

    @SerializedName("HierarchyNumberSent")
    private String HierarchyNumberSent;
    @SerializedName("HierarchyNumberOfOpportunities")
    private String HierarchyNumberOfOpportunities;
    @SerializedName("NumberOfResponses")
    private String NumberOfResponses;

    @SerializedName("HierarchyNumberOfResponses")
    private String HierarchyNumberOfResponses;
    @SerializedName("AmountAllOpportunities")
    private String AmountAllOpportunities;
    @SerializedName("HierarchyAmountAllOpportunities")
    private String HierarchyAmountAllOpportunities;

    @SerializedName("AmountWonOpportunities")
    private String AmountWonOpportunities;
    @SerializedName("HierarchyAmountWonOpportunities")
    private String HierarchyAmountWonOpportunities;
    @SerializedName("HierarchyNumberOfWonOpportunities")
    private String HierarchyNumberOfWonOpportunities;

    @SerializedName("Type")
    private String Type;

    @SerializedName("Data_Quality_Description__c")
    private String Data_Quality_Description;

    @SerializedName("Data_Quality_Score__c")
    private String Data_Quality_Score;

    @SerializedName("Product__c")
    private String Product;


    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getActualCost() {
        return ActualCost;
    }

    public void setActualCost(String actualCost) {
        ActualCost = actualCost;
    }

    public String getBudgetedCost() {
        return BudgetedCost;
    }

    public void setBudgetedCost(String budgetedCost) {
        BudgetedCost = budgetedCost;
    }

    public String getExpectedResponse() {
        return ExpectedResponse;
    }

    public void setExpectedResponse(String expectedResponse) {
        ExpectedResponse = expectedResponse;
    }

    public String getExpectedRevenue() {
        return ExpectedRevenue;
    }

    public void setExpectedRevenue(String expectedRevenue) {
        ExpectedRevenue = expectedRevenue;
    }

    public String getNumberSent() {
        return NumberSent;
    }

    public void setNumberSent(String numberSent) {
        NumberSent = numberSent;
    }

    public String getNumberOfOpportunities() {
        return NumberOfOpportunities;
    }

    public void setNumberOfOpportunities(String numberOfOpportunities) {
        NumberOfOpportunities = numberOfOpportunities;
    }

    public String getNumberOfWonOpportunities() {
        return NumberOfWonOpportunities;
    }

    public void setNumberOfWonOpportunities(String numberOfWonOpportunities) {
        NumberOfWonOpportunities = numberOfWonOpportunities;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getHierarchyActualCost() {
        return HierarchyActualCost;
    }

    public void setHierarchyActualCost(String hierarchyActualCost) {
        HierarchyActualCost = hierarchyActualCost;
    }

    public String getHierarchyBudgetedCost() {
        return HierarchyBudgetedCost;
    }

    public void setHierarchyBudgetedCost(String hierarchyBudgetedCost) {
        HierarchyBudgetedCost = hierarchyBudgetedCost;
    }

    public String getNumberOfContacts() {
        return NumberOfContacts;
    }

    public void setNumberOfContacts(String numberOfContacts) {
        NumberOfContacts = numberOfContacts;
    }

    public String getHierarchyNumberOfContacts() {
        return HierarchyNumberOfContacts;
    }

    public void setHierarchyNumberOfContacts(String hierarchyNumberOfContacts) {
        HierarchyNumberOfContacts = hierarchyNumberOfContacts;
    }

    public String getHierarchyNumberOfConvertedLeads() {
        return HierarchyNumberOfConvertedLeads;
    }

    public void setHierarchyNumberOfConvertedLeads(String hierarchyNumberOfConvertedLeads) {
        HierarchyNumberOfConvertedLeads = hierarchyNumberOfConvertedLeads;
    }

    public String getCurrencyIsoCode() {
        return CurrencyIsoCode;
    }

    public void setCurrencyIsoCode(String currencyIsoCode) {
        CurrencyIsoCode = currencyIsoCode;
    }

    public String getCampaignMemberRecordType() {
        return CampaignMemberRecordType;
    }

    public void setCampaignMemberRecordType(String campaignMemberRecordType) {
        CampaignMemberRecordType = campaignMemberRecordType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumberOfConvertedLeads() {
        return NumberOfConvertedLeads;
    }

    public void setNumberOfConvertedLeads(int numberOfConvertedLeads) {
        NumberOfConvertedLeads = numberOfConvertedLeads;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getHierarchyExpectedRevenue() {
        return HierarchyExpectedRevenue;
    }

    public void setHierarchyExpectedRevenue(String hierarchyExpectedRevenue) {
        HierarchyExpectedRevenue = hierarchyExpectedRevenue;
    }

    public String getNumberOfLeads() {
        return NumberOfLeads;
    }

    public void setNumberOfLeads(String numberOfLeads) {
        NumberOfLeads = numberOfLeads;
    }

    public String getHierarchyNumberOfLeads() {
        return HierarchyNumberOfLeads;
    }

    public void setHierarchyNumberOfLeads(String hierarchyNumberOfLeads) {
        HierarchyNumberOfLeads = hierarchyNumberOfLeads;
    }

    public String getHierarchyNumberSent() {
        return HierarchyNumberSent;
    }

    public void setHierarchyNumberSent(String hierarchyNumberSent) {
        HierarchyNumberSent = hierarchyNumberSent;
    }

    public String getHierarchyNumberOfOpportunities() {
        return HierarchyNumberOfOpportunities;
    }

    public void setHierarchyNumberOfOpportunities(String hierarchyNumberOfOpportunities) {
        HierarchyNumberOfOpportunities = hierarchyNumberOfOpportunities;
    }

    public String getNumberOfResponses() {
        return NumberOfResponses;
    }

    public void setNumberOfResponses(String numberOfResponses) {
        NumberOfResponses = numberOfResponses;
    }

    public String getHierarchyNumberOfResponses() {
        return HierarchyNumberOfResponses;
    }

    public void setHierarchyNumberOfResponses(String hierarchyNumberOfResponses) {
        HierarchyNumberOfResponses = hierarchyNumberOfResponses;
    }

    public String getAmountAllOpportunities() {
        return AmountAllOpportunities;
    }

    public void setAmountAllOpportunities(String amountAllOpportunities) {
        AmountAllOpportunities = amountAllOpportunities;
    }

    public String getHierarchyAmountAllOpportunities() {
        return HierarchyAmountAllOpportunities;
    }

    public void setHierarchyAmountAllOpportunities(String hierarchyAmountAllOpportunities) {
        HierarchyAmountAllOpportunities = hierarchyAmountAllOpportunities;
    }

    public String getAmountWonOpportunities() {
        return AmountWonOpportunities;
    }

    public void setAmountWonOpportunities(String amountWonOpportunities) {
        AmountWonOpportunities = amountWonOpportunities;
    }

    public String getHierarchyAmountWonOpportunities() {
        return HierarchyAmountWonOpportunities;
    }

    public void setHierarchyAmountWonOpportunities(String hierarchyAmountWonOpportunities) {
        HierarchyAmountWonOpportunities = hierarchyAmountWonOpportunities;
    }

    public String getHierarchyNumberOfWonOpportunities() {
        return HierarchyNumberOfWonOpportunities;
    }

    public void setHierarchyNumberOfWonOpportunities(String hierarchyNumberOfWonOpportunities) {
        HierarchyNumberOfWonOpportunities = hierarchyNumberOfWonOpportunities;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getData_Quality_Description() {
        return Data_Quality_Description;
    }

    public void setData_Quality_Description(String data_Quality_Description) {
        Data_Quality_Description = data_Quality_Description;
    }

    public String getData_Quality_Score() {
        return Data_Quality_Score;
    }

    public void setData_Quality_Score(String data_Quality_Score) {
        Data_Quality_Score = data_Quality_Score;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) {
        Owner = owner;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
