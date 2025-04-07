package org.telran.lecture_08_st_and_qu.hw;

// Дана строка s, содержащая только символы '(', ')', '{', '}', '[' и ']'.
// Определите, является ли входная строка допустимой.
//
// Входная строка допустима, если:
//
// 1. Открытые скобки должны быть закрыты скобками того же типа.
// 2. Открытые скобки должны быть закрыты в правильном порядке.
// 3. Каждой закрывающейся скобке соответствует открытая скобка того же типа.

// Пример 1:
// Input: s = "()"
// Output: true

// Пример 2:
// Input: s = "[()[]{}]"
// Output: true

// Пример 3:
// Input: s = "(]"
// Output: false

// Пример 4:
// Input: s = "([(]))"
// Output: false

import java.util.Queue;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println("areParenthesesValid(\"()\") = " + areParenthesesValid("()"));
        System.out.println("areParenthesesValid(\"(()())\") = " + areParenthesesValid("(()())"));
        System.out.println("areParenthesesValid(\"[()[]{}]\") = " + areParenthesesValid("[()[]{}]"));
        System.out.println("areParenthesesValid(\")))\") = " + areParenthesesValid(")))"));
        System.out.println("areParenthesesValid(\"))]})\") = " + areParenthesesValid(")))"));
        System.out.println("areParenthesesValid(\"([(]))\") = " + areParenthesesValid("([(]))"));
    }

    public static boolean areParenthesesValid(String text) {
        Stack<Character> parenthesesStack = new Stack<>();
        for (char currentParenthesis : text.toCharArray()) {
            if (currentParenthesis == '(' || currentParenthesis == '[' || currentParenthesis == '{') {
                parenthesesStack.push(currentParenthesis);
            } else {
                if (!parenthesesStack.isEmpty()) {
                    char lastParenthesis = parenthesesStack.pop();
                    if ((lastParenthesis == '(' && currentParenthesis == ')') ||
                        (lastParenthesis == '[' && currentParenthesis == ']') ||
                        (lastParenthesis == '{' && currentParenthesis == '}')) {
                        continue;
                    }
                }
                return false;
            }
        }
        return parenthesesStack.isEmpty();
    }
}
