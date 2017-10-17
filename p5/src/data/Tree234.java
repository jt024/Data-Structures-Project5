/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author jt024
 */
public class Tree234 {

    private Node root = new Node();	// make root node
    private String treeString;

    public int find(String key) {
        Node focus = root;
        int childNumber;
        while (true) {
            if ((childNumber = focus.findItem(key)) != -1) {
                return childNumber;               // found it
            } else if (focus.isLeaf()) {
                return -1;                        // can't find it
            } else // search deeper
            {
                focus = getNextChild(focus, key);
            }
        }  // end while
    }
    // insert a DataItem

    public void insert(String dWord) {	// Performs the splits
        // in a top-down (root -----> leaf) fashion.
        Node focus = root;

        DataItem tempItem = new DataItem(dWord);

        while (true) {

            if (focus.isFull()) { // if node full
                split(focus);	// split it
                focus = focus.getParent(); 		// back up
                // search once
                focus = getNextChild(focus, dWord);
            } // end if (node is full)
            else if (focus.isLeaf()) {	// if node is leaf	
                break;					// jump to insert
            } else {	// node is not full, not a leaf; so go to lower level 
                focus = getNextChild(focus, dWord);
            }
        }  // end while

        // insert new DataItem
        focus.insertItem(tempItem);

    }    // end insert

    private void split(Node thisNode) { // split the node

        // assumes node is full
        DataItem itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;

        // two right most items are removed from the node and stored
        itemC = thisNode.removeItem();	// remove items from
        itemB = thisNode.removeItem();	// this node
        child2 = thisNode.disconnectChild(2);	// remove children
        child3 = thisNode.disconnectChild(3);	// from this node

        // make new node, goes to the right of the node being split
        Node newRight = new Node();

        if (thisNode == root) { // if this is the root
            // make new root
            root = new Node();
            // root is our parent
            parent = root;
            // connect to parent
            root.connectChild(0, thisNode);
        } else {// this node not the root get parent
            parent = thisNode.getParent();
        }
        // deal with parent

        // item B is inserted in the parent node
        itemIndex = parent.insertItem(itemB); // deal with parent
        // total items?
        int n = parent.getNumItems();

        // move parent's connections
        // one child to the right
        for (int j = n - 1; j > itemIndex; j--) {
            Node temp = parent.disconnectChild(j);
            parent.connectChild(j + 1, temp);
        }
        // connect newRight to parent
        parent.connectChild(itemIndex + 1, newRight);

        // deal with newRight
        newRight.insertItem(itemC);             // item C to newRight
        newRight.connectChild(0, child2);       // connect to 0 and 1
        newRight.connectChild(1, child3);       // on newRight
    }   // end split()

    private Node getNextChild(Node theNode, String keyWord) {

        int j;
        // assumes node is not empty, not full, not a leaf
        int numItems = theNode.getNumItems();

        // for each item in node
        for (j = 0; j < numItems; j++) {
            // is the value given less than the value at the 
            //first index of the array?                           
            if (keyWord.compareToIgnoreCase(theNode.getItem(j).dData) < 0) {
                // if so, return left child so we can use it at another 
                // point in time
                return theNode.getChild(j);
            }

        } // end for
        return theNode.getChild(j);
        // otherwise, our value is greater we're greater, so
        // we return right child (the child right after the 
        // value given, greater)        
    }

    public String displayTree() {
        recDisplayTree(root, 0, 0);
        return treeString;
    }

    private void recDisplayTree(Node thisNode, int level, int childNumber) {
        treeString += "level=" + level + " child=" + childNumber + " ";
        treeString += thisNode.displayNode();               // display this node

        // call ourselves for each child of this node
        int numItems = thisNode.getNumItems();
        for (int j = 0; j < numItems + 1; j++) {
            Node nextNode = thisNode.getChild(j);

            if (nextNode != null) {
                recDisplayTree(nextNode, level + 1, j);
            } else {
                return;
            }
        }
    }         // end recDisplayTree()

}               // end class Tree234

