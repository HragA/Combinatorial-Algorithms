/*
* Hrag Ayvazian
* Project 2  Comp 482
* 11/9/17
* */

import java.util.*;

public class KnapsackDriver
{

    public static void main( String[] args)
    {
        //Testcase #1
        System.out.println("Test #1");

        int n = 7;
        int[] weights = {-1, 60, 50, 60, 50, 70, 70, 45};
        int W = 100;
        int[] benefits = {-1, 180, 95, 40, 95, 40, 40, 105};

        // Print input values as required in Project 2

        System.out.println("\nBrute Force Solution");
        Knapsack kp1 = new Knapsack(W, weights, benefits);
        kp1.BruteForceSolution();

        int [] weightss = {-1, 2, 4, 3, 4, 4, 1};
        int Ws = 10;
        int [] valuess = {-1, 1, 2, 3, 3, 3, 6};
        System.out.println("\nDynamic Programming Solution");
        Knapsack kp3 = new Knapsack(W,weights, benefits);
        kp3.DynamicProgrammingSolution(false);

        System.out.println("\nGreedy Approximate Solution");
        Knapsack kp4 = new Knapsack(W, weights, benefits);
        kp4.GreedyApproximateSolution();

        //Testcase #2
        System.out.println("\nTest #2");

        int n2 = 18;
        int[] weights2 = {-1,25,4,2,5,6, 2,7,8,2,1, 1,3,5,8,9,  6,3,2};
        int W2 = 39;
        int[] benefits2 = {-1,75,7,4,3,2,  6,8,7,9,6,  5,4,8,10,8,  1,2,2};

        System.out.println("\nBrute Force Solution");
        kp1 = new Knapsack(W2, weights2, benefits2);
        kp1.BruteForceSolution();

        System.out.println("\nDynamic Programming Solution");
        kp3 = new Knapsack(W2, weights2, benefits2);
        kp3.DynamicProgrammingSolution(false);

        System.out.println("\nGreedy Approximate Solution");
        kp4 = new Knapsack(W2, weights2, benefits2);
        kp4.GreedyApproximateSolution();

        //Testcase #3
        System.out.println("\nTest #3");

        int n3 = 20;
        int[] weights3 = {-1, 10,14,35,12,16, 20,13,7,2,4,
                3,10,5,6,17, 7,9,3,4,3};
        int W3 = 29;
        int[] benefits3 = {-1, 2,13,41,1,12, 5,31,2,41,16,
                2,12,1,13,4, 51,6,12,1,9};

        System.out.println("\nBrute Force Solution");
        kp1 = new Knapsack(W3, weights3, benefits3);
        kp1.BruteForceSolution();

        System.out.println("\nDynamic Programming Solution");
        kp3 = new Knapsack(W3, weights3, benefits3);
        kp3.DynamicProgrammingSolution(false);

        System.out.println("\nGreedy Approximate Solution");
        kp4 = new Knapsack(W3, weights3, benefits3);
        kp4.GreedyApproximateSolution();

        //Testcase #4
        System.out.println("\nTest #4");

        int n4 = 7;
        int[] weights4 = {-1, 2,5,3,2,5,3,7 };
        int W4 = 10;
        int[] benefits4 = {-1, 5,10,5,20,15,5,10};

        System.out.println("\nBrute Force Solution");
        kp1 = new Knapsack(W4, weights4, benefits4);
        kp1.BruteForceSolution();

        System.out.println("\nDynamic Programming Solution");
        kp3 = new Knapsack(W4, weights4, benefits4);
        kp3.DynamicProgrammingSolution(true);

        System.out.println("\nGreedy Approximate Solution");
        kp4 = new Knapsack(W4, weights4, benefits4);
        kp4.GreedyApproximateSolution();
    }
}


