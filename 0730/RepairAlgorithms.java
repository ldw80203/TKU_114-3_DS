import java.util.ArrayList;

public class RepairAlgorithms {
    public static void mergeSortByPriorityDesc(ArrayList<RepairTask> tasks) {
        if (tasks == null || tasks.size() < 2) {
            return;
        }
        ArrayList<RepairTask> temp = new ArrayList<RepairTask>(tasks);
        mergeSortByPriorityDesc(tasks, temp, 0, tasks.size() - 1);
    }

    public static RepairTask searchById(ArrayList<RepairTask> tasks, String id) {
        if (tasks == null || id == null) {
            return null;
        }
        for (RepairTask task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public static ArrayList<RepairTask> searchByEquipmentName(
        ArrayList<RepairTask> tasks,
        String equipmentName
    ) {
        ArrayList<RepairTask> result = new ArrayList<RepairTask>();
        if (tasks == null || equipmentName == null) {
            return result;
        }
        for (RepairTask task : tasks) {
            if (task.getEquipmentName().equalsIgnoreCase(equipmentName)) {
                result.add(task);
            }
        }
        return result;
    }

    private static void mergeSortByPriorityDesc(
        ArrayList<RepairTask> tasks,
        ArrayList<RepairTask> temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByPriorityDesc(tasks, temp, left, mid);
        mergeSortByPriorityDesc(tasks, temp, mid + 1, right);
        merge(tasks, temp, left, mid, right);
    }

    private static void merge(
        ArrayList<RepairTask> tasks,
        ArrayList<RepairTask> temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            RepairTask leftTask = tasks.get(i);
            RepairTask rightTask = tasks.get(j);
            if (leftTask.getPriority() >= rightTask.getPriority()) {
                temp.set(k++, tasks.get(i++));
            } else {
                temp.set(k++, tasks.get(j++));
            }
        }
        while (i <= mid) {
            temp.set(k++, tasks.get(i++));
        }
        while (j <= right) {
            temp.set(k++, tasks.get(j++));
        }
        for (int index = left; index <= right; index++) {
            tasks.set(index, temp.get(index));
        }
    }
}
