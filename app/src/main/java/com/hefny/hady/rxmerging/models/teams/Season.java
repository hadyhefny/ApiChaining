package com.hefny.hady.rxmerging.models.teams;

import com.google.gson.annotations.SerializedName;

public class Season {

    @SerializedName("winner")
    private Object winner;

    @SerializedName("currentMatchday")
    private int currentMatchday;

    @SerializedName("endDate")
    private String endDate;

    @SerializedName("id")
    private int id;

    @SerializedName("startDate")
    private String startDate;

    public void setWinner(Object winner) {
        this.winner = winner;
    }

    public Object getWinner() {
        return winner;
    }

    public void setCurrentMatchday(int currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public int getCurrentMatchday() {
        return currentMatchday;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return
                "Season{" +
                        "winner = '" + winner + '\'' +
                        ",currentMatchday = '" + currentMatchday + '\'' +
                        ",endDate = '" + endDate + '\'' +
                        ",id = '" + id + '\'' +
                        ",startDate = '" + startDate + '\'' +
                        "}";
    }
}