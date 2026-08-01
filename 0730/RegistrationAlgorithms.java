import java.util.ArrayList;

public class RegistrationAlgorithms {
    public static void mergeSortById(ArrayList<Registration> registrations) {
        if (registrations == null || registrations.size() < 2) {
            return;
        }
        ArrayList<Registration> temp =
            new ArrayList<Registration>(registrations);
        mergeSortById(registrations, temp, 0, registrations.size() - 1);
    }

    public static Registration binarySearchById(
        ArrayList<Registration> sortedRegistrations,
        String id
    ) {
        if (sortedRegistrations == null || id == null) {
            return null;
        }
        int left = 0;
        int right = sortedRegistrations.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = sortedRegistrations.get(mid).getId().compareTo(id);
            if (comparison == 0) {
                return sortedRegistrations.get(mid);
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static ArrayList<Registration> sequentialSearchByName(
        ArrayList<Registration> registrations,
        String name
    ) {
        ArrayList<Registration> result = new ArrayList<Registration>();
        if (registrations == null || name == null) {
            return result;
        }
        for (Registration registration : registrations) {
            if (registration.getName().equalsIgnoreCase(name)) {
                result.add(registration);
            }
        }
        return result;
    }

    public static Registration sequentialSearchById(
        ArrayList<Registration> registrations,
        String id
    ) {
        if (registrations == null || id == null) {
            return null;
        }
        for (Registration registration : registrations) {
            if (registration.getId().equals(id)) {
                return registration;
            }
        }
        return null;
    }

    private static void mergeSortById(
        ArrayList<Registration> registrations,
        ArrayList<Registration> temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortById(registrations, temp, left, mid);
        mergeSortById(registrations, temp, mid + 1, right);
        merge(registrations, temp, left, mid, right);
    }

    private static void merge(
        ArrayList<Registration> registrations,
        ArrayList<Registration> temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (registrations.get(i).getId()
                    .compareTo(registrations.get(j).getId()) <= 0) {
                temp.set(k++, registrations.get(i++));
            } else {
                temp.set(k++, registrations.get(j++));
            }
        }
        while (i <= mid) {
            temp.set(k++, registrations.get(i++));
        }
        while (j <= right) {
            temp.set(k++, registrations.get(j++));
        }
        for (int index = left; index <= right; index++) {
            registrations.set(index, temp.get(index));
        }
    }
}
