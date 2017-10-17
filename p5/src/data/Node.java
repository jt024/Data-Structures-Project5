/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 * 234 Tree Node Only
 *
 * @author jt024
 */
public class Node {

    // Max size
    private static final int SIZE = 4;

    private int numItems;
    private Node parent;
    private Node children[] = new Node[SIZE];
    private DataItem items[] = new DataItem[SIZE - 1];

    public void connectChild(int childNum, Node child) {	//  Connect child to this Node
        children[childNum] = child;
        if (child != null) {
            child.parent = this;
        }
    }

    public Node disconnectChild(int childNum) {	//  Disconnect child from this Node
        Node temp = children[childNum];
        children[childNum] = null;
        return temp;
    }

    public Node getChild(int childNum) {	// Returns child
        return children[childNum];
    }

    public Node getParent() {	// Returns parent
        return parent;
    }

    public Boolean isLeaf() {
        // test if child is a leaf
        return children[0] == null;
    }

    public int getNumItems() {
        return numItems;
    }

    public DataItem getItem(int index) {
        return items[index];
    }

    public Boolean isFull() {
        return numItems == SIZE - 1;
    }

    public int findItem(String term) {
        for (int j = 0; j < SIZE; j++) {

            if (items[j] == null) {
                break;
            } else if (items[j].dData.equalsIgnoreCase(term)) {
                return j;
            }
        }
        return -1;
    }	  // end findItem()

    public int insertItem(DataItem newItem) {
        if (findItem(newItem.dData) != -1) {
            items[findItem(newItem.dData)].count++;
            return 0;
        }
        numItems++;	// will add new item
        String newKey = newItem.dData;

        for (int j = numItems-1; j >= 0; j--) {

            if (items[j] == null) {
                continue;
            } else {
                String itsKey = items[j].dData;
                if (newKey.compareToIgnoreCase(itsKey) < 0) {
                    items[j + 1] = items[j];
                } else {
                    items[j + 1] = newItem;
                    return j + 1;
                    
                }
            }	// end else (not null)
        }		// end for  ---->   //	shifted all items,
        items[0] = newItem;		//	insert new item
        return 0;

    }   // end insertItem()

    public DataItem removeItem() {
        DataItem temp = items[numItems - 1];
        items[numItems - 1] = null;
        numItems--;
        return temp;
    }

    public String displayNode() {
        String temp = "";
        for (int j = 0; j < numItems; j++) {
            temp += items[j].displayItem();
        }
        temp += "\n";
        return temp;
    }
}	// end class Node

