package org.personal.salportfoliobackend.domain;

import java.util.ArrayList;
import java.util.List;

public class User extends CommonDomain {
    
    private String firstName;
    private String middleInitial;
    private String lastName;
    private List<Asset> assets = new ArrayList<Asset>();
    
    public User () {};
    
    public User (String id, String firstName, String middleInitial, String lastName) {
        setId(id);
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
    }
    
    public String getFirstName () {
        return firstName;
    }
    
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
    
    public String getMiddleInitial () {
        return middleInitial;
    }
    
    public void setMiddleInitial (String middleInitial) {
        this.middleInitial = middleInitial;
    }
    
    public String getLastName () {
        return lastName;
    }
    
    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
    
    public void addAsset(Asset asset) {
        assets.add(asset);
    }
}
