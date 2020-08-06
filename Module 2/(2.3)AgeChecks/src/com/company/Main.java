package com.company;

import java.awt.event.KeyAdapter;

public class Main {

    public static void main(String[] args) {
        int vasyaAge = 23;
        int katyaAge = 22;
        int mishaAge = 35;

        int maxAge = -1;
        int minAge = -1;
        int midAge = -1;

        if (vasyaAge >= katyaAge && vasyaAge >= mishaAge) {
                maxAge = vasyaAge;
                if (katyaAge >= mishaAge) {
                    midAge = katyaAge;
                    minAge = mishaAge;
                } else {
                    midAge = mishaAge;
                    minAge = katyaAge;
                }
        }
        else if (katyaAge >= vasyaAge && katyaAge >= mishaAge) {
            maxAge = katyaAge;
            if (vasyaAge >= mishaAge) {
                midAge = vasyaAge;
                minAge = mishaAge;
            } else {
                midAge = mishaAge;
                minAge = vasyaAge;
            }
        }
        else {
            maxAge = mishaAge;
            if (vasyaAge >= katyaAge) {
                midAge = vasyaAge;
                minAge = katyaAge;
            } else {
                midAge = katyaAge;
                minAge = vasyaAge;
            }
        }

        System.out.println("Max age: " + maxAge);
        System.out.println("Mid age: " + midAge);
        System.out.println("Min age: " + minAge);
    }
}
