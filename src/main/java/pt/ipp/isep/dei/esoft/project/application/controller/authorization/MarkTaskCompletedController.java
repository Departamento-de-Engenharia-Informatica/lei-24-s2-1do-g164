package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import java.util.List;
import pt.ipp.isep.dei.esoft.project.domain.ToDoEntry;
import pt.ipp.isep.dei.esoft.project.repository.ToDoEntryRepository;

public class MarkTaskCompletedController {
    private ToDoEntryRepository toDoEntryRepository;

    public MarkTaskCompletedController(ToDoEntryRepository toDoEntryRepository) {
        this.toDoEntryRepository = toDoEntryRepository;
    }

    public List<ToDoEntry> getToDoEntries() {
        return toDoEntryRepository.getToDoEntryList();
    }

    public boolean markTaskAsCompleted(String taskId) {
        ToDoEntry task = toDoEntryRepository.getTaskById(taskId);
        if (task != null) {
            task.completeTask();
            return true;
        }
        return false;
    }
}
