public class RepairTask {
    private String id;
    private String equipmentName;
    private int priority;
    private int registerOrder;
    private String status;

    public RepairTask(
        String id,
        String equipmentName,
        int priority,
        int registerOrder
    ) {
        this.id = id;
        this.equipmentName = equipmentName;
        this.priority = priority;
        this.registerOrder = registerOrder;
        this.status = "waiting";
    }

    public String getId() {
        return id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getPriority() {
        return priority;
    }

    public int getRegisterOrder() {
        return registerOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " equipment=" + equipmentName
            + " priority=" + priority
            + " order=" + registerOrder
            + " status=" + status;
    }
}
