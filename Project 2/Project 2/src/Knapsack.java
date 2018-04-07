/*
* Hrag Ayvazian
* Project 2  Comp 482
* 11/9/17
* */

import java.util.*;

public class Knapsack {
    //set private fields here
    private int W;
    private int[] w;
    private int[] b;

    public Knapsack(int W, int[] w, int[] b) {
        //constructor
        this.W = W;
        this.w = w;
        this.b = b;
    }

    public static int[] generateSubset(int k, int n) {
        //  0 <= k <= 2n - 1
        //  Generates the kth subset of { 0,1,..., n-1 }
        //  in the form of the binary representation of k
        int[] sub = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            sub[i] = k % 2;
            k = k / 2;
        }
        return sub;
    }

    public void BruteForceSolution() {
        //Prints all optimal solutions to the 0-1 knapsack problem
        //using brute force algorithm described in Project 2
        //Print solution in format specified in Project 2
        int binLimit = (int) Math.pow(2, w.length);
        int[] binLimitArray = new int[binLimit];
        int weightTotal = 0;
        int benefitTotal = 0;
        int maxBenefit = 0;
        int maxWeight = 0;
        int[] pointer = new int[binLimit];
        int counter = 0;
        for (int a = 0; a < binLimit; a++) {
            binLimitArray[a] = a;
        }
        for (int i = 0; i < binLimit; i++) {
            weightTotal = 0;
            benefitTotal = 0;
            int[] subArray = generateSubset(binLimitArray[i], w.length);
            for (int s = 0; s < subArray.length; s++) {
                if (subArray[s] == 1) {
                    weightTotal += w[s];
                    benefitTotal += b[s];
                }
            }
            if (weightTotal >= maxWeight && weightTotal <= W) {
                if (benefitTotal >= maxBenefit) {
                    maxBenefit = benefitTotal;
                    if (weightTotal == maxWeight && maxWeight != 0) {
                        counter++;
                    }
                    pointer[counter] = i;
                }
                maxWeight = weightTotal;
            }
        }
        int[] arr = new int[w.length];
        for (int i = 0; i < pointer.length; i++) {
            if (pointer[i] != 0) {
                counter = 0;
                weightTotal = 0;
                benefitTotal = 0;
                int[] subArray = generateSubset(pointer[i], w.length);
                for (int s = 0; s < subArray.length; s++) {
                    if (subArray[s] == 1) {
                        weightTotal += w[s];
                        benefitTotal += b[s];
                        arr[counter] = s;
                        counter++;
                    }
                }
                if (weightTotal <= W && benefitTotal >= maxBenefit && weightTotal != 0) {
                    System.out.print("Optimal Set = { ");
                    for (int p = 0; p < arr.length; p++) {
                        if (arr[p] != 0) {
                            System.out.print(arr[p]);
                            if (arr[p + 1] != 0) {
                                System.out.print(", ");
                            }
                        }
                    }
                    System.out.print(" } ");
                    System.out.println("weight sum = " + weightTotal + " " + "benefit sum = " + benefitTotal);
                }
            }
        }
    }

    public void DynamicProgrammingSolution(boolean printBmatrix) {
        //Prints one optimal solutions to the 0-1 knapsack problem
        // using dynamic programming algorithm described in Project 2
        // Print solution in format specified in Project 2
        // If printmatrix is true, print the OPT matrix.
        int WeightCol = W + 1;
        int BenefitRow = b.length;
        int[][] B = new int[BenefitRow][WeightCol];
        for (int k = 1; k < BenefitRow; k++) {
            for (int i = 1; i < WeightCol; i++) {
                if (i < w[k]) {
                    B[k][i] = B[k - 1][i];
                } else {
                    B[k][i] = Math.max(B[k - 1][i], B[k - 1][i - w[k]] + b[k]);
                }

            }
        }
        if (printBmatrix) {
            for (int p = 0; p < BenefitRow; p++) {
                for (int r = 0; r < WeightCol; r++) {
                    System.out.print(B[p][r] + " ");
                }
                System.out.println();
            }
        }
        int TrackRow = BenefitRow - 1;
        int TrackColumn = WeightCol - 1;
        int optimalbenefit = B[TrackRow][TrackColumn];
        int weightTrack = 0;
        int benefitTrack = 0;
        int count = 0;
        int[] backtrack = new int[BenefitRow];
        while (optimalbenefit != 0) {
            if (optimalbenefit == B[TrackRow - 1][TrackColumn]) {
                TrackRow--;
            } else {
                backtrack[count] = TrackRow;
                count++;
                weightTrack = weightTrack + w[TrackRow];
                benefitTrack = benefitTrack + b[TrackRow];
                TrackColumn = TrackColumn - w[TrackRow];
                TrackRow--;
            }
            optimalbenefit = B[TrackRow][TrackColumn];
        }
        System.out.print("Optimal Set = { ");
        for (int i = backtrack.length - 1; i >= 0; i--) {
            if (backtrack[i] != 0) {
                System.out.print(backtrack[i]);
                if (i - 1 >= 0) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(" }  weight sum = " + weightTrack + "  benefit sum = " + benefitTrack);
    }

    public void GreedyApproximateSolution() {
        //Prints one approximate solution to the 0-1 knapsack problem using a variation of
        // used to solve the Fractional Knapsack Problem.
        double[] values = new double[w.length];
        double divv;
        for (int i = 1; i < values.length; i++) {
            divv = (double) b[i] / (double) w[i];
            values[i] = divv;
        }
        int TotalWeight = 0;
        int index = 1;
        int TotalBenefit = 0;
        int count = 1;
        int[] positions = new int[w.length];
        int num = 0;

        double max = values[0];
        int m = 0;
        while (m < values.length) {
            if (max < values[m]) {
                max = values[m];
                index = m;
            }
            m++;
        }
        values[index] = 0;
        while (count != values.length) {
            if ((TotalWeight + w[index]) <= W) {
                TotalBenefit += b[index];
                TotalWeight += w[index];
                positions[num] = index;
                num ++;
            }
            max = values[0];
            m = 0;
            index = 1;
            while (m < values.length) {
                if (max < values[m]) {
                    max = values[m];
                    index = m;
                }
                m++;
            }
            values[index] = 0;
            count++;
        }
        System.out.print("Optimal Set = { ");
        for (int p = 0; p < positions.length; p++) {
            if (positions[p] != 0) {
                System.out.print(positions[p]);
                if (positions[p + 1] != 0) {
                    System.out.print(", ");
                }
            }
        }
        System.out.print(" } ");
        System.out.println("weight sum = " + TotalWeight + " benefit sum = " + TotalBenefit);
    }
}