package org.telran.lecture_13_bst.practiceNew;

import org.telran.utils.TreePrinter;

import java.util.NoSuchElementException;

public class AvlTree {
    private AVLNode root;

    /**
     * Возвращает высоту узла.
     *
     * @param node узел
     * @return высота или 0, если null
     */
    private int height(AVLNode node) {
        // Если node == null, вернуть 0, иначе вернуть node.getHeight()
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
        // Установить высоту как 1 + max(высота левого, высота правого)
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
        // 1. Сохраняем левое поддерево yNode
        //    Это узел x, который станет новым корнем поддерева
        if (yNode == null || yNode.getLeft() == null) {
            return yNode;
        }
        AVLNode xNode = yNode.getLeft();

        // 2. Делаем вращение:
        //    - правый ребёнок x становится левым ребёнком yNode
        AVLNode bSubTree = xNode.getRight();
        yNode.setLeft(bSubTree);
        //    - x становится родителем yNode (yNode правый ребенок для x)
        xNode.setRight(yNode);
        // 3. Обновляем высоты узлов: сначала yNode, затем x
        updateHeight(yNode);
        updateHeight(xNode);
        // 4. Возвращаем x как новый корень поддерева
        return xNode;
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
     *                 A   X                                Y   C
     *                    / \                              / \
     *                   B   C                            A   B
     * </pre>
     *
     * @param yNode Узел, у которого нарушен баланс
     * @return Новый корень поддерева
     */
    private AVLNode rotateLeft(AVLNode yNode) {
        // 1. Сохраняем правое поддерево yNode — это x
        if (yNode == null || yNode.getRight() == null) {
            return yNode;
        }
        AVLNode xNode = yNode.getRight();
        // 2. x.left (B) становится правым поддеревом yNode
        AVLNode bSubTree = xNode.getLeft();
        yNode.setRight(bSubTree);
        // 3. x становится родителем yNode
        xNode.setLeft(yNode);
        // 4. Обновляем высоты узлов (сначала yNode, потом x)
        updateHeight(xNode);
        updateHeight(yNode);
        // 5. Возвращаем x как новый корень поддерева
        return xNode;
    }

    /**
     * Выполняет двойной поворот влево-вправо (LR-rotation) при левом дисбалансе правого поддерева.
     * <pre>
     *  До поворота:     Z      После первого поворота:    Z     После второго поворота:   Y
     *                  / \                               / \                             / \
     *                 X   D                             Y   D                           X   Z
     *                / \                               / \                             / \ / \
     *               A   Y                             X   C                           A  B C  D
     *                  / \                           / \
     *                 B   C                         A   B
     * </pre>
     *
     * @param zNode Узел с нарушением баланса
     * @return Новый корень сбалансированного поддерева
     */
    private AVLNode rotateLeftRight(AVLNode zNode) {
        // Сначала малое левое вращение левого поддерева, затем правое вращение
        if (zNode == null || zNode.getLeft() == null) {
            return zNode;
        }
        AVLNode yNode = rotateLeft(zNode.getLeft());
        zNode.setLeft(yNode);
        return rotateRight(zNode);
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
        //Сначала малое правое вращение правого поддерева, затем левое вращение
        if (zNode == null || zNode.getRight() == null) {
            return zNode;
        }
        AVLNode yNode = rotateRight(zNode.getRight());
        zNode.setRight(yNode);
        return rotateLeft(zNode);
    }

    /**
     * Выполняет ребалансировку дерева после вставки или удаления.
     *
     * @param node узел
     * @return сбалансированный узел
     */
    private AVLNode rebalance(AVLNode node) {
        //  1. Вычислить balanceFactor
        if (node == null) {
            return null;
        }
        //  1. Вычислить balanceFactor
        updateHeight(node);
        int balance = balanceFactor(node);
        //  2. В зависимости от значения выполнить соответствующее вращение:
        if (balance > 1) {
            if (balanceFactor(node.getLeft()) >= 0) {
                return rotateRight(node);
            } else {
                return rotateLeftRight(node);
            }
        }
        if (balance < -1) {
            if (balanceFactor(node.getRight()) < 0) {
                return rotateLeft(node);
            } else {
                return rotateRightLeft(node);
            }
        }
        //     > 1 и левый баланс >= 0 → rotateRight
        //     > 1 и левый баланс < 0  → rotateLeftRight
        //     < -1 и правый баланс <= 0 → rotateLeft
        //     < -1 и правый баланс > 0  → rotateRightLeft
        return node;
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
        //  1. Если current == null, создать новый узел
        if (current == null) {
            return new AVLNode(value);
        }
        //  2. Если value < current.value, рекурсивно вставить влево
        if (value < current.getValue()) {
            current.setLeft(insertRecursively(current.getLeft(), value));
        }
        //  3. Если value > current.value, рекурсивно вправо
        else if (value > current.getValue()) {
            current.setRight(insertRecursively(current.getRight(), value));
        }
        //  4. Обновить высоту и сбалансировать
        return rebalance(current);
        //return current;
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
        //  1. Ищем узел для удаления
        if (node == null) {
            return null;
        }
        //  2. Удаление:
        if (value < node.getValue()) {
            node.setLeft(removeRecursively(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(removeRecursively(node.getRight(), value));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            AVLNode minNode = min(node.getRight());
            node.setValue(minNode.getValue());
            node.setRight(removeRecursively(node.getRight(), node.getValue()));

        }
        return rebalance(node);
    }

    /**
     * Возвращает минимальное значение дерева.
     *
     * @return минимальное значение
     */
    public int min() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return min(root).getValue();
    }

    private AVLNode min(AVLNode node) {
        // Идти по левым ссылкам, пока не достигнем самого левого узла
        if (node.getLeft() == null) return node;
        return min(node.getLeft());
    }

    /**
     * Возвращает максимальное значение дерева.
     *
     * @return максимальное значение
     */
    public int max() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return max(root).getValue();
    }

    private AVLNode max(AVLNode node) {
        // Идти по правым ссылкам, пока не достигнем самого правого узла
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
        // Классический бинарный поиск
        if (node == null) {
            return null;
        }
        if (value < node.getValue()) {
            return searchRecursively(node.getLeft(), value);
        }
        if (value > node.getValue()) {
            return searchRecursively(node.getRight(), value);
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
        // Рекурсивно считаем количество узлов: size(left) + size(right) + 1
        if (node == null) {
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
        return size() == 0;
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

        // Вставка значений в дерево (например, от 1 до 12)
        for (int i = 1; i < 14; i++) {
            tree.insert(i);
            printer.printTree(tree.root);
            System.out.println(tree.balanceFactor(tree.root));
        }
        tree.remove(5);
        //Вывести размер и визуализировать дерево
        System.out.println(tree.isBalanced(tree.getRoot()));
        System.out.println("Size = " + tree.size());
        printer.printTree(tree.root);
    }
}
