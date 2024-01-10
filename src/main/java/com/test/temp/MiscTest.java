package com.test.temp;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MiscTest {

    static class Node { 
        Node left, right; 
        int key; 
    }
     
    static Node newNode(int key) 
    { 
        Node ptr = new Node(); 
        ptr.key = key; 
        ptr.left = null;
        ptr.right = null; 
        return ptr; 
    } 
     
    // Standard BST insert function 
    static Node insert(Node root, int key) 
    { 
        if (root == null) 
            root = newNode(key); 
        else if (root.key > key) 
            root.left = insert(root.left, key); 
        else if (root.key < key) 
            root.right = insert(root.right, key); 
        return root; 
    }

    // This function returns distance of x from 
    // root. This function assumes that x exists 
    // in BST and BST is not NULL. 
    static int distanceFromRoot(Node root, int x) 
    { 
        if (root.key == x) 
            return 0; 
        else if (root.key > x) 
            return 1 + distanceFromRoot(root.left, x); 
        return 1 + distanceFromRoot(root.right, x); 
    } 
    
    // Returns minimum distance between a and b. 
    // This function assumes that a and b exist 
    // in BST. 
    static int distanceBetween2(Node root, int a, int b) 
    { 
        if (root == null) 
            return 0; 
    
        // Both keys lie in left 
        if (root.key > a && root.key > b) 
            return distanceBetween2(root.left, a, b); 
    
        // Both keys lie in right 
        if (root.key < a && root.key < b) // same path 
            return distanceBetween2(root.right, a, b); 
    
        // Lie in opposite directions (Root is 
        // LCA of two nodes) 
        if (root.key >= a && root.key <= b) 
            return distanceFromRoot(root, a) + distanceFromRoot(root, b);
            
        return 0;
    } 

    static int findDistWrapper(Node root, int a, int b) 
    { 
        int temp = 0;
        if (a > b) 
        {
            temp = a;
            a = b;
            b = temp;
        } 
        return distanceBetween2(root, a, b); 
    }

    public static int findRoot(Vector<Vector<Integer> > edgeNodes) {
        int tempCommon=0, root=0, counter=0;
        for (Vector<Integer> nodes: edgeNodes) {
            for (Integer nodehash : nodes) {
                System.out.println("nodehash is === " +nodehash);
            }
        }

        for(int i=0;i<edgeNodes.size();i++) {
            Vector<Integer> temp = edgeNodes.get(i);
            if (temp.contains(tempCommon)) {
                root = tempCommon;
                counter++;
            }
            if (temp.firstElement() < temp.lastElement())
                tempCommon = temp.firstElement();
            else
                tempCommon = temp.lastElement();
            if (counter>=(edgeNodes.size()-1)) {
                System.out.println("tempcommon is " +tempCommon);
                System.out.println("root is " +root);
            }
        }
        return root;
    }

    public static void main(String args[]) {
        System.out.println("hello..... testing misctest");
        Vector<Vector<Integer> > edges = new Vector<Vector<Integer> >();
         
        Vector<Integer> vecInt1 = new Vector<Integer>();
        Vector<Integer> vecInt2 = new Vector<Integer>();
        Vector<Integer> vecInt3 = new Vector<Integer>();
        vecInt1.add(0, 2);
        vecInt1.add(0, 1);
        edges.add(vecInt1);

        vecInt2.add(0, 3);
        vecInt2.add(0, 1);
        edges.add(vecInt2);

        vecInt3.add(0, 4);
        vecInt3.add(0, 1);
        edges.add(vecInt3);

        int treeRoot = findRoot(edges);

        Node rootNode = null; 
        rootNode = insert(rootNode, treeRoot); 
        insert(rootNode, 2); 
        insert(rootNode, 4); 
        insert(rootNode, 3); 
        System.out.println(findDistWrapper(rootNode,2, 4));
        List<Integer> abc = new ArrayList<Integer>();
        abc.size();
    }
}
