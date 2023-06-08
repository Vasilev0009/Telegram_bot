package org.example;

public class ScriptMenu {
    private String inMess;
    private int stage;

    public void setInMess(String inMess, int stage) {
        this.inMess = inMess;
        this.stage = stage;
        }

    public void getInMess() {
        if (stage ==0){
            if ("/start".equals(inMess)) {
                stage = 1;
            } else {
                inMess = "Неверная команда. Попробуйте /start";
            }
        }
    }

    public int getStage() {
        return stage;
    }
}
