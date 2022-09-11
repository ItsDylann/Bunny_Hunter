package com.lusionmc.bunnymg;

public enum GameState {

    //waiting for players
    RECRUITING,
    //countdown to start
    COUNTDOWN,
    //game is running - if someone quits, and players is below required, it will go back to recruiting
    LIVE;

}
