package com.example.andrew.tipcalculator;

public class TipCalculator {
    private float tip;
    private float bill;
    private float guest;

    public TipCalculator(float newTip, float newBill, float newGuest){
        setTip(newTip);
        setBill(newBill);
        setGuest(newGuest);
    }

    public float getTip(){
        return tip;
    }

    public float getBill(){
        return bill;
    }

    public float getGuest() { return guest; }

    public void setTip(float newTip){
        if(newTip > 0)
            tip = newTip;
        if(newTip == 0)
            tip = 0;
    }

    public void setBill(float newBill){
        if(newBill > 0)
            bill = newBill;
    }

    public void setGuest(float newGuest){
        if(newGuest > 0)
            guest = newGuest;
        if(newGuest == 0)
            guest = 1;
    }

    public float tipAmount(){
        return bill * tip;
    }

    public float tipAmountPerGuest() {return (bill * tip) / guest; }

    public float totalAmount(){
        return bill + tipAmount();
    }
}
