public class Registration {
    private String id;
    private String name;
    private String status;

    public Registration(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = "registered";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " " + name + " status=" + status;
    }
}
