package com.company;

import javafx.util.Pair;

import java.util.ArrayList;

public class FuzzyRules {
    ArrayList<String> LinguisticVariables;
    ArrayList<Pair<String,Boolean>> LinguisticValues;
    ArrayList<String> controlRule;
    //For rules with Linguistic Vars, Vals, and Control
    FuzzyRules(ArrayList<String> Variables,ArrayList<Pair<String,Boolean>> Values, ArrayList<String> control){
        LinguisticVariables = new ArrayList<>();
        LinguisticVariables.addAll(Variables);

        LinguisticValues = new ArrayList<>();
        LinguisticValues.addAll(Values);

        controlRule = new ArrayList<>();
        controlRule.addAll(control);
    }

    //For rules with Linguistic Vars, Vals (one variable only)
    FuzzyRules(ArrayList<String> Variables, ArrayList<Pair<String,Boolean>> Values){
        LinguisticVariables = new ArrayList<>();
        LinguisticVariables.addAll(Variables);

        LinguisticValues = new ArrayList<>();
        LinguisticValues.addAll(Values);
    }


    public ArrayList<String> getLinguisticVariables() {
        return LinguisticVariables;
    }
    public void setLinguisticVariables(ArrayList<String> linguisticVariables) {
        LinguisticVariables = linguisticVariables;
    }

    public ArrayList<Pair<String,Boolean>> getLinguisticValues() {
        return LinguisticValues;
    }
    public void setLinguisticValues(ArrayList<Pair<String,Boolean>> linguisticValues) {
        LinguisticValues = linguisticValues;
    }

    public ArrayList<String> getControlRule() {
        return controlRule;
    }
    public void setControlRule(ArrayList<String> controlRule) {
        this.controlRule = controlRule;
    }
}
