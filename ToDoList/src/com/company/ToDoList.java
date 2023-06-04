package com.company;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {


    //private List<Task> taskList = new ArrayList<>();
    private List<Task> taskList;
    public ToDoList(){
        this.taskList = new ArrayList<>();
    }

    public void addTask(String name,String description){

        taskList.add(new Task(name,description));
    }

    public void addTask(String name){
        taskList.add(new Task(name));
    }

    public void removeTask(int index){
        taskList.remove(index);
    }


    public void displayTask(){
        for (int i=0;i<taskList.size();i++){
            System.out.println(i+1+"."+taskList.get(i).getName());
        }

    }
    public String getDesc(int index){
        return taskList.get(index).getDescription();
    }


    public void displayTaskDescp(int index){

        System.out.println(taskList.get(index).getDescription());
    }

    public void modifyName(int index,String name){
        taskList.get(index).setName(name);
        System.out.println("tasks name modified");
    }

    public void modifyDesc(int index,String desc){
        taskList.get(index).setDescription(desc);
        System.out.println("description modified succesfully");
    }
    public void completedTask(int index){
        String name= taskList.get(index).getName();
        taskList.get(index).setName(name+" Completed");
        displayTask();
    }
    public int size(){
        return taskList.size();
    }

    public boolean validTask(int index){
        String desc = taskList.get(index).getDescription();
        return desc != null && !desc.isEmpty();
    }
    public boolean isEmpty(){
        if (taskList.isEmpty()){
            return true;
        }
        return false;
    }

}
