package helperMethods;

import Objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// this class is used to have methods that load different files
// it makes the code cleaner
public final class loadSave {
    public static BufferedImage getSpriteBeach() {

        BufferedImage imageBeach = null;

        InputStream is1 = loadSave.class.getClassLoader().getResourceAsStream("res/BeachTileset.png"); // read an image
        try {
            imageBeach = ImageIO.read(is1); // try to import the image
        } catch (IOException e) {
            e.printStackTrace(); // if there is an error it will be displayed
        } finally {
            try {
                is1.close(); // release all the resources that were used temporary
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imageBeach;
    }

    public static BufferedImage getSpriteGround() {

        BufferedImage imageGround = null;

        InputStream is1 = loadSave.class.getClassLoader().getResourceAsStream("res/Map_tiles.png"); // read an image
        try {
            imageGround = ImageIO.read(is1); // try to import the image
        } catch (IOException e) {
            e.printStackTrace(); // if there is an error it will be displayed
        } finally {
            try {
                is1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imageGround;
    }

    public static BufferedImage getBackground() {

        BufferedImage BgImg = null;

        InputStream is1 = loadSave.class.getClassLoader().getResourceAsStream("res/Background.jpg"); // read an image
        try {
            BgImg = ImageIO.read(is1); // try to import the image
        } catch (IOException e) {
            e.printStackTrace(); // if there is an error it will be displayed
        } finally {
            try {
                is1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return BgImg;
    }

    public static BufferedImage getSpriteProps() {

        BufferedImage imageProps = null;

        InputStream is1 = loadSave.class.getClassLoader().getResourceAsStream("res/Props.png"); // read an image
        try {
            imageProps = ImageIO.read(is1); // try to import the image
        } catch (IOException e) {
            e.printStackTrace(); // if there is an error it will be displayed
        } finally {
            try {
                is1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imageProps;
    }

    public static BufferedImage getStartImg() {
        BufferedImage imageE = null;

        InputStream is1 = loadSave.class.getClassLoader().getResourceAsStream("res/start.png"); // read an image
        try {
            imageE = ImageIO.read(is1); // try to import the image
        } catch (IOException e) {
            e.printStackTrace(); // if there is an error it will be displayed
        } finally {
            try {
                is1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imageE;
    }

    public static BufferedImage getEndImg() {
        BufferedImage imageS = null;

        InputStream is1 = loadSave.class.getClassLoader().getResourceAsStream("res/end.png"); // read an image
        try {
            imageS = ImageIO.read(is1); // try to import the image
        } catch (IOException e) {
            e.printStackTrace(); // if there is an error it will be displayed
        } finally {
            try {
                is1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imageS;
    }


    public static BufferedImage getSpritePlayer() {
        BufferedImage imagePlayer = null;

        InputStream is1 = loadSave.class.getClassLoader().getResourceAsStream("res/playerImg.png"); // read an image
        try {
            imagePlayer = ImageIO.read(is1); // try to import the image
        } catch (IOException e) {
            e.printStackTrace(); // if there is an error it will be displayed
        } finally {
            try {
                is1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imagePlayer;
    }

    private static void WtoFile(File file, int[] IDArr, PathPoint start, PathPoint end) { // method to write into an existing text file
        try {
            PrintWriter pw = new PrintWriter(file);

            for (Integer i : IDArr){
                pw.println(i);
            }
            pw.println(start.getX());
            pw.println(start.getY());
            pw.println(end.getX());
            pw.println(end.getY());

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void SaveF(String name, int[][] array, PathPoint start, PathPoint end){
        File lvlFile = new File("src\\res\\" + name + ".txt");

        if (lvlFile.exists()){
            int[] array2 = UsefulMethods.convert2DarrayToArray(array);
            WtoFile(lvlFile, array2, start, end);
        } else {
            System.out.println("File " + name + " does not exist");
        }
    }


    private static ArrayList<Integer> RFile(File file) { // method to read form another file
        ArrayList<Integer> dataList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while(s.hasNextLine()){ // to print all the lines of the document
                dataList.add(Integer.parseInt(s.nextLine()));
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // create the levels using arrays
    public static void CreateLevel(String name, int[] IDArr){
        File nLevel = new File("src\\res\\" + name + ".txt");

        if(nLevel.exists()){
            System.out.println("File " + name + " already exist");
        } else {
            try {
                nLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WtoFile(nLevel, IDArr, new PathPoint(0,0), new PathPoint(0,0)); //the new path-points are default for the start and end
        }
    }

    public static ArrayList<PathPoint> getLevelPathpoints(String name){
        File lvlFile = new File("src\\res\\" + name + ".txt");

        if(lvlFile.exists()){
            ArrayList<Integer> dataList = RFile(lvlFile);
            ArrayList<PathPoint> pathPoints = new ArrayList<>();
            // use the last four values of the file as the coordinates for the path points
            pathPoints.add(new PathPoint(dataList.get(640), dataList.get(641)));
            pathPoints.add(new PathPoint(dataList.get(642), dataList.get(643)));

            return pathPoints;
        } else {
            System.out.println("File " + name + " does not exist");
            return null;
        }

    }

    // use the data from the text file to make an array
    public static int[][] getLevelData(String name){
        File lvlFile = new File("src\\res\\" + name + ".txt");

        if(lvlFile.exists()){
            ArrayList<Integer> dataList = RFile(lvlFile);
            return UsefulMethods.convertArrayListTo2Darray(dataList, 20, 32);
        } else {
            System.out.println("File " + name + " does not exist");
            return null;
        }
    }
}
