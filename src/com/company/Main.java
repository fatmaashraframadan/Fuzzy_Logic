package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        FuzzyLogicToolBox fuzzyLogicToolBox = new FuzzyLogicToolBox();
        Variables project = new Variables("dirt");
        ArrayList<Double> set = new ArrayList<>(Arrays.asList(0.0, 0.0, 20.0, 40.0));
        project.addFuzzySet("small", set);
        set = new ArrayList<>(Arrays.asList(20.0,40.0, 60.0, 80.0));
        project.addFuzzySet("medium", set);
        set = new ArrayList<>(Arrays.asList(60.0, 80.0, 100.0, 100.0));
        project.addFuzzySet("large", set);
        fuzzyLogicToolBox.addVariable(project);

        project = new Variables("fabric");
        set = new ArrayList<>(Arrays.asList(0.0, 0.0, 20.0, 40.0));
        project.addFuzzySet("soft", set);
        set = new ArrayList<>(Arrays.asList(20.0,40.0, 60.0, 80.0));
        project.addFuzzySet("ordinary", set);
        set = new ArrayList<>(Arrays.asList(60.0, 80.0, 100.0, 100.0));
        project.addFuzzySet("stiff", set);
        fuzzyLogicToolBox.addVariable(project);

        project = new Variables("time");
        set = new ArrayList<>(Arrays.asList(0.0, 0.0, 15.0));
        project.addFuzzySet("very_short", set);
        set = new ArrayList<>(Arrays.asList(0.0,15.0,30.0));
        project.addFuzzySet("short", set);
        set = new ArrayList<>(Arrays.asList(15.0,30.0,45.0));
        project.addFuzzySet("standard", set);
        set = new ArrayList<>(Arrays.asList(30.0,45.0,60.0));
        project.addFuzzySet("long", set);
        set = new ArrayList<>(Arrays.asList(45.0,60.0,60.0));
        project.addFuzzySet("very_long", set);
        fuzzyLogicToolBox.addVariable(project);

//        fuzzyLogicToolBox.addRule("If dirt is small and fabric is soft then time is very_short");
//                "If dirt is medium and fabric is ordinary then time is standard " +
//                "If dirt is small and fabric is not soft or the amount of dirt is medium and the fabric is soft then the wash time is small." +
//                "If the amount of dirt is medium and the fabric is stiff then the wash time is large.\n" +
//                "5. If the amount of dirt is large and the fabric is not soft then the wash time is very\n" +
//                "large.\n" +
//                "6. If the amount of dirt is large and the fabric is soft then the wash time is standard.");
//        fuzzyLogicToolBox.addRule("if project_funding is high or team_experience_level is expert then risk is low");
//        fuzzyLogicToolBox.addRule("if project_funding is medium and team_experience_level is intermediate or team_experience_level is beginner then risk is normal");

        fuzzyLogicToolBox.inputData();
    }
}
