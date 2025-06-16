package com.ps.models;

import java.time.LocalDate;

public class SalesContract {
    private int contractId;
    private LocalDate contractDate;
    private String customerName;
    private String customerEmail;
    private double totalPrice;
    private String financed;
    private String vin;

    public SalesContract(int contractId, LocalDate contractDate, String customerName,
                         String customerEmail, double totalPrice, String financed, String vin) {
        this.contractId = contractId;
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.totalPrice = totalPrice;
        this.financed = financed;
        this.vin = vin;
    }

    public SalesContract(LocalDate contractDate, String customerName,
                         String customerEmail, double totalPrice, String financed, String vin) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.totalPrice = totalPrice;
        this.financed = financed;
        this.vin = vin;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFinanced() {
        return financed;
    }

    public void setFinanced(String financed) {
        this.financed = financed;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%.2f,%s,%s\n",
                contractId,
                contractDate,
                customerName,
                customerEmail,
                totalPrice,
                financed,
                vin
        );
    }
}
