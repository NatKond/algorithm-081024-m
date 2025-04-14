package org.telran.lecture_10_greedy_algo.hw;

// Дано:
//  Список активностей, каждая из которых характеризуется четырьмя параметрами:
//      Название (строка, уникальное имя активности)
//      Время начала (целое число, например, час начала активности)
//      Время окончания (целое число, например, час окончания активности)
//      Цена (целое число, положительное значение)
//Найти:
// Максимальный по суммарной стоимости набор, непересекающихся по времени активностей.
// Условия:
//  Активности считаются непересекающимися, если время окончания одной активности меньше или равно времени начала другой активности.
//  Необходимо использовать жадный алгоритм.


class Activity {
    private String name;
    private int start;
    private int end;
    private int cost;

    public Activity(String name, int start, int end, int cost) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (" + start + "; " + end + ") :" + cost;
    }
}

class ActivityGreed {
    public static void main(String[] args) {
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(new Activity("drawing", 10, 13, 10));
        activities.add(new Activity("writing", 10, 17, 16));
        activities.add(new Activity("eating", 12, 14, 8));
        activities.add(new Activity("coding", 15, 19, 12));
        activities.add(new Activity("reading", 13, 16, 14));
        activities.add(new Activity("dancing", 17, 18, 6));

        print(activities);
        System.out.println("------");

        List<Activity> selectedActivities = selectActivities(activities);
        print(selectedActivities);
    }

    public static List<Activity> selectActivities(List<Activity> allActivities) {
        if (allActivities == null || allActivities.isEmpty()) {
            throw new IndexOutOfBoundsException("Invalid data.");
        }

        List<Activity> allActivitiesCopy = new ArrayList<>(allActivities);
        allActivitiesCopy.sort(Comparator.comparingInt(activity ->((activity.getEnd() - activity.getStart()) * 100 / activity.getCost())));
        List<Activity> selectedActivities = new ArrayList<>();
        print(allActivitiesCopy);
        System.out.println();

        Activity first = allActivitiesCopy.get(0);

        int currentEndTime = first.getEnd();
        int currentStartTime = first.getStart();
        selectedActivities.add(first);

        for (int i = 1; i < allActivitiesCopy.size(); i++) {
            if (allActivitiesCopy.get(i).getStart() >= currentEndTime) {
                currentEndTime = allActivitiesCopy.get(i).getEnd();
                selectedActivities.add(allActivitiesCopy.get(i));
            }

            if (allActivitiesCopy.get(i).getEnd() <= currentStartTime) {
                currentStartTime = allActivitiesCopy.get(i).getStart();
                selectedActivities.add(allActivitiesCopy.get(i));
            }
        }
        return selectedActivities;
    }

    public static void print(List<Activity> list) {
        for (Activity e : list) {
            System.out.println(e + " ");
        }
    }
}

