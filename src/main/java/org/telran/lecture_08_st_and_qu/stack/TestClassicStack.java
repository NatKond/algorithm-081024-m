package org.telran.lecture_08_st_and_qu.stack;

public class TestClassicStack {
    public static void main(String[] args) {
        ClassicStack stack = new ClassicStack(8);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(6);

        System.out.print("Stack: ");
        stack.printStack();
        System.out.print(" <-- top");
        System.out.println();
        System.out.println("stack.getSize() = " + stack.getSize());
        // remove element from stack
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.getSize() = " + stack.getSize());
        System.out.println("After pop out");
        stack.printStack();

        System.out.println("Call peek()");
        System.out.println(stack.peek());
        stack.printStack();
    }
}
