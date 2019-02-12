import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class TimeComplexity {
    public static void main(String[] args) throws IOException {
        int numberOfSimulations = 10;
        int start = 12;
        int end = 18;
        double[][] time = new double[numberOfSimulations][end-start];
        double medianTime[] = new double[end-start];
        for(int k=0 ; k< numberOfSimulations; k++) {
            for (int i = start; i < end; i++) {
                Graph graph = new Graph(i, 0.5);
                double previousTime = System.nanoTime();
                Colouring colouring = graph.bestColouring(3);
                time[k][i - start] = (System.nanoTime() - previousTime) / 1000000000.0;
                System.out.println(k + " " + i + " " + time[k][i - start]);
            }
        }

        time = transposeMatrix(time);

        for(int i = start; i<end; i++){
            medianTime[i-start] = median(time[i-start]);
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");

        Object[][] bookData = new Object[end-start+1][2];

        bookData[0][0] = "Number of Neighbours";
        bookData[0][1] = "Time to solve";

        for(int i = 0; i<2; i++){

            if(i == 0){
                for(int j =start; j<end;j++ ) {
                    bookData[j-start+1][i] = j;
                }
            } else {

                for(int j =start; j<end;j++ ) {

                    bookData[j-start+1][i]= medianTime[j-start];

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


        try (FileOutputStream outputStream = new FileOutputStream("testColouring.xlsx")) {
            workbook.write(outputStream);
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
