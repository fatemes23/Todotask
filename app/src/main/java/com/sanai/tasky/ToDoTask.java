package com.sanai.tasky;

public class ToDoTask {
    String todoTitle;
    String todoTime;
    String todoBody;
    String todoNotifictionTime;
    int id;

    public ToDoTask(String todoTitle, String todoTime, String todoBody, String todoNotifictionTime ) {
        this.todoTitle = todoTitle;
        this.todoTime = todoTime;
        this.todoBody = todoBody;
        this.todoNotifictionTime = todoNotifictionTime;

    }

    public int getIdtOdO() {
        return id;
    }

    public ToDoTask(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoTime() {
        return todoTime;
    }

    public void setTodoTime(String todoTime) {
        this.todoTime = todoTime;
    }

    public String getTodoBody() {
        return todoBody;
    }

    public void setTodoBody(String todoBody) {
        this.todoBody = todoBody;
    }

    public String getTodoNotifictionTime() {
        return todoNotifictionTime;
    }

    public void setTodoNotifictionTime(String todoNotifictionTime) {
        this.todoNotifictionTime = todoNotifictionTime;
    }
}
