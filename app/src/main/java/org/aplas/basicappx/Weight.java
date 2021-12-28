package org.aplas.basicappx;

public class Weight {
    private Double gram;

    public Weight() {
        this.gram = 0.0;
    }

    public void setGram(double value){
        this.gram = value;
    }

    public double getGram(){
        return gram;
    }

    public void setOunce(double value){
        this.gram = value*28.3495231;
    }

    public double getOunce(){
        return gram/28.3495231;
    }

    public void setPound(double value){
        this.gram = value*453.59237;
    }

    public  double getPound(){
        return gram/453.59237;
    }

    public double convert(String oriUnit, String convUnit, double value) {
        if (oriUnit.equals("Grm")) {
            if (convUnit.equals("Onc")) {
                return getOunce();
            } else if (convUnit.equals("Pnd")) {
                return getPound();
            } else {
                return gram;
            }
        } else if (oriUnit.equals("Onc")) {
            if (convUnit.equals("Grm")) {
                setOunce(value);
                return gram;
            } else if (convUnit.equals("Pnd")) {
                setOunce(value);
                return getPound();
            } else {
                return value;
            }
        } else if (oriUnit.equals("Pnd")) {
            if (convUnit.equals("Grm")) {
                setPound(value);
                return gram;
            } else if (convUnit.equals("Onc")) {
                setPound(value);
                return getOunce();
            } else {
                return value;
            }
        } else {
            return gram;
        }
    }
}
