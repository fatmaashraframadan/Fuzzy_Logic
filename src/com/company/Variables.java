package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Variables {
    String name;
    ArrayList<FuzzySet> fuzzySet;
    Double CrispValue;

    public Variables(String name) {
        this.name = name;
        fuzzySet = new ArrayList<>();
        CrispValue = null;
    }

    public void addFuzzySet(String setName, ArrayList<Double> SetVal){
        FuzzySet set = new FuzzySet(setName, SetVal);
        fuzzySet.add(set);
    }

    public void calculateMembership(){
        for(FuzzySet f: fuzzySet){
            ArrayList<point> points = getClosest(f.setDimension);
            if(points.isEmpty()) f.memberShip = 0.0;
            else {
                Double s = slope(points.get(0), points.get(1));
                Double y = lineEqu(s, points.get(0));
                f.memberShip = y;
            }
        }
    }
    ArrayList<point> getClosest(ArrayList<point> points){
        ArrayList<point> closest = new ArrayList<>();
        for(int i=0; i< points.size()-1; i++){
            if(points.get(i).getX() <= CrispValue && points.get(i+1).getX()>= CrispValue){
                closest.add(points.get(i));
                closest.add(points.get(i+1));
            }
        }
        return closest;
    }
    Double slope(point a, point b){
        return (a.y - b.y)/(a.x - b.x);
    }
    Double lineEqu(Double m, point p){
        // y = mx + c (get c)
        // get y
        Double c = p.getY() - (p.getX()*m);
        Double y = (m*CrispValue) + c;
        return y;
    }

    public void printMembership(){
        for(FuzzySet s : fuzzySet){
            System.out.println(s.getName() + " crisp membership: " + s.getMemberShip());
        }
    }

    FuzzySet getByName(String name){
        for(FuzzySet v: fuzzySet){
            if(v.getName().equals(name))
                return v;
        }
        return null;
    }

    public Double getCrispValue() {
        return CrispValue;
    }
    public void setCrispValue(Double crispValue) {
        CrispValue = crispValue;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<FuzzySet> getFuzzySet() {
        return fuzzySet;
    }
    public void setFuzzySet(ArrayList<FuzzySet> fuzzySet) {
        this.fuzzySet = fuzzySet;
    }
}
