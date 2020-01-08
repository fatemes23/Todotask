package com.sanai.tasky;


//kelas kar haye tmoom shode
public class OverdueTask {

    String doneTitle ;
    int idOfDone;

    public OverdueTask(String doneTitle , int idOfDone) {
        this.doneTitle = doneTitle;
        this.idOfDone= idOfDone;
    }

    public String getDoneTitle() {
        return doneTitle;
    }

    public void setDoneTitle(String doneTitle) {
        this.doneTitle = doneTitle;
    }
}
