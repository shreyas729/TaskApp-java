package com.shreyas.taskcli;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TaskRepository {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private int nextId = 1 ;
    private final ObjectMapper objectMapper;
    private static final String FILE_PATH = "tasks.json";

    public TaskRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // enables LocalDate support
        loadFromFile();
    }



    public Task create(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(),task);
        saveToFile();
        return task;
    }

    public Optional<Task> findById(int id){
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> findAll(){
        return new ArrayList<>(tasks.values());
    }

    public boolean update(int id , Task updatedTask){
        if(!tasks.containsKey(id))return false;
        updatedTask.setId(id);
        tasks.put(updatedTask.getId(), updatedTask);
        saveToFile();
        return true;

    }

    public boolean delete(int id){
        boolean removed = tasks.remove(id) != null;
        if(removed) saveToFile();
        return removed;

    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // first run, nothing to load
        }
        try {
            Map<Integer, Task> loaded = objectMapper.readValue(
                    file,
                    new TypeReference<Map<Integer, Task>>() {}
            );
            tasks.putAll(loaded);
            // restore nextId to continue after the highest existing id
            nextId = tasks.keySet().stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElse(0) + 1;
        } catch (IOException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), tasks);
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }



}
