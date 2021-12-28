package org.aplas.basicappx;

public class Temperature {
    private Double celcius;

    public Temperature() {
        this.celcius = 0.0;
    }

    public void setCelcius(Double value) {
        this.celcius = value;
    }

    public Double getCelcius() {
        return celcius;
    }

    public void setFahrenheit(Double value){
        this.celcius = (value-32)/9*5;
    }

    public double getFahrenheit(){
        return celcius*9/5+32;
    }

    public void setKelvins(Double value){
        this.celcius = value - 273.15;
    }

    public double getKelvins(){
        return celcius+273.15;
    }

    public double convert(String oriUnit, String convUnit, double value){
        if(oriUnit.equals("°C")){
            if (convUnit.equals("°F")){
                return getFahrenheit();
            } else if(convUnit.equals("K")){
                return getKelvins();
            } else {
                return celcius;
            }
        } else if(oriUnit.equals("°F")){
            if(convUnit.equals("°C")) {
                setFahrenheit(value);
                return celcius;
            } else if(convUnit.equals("K")){
                setFahrenheit(value);
                return getKelvins();
            } else {
                return value;
            }
        } else if(oriUnit.equals("K")){
            if(convUnit.equals("°C")){
                setKelvins(value);
                return celcius;
            } else if(convUnit.equals("°F")){
                setKelvins(value);
                return getFahrenheit();
            } else {
                return value;
            }
        } else {
            return celcius;
        }
    }
}
