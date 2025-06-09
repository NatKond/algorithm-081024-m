package org.telran.lecture_12_trees.practiceNew;

import org.telran.lecture_13_bst.practiceNew.AVLNode;
import org.telran.utils.TreePrinter;

public class BinarySearchTree {
    private Node root;
    private TreePrinter<Node> treePrinter;
    private int length = 0;

    /**
     * Конструктор для создания нового бинарного дерева поиска.
     */
    public BinarySearchTree() {
        root = null;
        treePrinter = new TreePrinter<>(node -> String.valueOf(node.getValue()), Node::getLeft, Node::getRight);
        treePrinter.setTspace(2);
        treePrinter.setSquareBranches(true);
        treePrinter.setLrAgnostic(false);
        treePrinter.setHspace(2);
    }

    /**
     * Вставляет новый узел в дерево.
     *
     * @param value Значение нового узла.
     */
    public void insert(int value) {
        // insertNodeRec(root, value);
        if (root == null) {
            root = new Node(value);
            length++;
            return;
        }
        // insertNodeRec(root,value);

        Node currentNode = root;
        while (true) {
            if (value < currentNode.getValue()) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new Node(value));
                    length++;
                    return;
                }
            } else if (value > currentNode.getValue()) {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new Node(value));
                    length++;
                    return;
                }
            } else {
                currentNode.setValue(value);
                return;
            }
        }
    }

    /**
     * Рекурсивно вставляет новый узел в поддерево.
     *
     * @param node  Текущий узел.
     * @param value Значение.
     * @return Новый корень поддерева.
     */
    private Node insertNodeRec(Node node, int value) {
        if (node == null) {
            this.length++;
            return new Node(value);
        }

        if (value < node.getValue()) {
            node.setLeft(insertNodeRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertNodeRec(node.getRight(), value));
        }
        return node;
    }

    /**
     * Ищет значение узла по заданному значению.
     *
     * @param value Значение искомого узла.
     * @return Узел или null.
     */
    public Node getNode(int value) {
        if (root == null) return null;
        // return searchNodeRec(root, value);
        Node currentNode = root;
        while (currentNode != null) {
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeft();
            } else if (value > currentNode.getValue()) {
                currentNode = currentNode.getRight();
            } else {
                return currentNode;
            }
        }
        return null;
    }

    /**
     * Рекурсивно ищет узел с заданным значением.
     */
    private Node searchNodeRec(Node node, int value) {
        if (node == null) return null;
        if (value < node.getValue()) {
            return searchNodeRec(node.getLeft(), value);
        } else if (value > node.getValue()) {
            return searchNodeRec(node.getRight(), value);
        } else {
            return node;
        }
    }

    /**
     * Находит минимальный узел в дереве.
     *
     * @return Узел с минимальным значением или null.
     */
    public Node min() {
        if (root == null) return null;
        Node currentNode = root;
        while (true) {
            if (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            } else {
                return currentNode;
            }
        }
    }

    private Node min(Node node){
        if (node == null) return null;
        Node currentNode = node;
        while (true) {
            if (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            } else {
                return currentNode;
            }
        }
    }

    /**
     * Находит максимальный узел в дереве.
     *
     * @return Узел с максимальным значением или null.
     */
    public Node max() {
        if (root == null) return null;
        Node currentNode = root;
        while (true) {
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
            } else {
                return currentNode;
            }
        }
    }

    /**
     * Возвращает количество узлов в дереве.
     */
    public int length() {
        return length;
    }

    /**
     * Отображает дерево (например, in-order обход).
     */
    public void displayTree() {
        if (root == null) {
            System.out.println("Дерево пустое.");
            return;
        }
        treePrinter.printTree(root);
    }

    /**
     * Удаляет узел с заданным значением.
     */
    public void remove(int value) {
        root = removeRec(root, value);

    }

    /**
     * Рекурсивно удаляет узел.
     */
    private Node removeRec(Node node, int value) {
        if (node == null) return null;

        if (value < node.getValue()) {
            node.setLeft(removeRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(removeRec(node.getRight(), value));
        } else {
            length--;

            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            Node minNode = min(node.getRight());
            node.setValue(minNode.getValue());
            node.setRight(removeRec(node.getRight(), minNode.getValue()));
        }
        return node;
    }

    /**
     * Проверяет, содержится ли значение в дереве.
     */
    public boolean contains(int value) {
        return getNode(value) != null;
    }

    /**
     * Пример использования дерева.
     */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.insert(2);
        bst.insert(1);
        bst.insert(4);
        bst.insert(0);
        bst.remove(1);
        System.out.println(bst.getNode(4));
        System.out.println(bst.length());
        bst.displayTree();

        System.out.println("bst.contains(3) = " + bst.contains(3));
        System.out.println("bst.contains(5) = " + bst.contains(5));
        System.out.println("bst.contains(2) = " + bst.contains(2));
        System.out.println("bst.contains(1) = " + bst.contains(1));
        System.out.println("bst.contains(4) = " + bst.contains(4));
        System.out.println("bst.contains(0) = " + bst.contains(0));
    }

}
