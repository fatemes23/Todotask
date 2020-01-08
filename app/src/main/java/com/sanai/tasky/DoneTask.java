package com.sanai.tasky;

//kelas kar haye tmoom shode
public class DoneTask {

    String doneTitle ;
    int idOfDone;

    public DoneTask(String doneTitle , int idOfDone) {
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
