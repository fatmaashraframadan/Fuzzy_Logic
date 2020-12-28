package com.company;

import java.util.ArrayList;

public class FuzzySet {
    String name;
    ArrayList<point> setDimension;
    Double memberShip;

    /// Generic fuzzy set
    FuzzySet(String name, ArrayList<Double> setValues){
        this.name = name;
        setDimension = new ArrayList<>();
        point p;
        p = new point(setValues.get(0), 0.0);
        setDimension.add(p);
        for(int y=1; y<setValues.size()-1; y++){
            p = new point(setValues.get(y), 1.0);
            setDimension.add(p);
        }
        p = new point(setValues.get(setValues.size()-1), 0.0);
        setDimension.add(p);

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<point> getSetDimension() {
        return setDimension;
    }
    public void setSetDimension(ArrayList<point> setDimension) {
        this.setDimension = setDimension;
    }

    public Double getMemberShip() {
        return memberShip;
    }
    public void setMemberShip(Double memberShip) {
        this.memberShip = memberShip;
    }
}
