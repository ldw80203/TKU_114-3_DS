public class TaskNode {
    private String code;
    private String description;
    private boolean completed;
    private TaskNode next;

    public TaskNode(String code, String description) {
        this.code = code;
        this.description = description;
        this.completed = false;
        this.next = null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        completed = true;
    }

    public TaskNode getNext() {
        return next;
    }

    public void setNext(TaskNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String status = completed ? "已完成" : "未完成";
        return code + " | " + description + " | " + status;
    }
}
