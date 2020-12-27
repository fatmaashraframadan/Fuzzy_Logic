package com.company;

import java.util.ArrayList;

public class FuzzyRules {
    ArrayList<String> LinguisticVariables;
    ArrayList<String> LinguisticValues;
    ArrayList<String> controlRule;

    //For rules with Linguistic Vars, Vals, and Control
    FuzzyRules(ArrayList<String> Variables, ArrayList<String> Values, ArrayList<String> control){
        LinguisticVariables = new ArrayList<>();
        LinguisticVariables.addAll(Variables);

        LinguisticValues = new ArrayList<>();
        LinguisticValues.addAll(Values);

        controlRule = new ArrayList<>();
        controlRule.addAll(control);
    }

    //For rules with Linguistic Vars, Vals (one variable only)
    FuzzyRules(ArrayList<String> Variables, ArrayList<String> Values){
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

    public ArrayList<String> getLinguisticValues() {
        return LinguisticValues;
    }
    public void setLinguisticValues(ArrayList<String> linguisticValues) {
        LinguisticValues = linguisticValues;
    }

    public ArrayList<String> getControlRule() {
        return controlRule;
    }
    public void setControlRule(ArrayList<String> controlRule) {
        this.controlRule = controlRule;
    }
}
