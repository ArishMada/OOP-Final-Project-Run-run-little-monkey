package main;

import Audio.Music;
import Audio.Sound;
import Handling.TileHandling;
import helperMethods.loadSave;
import scenes.*;

import javax.swing.*;


public class Game extends JFrame implements Runnable { // to be able to use threads and have fewer lags
    private GameScreen gameScreen;

    private final double FPS = 120.0; //change this to set the FPS
    private final double UPS = 60.0; //change this to set the UPS

    private Thread gameThread;

    //Classes
    private Rendering render;
    private Menu menu;
    private MainGame mainGame;
    private WiningScreen winingScreen;
    private Instructions instructions;
    private Editing editing;
    private TileHandling tileManager;
    private Sound sound;
    private Music music;

    public Game() {
        createDefaultLevel();
        setDefaultCloseOperation(EXIT_ON_CLOSE); // tells the program to exit when the x is clicked
        setLocationRelativeTo(null); // make the screen in the middle
        setLocation(300, 50);

        setResizable(false); //stop the user from resizing the window
        initClasses();



        add(gameScreen); // add the panel to the screen

        pack();
        setVisible(true); // make the screen appear on the screen
    }

    private void createDefaultLevel() {
        int[] arr = new int[644]; // set the default level as a screen full of grass
        loadSave.CreateLevel("new_level", arr); //create the new text file for the new level
    }

    private void initClasses() { // initialize all the instances of the different classes
        tileManager = new TileHandling();
        render = new Rendering(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        mainGame = new MainGame(this);
        instructions = new Instructions(this);
        editing = new Editing(this);
        winingScreen = new WiningScreen(this);
        sound = new Sound();
        music = new Music();
    }

    public void start() { //creatin yhe main thread that will be executed
        gameThread = new Thread(this){};
        gameThread.start();
    }

    private void updateGame(){
        if(GameState.gameState == GameState.MainGame) {
            mainGame.update();
        }
    }

    public static void main(String[] args) {
        Game game = new Game(); // make a new game object
        game.gameScreen.initializeInputs();
        game.start();
    }

    @Override
    public void run() { // replacement of game loop

        music.setFile(1);
        music.loop();
        double timePerFrame= 1000000000.0/FPS; //set the number of frames per second;
        double timePerUpdate = 1000000000.0/UPS; //set the number of updates per second;

        long lastFrameTime = System.nanoTime();
        long lastUpdateTime = System.nanoTime();
        long timeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long currentTime;

        while(true) {
            currentTime = System.nanoTime();

            // Rendering
            if (currentTime-lastFrameTime >= timePerFrame) { //to make sure that the speed of the number of FPS is stable
                repaint(); // redraw the components on the screen and create some kind of loop
                lastFrameTime = currentTime;
                frames++;
            }

            //Updating
            if (currentTime-lastUpdateTime >= timePerUpdate) {
                updateGame();
                lastUpdateTime = currentTime;
                updates++;
            }

            if (System.currentTimeMillis()- timeCheck    >= 1000) { // stabilizing the updates and frames speed
//                System.out.println("FPS = " + frames + " | UPS: " + updates); -> uncomment to see the FPS and UPS
                frames = 0;
                updates = 0;
                timeCheck = System.currentTimeMillis();
            }
        }
    }


    //Getters
    public Rendering getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public WiningScreen getWiningScreen() {
        return winingScreen;
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public Editing getEditing() {
        return editing;
    }

    public TileHandling getTileManager() {
        return tileManager;
    }

    public Sound getSound() {
        return sound;
    }

    public Music getMusic() {
        return music;
    }
}
