package org.telran.lecture_13_bst.practiceNew;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {
    private AvlTree tree;

    @BeforeEach
    void setUp() {
        tree = new AvlTree();
    }

    // Вспомогательные методы для проверки баланса дерева
    private int height(AVLNode node) {
        return node == null ? 0 : node.getHeight();
    }

    private int balanceFactor(AVLNode node) {
        return node == null ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    @Test
    void testEmptyTree() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertThrows(NoSuchElementException.class, () -> tree.min());
        assertThrows(NoSuchElementException.class, () -> tree.max());
        assertFalse(tree.contains(5));
        assertNull(tree.search(5));
    }

    @Test
    void testInsertSingleNode() {
        tree.insert(5);

        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        assertEquals(5, tree.min());
        assertEquals(5, tree.max());
        assertTrue(tree.contains(5));
        assertNotNull(tree.search(5));
        assertEquals(5, tree.search(5).getValue());
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testInsertMultipleNodes() {
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        assertEquals(7, tree.size());
        assertEquals(2, tree.min());
        assertEquals(8, tree.max());
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(7));
        assertFalse(tree.contains(1));
        assertFalse(tree.contains(9));
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testInsertDuplicates() {
        tree.insert(5);
        tree.insert(5);
        tree.insert(5);

        assertEquals(1, tree.size());
        assertEquals(5, tree.min());
        assertEquals(5, tree.max());
    }

    @Test
    void testLeftRotation() {
        // Right-heavy scenario that should trigger left rotation
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        // Check if the tree is balanced
        AVLNode root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(10, root.getLeft().getValue());
        assertEquals(30, root.getRight().getValue());
        assertTrue(tree.isBalanced(root));
    }

    @Test
    void testRightRotation() {
        // Left-heavy scenario that should trigger right rotation
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        // Check if the tree is balanced
        AVLNode root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(10, root.getLeft().getValue());
        assertEquals(30, root.getRight().getValue());
        assertTrue(tree.isBalanced(root));
    }

    @Test
    void testLeftRightRotation() {
        // Scenario that should trigger a left-right rotation
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);

        // Check if the tree is balanced
        AVLNode root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(10, root.getLeft().getValue());
        assertEquals(30, root.getRight().getValue());
        assertTrue(tree.isBalanced(root));
    }

    @Test
    void testRightLeftRotation() {
        // Scenario that should trigger a right-left rotation
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);

        // Check if the tree is balanced
        AVLNode root = tree.getRoot();
        assertEquals(20, root.getValue());
        assertEquals(10, root.getLeft().getValue());
        assertEquals(30, root.getRight().getValue());
        assertTrue(tree.isBalanced(root));
    }

    @Test
    void testRemoveSingleNode() {
        tree.insert(5);
        tree.remove(5);

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertFalse(tree.contains(5));
    }

    @Test
    void testRemoveNonExistentNode() {
        tree.insert(5);
        tree.remove(10);

        assertEquals(1, tree.size());
        assertTrue(tree.contains(5));
    }

    @Test
    void testRemoveLeafNode() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);

        assertEquals(4, tree.size());
        tree.remove(5);

        assertEquals(3, tree.size());
        assertFalse(tree.contains(5));
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testRemoveNodeWithOneChild() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);

        assertEquals(4, tree.size());
        tree.remove(10);

        assertEquals(3, tree.size());
        assertFalse(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testRemoveNodeWithTwoChildren() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);

        assertEquals(5, tree.size());
        tree.remove(10);

        assertEquals(4, tree.size());
        assertFalse(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(15));
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testRemoveRoot() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);

        assertEquals(3, tree.size());
        tree.remove(20);

        assertEquals(2, tree.size());
        assertFalse(tree.contains(20));
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(30));
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testBalanceAfterRemove() {
        // Create a tree that will become unbalanced after removal
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(12);
        tree.insert(37);
        tree.insert(62);
        tree.insert(87);
        tree.insert(6);
        tree.insert(18);

        // Remove nodes to create potential imbalance
        tree.remove(87);
        tree.remove(75);

        // Verify tree is still balanced
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testInsertAscendingOrder() {
        for (int i = 1; i <= 20; i++) {
            tree.insert(i);
            assertTrue(tree.isBalanced(tree.getRoot()), "Tree should be balanced after inserting " + i);
        }

        assertEquals(20, tree.size());
        assertEquals(1, tree.min());
        assertEquals(20, tree.max());
    }

    @Test
    void testInsertDescendingOrder() {
        for (int i = 20; i >= 1; i--) {
            tree.insert(i);
            assertTrue(tree.isBalanced(tree.getRoot()), "Tree should be balanced after inserting " + i);
        }

        assertEquals(20, tree.size());
        assertEquals(1, tree.min());
        assertEquals(20, tree.max());
    }

    @Test
    void testInsertRandomOrder() {
        int[] values = {42, 15, 88, 6, 27, 63, 94, 3, 9, 21, 36, 51, 79, 91, 99};

        for (int value : values) {
            tree.insert(value);
            assertTrue(tree.isBalanced(tree.getRoot()), "Tree should be balanced after inserting " + value);
        }

        assertEquals(values.length, tree.size());
        assertEquals(3, tree.min());
        assertEquals(99, tree.max());
    }

    @Test
    void testMinMaxCornerCases() {
        // Single node
        tree.insert(5);
        assertEquals(5, tree.min());
        assertEquals(5, tree.max());

        // Two nodes
        tree.insert(10);
        assertEquals(5, tree.min());
        assertEquals(10, tree.max());

        // Remove min
        tree.remove(5);
        assertEquals(10, tree.min());
        assertEquals(10, tree.max());
    }

    @Test
    void testComplexScenario() {
        // Insert a series of nodes
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(12);
        tree.insert(37);
        tree.insert(62);
        tree.insert(87);

        // Verify tree state
        assertEquals(7, tree.size());
        assertEquals(12, tree.min());
        assertEquals(87, tree.max());
        assertTrue(tree.isBalanced(tree.getRoot()));

        // Remove nodes and verify balancing is maintained
        tree.remove(12);
        assertEquals(6, tree.size());
        assertTrue(tree.isBalanced(tree.getRoot()));

        tree.remove(87);
        assertEquals(5, tree.size());
        assertTrue(tree.isBalanced(tree.getRoot()));

        tree.remove(50);  // Remove root
        assertEquals(4, tree.size());
        assertTrue(tree.isBalanced(tree.getRoot()));

        // Insert more nodes
        tree.insert(40);
        tree.insert(35);

        assertEquals(6, tree.size());
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testSearchCornerCases() {
        assertNull(tree.search(10));

        tree.insert(10);
        assertNotNull(tree.search(10));
        assertNull(tree.search(5));

        tree.insert(5);
        tree.insert(15);
        assertNotNull(tree.search(5));
        assertNotNull(tree.search(10));
        assertNotNull(tree.search(15));
        assertNull(tree.search(20));
    }

    @Test
    void testHeightCalculation() {
        tree.insert(10);
        assertEquals(1, tree.getRoot().getHeight());

        tree.insert(5);
        assertEquals(2, tree.getRoot().getHeight());

        tree.insert(15);
        assertEquals(2, tree.getRoot().getHeight());

        tree.insert(3);
        assertEquals(3, tree.getRoot().getHeight());
        assertTrue(tree.isBalanced(tree.getRoot()));
    }

    @Test
    void testRemoveAllNodes() {
        int[] values = {50, 25, 75, 12, 37, 62, 87};

        for (int value : values) {
            tree.insert(value);
        }

        assertEquals(values.length, tree.size());

        // Remove all nodes in order
        for (int value : values) {
            tree.remove(value);
            assertTrue(tree.isBalanced(tree.getRoot()));
        }

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void testStressTest() {
        // Insert a large number of elements to stress test the tree
        for (int i = 0; i < 1000; i++) {
            tree.insert(i);
            assertTrue(tree.isBalanced(tree.getRoot()));
        }

        assertEquals(1000, tree.size());

        // Remove elements randomly
        for (int i = 0; i < 1000; i += 2) {
            tree.remove(i);
            assertTrue(tree.isBalanced(tree.getRoot()));
        }

        assertEquals(500, tree.size());
    }

}