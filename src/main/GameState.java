package main;

public enum GameState { // to set constants

    MainGame,
    Menu,
    Instructions,
    Editing,
    WiningScreen;

    public static GameState gameState = Menu;

    public static void SetGameState(GameState state) {
        gameState = state;
    }
}
