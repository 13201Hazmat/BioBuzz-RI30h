package org.firstinspires.ftc.teamcode.data;

public enum Alliance {
    BLUE("blue_nectar"),
    RED("red_nectar");

    private String className;
    Alliance(String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }
}
