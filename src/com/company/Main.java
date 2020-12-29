package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
//         FuzzyLogicToolBox fuzzyLogicToolBox = new FuzzyLogicToolBox();
//
//        Variables project = new Variables("dirt");
//        ArrayList<Double> set = new ArrayList<>(Arrays.asList(0.0, 0.0, 20.0, 40.0));
//        project.addFuzzySet("small", set);
//        set = new ArrayList<>(Arrays.asList(20.0,40.0, 60.0, 80.0));
//        project.addFuzzySet("medium", set);
//        set = new ArrayList<>(Arrays.asList(60.0, 80.0, 100.0, 100.0));
//        project.addFuzzySet("large", set);
//        fuzzyLogicToolBox.addVariable(project);
//
//        project = new Variables("fabric");
//        set = new ArrayList<>(Arrays.asList(0.0, 0.0, 20.0, 40.0));
//        project.addFuzzySet("soft", set);
//        set = new ArrayList<>(Arrays.asList(20.0,40.0, 60.0, 80.0));
//        project.addFuzzySet("ordinary", set);
//        set = new ArrayList<>(Arrays.asList(60.0, 80.0, 100.0, 100.0));
//        project.addFuzzySet("stiff", set);
//        fuzzyLogicToolBox.addVariable(project);
//
//        project = new Variables("time");
//        set = new ArrayList<>(Arrays.asList(0.0, 0.0, 15.0));
//        project.addFuzzySet("very_short", set);
//        set = new ArrayList<>(Arrays.asList(0.0,15.0,30.0));
//        project.addFuzzySet("short", set);
//        set = new ArrayList<>(Arrays.asList(15.0,30.0,45.0));
//        project.addFuzzySet("standard", set);
//        set = new ArrayList<>(Arrays.asList(30.0,45.0,60.0));
//        project.addFuzzySet("long", set);
//        set = new ArrayList<>(Arrays.asList(45.0,60.0,60.0));
//        project.addFuzzySet("very_long", set);
//        fuzzyLogicToolBox.addVariable(project);
//
//        fuzzyLogicToolBox.addRule("If dirt is small and fabric is soft then time is very_short");
//        fuzzyLogicToolBox.addRule("If dirt is medium and fabric is ordinary then time is standard");
//        fuzzyLogicToolBox.addRule("If dirt is small and fabric is not soft or dirt is medium and fabric is soft then time is short.");
//        fuzzyLogicToolBox.addRule("If dirt is medium and fabric is stiff then time is long");
//        fuzzyLogicToolBox.addRule("If dirt is large and fabric is not soft then time is very_long.");
//        fuzzyLogicToolBox.addRule("If dirt is large and fabric is soft then time is standard.");

        
        FuzzyLogicToolBox fuzzyLogicToolBox = new FuzzyLogicToolBox();

        Variables project = new Variables("Project_Funding");
        ArrayList<Double> set = new ArrayList<>(Arrays.asList(0.0, 0.0, 10.0, 30.0));
        project.addFuzzySet("VL", set);
        set = new ArrayList<>(Arrays.asList(10.0,30.0, 40.0, 60.0));
        project.addFuzzySet("L", set);
        set = new ArrayList<>(Arrays.asList(40.0, 60.0, 70.0, 90.0));
        project.addFuzzySet("M", set);
        set = new ArrayList<>(Arrays.asList(70.0, 90.0, 100.0, 100.0));
        project.addFuzzySet("H", set);
        fuzzyLogicToolBox.addVariable(project);

        Variables team = new Variables("Team_Experience_Level");
        set = new ArrayList<>(Arrays.asList(0.0, 15.0, 30.0));
        team.addFuzzySet("Beginner", set);
        set = new ArrayList<>(Arrays.asList(15.0,30.0,45.0));
        team.addFuzzySet("Intermediate", set);
        set = new ArrayList<>(Arrays.asList(30.0,45.0,60.0));
        team.addFuzzySet("Expert", set);
        fuzzyLogicToolBox.addVariable(team);

        Variables Risk = new Variables("Risk");
        set = new ArrayList<>(Arrays.asList(0.0, 25.0, 50.0));
        Risk.addFuzzySet("H", set);
        set = new ArrayList<>(Arrays.asList(25.0,50.0,75.0));
        Risk.addFuzzySet("N", set);
        set = new ArrayList<>(Arrays.asList(50.0,75.0,100.0));
        Risk.addFuzzySet("L", set);
        fuzzyLogicToolBox.addVariable(Risk);

        fuzzyLogicToolBox.addRule("If Project_Funding is H or Team_Experience_Level is Expert then Risk is L");
        fuzzyLogicToolBox.addRule("If Project_Funding is M and Team_Experience_Level is Intermediate or Team_Experience_Level is Beginner then Risk is N");
        fuzzyLogicToolBox.addRule("If Project_Funding is VL then Risk is H");
        fuzzyLogicToolBox.addRule("If Project_Funding is L and Team_Experience_Level is Beginner then Risk is H");

        fuzzyLogicToolBox.inputData();


    }
}