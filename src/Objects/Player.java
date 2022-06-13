package Objects;

import helperMethods.loadSave;
import main.GameState;
import scenes.MainGame;
import scenes.MainGame.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static helperMethods.constant.Directions.*;
import static helperMethods.constant.Directions.RIGHT;
import static helperMethods.constant.PlayerConstants.*;
import static helperMethods.constant.Tiles.*;
import static main.GameState.SetGameState;

public class Player {
    private MainGame mainGame;
    private int x, y;
    private BufferedImage[][] animations;
    private int animationTick;
    private int animationIndex;
    private final int animationSpeed;
    private int playerAction;
    private int playerDirection; // will represent the direction the player is pointing at
    private boolean moving;
    private int playerSpeed; // can be changed along the way
    public long finalTime;


    public Player(MainGame mainGame) {
        this.x = mainGame.getStart().getX()*32;
        this.y = mainGame.getStart().getY()*32;
        this.mainGame = mainGame;
        this.playerDirection = RIGHT;
        this.animationSpeed = 10;
        this.moving = false;
        this.playerSpeed = 8;

        loadAnimations();
    }

    public void update(){
        updateAnimationTick();
        setAnimation();
        setMovement();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][animationIndex], x + 4, y, 20, 20, null);
//        g.drawRect( x + 20, y + 10, 20, 20);
//        g.setColor(Color.black);
//        g.drawRect( x + getPlayerSpeedXandWidth(playerDirection), y + getPlayerSpeedYandHeight(playerDirection), 20, 20);
    }

    private void setMovement() {
        playerSpeed = 8;
        winningCondition(x - 2, y);
        lavaEffect(x + 20, y+10); // the values of x and y are incremented to make it more difficult
        waterEffect(x + 20, y + 10);
        if(moving) {
            if (isNextTileWalkable()) //check if hte next tile can be followed
            {
//                System.out.println(x + " " + y);
                switch (playerDirection) {
                    case LEFT:
                        if (x>=0) {
                            x -= playerSpeed;
                        } else {
                            x = 0;
                        }
                        break;
                    case UP:
                        if (y>=0) {
                            y -= playerSpeed;
                        } else {
                            y = 0;
                        }
                        break;
                    case DOWN:
                        if (y<640) {
                            y += playerSpeed;
                        } else {
                            y = 640;
                        }
                        break;
                    case RIGHT:
                        if (x<=1032) {
                            x += playerSpeed;
                        } else {
                            x = 1032;
                        }
                        break;
                }
            }
        }
    }

    private void lavaEffect(int xCor, int yCor){
        if (getType(xCor, yCor) == LAVA) {
            resetPos();
        }
    }

    private void winningCondition(int xCor, int yCor){

        if (xCor >= mainGame.getEnd().getX()*32 - 10 && xCor <= mainGame.getEnd().getX()*32 + 10) {
            if (yCor >= mainGame.getEnd().getY()*32 - 10 && yCor <= mainGame.getEnd().getY()*32 + 10) {
                finalTime = mainGame.getFinalTime();
                SetGameState(GameState.WiningScreen);
                mainGame.game.getMusic().stop();
                mainGame.game.getSound().setFile(0);
                mainGame.game.getSound().play();
            }
        }
    }

    private void waterEffect(int xCor, int yCor) {
        Random random = new Random();
        if (getType(xCor, yCor) == WATER){
            int tempX = random.nextInt(32);
            int tempY = random.nextInt(20);
            while(getType(tempX*32, tempY*32) < 3){
                tempX = random.nextInt(32);
                tempY = random.nextInt(20);
            }
                x = tempX*32;
                y = tempY*32;
        }
    }

    private boolean isNextTileWalkable() {
        int nextX = x + getPlayerSpeedXandWidth(playerDirection);
        int nextY = y + getPlayerSpeedYandHeight(playerDirection);

        if (getType(nextX, nextY) > 2) { //if the tile is a special tile or a road
                //due to the sprite offset each direction need a value that will adjust the point that will define the position
                if (playerDirection == UP) {
                    return getWalkState(x + 20, y + 22);
                } else if (playerDirection == DOWN) {
                    return getWalkState(x + 15, y + 10);
                } else if (playerDirection == RIGHT) {
                    return getWalkState(x, y + 22);
                } else {
                    return getWalkState(x + 20, y + 22);
                }
            } else {
                return false;
            }
        }
    private boolean getWalkState(int xCor, int yCor){ //this function checks the direction you can go in for the current tile
        switch (getType(xCor, yCor)) {
            case ROAD_VERTICAL:
                return playerDirection == DOWN || playerDirection == UP;
            case ROAD_HORIZONTAL:
                return playerDirection == LEFT || playerDirection == RIGHT;
            case ALL:
                return playerDirection == UP || playerDirection == DOWN || playerDirection == LEFT || playerDirection == RIGHT;
            case LEFT_DOWN_CORNER:
                return playerDirection == LEFT || playerDirection == DOWN;
            case RIGHT_DOWN_CORNER:
                return playerDirection == RIGHT || playerDirection == DOWN;
            case RIGHT_UP_CORNER:
                return playerDirection == RIGHT || playerDirection == UP;
            case LEFT_UP_CORNER:
                return playerDirection == LEFT || playerDirection == UP;
            case LEFT_UP_RIGHT:
                return playerDirection == LEFT || playerDirection == UP || playerDirection == RIGHT;
            case RIGHT_UP_DOWN:
                return playerDirection == RIGHT || playerDirection == UP || playerDirection == DOWN;
            case LEFT_DOWN_RIGHT:
                return playerDirection == LEFT || playerDirection == DOWN || playerDirection == RIGHT;
            case LEFT_UP_DOWN:
                return playerDirection == LEFT || playerDirection == UP || playerDirection == DOWN;
            case SAND:
                playerSpeed = 1;
                return true;
            default:
                return false;
        }
    }

    public void resetPos(){
        x = mainGame.getStart().getX() * 32;
        y = mainGame.getStart().getY() * 32;
        playerDirection = RIGHT;
        moving = false;
    }

    private int getType(int x, int y) {
        return mainGame.getTileType(x, y);
    }

    private int getPlayerSpeedYandHeight(int direction) {
        if (direction == UP){
            return -playerSpeed + 10;
        } else if (direction == DOWN) {
            return playerSpeed + 20;
        } else {
            return 15;
        }
    }

    private int getPlayerSpeedXandWidth(int direction) {
        if (direction == LEFT){
            return -playerSpeed;
        } else if (direction == RIGHT) {
            return playerSpeed + 22;
        } else {
            return 0; //this will be executed only if the player is moving up or down so that it doesn't affect the next X value
        }
    }

    private void setAnimation() {
        if(moving){
            switch (playerDirection){
                case LEFT:
                    playerAction = RUNNINGLEFT;
                    break;
                case UP:
                    playerAction = RUNNINGUP;
                    break;
                case DOWN:
                    playerAction = RUNNINGDOWN;
                    break;
                case RIGHT:
                    playerAction = RUNNINGRIGHT;
                    break;
            }
        }
    }

    private void updateAnimationTick() {
        if (moving) {
            animationTick++;
            if (animationTick >= animationSpeed) {
                animationTick = 0;
                animationIndex++;
                if (animationIndex >= getSpriteAmount(playerAction)) {
                    animationIndex = 0;
                }
            }
        } else {
            animationIndex = 0;
        }
    }

    public int getPlayerDirection() {
        return playerDirection;
    }

    public void setPlayerDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void loadAnimations() {
        animations = new BufferedImage[8][4];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = loadSave.getSpritePlayer().getSubimage(i * 16, j*16, 16, 16);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
