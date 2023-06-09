package org.example;

public class ScriptMenu {
    private String inMess;
    private int stage;

    public void setInMess(String inMess, int stage) {
        this.inMess = inMess;
        this.stage = stage;
        }

    public String getInMess() {
        if (stage ==0){
            if ("/start".equals(inMess)) {
                stage = 1;
                inMess = "OK";
            } else {
                inMess = "Неверная команда. Попробуйте /start";
            }
        }
        return inMess;
    }

    public int getStage() {
        return stage;
    }
}
