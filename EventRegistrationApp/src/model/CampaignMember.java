package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Abanoub Wagdy on 1/17/2016.
 */
public class CampaignMember implements Serializable{

    @SerializedName("Id")
    private String ID;
    @SerializedName("FirstName")
    private String FirstName;
    @SerializedName("LastName")
    private String LastName;
    @SerializedName("Email")
    private String Email;
    @SerializedName("Phone")
    private String Phone;
    @SerializedName("LeadSource")
    private String LeadSource;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLeadSource() {
        return LeadSource;
    }

    public void setLeadSource(String leadSource) {
        LeadSource = leadSource;
    }
}
