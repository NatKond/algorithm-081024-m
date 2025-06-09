package org.telran.lecture_12_trees.practiceNew;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private BinarySearchTree bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
    }

    @Test
    void testEmptyTreeLength() {
        assertEquals(0, bst.length(), "Empty tree should have length 0");
    }

    @Test
    void testInsertSingleNode() {
        bst.insert(10);
        assertEquals(1, bst.length(), "Tree with one node should have length 1");
        bst.displayTree();
        //assertTrue(bst.contains(10), "Tree should contain inserted value");
    }

    @Test
    void testInsertMultipleNodes() {
        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }
        bst.displayTree();
        assertEquals(7, bst.length(), "Tree should have correct length after multiple inserts");

        /*for (int value : values) {
            assertTrue(bst.contains(value), "Tree should contain all inserted values");
        }*/
    }

    @Test
    void testInsertDuplicateValues() {
        bst.insert(10);
        bst.insert(10);

        assertEquals(1, bst.length(), "Tree should not increase size for duplicate inserts");
    }

    @Test
    void testGetNode() {
        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }

        for (int value : values) {
            Node node = bst.getNode(value);
            assertNotNull(node, "getNode should return a node for existing values");
            assertEquals(value, node.getValue(), "getNode should return node with correct value");
        }

        assertNull(bst.getNode(100), "getNode should return null for non-existing values");
    }

    @Test
    void testMin() {
        assertNull(bst.min(), "Min of empty tree should be null");

        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }

        Node minNode = bst.min();
        assertNotNull(minNode, "Min should not be null in non-empty tree");
        assertEquals(2, minNode.getValue(), "Min should return node with smallest value");
    }

    @Test
    void testMax() {
        assertNull(bst.max(), "Max of empty tree should be null");

        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }

        Node maxNode = bst.max();
        assertNotNull(maxNode, "Max should not be null in non-empty tree");
        assertEquals(8, maxNode.getValue(), "Max should return node with largest value");
    }

    @Test
    void testContains() {
        assertFalse(bst.contains(10), "Empty tree should not contain any value");

        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }

        for (int value : values) {
            assertTrue(bst.contains(value), "contains should return true for existing values");
        }

        assertFalse(bst.contains(100), "contains should return false for non-existing values");
    }

    @Test
    void testRemoveSingleNode() {
        bst.insert(10);
        assertEquals(1, bst.length(), "Tree should have one node before removal");

        bst.remove(10);
        assertEquals(0, bst.length(), "Tree should be empty after removing only node");
        bst.displayTree();
        assertFalse(bst.contains(10), "Tree should not contain removed value");
    }

    @Test
    void testRemoveNodeWithNoChildren() {
        // Create a tree:
        //      5
        //    /   \
        //   3     7
        //  / \   / \
        // 2   4 6   8

        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }

        bst.remove(2); // Remove leaf node
        assertEquals(6, bst.length(), "Tree should have one less node after removal");
        assertFalse(bst.contains(2), "Tree should not contain removed value");

        // Check remaining structure
        assertTrue(bst.contains(3), "Parent of removed node should still exist");
        assertTrue(bst.contains(4), "Sibling of removed node should still exist");
    }

    @Test
    void testRemoveNodeWithOneChild() {
        // Create an unbalanced tree:
        //      5
        //    /   \
        //   3     7
        //  /     / \
        // 2     6   8
        //             \
        //              9

        int[] values = {5, 3, 7, 2, 6, 8, 9};
        for (int value : values) {
            bst.insert(value);
        }

        bst.remove(8); // Remove node with one child
        assertEquals(6, bst.length(), "Tree should have one less node after removal");
        assertFalse(bst.contains(8), "Tree should not contain removed value");

        // Check remaining structure
        assertTrue(bst.contains(9), "Child of removed node should still exist");
        assertTrue(bst.contains(7), "Parent of removed node should still exist");
    }

    @Test
    void testRemoveNodeWithTwoChildren() {
        // Create a tree:
        //      5
        //    /   \
        //   3     7
        //  / \   / \
        // 2   4 6   8

        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }

        bst.remove(3); // Remove node with two children
        bst.displayTree();
        assertEquals(6, bst.length(), "Tree should have one less node after removal");
        assertFalse(bst.contains(3), "Tree should not contain removed value");

        // Check remaining structure
        assertTrue(bst.contains(2), "Left child of removed node should still exist");
        assertTrue(bst.contains(4), "Right child of removed node should still exist");
    }

    @Test
    void testRemoveRoot() {
        // Create a tree:
        //      5
        //    /   \
        //   3     7
        //  / \   / \
        // 2   4 6   8

        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }

        bst.remove(5); // Remove root node
        assertEquals(6, bst.length(), "Tree should have one less node after removal");
        assertFalse(bst.contains(5), "Tree should not contain removed root value");

        // Check remaining structure is intact
        for (int value : new int[]{2, 3, 4, 6, 7, 8}) {
            assertTrue(bst.contains(value), "Tree should still contain other values");
        }
    }

    @Test
    void testRemoveNonExistingNode() {
        int[] values = {5, 3, 7, 2, 4, 6, 8};
        for (int value : values) {
            bst.insert(value);
        }

        int initialLength = bst.length();
        bst.remove(100); // Remove non-existing node

        assertEquals(initialLength, bst.length(), "Tree length should not change when removing non-existing node");

        bst.displayTree();
        // Check structure is unchanged
        for (int value : values) {
            assertTrue(bst.contains(value), "Tree should still contain all original values");
        }
    }

    @Test
    void testComplexOperations() {
        // Insert values
        int[] initialValues = {10, 5, 15, 3, 7, 12, 17};
        for (int value : initialValues) {
            bst.insert(value);
        }

        // Remove some values
        bst.remove(3);
        bst.remove(12);

        // Insert new values
        bst.insert(4);
        bst.insert(13);

        // Verify tree structure
        assertFalse(bst.contains(3), "Tree should not contain removed value 3");
        assertFalse(bst.contains(12), "Tree should not contain removed value 12");
        assertTrue(bst.contains(4), "Tree should contain newly inserted value 4");
        assertTrue(bst.contains(13), "Tree should contain newly inserted value 13");

        // Check min and max
        assertEquals(4, bst.min().getValue(), "Min value should be 4 after operations");
        assertEquals(17, bst.max().getValue(), "Max value should be 17 after operations");

        // Check length
        assertEquals(7, bst.length(), "Tree should have correct length after operations");
    }
}