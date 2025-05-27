package org.telran.lecture_13_bst.practiceNew;

import org.telran.lecture_12_trees.practiceNew.Node;
import org.telran.utils.TreePrinter;

import java.util.*;
import java.util.stream.Collectors;

public class AvlTree {
    private AVLNode root;

    /**
     * Возвращает высоту узла.
     *
     * @param node узел
     * @return высота или 0, если null
     */
    private int height(AVLNode node) {
        // TODO: Если node == null, вернуть 0, иначе вернуть node.getHeight()
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    /**
     * Вычисляет баланс-фактор узла (разность высот левого и правого поддеревьев).
     *
     * @param node узел
     * @return баланс-фактор
     */
    private int balanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    /**
     * Обновляет высоту узла на основе высоты его дочерних поддеревьев.
     *
     * @param node узел
     */
    private void updateHeight(AVLNode node) {
        // TODO: Установить высоту как 1 + max(высота левого, высота правого)
        if (node == null) {
            return;
        }
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }

    /**
     * Выполняет правый поворот для восстановления баланса дерева.
     * Используется в случае левостороннего дисбаланса.
     * <pre>
     *
     *  До поворота:          Y         После поворота:    X
     *                       /                            / \
     *                      X                            A   Y
     *                     /
     *                    A
     *
     *  До поворота :     Y            После поворота:      X
     *                   / \                               / \
     *                  X   C                             A   Y
     *                 / \                                   / \
     *                A   B                                 B   C
     * </pre>
     *
     * @param yNode Узел, у которого нарушен баланс
     * @return Новый корень поддерева
     */
    private AVLNode rotateRight(AVLNode yNode) {
        if (yNode == null){
            return null;
        }
        AVLNode xNode = yNode.getLeft();
        // TODO: Выполнить малое правое вращение
        // 1. Сохраняем левое поддерево yNode
        //    Это узел x, который станет новым корнем поддерева
        // 2. Делаем вращение:
        //    - правый ребёнок x становится левым ребёнком yNode
        //    - x становится родителем yNode (yNode правый ребенок для x)
        //
        // 3. Обновляем высоты узлов: сначала yNode, затем x
        //
        // 4. Возвращаем x как новый корень поддерева

        throw new UnsupportedOperationException("method rotateRight(AVLNode zNode) is not implemented yet");
    }

    /**
     * Выполняет левый поворот для восстановления баланса дерева.
     * Используется в случае правостороннего дисбаланса.
     * <pre>
     *
     *  До поворота:      Y         После поворота:      X
     *                     \                            / \
     *                      X                          Y   A
     *                       \
     *                        A
     *
     *  До поворота :    Y              После поворота:       X
     *                  / \                                  / \
     *                 A   X                                Z   C
     *                    / \                              / \
     *                   B   C                            A   B
     * </pre>
     *
     * @param yNode Узел, у которого нарушен баланс
     * @return Новый корень поддерева
     */
    private AVLNode rotateLeft(AVLNode yNode) {
        // TODO: Выполнить малое левое вращение
        // 1. Сохраняем правое поддерево yNode — это x
        // 2. x.left (B) становится правым поддеревом yNode
        // 3. x становится родителем yNode
        // 4. Обновляем высоты узлов (сначала yNode, потом x)
        // 5. Возвращаем x как новый корень поддерева
        throw new UnsupportedOperationException("method rotateLeft(AVLNode zNode) is not implemented yet");
    }

    /**
     * Выполняет двойной поворот влево-вправо (LR-rotation) при левом дисбалансе правого поддерева.
     * <pre>
     *  До поворота:     Z      После первого поворота:    Z     После второго поворота:   Y
     *                  / \                               / \                             / \
     *                 X   D                            Y   D                           X   Z
     *                / \                              / \                             / \ / \
     *               A   Y                            X   C                           A  B C  D
     *                  / \                          / \
     *                 B   C                        A   B
     * </pre>
     *
     * @param zNode Узел с нарушением баланса
     * @return Новый корень сбалансированного поддерева
     */
    private AVLNode rotateLeftRight(AVLNode zNode) {
        // TODO: Сначала малое левое вращение левого поддерева, затем правое вращение
        throw new UnsupportedOperationException("method rotateLeftRight(AVLNode zNode) is not implemented yet");
    }

    /**
     * Выполняет двойной поворот вправо-влево (RL-rotation) при правом дисбалансе левого поддерева.
     * <pre>
     *  До поворота:     Z      После первого поворота:    Z        После второго поворота:    Y
     *                  / \                               / \                                /   \
     *                 A   X                             A   Y                              Z     X
     *                    / \                               / \                            / \   / \
     *                   Y   D                             B   X                          A   B C   D
     *                  / \                                   / \
     *                 B   C                                 C   D
     * <pre>
     * @param zNode Узел с нарушением баланса
     * @return Новый корень сбалансированного поддерева
     */
    private AVLNode rotateRightLeft(AVLNode zNode) {
        // TODO: Сначала малое правое вращение правого поддерева, затем левое вращение
        throw new UnsupportedOperationException("method rotateRightLeft(AVLNode zNode) is not implemented yet");
    }

    /**
     * Выполняет ребалансировку дерева после вставки или удаления.
     *
     * @param node узел
     * @return сбалансированный узел
     */
    private AVLNode rebalance(AVLNode node) {
        // TODO:
        //  1. Вычислить balanceFactor
        //  2. В зависимости от значения выполнить соответствующее вращение:
        //     > 1 и левый баланс >= 0 → rotateRight
        //     > 1 и левый баланс < 0  → rotateLeftRight
        //     < -1 и правый баланс <= 0 → rotateLeft
        //     < -1 и правый баланс > 0  → rotateRightLeft
        throw new UnsupportedOperationException("method rebalance(AVLNode node) is not implemented yet");
    }

    /**
     * Вставляет значение в дерево.
     *
     * @param value значение для вставки
     */
    public void insert(int value) {
        root = insertRecursively(root, value);
    }

    /**
     * Рекурсивная вставка элемента с поддержанием балансировки.
     *
     * @param current текущий узел
     * @param value   вставляемое значение
     * @return корень поддерева
     */
    private AVLNode insertRecursively(AVLNode current, int value) {
        // TODO:
        //  1. Если current == null, создать новый узел
        //  2. Если value < current.value, рекурсивно вставить влево
        //  3. Если value > current.value, рекурсивно вправо
        //  4. Обновить высоту и сбалансировать

        if (current == null) {
            return new AVLNode(value);
        }

        if (value < current.getValue()) {
            current.setLeft(insertRecursively(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(insertRecursively(current.getRight(), value));
        }
        return current;
    }

    /**
     * Удаляет значение из дерева, если оно есть.
     *
     * @param value значение для удаления
     */
    public void remove(int value) {
        root = removeRecursively(root, value);
    }

    /**
     * Рекурсивное удаление с балансировкой.
     *
     * @param node  текущий узел
     * @param value значение для удаления
     * @return новый корень поддерева
     */
    private AVLNode removeRecursively(AVLNode node, int value) {
        // TODO:
        //  1. Ищем узел для удаления
        //  2. Удаление:
        //     - нет детей: вернуть null
        //     - один ребёнок: вернуть не-null ребёнка
        //     - два ребёнка: найти min в правом поддереве, заменить значение, удалить min
        //  3. Обновить высоту и ребалансировать
        throw new UnsupportedOperationException("method removeRecursively(AVLNode node, int value) is not implemented yet");
    }

    /**
     * Возвращает минимальное значение дерева.
     *
     * @return минимальное значение
     */
    public int min() {
        return min(root).getValue();
    }

    private AVLNode min(AVLNode node) {
        // TODO: Идти по левым ссылкам, пока не достигнем самого левого узла
        if (node.getLeft() == null) return node;
        return min(node.getLeft());
    }

    /**
     * Возвращает максимальное значение дерева.
     *
     * @return максимальное значение
     */
    public int max() {
        return max(root).getValue();
    }

    private AVLNode max(AVLNode node) {
        // TODO: Идти по правым ссылкам, пока не достигнем самого правого узла
        if (node.getRight() == null) return node;
        return max(node.getRight());
    }

    /**
     * Проверяет, содержится ли значение в дереве.
     *
     * @param value значение
     * @return true, если найдено
     */
    public boolean contains(int value) {
        return search(value) != null;
    }

    /**
     * Поиск узла по значению.
     *
     * @param value значение
     * @return найденный узел или null
     */
    public AVLNode search(int value) {
        return searchRecursively(root, value);
    }

    private AVLNode searchRecursively(AVLNode node, int value) {
        // TODO: Классический бинарный поиск
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            searchRecursively(node.getLeft(), value);
        }
        if (value > node.getValue()) {
            searchRecursively(node.getRight(), value);
        }
        return node;
    }

    /**
     * Возвращает количество узлов в дереве.
     *
     * @return размер дерева
     */
    public int size() {
        return size(root);
    }

    private int size(AVLNode node) {
        // TODO: Рекурсивно считаем количество узлов: size(left) + size(right) + 1
        if (node == null){
            return 0;
        }
        return size(node.getLeft()) + size(node.getRight()) + 1;
    }

    public boolean isBalanced(AvlTree tree) {
        return isBalanced(tree.root);
    }

    public boolean isBalanced(AVLNode node) {
        if (node == null) {
            return true;
        }

        int balance = balanceFactor(node);
        if (balance < -1 || balance > 1) {
            return false;
        }

        return isBalanced(node.getLeft()) && isBalanced(node.getRight());
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("method isEmpty() is not implemented yet");
    }

    protected AVLNode getRoot() {
        return root;
    }

    public static void main(String[] args) {

        TreePrinter<AVLNode> printer = new TreePrinter<>(
                node -> node.getValue() + " (" + (node.getHeight() - 1) + ")",
                AVLNode::getLeft,
                AVLNode::getRight
        );
        printer.setHspace(2);
        printer.setLrAgnostic(false);
        printer.setSquareBranches(true);
        printer.setTspace(2);

        AvlTree tree = new AvlTree();

        //TODO: Вставка значений в дерево (например, от 1 до 12)
        for (int i = 1; i <= 12; i++) {
            tree.insert(i);
        }

        int[] array = new int[10];
        System.out.println(array.length);
        //TODO: Вывести размер и визуализировать дерево
        System.out.println("Size = " + tree.size());
        printer.printTree(tree.root);
    }
}
