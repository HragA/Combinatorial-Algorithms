/**
 * Hrag Ayvazian
 * 9/26/17
 * Project #1
 */
public class SortTester {
    public static void main(String[] args) {
        int arra[] = {34,67,23,19,122,300,2,5,17,18,5,4,3,19,-40,23};
        int arra2[] = {34,67,23,19,122,300,2,5,17,18,5,4,3,19,-40,23};
        System.out.print("Unsorted Array: ");
        for(int x =0; x < arra.length; x++) // Print's Unsorted Array
        {
            System.out.print(arra[x]);
            if(x < arra.length-1) {
                System.out.print(",");
            }
        }

        Sorts tests = new Sorts();
        System.out.println("\nIs the array Sorted (true/false)? " + tests.isSorted(arra)); // Checks to see if array is already sorted

        ////////////////////////////////////////////////////////////////////////////////////
        //Merge Sort
        System.out.println("\nMerge Sort");
        long star = System.nanoTime(); // Start timer
        //tests.mergeSort(arra,0,arra.length-1); // Run(Call) Merge Sort
        tests.mergeSort(arra); // Run(Call) Merge Sort
        long en = System.nanoTime(); // End timer
        System.out.print("Sorted array: ");
        for(int x = 0; x < arra.length; x++) // Print's Sorted Array
        {
            System.out.print(arra[x]);
            if(x < arra.length-1) {
                System.out.print(",");
            }
        }
        long tota = en - star; // Total time taken to sort
        System.out.println("\nTime: " + tota);

        ////////////////////////////////////////////////////////////////////////////////////
        //Quick Sort
        System.out.println("\nQuick Sort");
        long star2 = System.nanoTime(); // Start timer
        //tests.quickSort(arra2,0, arra2.length-1); // Run(Call) Quick Sort
        tests.quickSort(arra2); // Run(Call) Quick Sort
        long en2 = System.nanoTime(); // End timer
        System.out.print("Sorted array: ");
        for(int x =0; x < arra2.length; x++) // Print's Sorted Array
        {
            System.out.print(arra2[x]);
            if(x < arra.length-1) {
                System.out.print(",");
            }
        }
        long tota2 = en2 - star2; // Total time taken to sort
        System.out.println("\nTime: " + tota2);


        ////////////////////////////////////////////////////////////////////////////////////
        //Table Format
        //Experiment #1 & Experiment #2

        System.out.println("\nInfo for Table");

        int mergewon = 0;
        int quickwon = 0;
        int n = 10;
        long mergemean = 0;
        long quickmean = 0;

        for(int i = 0; i < 7; i++) {
            int[] array = new int[n+1];
            int[] array2 = new int[n+1];
            for (int ntrial = 0; ntrial < 20; ntrial++) { //Runs 20 different trials
                for (int a = 0; a < n; a++) { //Makes array n length
                    array[a] = (int) (Math.random() * 1000000 + 1);
                    array2[a] = array[a];
                }

                Sorts test = new Sorts();
                //Merge Sort
                long start = System.nanoTime(); // Start timer
                test.mergeSort(array); // Run(Call) Merge Sort
                long end = System.nanoTime(); // Run(Call) Merge Sort
                long total = end - start; // Total time taken to sort
                mergemean = mergemean + total; // Adds all the total time for all 20 trials to calculate merge


                //Quick Sort
                long start2 = System.nanoTime(); // Start timer
                test.quickSort(array2,0, array2.length-1); // Run(Call) Quick Sort
                long end2 = System.nanoTime(); // Run(Call) Merge Sort
                long total2 = end2 - start2; // Total time taken to sort
                quickmean = quickmean + total2;

                if(total <= total2) { //If Merge Sort < Quick Sort. Then Merge Sort is faster
                    mergewon++;
                }

                else if(total > total2) { //If Merge Sort > Quick Sort. Then Quick Sort is faster
                    quickwon++;
                }
            }

            System.out.println(n + "    " + "20    Merge Won:" + mergewon + "    Quick Won:" + quickwon);

            mergemean = mergemean / 20;
            quickmean = quickmean / 20;

            System.out.println("Merge mean " + mergemean);
            System.out.println("Mean/nLog2(n) " + mergemean / (n * (Math.log(n) / Math.log(2))));
            System.out.println("Quick mean " + quickmean);
            System.out.println("Mean/nLog2(n) " + quickmean/ (n * (Math.log(n) / Math.log(2))) + "\n");

            mergemean = 0;
            quickmean = 0;
            mergewon = 0;
            quickwon = 0;

            if(i < 5) {
                n = n * 10;
            }

            else {
                n = n * 2;
            }
        }
    }
}