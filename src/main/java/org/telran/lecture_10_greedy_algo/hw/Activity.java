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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        return name + " (" + start + "; " + end + ") " + cost;
    }
}

/*
class Interval {
    private int start;
    private int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

class Plan {
    private List<Interval> intervals;

    public Plan() {
        this.intervals = new ArrayList<>();
    }

    private boolean checkInterval(Interval intervalToAdd){
        if (intervals.isEmpty()){
            intervals.add(intervalToAdd);
            return true;
        }
        int currentStart = 10;
        for (int i = 0; i < intervals.size(); i++) {
            if ((intervalToAdd.getStart() >= currentStart) && (intervalToAdd.getEnd() <= intervals.get(i).getStart())){
                if (intervalToAdd.getEnd() == intervals.get(i).getStart()){
                    intervals.get(i).setStart(intervalToAdd.getStart());
                } else if (intervalToAdd.getStart() == currentStart && i != 0) {
                    intervals.get(i-1).setEnd(intervalToAdd.getEnd());
                } else {
                    intervals.add(intervalToAdd);
                    intervals.sort(Comparator.comparingInt(Interval::getStart));
                }
                return true;
            }
            currentStart = intervals.get(i).getEnd();
        }
        if (intervals.getLast().getEnd() <= intervalToAdd.getStart()){
            if (intervalToAdd.getStart() == intervals.getLast().getEnd()){
                intervals.getLast().setEnd(intervalToAdd.getEnd());
            }else {
                intervals.add(intervalToAdd);
                intervals.sort(Comparator.comparingInt(Interval::getStart));
            }
            return true;
        }
        return false;
    }
}
*/
class ActivityGreed {
    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>(
                List.of(
                        new Activity("drawing", 10, 13, 60),
                        new Activity("writing", 10, 17, 40),
                        new Activity("eating", 12, 14, 40),
                        new Activity("coding", 15, 19, 40),
                        new Activity("reading", 13, 16, 100),
                        new Activity("dancing", 17, 18, 20)
                )
        );

        print(activities);
        System.out.println("------");

        List<Activity> selectedActivities = getMaxCostActivities(activities);
        print(selectedActivities);
    }

    public static List<Activity> getMaxCostActivities(List<Activity> allActivities) {
        if (allActivities == null || allActivities.isEmpty()) {
            throw new IndexOutOfBoundsException("Invalid data.");
        }

        List<Activity> allActivitiesCopy = new ArrayList<>(allActivities);
//        allActivitiesCopy.sort(Comparator.comparingDouble(
//                (Activity activity) -> ((double) activity.getCost() /(activity.getEnd() - activity.getStart()))).reversed());
        allActivitiesCopy.sort(Comparator.comparingInt(((Activity a) -> -a.getCost()))
                .thenComparing(Activity::getEnd));
        List<Activity> selectedActivities = new ArrayList<>();

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

    private static void print(List<Activity> list) {
        for (Activity e : list) {
            System.out.println(e + " ");
        }
    }
}