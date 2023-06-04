package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static ToDoList todoList = new ToDoList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int OPTION_PRINT_INSTRUCTIONS = 0;
        final int OPTION_ADD_TASK = 1;
        final int OPTION_REMOVE_TASK = 2;
        final int OPTION_DISPLAY_TASK = 3;
        final int OPTION_DISPLAY_TASK_DESCRIPTION = 4 ;
        final int OPTION_MODIFY_NAME = 5;
        final int OPTION_MODIFY_DESCRIPTION = 6;
        final int OPTION_TASK_COMPLETE = 7;



        boolean quit = false;
        int choice= 0;

        while (!quit){
            printInstructions();
            System.out.println("Enter your choice:");
            choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case OPTION_PRINT_INSTRUCTIONS:
                    printInstructions();
                    break;
                case OPTION_ADD_TASK:
                    addTask();
                    break;
                case OPTION_REMOVE_TASK:
                    removeTask();
                    break;
                case OPTION_DISPLAY_TASK:
                    todoList.displayTask();
                    break;
                case OPTION_DISPLAY_TASK_DESCRIPTION:
                    displayTaskDescription();
                    break;
                case OPTION_MODIFY_NAME:
                    modifyTaskName();
                    break;
                case OPTION_MODIFY_DESCRIPTION:
                    modifyDesc();
                    break;
                case OPTION_TASK_COMPLETE:
                    taskComplete();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    printInstructions();

            }
        }
        scanner.close();
    }
    public static void printInstructions(){
        System.out.println("\n press");
        System.out.println("\t 0.To print choice options.");
        System.out.println("\t 1.To add task.");
        System.out.println("\t 2.To remove taks.");
        System.out.println("\t 3.To display all tasks.");
        System.out.println("\t 4.To diplay a tasks description.");
        System.out.println("\t 5.To modify task name.");
        System.out.println("\t 6.To modify task description.");
        System.out.println("\t 7.Mark task as completed.");


    }

    public static void addTask(){
        System.out.print("Give tasks name:");
        String taskName = scanner.nextLine();


        String response =YesNo("Do you want to add a description?\n (Y/N)");

        if (response.equals("Y")) {
            System.out.println("Give a description for the task:");
            String desc = scanner.nextLine();
            todoList.addTask(taskName, desc);
        } else {
            todoList.addTask(taskName);
        }
    }

    public static void removeTask(){
        if (todoList.isEmpty()){
            System.out.println("There are no more tasks to remove.");
            return;
        }
        boolean isDone = false;
        while (!isDone){
            System.out.println(todoList.size() + " tasks remaining");
            if (todoList.isEmpty()){
                System.out.println("There are no more tasks to remove.");
                return;
            }
            int taskNumber = promptTaskNumber("Select which task  do you want to remove.");
            todoList.removeTask(taskNumber);
            String respone =YesNo("Do you want to remove more?\n (Y/N)");
            if (respone.equals("N")){
                isDone=true;
            }


        }
    }
    public static void displayTaskDescription(){
        
        int taskNumber = promptTaskNumber("Select which task's description to display.");
    
        if (todoList.validTask(taskNumber)){

            todoList.displayTaskDescp(taskNumber);
        }else {
            System.out.println("This task description is empty");
        }


    }
    public static void modifyTaskName() {
        int taskNumber = promptTaskNumber("Select the task number to modify its name: ");
        String newName = promptNewName();
        todoList.modifyName(taskNumber, newName);
    }

    public static String promptNewName() {
        System.out.println("Enter the new name for the task:");
        return scanner.nextLine();
    }

    public static void modifyDesc() {
        int taskNumber=promptTaskNumber("Select the number of the task to modify its description:");

        if ((todoList.getDesc(taskNumber) == null) || (!todoList.getDesc(taskNumber).isEmpty()) ) {
            String respone = YesNo("The description is empty. Would you like to add a description \n (Y/N)");
            if (respone.equals("Y")){
                System.out.println("Enter the new description for the task:");
                String desc = scanner.nextLine();
                todoList.modifyDesc(taskNumber,desc);
                return;
            }
            else {
                printInstructions();
                return;
            }

        }
        System.out.println("Old description of the task:"+todoList.getDesc(taskNumber));
        System.out.println("Enter the new description for the task:");
        String desc = scanner.nextLine();
        todoList.modifyDesc(taskNumber,desc);


    }

    public static int promptTaskNumber(String prompt) {
        while (true) {
            System.out.println(prompt);
            todoList.displayTask();
            System.out.println("Enter the task number:");
            int taskNumber = readIntegerInput();

            if (taskNumber < 1 || taskNumber > todoList.size()) {
                System.out.println("Invalid task number. Please try again.");
            } else {
                return taskNumber - 1;
            }
        }
    }


    public static String YesNo(String prompt) {
        String response;
        do {
            System.out.println(prompt);
            response = scanner.nextLine().toUpperCase();
        } while (!response.equals("Y") && !response.equals("N"));
        return response;
    }

    public static void taskComplete(){
        int taskNumber=promptTaskNumber("Select the completed task");

        todoList.completedTask(taskNumber);
    }


    public static int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 0 to 9 inclusive.");
            }
        }
    }



}
