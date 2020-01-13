package org.personal.salportfoliobackend.domain;

public class Asset extends CommonDomain {

    private String make;
    private String model;
    private String serialNumber;
    
    public Asset () {};
    
    public Asset (String id, String make, String model, String serialNumber) {
        setId(id);
        this.make = make;
        this.model = model;
        this.serialNumber = serialNumber;
    }
    
    public String getMake() {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
