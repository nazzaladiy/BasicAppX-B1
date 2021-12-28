package org.aplas.basicappx;

public class Distance {
    private Double meter;

    public Distance() {
        this.meter = 0.0;
    }

    public void setMeter(Double value){
        this.meter = value;
    }

    public double getMeter(){
        return meter;
    }

    public void setInch(Double value){
        this.meter = value/39.3701;
    }

    public double getInch(){
        return meter*39.3701;
    }

    public void setMile(Double value){
        this.meter = value/0.000621371;
    }

    public double getMile(){
        return meter*0.000621371;
    }

    public void setFoot(Double value){
        this.meter = value/3.28084;
    }

    public double getFoot(){
        return meter*3.28084;
    }

    public double convert(String oriUnit, String convUnit, double value){
        if(oriUnit.equals("Mtr")){
            if (convUnit.equals("Inc")){
                return getInch();
            } else if(convUnit.equals("Mil")){
                return getMile();
            } else if(convUnit.equals("Ft")){
                return getFoot();
            } else {
                return getMeter();
            }
        } else if(oriUnit.equals("Inc")){
            if(convUnit.equals("Mtr")) {
                setInch(value);
                return meter;
            } else if(convUnit.equals("Mil")){
                setInch(value);
                return getMile();
            } else if(convUnit.equals("Ft")){
                setInch(value);
                return getFoot();
            } else {
                return value;
            }
        } else if(oriUnit.equals("Mil")){
            if(convUnit.equals("Mtr")){
                setMile(value);
                return meter;
            } else if(convUnit.equals("Inc")){
                setMile(value);
                return getInch();
            } else if(convUnit.equals("Ft")){
                setMile(value);
                return getFoot();
            } else{
                return value;
            }
        } else if(oriUnit.equals("Ft")){
            if(convUnit.equals("Mtr")){
                setFoot(value);
                return meter;
            } else if(convUnit.equals("Inc")){
                setFoot(value);
                return getInch();
            } else if(convUnit.equals("Mil")){
                setFoot(value);
                return getMile();
            } else {
                return value;
            }
        } else {
            return meter;
        }
    }
}
