package com.ps.models;

import java.time.LocalDate;

public class LeaseContract {
    private int contractId;
    private LocalDate contractDate;
    private String customerName;
    private String customerEmail;
    private double monthlyPayment;
    private int leasingPeriod;
    private LocalDate startDate;
    private LocalDate endDate;
    private String vin;

    public LeaseContract(int contractId, LocalDate contractDate, String customerName, String customerEmail,
                         double monthlyPayment, int leasingPeriod, LocalDate startDate, LocalDate endDate, String vin) {
        this.contractId = contractId;
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.monthlyPayment = monthlyPayment;
        this.leasingPeriod = leasingPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int getLeasingPeriod() {
        return leasingPeriod;
    }

    public void setLeasingPeriod(int leasingPeriod) {
        this.leasingPeriod = leasingPeriod;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%.2f,%d,%s,%s,%s\n",
                contractId,
                contractDate,
                customerName,
                customerEmail,
                monthlyPayment,
                leasingPeriod,
                startDate,
                endDate,
                vin
        );
    }
}
