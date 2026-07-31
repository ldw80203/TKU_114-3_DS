public class EmployeeSearchSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E101", "Amy", "Sales", "1201"),
            new Employee("E105", "Ben", "IT", "1302"),
            new Employee("E105", "Bella", "IT", "1303"),
            new Employee("E210", "Cara", "HR", "1401"),
            new Employee("E330", "Dan", "Finance", "1502")
        };

        if (hasDuplicateId(employees)) {
            System.out.println("注意：資料中有重複編號；查詢時回傳第一筆符合資料。");
        }
        printSearchResult(employees, "E105");
        printSearchResult(employees, "E999");
        printSearchResult(new Employee[0], "E101");
    }

    public static Employee binarySearchById(Employee[] employees, String targetId) {
        if (employees == null || employees.length == 0 || targetId == null) {
            return null;
        }

        int low = 0;
        int high = employees.length - 1;
        Employee answer = null;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = employees[mid].getId().compareToIgnoreCase(targetId);

            if (comparison == 0) {
                answer = employees[mid];
                high = mid - 1;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return answer;
    }

    public static boolean hasDuplicateId(Employee[] employees) {
        if (employees == null) {
            return false;
        }

        for (int index = 1; index < employees.length; index++) {
            if (employees[index - 1].getId().equalsIgnoreCase(employees[index].getId())) {
                return true;
            }
        }
        return false;
    }

    private static void printSearchResult(Employee[] employees, String targetId) {
        Employee result = binarySearchById(employees, targetId);
        if (result == null) {
            System.out.println(targetId + "：找不到員工資料");
        } else {
            System.out.println("找到：" + result);
        }
    }
}
