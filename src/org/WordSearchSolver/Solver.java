package org.WordSearchSolver;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Daanjbrink on 29-2-2016.
 */

public class Solver {
    Scanner in = new Scanner(System.in);

    String[][] colors;

    public Solver() {

        System.out.print("Load from file? ");

        if (in.nextLine().equals("y") || in.nextLine().equals("Y") || in.nextLine().equals("yes")) {
            try {
                Scanner reader = new Scanner(new File("C:\\Users\\Rhino\\Desktop\\map.txt"));

                int width = 0, height = 0, wpos = 0, hpos = 0;

                if (reader.hasNextLine()) {
                    String[] wh = reader.nextLine().split(" ");
                    width = Integer.parseInt(wh[0]);
                    height = Integer.parseInt(wh[1]);
                }

                char[][] field = new char[width][height];

                while (reader.hasNextLine()) {
                    String[] tmp = reader.nextLine().split(" ");

                    if (tmp[0].equals("Word: ")) {
                        // Add word, this is on the bottom of the file
                        continue;
                    }

                    for (int i = 0; i != tmp.length; i++)
                        field[wpos + i][hpos] = tmp[i].charAt(0);

                    hpos++;
                    if (hpos == height)
                        break;
                }

                if (width == 0 || height == 0)
                    return;

                initSolve(field);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            int width, height;

            System.out.print("Please enter the width of the game: ");
            width = Integer.parseInt(in.nextLine());
            System.out.print("Please enter the height of the game: ");
            height = Integer.parseInt(in.nextLine());

            if (width == 0 || height == 0)
                return;

            //Ask for words and put them in a map

            initSolve(width, height);
        }
    }

    private void initSolve(int width, int height) {
        char[][] field = new char[width][height];
        colors = new String[width][height];

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

    private void initSolve(char[][] field) {
        int width, height;
        width = field.length;
        height = field[0].length;
        colors = new String[width][height];

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
                    for (Direction dir : Direction.values()) {
                        Recursive(field, j, i, "haa", dir, 0);
                    }
                }
            }
        }
    }

    private void found(char[][] field) {
        int width = field.length;
        int height = field[0].length;

        for (int i = 0; i != height; i++) {
            System.out.println();
            for (int j = 0; j != width; j++) {
                if (colors[j][i] != null)
                    System.out.print((char) 27 + colors[j][i] + field[j][i] + (char) 27 + "[40m" + (char) 27 + "[37m" + " ");
                else
                    System.out.print(field[j][i] + " ");
            }
        }
        System.out.println("Found");
    }

    private void Recursive(char[][] field, int pos1, int pos2, String word, Direction dir, int atChar) {
        if (dir == Direction.TOPLEFT) {
            if (atChar > word.length())
                return;
            if (pos1 - 1 < 0 || pos2 - 1 < 0)
                return;
            if (field[pos1][pos2] == word.charAt(atChar)) {
                colors[pos1][pos2] = "[41m";
                if (word.length() == atChar + 1) {
                    found(field);
                    return;
                }
                Recursive(field, pos1 - 1, pos2 - 1, word, dir, atChar + 1);
            }
        }

        if (dir == Direction.TOP) {
            if (atChar > word.length())
                return;
            if (pos2 - 1 < 0)
                return;
            if (field[pos1][pos2] == word.charAt(atChar)) {
                colors[pos1][pos2] = "[41m";
                if (word.length() == atChar + 1) {
                    found(field);
                    return;
                }
                Recursive(field, pos1, pos2 - 1, word, dir, atChar + 1);
            }
        }

        if (dir == Direction.TOPRIGHT) {
            if (atChar > word.length())
                return;
            if (pos1 + 1 > field.length || pos2 - 1 < 0)
                return;
            if (field[pos1][pos2] == word.charAt(atChar)) {
                colors[pos1][pos2] = "[41m";
                if (word.length() == atChar + 1) {
                    found(field);
                    return;
                }
                Recursive(field, pos1 + 1, pos2 - 1, word, dir, atChar + 1);
            }
        }

        if (dir == Direction.LEFT) {
            if (atChar > word.length())
                return;
            if (pos1 - 1 < 0)
                return;
            if (field[pos1][pos2] == word.charAt(atChar)) {
                colors[pos1][pos2] = "[41m";
                if (word.length() == atChar + 1) {
                    found(field);
                    return;
                }
                Recursive(field, pos1 - 1, pos2, word, dir, atChar + 1);
            }
        }

        if (dir == Direction.RIGHT) {
            if (atChar > word.length())
                return;
            if (pos1 + 1 > field.length)
                return;
            if (field[pos1][pos2] == word.charAt(atChar)) {
                colors[pos1][pos2] = "[41m";
                if (word.length() == atChar + 1) {
                    found(field);
                    return;
                }
                Recursive(field, pos1 + 1, pos2, word, dir, atChar + 1);
            }
        }

        if (dir == Direction.BOTTOMLEFT) {
            if (atChar > word.length())
                return;
            if (pos1 - 1 < 0 || pos2 + 1 > field[0].length)
                return;
            if (field[pos1][pos2] == word.charAt(atChar)) {
                if (word.length() == atChar + 1) {
                    found(field);
                    return;
                }
                Recursive(field, pos1 - 1, pos2 + 1, word, dir, atChar + 1);
            }
        }

        if (dir == Direction.BOTTOM) {
            if (atChar > word.length())
                return;
            if (pos2 + 1 > field[0].length)
                return;
            if (field[pos1][pos2] == word.charAt(atChar)) {
                if (word.length() == atChar + 1) {
                    found(field);
                    return;
                }
                Recursive(field, pos1, pos2 + 1, word, dir, atChar + 1);
            }
        }

        if (dir == Direction.BOTTOMRIGHT) {
            if (atChar > word.length())
                return;
            if (pos1 + 1 > field.length || pos2 + 1 > field[0].length)
                return;
            if (field[pos1][pos2] == word.charAt(atChar)) {
                if (word.length() == atChar + 1) {
                    found(field);
                    return;
                }
                Recursive(field, pos1 + 1, pos2 + 1, word, dir, atChar + 1);
            }
        }
    }
}
