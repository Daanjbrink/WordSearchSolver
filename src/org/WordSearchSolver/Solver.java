package org.WordSearchSolver;

import java.util.Scanner;

/**
 * Created by Daanjbrink on 29-2-2016.
 */

public class Solver {
    Scanner in = new Scanner(System.in);

    public Solver() {
        int width, height;

        System.out.print("Please enter the width of the game: ");
        width = Integer.parseInt(in.nextLine());
        System.out.print("Please enter the height of the game: ");
        height = Integer.parseInt(in.nextLine());

        initSolve(width, height);

        //Ask for words and put them in a map, lets test with "hey"
    }

    private void initSolve(int width, int height) {
        char[][] field = new char[width][height];

        for (int i = 0; i != height; i++) {
            for (int j = 0; j != width; j++) {
                // Add clear screen
                System.out.print("Enter next character Line " + i + ": ");
                field[j][i] = in.nextLine().charAt(0);
            }
        }

        for (int i = 0; i != height; i++) {
            System.out.println();
            for (int j = 0; j != width; j++) {
                System.out.print(field[j][i] + " ");
            }
        }

        Solve(field);
    }

    private void Solve(char[][] field) {
        int width = field.length;
        int height = field[0].length;

        for (int i = 0; i != height; i++) {
            for (int j = 0; j != width; j++) {
                // Add for loop through word map
                if (field[j][i] == "h".charAt(0)) {
                    // Do some recursive stuff
                }
            }
        }
    }
}
