package com.company;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FuzzyLogicToolBox {
    ArrayList<Variables> fuzzyVariables;
    ArrayList<FuzzyRules> fuzzyRules;//All existing rules.
    ArrayList<Double> ruleInference;

    FuzzyLogicToolBox() {
        fuzzyVariables = new ArrayList<>();
        fuzzyRules = new ArrayList<>();
    }

    void fuzzification() {
        System.out.println("1.Fuzzification Result : ");
        for (Variables v : fuzzyVariables) {
            if (v.getCrispValue() != null) {
                v.calculateMembership();
            }
        }
        for (Variables v : fuzzyVariables) {
            System.out.println(v.getName() + " memberships:");
            v.printMembership();
            System.out.println("\n");
        }
    }

    void inference() {
        System.out.println("2.Inference Result : ");
        ruleInference = new ArrayList<>();
        int it = 1;
        for (FuzzyRules R : fuzzyRules) {
            ArrayList<Double> result = new ArrayList<>();
            for (int i = 0; i < R.LinguisticVariables.size() - 1; i++) {
                Double val1 = 0.0;
                Variables v1 = getByName(R.LinguisticVariables.get(i));
                for (FuzzySet s : v1.getFuzzySet()) {
                    if (s.getName().equals(R.LinguisticValues.get(i).getKey())) {
                        if (R.LinguisticValues.get(i).getValue() == true) val1 = 1.0 - s.getMemberShip();
                        else val1 = s.getMemberShip();
                        break;
                    }
                }
                result.add(val1);
            }
            int x = 0;
            double temp = 0.0;
            if (R.controlRule != null) {
                for (int y = 0; y < R.controlRule.size(); y++) {
                    if ("and".equals(R.controlRule.get(y))) {
                        temp = Math.min(result.get(x++), result.get(x));
                        result.set(x, temp);
                    } else if ("or".equals(R.controlRule.get(y))) {
                        temp = Math.max(result.get(x++), result.get(x));
                        result.set(x, temp);
                    }
                }
            }
            Variables v = getByName(R.LinguisticVariables.get(R.LinguisticVariables.size() - 1));
            for (FuzzySet s : v.getFuzzySet()) {
                if (s.getName().equals(R.LinguisticValues.get(R.LinguisticValues.size() - 1).getKey())) {
                    if (s.getMemberShip() == null) s.setMemberShip(temp);
                    else s.setMemberShip(Math.max(s.getMemberShip(), temp));
                    break;
                }
            }
            System.out.println("Rule " + (it++) + " : " + temp);
        }
        System.out.println("\nRules inference values : ");
        Variables v = fuzzyVariables.get(fuzzyVariables.size() - 1);
        System.out.println(v.getName() + " memberships:");
        v.printMembership();
    }


    void defuzzification() {
        System.out.println("3.De-Fuzzification Result : ");
        HashMap<FuzzySet, Double> centroids = new HashMap<>();
        Variables cost = fuzzyVariables.get(fuzzyVariables.size() - 1);
        FuzzySet maximum = cost.fuzzySet.get(0);

        for (FuzzySet s : cost.getFuzzySet()) {
            if (s.memberShip > maximum.memberShip) maximum = s;
            centroids.put(s, calcCentroid(s));
        }
        System.out.println("\nPredicted Value: " + equation(centroids));
        System.out.println(cost.getName() + " will be " + maximum.getName());
    }

    Double equation(HashMap<FuzzySet, Double> centroids) {
        Double sum = 0.0, Denomi = 0.0;
        //  Variables output = fuzzyVariables.get(fuzzyVariables.size() - 1);
        for (HashMap.Entry<FuzzySet, Double> me : centroids.entrySet()) {
            sum += me.getValue() * me.getKey().memberShip;
            Denomi += me.getKey().memberShip;
        }
        return sum / Denomi;
    }

    Double calcCentroid(FuzzySet set) {
        Double Area = A(set);
        Double sum = 0.0;

        ArrayList<point> Dimentions = new ArrayList<point>();
        Dimentions = set.getSetDimension();//Current fuzzyset dimentions.
        for (int i = 0; i < Dimentions.size() - 1; i++) {
            Double x = Dimentions.get(i + 1).getX() + Dimentions.get(i).getX();
            Double all = (Dimentions.get(i).getX() * Dimentions.get(i + 1).getY()) - (Dimentions.get(i + 1).getX() * Dimentions.get(i).getY());
            sum += x * all;
        }

        return (1.0 / (6.0 * Area)) * sum;
    }

    //A (Signed Area).
    Double A(FuzzySet set) {
        Double res = 0.0;
        ArrayList<point> Dimentions = new ArrayList<point>();
        Dimentions = set.getSetDimension();//Current fuzzyset dimentions.

        for (int i = 0; i < Dimentions.size() - 1; i++) {
            res += (Dimentions.get(i).getX() * Dimentions.get(i + 1).getY()) - (Dimentions.get(i + 1).getX() * Dimentions.get(i).getY());
        }
        return (0.5) * res;
    }

    //input number of variables with crisp values and their values
    public void inputData() {
        int nVariables;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter number of variables ");
        nVariables = scanner.nextInt();
        System.out.println("enter variable name and Crisp value (separated by space)");
        String name;
        for (int x = 0; x < nVariables; x++) {
            name = scanner.next();
            Variables v = getByName(name);
            v.setCrispValue(scanner.nextDouble());
            //System.out.println(v.getName() + v.getCrispValue());
        }
        fuzzification();
        inference();
        defuzzification();
    }

    Variables getByName(String name) {
        for (Variables v : fuzzyVariables) {
            if (v.getName().equals(name))
                return v;
        }
        return null;
    }

    public void addVariable(Variables variable) {
        fuzzyVariables.add(variable);
    }

    public void addRule(String Rule) {
        ArrayList<String> ruleVariables = new ArrayList<>();
        // values = new Pair<String,Boolean>();
        ArrayList<Pair<String, Boolean>> ruleValues = new ArrayList<>();
        ArrayList<String> controlRule = new ArrayList<>();
        FuzzyRules rule;
        String splitted[] = Rule.split(" ");

        for (int i = 0; i < splitted.length; i++) {
            // if variable
            // then variable
            if (splitted[i].equals("if") || splitted[i].equals("If") || splitted[i].equals("then")) {
                ruleVariables.add(splitted[i + 1]);
                i++;
            }
            // variable is value
            // variable is not value
            else if (splitted[i].equals("is")) {
                if (splitted[i + 1].contains("."))
                    splitted[i + 1] = splitted[i + 1].substring(0, splitted[i + 1].length() - 1);
                if (splitted[i + 1].equals("not")) {
                    // controlRule.add(splitted[i+1]);
                    ruleValues.add(new Pair(splitted[i + 2], true));
                    i++;
                } else {
                    ruleValues.add(new Pair(splitted[i + 1], false));
                    // ruleValues.add(splitted[i + 1]);
                }
                i++;
            }
            //or/and variable
            else {
                controlRule.add(splitted[i]);
                ruleVariables.add(splitted[i + 1]);
                i++;
            }
        }
        if (controlRule.isEmpty()) {
            rule = new FuzzyRules(ruleVariables, ruleValues);
        } else {
            rule = new FuzzyRules(ruleVariables, ruleValues, controlRule);
        }
        fuzzyRules.add(rule);
//        for(String s: ruleVariables){
//            System.out.println(s);
//        }
//        for(String s: ruleValues){
//            System.out.println(s);
//        }
//        for(String s: controlRule){
//            System.out.println(s);
//        }
    }
}
