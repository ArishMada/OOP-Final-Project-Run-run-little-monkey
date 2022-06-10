package helperMethods;

import java.util.ArrayList;

public final class UsefulMethods {
    public static int[][] convertArrayListTo2Darray(ArrayList<Integer> arrayList, int ySize, int xSize) {
        int[][] array2D = new int[ySize][xSize];
        for (int i = 0; i < array2D.length; i++) {
            for (int u = 0; u < array2D[i].length; u++){
                int index = i * xSize + u;
                array2D[i][u] = arrayList.get(index);
            }
        }
        return array2D;
    }

    public static  int[] convert2DarrayToArray(int[][] array2D){
        int [] array1D = new int[array2D.length * array2D[0].length]; // getting the total number of values in the 2d array
        for(int j=0; j<array2D.length; j++){
            for(int i=0; i<array2D[j].length;i++){
                int index = j * array2D[j].length + i;
                array1D[index] = array2D[j][i];
            }
        }
        return array1D;
    }
}
