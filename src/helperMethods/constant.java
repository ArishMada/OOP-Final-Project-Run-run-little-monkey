package helperMethods;

public final class constant {

    public static class Directions{ //player direction values
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants { // this class is used only to check the player's state
        public static final int RUNNINGRIGHT = 6;
        public static final int RUNNINGLEFT = 2;
        public static final int RUNNINGUP = 4;
        public static final int RUNNINGDOWN = 0;

        public static int getSpriteAmount(int player_action) { // this will return the number of sprite per animation to avoid out of bounds error
            switch (player_action) {
                case RUNNINGRIGHT:
                case RUNNINGUP:
                case RUNNINGDOWN:
                case RUNNINGLEFT:
                    return 4;
                default:
                    return 1;
            }
        }
    }

    public static class Tiles { // the values for the different type of tiles
        public static final int GRASS = 0;
        public static final int ROCK = 1;
        public static final int GRAVE = 2;
        public static final int WATER = 3;
        public static final int SAND = 4;
        public static final int LAVA = 5;
        public static final int ROAD_VERTICAL = 6;
        public static final int ROAD_HORIZONTAL = 7;
        public static final int ALL = 8;
        public static final int LEFT_UP_CORNER = 9;
        public static final int LEFT_DOWN_CORNER = 10;
        public static final int RIGHT_UP_CORNER = 11;
        public static final int RIGHT_DOWN_CORNER = 12;
        public static final int RIGHT_UP_DOWN = 13;
        public static final int LEFT_UP_DOWN = 14;
        public static final int LEFT_UP_RIGHT = 15;
        public static final int LEFT_DOWN_RIGHT = 16;
    }
}
