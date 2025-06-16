package com.ps.models;

public class Dealership {
    private int dealershipId;
    private String dealershipName;
    private String address;
    private String phone;

    public Dealership(int dealershipId, String dealershipName, String address, String phone) {
        this.dealershipId = dealershipId;
        this.dealershipName = dealershipName;
        this.address = address;
        this.phone = phone;
    }

    public int getDealershipId() {
        return dealershipId;
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public String getDealershipName() {
        return dealershipName;
    }

    public void setDealershipName(String dealershipName) {
        this.dealershipName = dealershipName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s\n",
                dealershipId,
                dealershipName,
                address,
                phone
        );
    }
}
