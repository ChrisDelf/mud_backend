package com.dev.mud_backend.responseObjects;

public class PlayerAction {
    String actionType;
    long actionNum;


    public PlayerAction() {

    }

    public PlayerAction(String actionType, long actionNum) {
        this.actionType = actionType;
        this.actionNum = actionNum;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public long getActionNum() {
        return actionNum;
    }

    public void setActionNum(long actionNum) {
        this.actionNum = actionNum;
    }
}
