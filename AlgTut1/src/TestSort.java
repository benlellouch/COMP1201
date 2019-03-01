import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class TestSort {

    public static void main(String[] args) throws IOException {
        int numberOfSimulations = 20;
        int N = 100002;
        int step = 10000;
        int startingValue = 1;

        double[][] insertionTimeComplexityAverage = new double[numberOfSimulations][N];
        double[][] shellTimeComplexityAverage = new double[numberOfSimulations][N];
        double[][] quickTimeComplexityAverage = new double[numberOfSimulations][N];

        double[] insertionMedian = new double[N];
        double[] shellMedian = new double[N];
        double[] quickMedian = new double[N];




            for (int j = 0; j < numberOfSimulations; j++) {

                for(int k = startingValue; k < N; k+=step) {
                    double[] data = new double[k];
                    for (int i = 0; i < k; i++)
                        data[i] = Math.random();
                    double[] data1 = (double[]) data.clone();
                    double[] data2 = (double[]) data.clone();
                    double[] data3 = (double[]) data.clone();
                    long time_prev = System.nanoTime();
                    Arrays.sort(data3);
                    double time = (System.nanoTime() - time_prev) / 1000000000.0;
                    quickTimeComplexityAverage[j][k] = time;

                    time_prev = System.nanoTime();
                    InsertionSort(data1);
                    time = (System.nanoTime() - time_prev) / 1000000000.0;
                    insertionTimeComplexityAverage[j][k] = time;
                    //insertionTimeComplexityAverage[j] += time;
                    //System.out.println("Insertion Sort\nTime= " + time);
                    time_prev = System.nanoTime();
                    ShellSort(data2);
                    time = (System.nanoTime() - time_prev) / 1000000000.0;
                    shellTimeComplexityAverage[j][k] = time;
                    //shellTimeComplexityAverage[j] += time;
                    //System.out.println("Shell Sort\nTime= " + time);


                    //quickTimeComplexityAverage[j] += time;
                    System.out.println("Simulation: " + j + " Input: " + k);
                    System.out.println(insertionTimeComplexityAverage[j][k] + " "+ shellTimeComplexityAverage[j][k] + " "+ quickTimeComplexityAverage[j][k]);
                }
//                System.out.println(j);

            }

            insertionTimeComplexityAverage = transposeMatrix(insertionTimeComplexityAverage);
            quickTimeComplexityAverage=transposeMatrix(quickTimeComplexityAverage);
            shellTimeComplexityAverage=transposeMatrix(shellTimeComplexityAverage);



            for(int j=startingValue; j<N; j+=step){
                insertionMedian[j]= median(insertionTimeComplexityAverage[j]);
                shellMedian[j]=median(shellTimeComplexityAverage[j]);
                quickMedian[j]=median(quickTimeComplexityAverage[j]);
                System.out.println( insertionMedian[j] + " " + shellMedian[j] + " " + quickMedian[j]);
            }




        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");

        Object[][] bookData = new Object[N+1][4];

        String numberOfInputs = " Number of Inputs";
        String insertionSort = "Insertion Sort";
        String shellSort = " Shell Sort";
        String quickSort = "Quick Sort";

        bookData[0][0] = numberOfInputs;
        bookData[0][1] = insertionSort;
        bookData[0][2]= shellSort;
        bookData[0][3]= quickSort;

        for (int i = 0; i < 4; i ++ ){

            if(i == 0){
                for (int j = startingValue; j < N; j+=step){

                        bookData[(j/step)+1][i] = j;

                }

            } else if (i == 1){

                for (int j = startingValue; j < N; j+=step){

                        bookData[(j/step)+1][i] = insertionMedian[j];


                }

            } else if (i == 2){
                for (int j = startingValue; j < N; j+=step){
                        bookData[(j/step)+1][i] = shellMedian[j];



                }

            } else if (i ==3){
                for (int j = startingValue; j < N; j+=step){
                        bookData[(j/step)+1][i] = quickMedian[j];


                }


            }




        }

        int rowCount = 0;

        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                } else if(field instanceof Double){
                    cell.setCellValue((Double) field);
                }
            }

        }


        try (FileOutputStream outputStream = new FileOutputStream("testSort.xlsx")) {
            workbook.write(outputStream);
        }
    }
      //  System.out.println("\tPresorted\tInsertion\t\t Shell\t\t Quick");
      //  for (int i=0; i<data.length; i++)
      //      System.out.println(data[i] + " " + data1[i] + " " + data2[i] + " " + data3[i]);



    // I changed this as the previous code was terribly inefficient

    public static void InsertionSort(double[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i]<data[i-1]) {
                double temp = data[i];
                int j = i;
                do {
                    data[j] = data[j-1];
                   j--;
                } while (j>0 && data[j-1] > temp);
                data[j] = temp;
            }
        }
    }

//    public static void InsertionSort(double[] data){
//
//        double temp;
//        for (int i = 1; i < data.length; i++) {
//            for( int j = i; j > 0; j--){
//                if(data[j]< data[j-1]){
//
//                    temp= data[j];
//                    data[j]= data[j-1];
//                    data[j-1]=temp;
//
//
//                }
//
//            }
//
//
//        }
//
//
//    }

    public  static void ShellSort(double[] a) {
        int increment = a.length / 3 + 1;

        // Sort by insertion sort at diminishing increments.
        while ( increment > 1 )
        {
            for ( int start = 0; start < increment; start++ )
                insertSort(a, start, increment );

            increment = increment / 3 + 1;
        }

        // Do a final pass with an increment of 1.
        // (This has to be outside the previous loop because
        // the formula above for calculating the next
        // increment will keep generating 1 repeatedly.)
        insertSort(a, 0, 1 );
    }
    public static void insertSort(double[] a, int start, int increment ) {
        int j, k;
        double temp;

        for ( int i = start + increment; i < a.length; i += increment ) // O(n)
        {
            j = i;
            k = j - increment;
            if ( a[j] < a[k] )
            {
                // Shift all previous entries down by the current
                // increment until the proper place is found.
                temp = a[j];
                do
                {
                    a[j] = a[k];
                    j = k;
                    k = j - increment;
                } while ( j != start && a[k] > temp );
                a[j] = temp;
            }
        }
    }
    public static double median(double[] a) {
        Arrays.sort(a);
        return a[a.length/2];
    }

    public static double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
}