package org.telran.lecture_15_hash.practiceNew;

/**
 * Класс для поиска самой длинной подстроки без повторяющихся символов.
 */
public class LongestSubstring {

    /**
     * Находит самую длинную подстроку без повторяющихся символов.
     *
     * @param s входная строка
     * @return самая длинная подстрока без повторов
     */
    public static String getMaxSubstring(String s) {
        // Проверка на null и пустую строку
        if (s == null || s.isBlank()) {
            return "";
        }

        // Создайте структуру данных для хранения уникальных символов
        UniqueBookStore set = new UniqueBookStore(s.length());

        // Заводим два указателя: левый (начало окна) и правый (конец окна)
        int left = 0;
        // Также создаём переменные: maxLength и startIndex — для отслеживания длины и позиции максимальной подстроки
        int maxLength = 0;
        int startIndex = 0;

        // Перебираем строку с помощью правого указателя:
        for (int right = 0; right < s.length(); right++) {
            String charValue = String.valueOf(s.charAt(right));
            // Если символ уже есть в множестве:
            // Удаляем символ на позиции левого указателя из множества
            // Сдвигаем левый указатель вправо
            while (set.contains(charValue)) {
                set.remove(String.valueOf(s.charAt(left)));
                left++;
            }
            // Добавляем текущий символ в множество
            set.add(charValue);
            // Если текущая длина окна больше максимальной — обновляем maxLength и startIndex
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                startIndex = left;
            }
        }
        // Верните подстроку от startIndex до startIndex + maxLength
        return s.substring(startIndex, startIndex + maxLength); // заглушка
    }

    public static void main(String[] args) {
        System.out.println(getMaxSubstring("abcdecfg1"));
        System.out.println(getMaxSubstring("aaaa"));
    }
}