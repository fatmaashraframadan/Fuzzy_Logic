package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FuzzyLogicToolBox {
    ArrayList<Variables> fuzzyVariables;
    ArrayList<FuzzyRules> fuzzyRules;
    ArrayList<Double> ruleInference;

    FuzzyLogicToolBox(){
        fuzzyVariables = new ArrayList<>();
        fuzzyRules = new ArrayList<>();
    }
    void fuzzification(){
        for(Variables v: fuzzyVariables){
            if(v.getCrispValue() != null){
                v.calculateMembership();
            }
        }
        for(Variables v : fuzzyVariables){
            System.out.println(v.getName() + " memberships:");
            v.printMembership();
        }
    }
    void inference(){
        ruleInference = new ArrayList<>();
        for(FuzzyRules R: fuzzyRules){
            ArrayList<Double> result = new ArrayList<>();
            for(int i=0; i<R.LinguisticVariables.size()-1; i++){
                Double val1=0.0;
                Variables v1 = getByName(R.LinguisticVariables.get(i));
                for(FuzzySet s : v1.getFuzzySet()){
                    if(s.getName().equals(R.LinguisticValues.get(i))) {
                        val1 = s.getMemberShip();
                    }
                }
                result.add(val1);
            }
            int x=0;
            Double temp=0.0;
            for(int y=0; y<R.controlRule.size() && x<result.size(); y++){
                if(R.controlRule.get(y).equals("or")){
                    temp = Math.max(result.get(x++), result.get(x++));
                }
                else if(R.controlRule.get(y).equals("and")){
                    temp = Math.min(result.get(x++), result.get(x++));
                }
                else if(R.controlRule.get(y).equals("not")){
                    temp = Math.min(result.get(x++), result.get(x++));
                }
            }
            ruleInference.add(temp);
        }
        System.out.println("Rules inference values : ");
        for(int u=0; u<ruleInference.size(); u++){
            System.out.println("R"+ u+1 + ": " + ruleInference.get(u));
        }
    }


    void defuzzification(){

    }

    //input number of variables with crisp values and their values
    public void inputData(){
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

    Variables getByName(String name){
        for(Variables v: fuzzyVariables){
            if(v.getName().equals(name))
                return v;
        }
        return null;
    }
    public void addVariable(Variables variable){
        fuzzyVariables.add(variable);
    }
    public void addRule(String Rule){
        ArrayList<String> ruleVariables = new ArrayList<>();
        ArrayList<String> ruleValues = new ArrayList<>();
        ArrayList<String> controlRule = new ArrayList<>();
        FuzzyRules rule;
        String splitted[] = Rule.split(" ");

        for(int i=0; i<splitted.length; i++){
            // if variable then variable
            if(splitted[i].equals("if") || splitted[i].equals("then")){
                ruleVariables.add(splitted[i+1]);
                i++;
            }
            // variable is value
            else if (splitted[i].equals("is")){
                if(splitted[i+1].contains(".")) splitted[i+1] = splitted[i+1].substring(0,splitted[i+1].length()-1);
                ruleValues.add(splitted[i+1]);
                i++;
            }
            //or/and variable
            else {
                controlRule.add(splitted[i]);
                ruleVariables.add(splitted[i+1]);
                i++;
            }
        }
        if(controlRule.isEmpty()) {
            rule = new FuzzyRules(ruleVariables, ruleValues);
        }
        else {
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
