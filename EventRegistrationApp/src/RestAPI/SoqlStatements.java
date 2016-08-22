package RestAPI;

/**
 * Created by Abanoub Wagdy on 8/1/2016.
 */
public class SoqlStatements {

//    private static final String soql_get_active_campaigns = "select Id,IsActive,ActualCost,BudgetedCost,CurrencyIsoCode,CampaignMemberRecordType,NumberOfConvertedLeads,Description,EndDate,ExpectedResponse,ExpectedRevenue,NumberSent,NumberOfOpportunities,NumberOfWonOpportunities,StartDate,Status,HierarchyActualCost,HierarchyBudgetedCost,NumberOfContacts,";

    private static final String soql_get_active_campaigns = "Select Id,Name,Owner.Id,Owner.Name from Campaign Where IsActive=true";
//    private static final String soql_get_filtered_campaign_members =

    public static String getSoqlActiveCampaigns() {
        return soql_get_active_campaigns;
    }

    public static String getSoqlFilteredCampaignMembers(String txtSearchCriteria, String campaignId) {
        return "select Id,FirstName,LeadSource,Phone,LastName,Email from CampaignMember Where (FirstName LIKE " + "'%" + txtSearchCriteria + "%'" + " OR LastName LIKE " + "'%" + txtSearchCriteria + "%'" + " OR Email LIKE " + "'%" + txtSearchCriteria + "%'" + ") AND CampaignId=" + "'" + campaignId + "'";
    }
}