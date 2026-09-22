import java.util.*;

public class TaskRepository {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private int nextId = 1 ;


    public Task create(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(),task);
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
        return true;

    }

    public boolean delete(int id){
        return tasks.remove(id) != null;
    }



}
