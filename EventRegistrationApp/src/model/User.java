package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Abanoub Wagdy on 8/1/2016.
 */
public class User {
    @SerializedName("Id")
    private String Id;
    @SerializedName("Name")
    private String Name;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
