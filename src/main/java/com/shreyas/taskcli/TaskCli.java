package com.shreyas.taskcli;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TaskCli {
    private final Scanner scanner;
    private final TaskRepository respository;

    public TaskCli(TaskRepository respository) {
        this.scanner = new Scanner(System.in);
        this.respository = respository;
    }

    public void run(){
        while(true){
            showMenu();
            String choice  = scanner.nextLine();
            switch(choice){
                case "1" : handleCreate(); break;
                case "2" : handleViewALl(); break;
                case "3" : handleViewOne(); break;
                case "4" : handleUpdate(); break;
                case "5" : handleDelete(); break;
                case "6" : return;
                default: System.out.println("Invalid Input");
            }
        }

    }
    private void showMenu(){
        System.out.println("1. Create 2. View All 3. View One 4. Update 5. Delete 6.Exit");
        System.out.println("Pick what you want to perform: ");

    }
    private void handleCreate(){
        System.out.println("Title:");
        String title = scanner.nextLine();
        System.out.println("Enter description, Status(NOT_STARTED, ONGOING, COMPLETED) and dueDate one at a time. ");
        String description = scanner.nextLine();
        TaskStatus status = TaskStatus.valueOf(scanner.nextLine().trim().toUpperCase());
        LocalDate dueDate = LocalDate.parse(scanner.nextLine().trim());
        Task task = new Task(title, description ,  status , dueDate);
        Task created = respository.create(task);
        System.out.println("Created:"+ created);

    }
    private void handleViewALl(){
        List<Task> allTasks = respository.findAll();
        if(allTasks.isEmpty()){
            System.out.println("No task found.");
            return;
        }
        for(Task task : allTasks){
            System.out.println(task);
        }


    }
    private void handleViewOne(){
        System.out.println("Enter the id you want to view:");
        int givenId = scanner.nextInt();
        Optional<Task> result = respository.findById(givenId);
        if(result.isPresent()){
            System.out.println(result.get());
        }
        else{
            System.out.println("Not Found");
        }



    }
    private void handleUpdate(){
        System.out.println("Enter the id of the task you want to update:");
        int updateId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("New title , description, status, dueDate:");
        String title = scanner.nextLine();
        String description= scanner.nextLine();
        TaskStatus status = TaskStatus.valueOf(scanner.nextLine().trim().toUpperCase());
        LocalDate dueDate = LocalDate.parse(scanner.nextLine().trim());
        Task updatedTask = new Task(title , description , status , dueDate);
        boolean success = respository.update(updateId , updatedTask);
        if(success){
            System.out.println("Updated Task"+ updateId);
        }
        else{
            System.out.println("no task with that id");
        }

    }
    private void handleDelete(){
        System.out.println("Enter id to delete");
        int deleteId = scanner.nextInt();
        boolean success = respository.delete(deleteId);
        if(success){
            System.out.println("Deleted"+deleteId);

        }
        else{
            System.out.println("Not Found id:"+deleteId);
        }




    }



}
