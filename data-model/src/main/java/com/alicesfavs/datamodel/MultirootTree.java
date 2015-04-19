package com.alicesfavs.datamodel;

import java.util.ArrayList;
import java.util.List;

public class MultirootTree<T>
{

    public final List<Node<T>> roots;

    public MultirootTree()
    {
        roots = new ArrayList<Node<T>>();
    }

    public static class Node<T>
    {
        public final T data;
        public final Node<T> parent;
        public final List<Node<T>> children;

        public Node(T data)
        {
            this(data, null);
        }

        public Node(T data, Node<T> parent)
        {
            this(data, parent, new ArrayList<Node<T>>());
        }

        public Node(T data, Node<T> parent, List<Node<T>> children)
        {
            this.data = data;
            this.parent = parent;
            this.children = children;
        }

        public boolean isLeafNode()
        {
            return children == null || children.size() == 0;
        }

        // depth starting from 1. i.e. if there is no parent, depth is 1.
        public int getDepth()
        {
            int depth = 1;
            Node<T> temp = this;
            while (temp.parent != null)
            {
                depth++;
                temp = temp.parent;
            }
            return depth;
        }
    }

    public List<Node<T>> getLeafNodes()
    {
        List<Node<T>> leafNodes = new ArrayList<Node<T>>();
        for (int index = 0; index < roots.size(); index++)
        {
            collectLeafNodes(leafNodes, roots.get(index));
        }

        return leafNodes;
    }

    private void collectLeafNodes(List<Node<T>> leafNodes, Node<T> currentNode)
    {
        if (currentNode.isLeafNode())
        {
            leafNodes.add(currentNode);
        }
        else
        {
            // use index instead of collection to keep the order
            for (int index = 0; index < currentNode.children.size(); index++)
            {
                collectLeafNodes(leafNodes, currentNode.children.get(index));
            }
        }
    }
}
