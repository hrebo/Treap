package sk.structure;

import java.util.LinkedList;

/**
 *
 * @author Tomas Hreben
 * @date 11.april 2015
 */
public class Treap {
    private Node root;
    private int size;  
    

    /**
     *
     * @param iElement - element for insert
     */
    public void Treap(Comparable iElement) {
        this.root = new Node(iElement);
        this.size = 0;
    }
    
    public void Treap() {
        this.root = null;
        this.size = 0;
    }

    public Node getRoot() {
        return root;
    }
    
    public int getSize(){
        return size;
    }
    
    /**
     * Insert node to tree
     *
     * @param iNode 
     * @return  
     */
    public boolean insert(Node iNode) {
        if (isEmpty()) {
            root = iNode;
        } else {
            boolean isInsert = false;
            Node actualNode = root;
            while (!isInsert) {
                if (actualNode.getElement().compareTo(iNode.getElement()) > 0) {
                    if (actualNode.getLeftSon() != null) {
                        actualNode = actualNode.getLeftSon();
                    } else {
                        iNode.setFather(actualNode);
                        actualNode.setLeftSon(iNode);
                        isInsert = true;
                        this.needRotation(iNode);
                    }
                } else if (actualNode.getElement().compareTo(iNode.getElement()) < 0) {
                    if (actualNode.gerRightSon() != null) {
                        actualNode = actualNode.gerRightSon();
                    } else {
                        iNode.setFather(actualNode);
                        actualNode.setRightSon(iNode);
                        isInsert = true;
                        this.needRotation(iNode);
                    }
                } else {
                    return false;
                }
            }
        }
        size++;
        return true;
    }

    /**
     * Find node with key
     *
     * @param iElement 
     * @return Node
     */
    public Node find(Comparable iElement) {
        Node wantedNode = null;
        if (!isEmpty()) {
            boolean isFind = false;
            Node actualNode = root;
            while (!isFind) {
                if (actualNode.getElement().compareTo(iElement) > 0) {
                    if (actualNode.getLeftSon() != null) {
                        actualNode = actualNode.getLeftSon();
                    } else {
                        wantedNode = null;
                        isFind = true;
                    }
                } else if (actualNode.getElement().compareTo(iElement) < 0) {
                    if (actualNode.gerRightSon() != null) {
                        actualNode = actualNode.gerRightSon();
                    } else {
                        wantedNode = null;
                        isFind = true;
                    }
                } else {
                    wantedNode = actualNode;
                    isFind = true;
                }
            }
        }
        return wantedNode;
    }

    /**
     * Remote node from treap
     *
     * @param iElement 
     * @return metoda vrati true/false
     */
    public boolean delete(Comparable iElement) {
        Node tempNode = find(iElement);
        Node tempFather = null;
        Node tempRightSon = null;
        Node tempLeftSon = null;
        
        if(tempNode == null){
            return false;
        }
        
        while (true) {
            if (isList(tempNode)) {
                if (tempNode == root) {
                    root = null;
                    
                    size--;
                    return true;
                } else {
                    tempFather = tempNode.getFather();
                    if (tempFather.getElement().compareTo(tempNode.getElement()) < 0) {
                        tempFather.setRightSon(null);
                    } else {
                        tempFather.setLeftSon(null);
                    }
                    tempNode = null;
                    
                    size--;
                    return true;
                }
            }

            if (tempNode.getLeftSon() == null) {
                tempRightSon = tempNode.gerRightSon();
                if (isList(tempRightSon)) {
                    if (tempNode == root) {
                        tempRightSon.setFather(null);
                        root = tempRightSon;
                        tempNode = null;
                        size--;
                        return true;
                    } else {
                        tempFather = tempNode.getFather();
                        if (tempFather.getElement().compareTo(tempNode.getElement()) < 0) {
                            tempFather.setRightSon(tempRightSon);
                            tempRightSon.setFather(tempFather);
                            tempNode = null;
                            size--;
                            return true;
                        } else {
                            tempFather.setLeftSon(tempRightSon);
                            tempRightSon.setFather(tempFather);
                            tempNode = null;
                            size--;
                            return true;
                        }
                    }

                }else{
                    leftRotation(tempRightSon);
                }
            }else if(tempNode.gerRightSon() == null){
                tempLeftSon = tempNode.getLeftSon();
                if (isList(tempLeftSon)) {
                    if (tempNode == root) {
                        tempLeftSon.setFather(null);
                        root = tempLeftSon;
                        tempNode = null;
                        size--;
                        return true;
                    } else {
                        tempFather = tempNode.getFather();
                        if (tempFather.getElement().compareTo(tempNode.getElement()) < 0) {
                            tempFather.setRightSon(tempLeftSon);
                            tempLeftSon.setFather(tempFather);
                            tempNode = null;
                            size--;
                            return true;
                        } else {
                            tempFather.setLeftSon(tempLeftSon);
                            tempLeftSon.setFather(tempFather);
                            tempNode = null;
                            size--;
                            return true;
                        }
                    }

                }else{
                    rightRotation(tempLeftSon);
                }
            }else{
                tempRightSon = tempNode.gerRightSon();
                tempLeftSon = tempNode.getLeftSon();
                if(isList(tempRightSon) && isList(tempLeftSon)){
                    if(tempRightSon.getPriority() < tempLeftSon.getPriority()){
                        tempRightSon.setLeftSon(tempLeftSon);
                        tempLeftSon.setFather(tempRightSon);
                        if(tempNode == root){
                            tempRightSon.setFather(null);
                            root = tempRightSon;
                            tempNode = null;
                            size--;
                            return true;
                        }else{
                            tempFather = tempNode.getFather();
                            if(tempFather.getElement().compareTo(tempNode.getElement()) < 0){
                                tempFather.setRightSon(tempRightSon);
                                tempRightSon.setFather(tempFather);
                                tempNode = null;
                                size--;
                                return true;
                            }else{
                                tempFather.setLeftSon(tempRightSon);
                                tempRightSon.setFather(tempFather);
                                tempNode = null;
                                size--;
                                return true;
                            }
                        }
                    }else{
                        tempLeftSon.setRightSon(tempRightSon);
                        tempRightSon.setFather(tempLeftSon);
                        if(tempNode == root){
                            tempLeftSon.setFather(null);
                            root = tempLeftSon;
                            tempNode = null;
                            size--;
                            return true;
                        }else{
                            tempFather = tempNode.getFather();
                            if(tempFather.getElement().compareTo(tempNode.getElement()) < 0){
                                tempFather.setRightSon(tempLeftSon);
                                tempLeftSon.setFather(tempFather);
                                tempNode = null;
                                size--;
                                return true;
                            }else{
                                tempFather.setLeftSon(tempLeftSon);
                                tempLeftSon.setFather(tempFather);
                                tempNode = null;
                                size--;
                                return true;
                            }
                        }
                    }
                }else{
                    if(tempLeftSon.getPriority() < tempRightSon.getPriority()){
                        rightRotation(tempLeftSon);
                    }else{
                        leftRotation(tempRightSon);
                    }
                }
            }
        }
    }

    /**
     * Is treap empty
     *
     * @return vracia true/false 
     */
    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Is node leaf?
     *
     * @param iNode 
     * @return true / false
     */
    public boolean isList(Node iNode) {
        if ((iNode.getLeftSon() == null) && (iNode.gerRightSon() == null)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * left rotation
     *
     * @param rotationNode 
     */
    private void leftRotation(Node rotationNode) {
        Node tempFather = rotationNode.getFather();
        tempFather.setRightSon(rotationNode.getLeftSon());
        
        if (rotationNode.getLeftSon() != null) {            
            rotationNode.getLeftSon().setFather(tempFather);
        }

        if (tempFather.getFather() == null) {
            root = rotationNode;
            rotationNode.setFather(null);
        } else {
            Node tempGrandFather = tempFather.getFather();
            if (tempGrandFather.getElement().compareTo(tempFather.getElement()) < 0) {
                tempGrandFather.setRightSon(rotationNode);
            } else {
                tempGrandFather.setLeftSon(rotationNode);
            }
            rotationNode.setFather(tempGrandFather);
        }

        rotationNode.setLeftSon(tempFather);
        tempFather.setFather(rotationNode);
    }

    /**
     * Right rotation
     *
     * @param rotationNode 
     */
    private void rightRotation(Node rotationNode) {
        Node tempFather = rotationNode.getFather();
        tempFather.setLeftSon(rotationNode.gerRightSon());
        
        if (rotationNode.gerRightSon() != null) {
            rotationNode.gerRightSon().setFather(tempFather);
        }

        if (tempFather.getFather() == null) {
            root = rotationNode;
            rotationNode.setFather(null);
        } else {
            Node tempGrandFather = tempFather.getFather();
            if (tempGrandFather.getElement().compareTo(tempFather.getElement()) < 0) {
                tempGrandFather.setRightSon(rotationNode);
            } else {
                tempGrandFather.setLeftSon(rotationNode);
            }
            rotationNode.setFather(tempGrandFather);
        }

        rotationNode.setRightSon(tempFather);
        tempFather.setFather(rotationNode);
    }

    /**
     * 
     * @param rotationNode 
     */
    public void needRotation(Node rotationNode) {
        Node tempFather = rotationNode.getFather();
        while ((tempFather != null) && (tempFather.getPriority() > rotationNode.getPriority())) {
            if (tempFather.getElement().compareTo(rotationNode.getElement()) > 0) {
                rightRotation(rotationNode);
            } else {
                leftRotation(rotationNode);
            }
            tempFather = rotationNode.getFather();
        }
    }
    
    /**
     * Remove node 
     * @param iNode 
     */
    public void removeElement(Node iNode){
        iNode.setLeftSon(null);
        iNode.setRightSon(null);
        iNode.setFather(null);
        iNode = null;
    }
    
    /**
     * 
     * @param iNode 
     */
    public void inorder(Node iNode){
        if(iNode.getLeftSon() != null){
            inorder(iNode.getLeftSon());
        }
        if(iNode != null){
            System.out.println("Node"+iNode.getElement().toString());
        }        
        if(iNode.gerRightSon() != null){
            inorder(iNode.gerRightSon());
        }        
    }
    
    public void inorder(Node iNode,LinkedList<Node> recordNodes){
        if(iNode != null){
            if(iNode.getLeftSon() != null){
                inorder(iNode.getLeftSon(),recordNodes);
            }
                recordNodes.add(iNode);
            if(iNode.gerRightSon() != null){
                inorder(iNode.gerRightSon(),recordNodes);
            }
        }
    }
    
    public void preorder(Node iNode){
        if(iNode != null){
            System.out.println("Node "+iNode.getElement().toString());
        }        
        if(iNode.getLeftSon() != null){
            preorder(iNode.getLeftSon());
        }        
        if(iNode.gerRightSon() != null){
            preorder(iNode.gerRightSon());
        }        
    }
    
    /**
     * Get and remove node from treap
     * 
     * @param iElement 
     * @return Comparable 
     */
    public Comparable remove(Comparable iElement){
        Comparable tempElement = null;
        Node tempNode = find(iElement);
        Node tempFather = null;
        Node tempRightSon = null;
        Node tempLeftSon = null;
        
        if(tempNode == null){
            return null;
        }
        
        while (true) {
            if (isList(tempNode)) {
                if (tempNode == root) {
                    tempElement = root.getElement();
                    root = null;
                    size--;
                    return tempElement;
                } else {
                    tempFather = tempNode.getFather();
                    if (tempFather.getElement().compareTo(tempNode.getElement()) < 0) {
                        tempFather.setRightSon(null);
                    } else {
                        tempFather.setLeftSon(null);
                    }
                    tempElement = tempNode.getElement();
                    tempNode = null;
                    
                    size--;
                    return tempElement;
                }
            }

            if (tempNode.getLeftSon() == null) {
                tempRightSon = tempNode.gerRightSon();
                if (isList(tempRightSon)) {
                    if (tempNode == root) {
                        tempRightSon.setFather(null);
                        root = tempRightSon;
                        tempElement = tempNode.getElement();
                        tempNode = null;
                        size--;
                        return tempElement;
                    } else {
                        tempFather = tempNode.getFather();
                        if (tempFather.getElement().compareTo(tempNode.getElement()) < 0) {
                            tempFather.setRightSon(tempRightSon);
                            tempRightSon.setFather(tempFather);
                            tempElement = tempNode.getElement();
                            tempNode = null;
                            size--;
                            return tempElement;
                        } else {
                            tempFather.setLeftSon(tempRightSon);
                            tempRightSon.setFather(tempFather);
                            tempElement = tempNode.getElement();
                            tempNode = null;
                            size--;
                            return tempElement;
                        }
                    }

                }else{
                    leftRotation(tempRightSon);
                }
            }else if(tempNode.gerRightSon() == null){
                tempLeftSon = tempNode.getLeftSon();
                if (isList(tempLeftSon)) {
                    if (tempNode == root) {
                        tempLeftSon.setFather(null);
                        root = tempLeftSon;
                        tempElement = tempNode.getElement();
                        tempNode = null;
                        size--;
                        return tempElement;
                    } else {
                        tempFather = tempNode.getFather();
                        if (tempFather.getElement().compareTo(tempNode.getElement()) < 0) {
                            tempFather.setRightSon(tempLeftSon);
                            tempLeftSon.setFather(tempFather);
                            tempElement = tempNode.getElement();
                            tempNode = null;
                            size--;
                            return tempElement;
                        } else {
                            tempFather.setLeftSon(tempLeftSon);
                            tempLeftSon.setFather(tempFather);
                            tempElement = tempNode.getElement();
                            tempNode = null;
                            size--;
                            return tempElement;
                        }
                    }

                }else{
                    rightRotation(tempLeftSon);
                }
            }else{
                tempRightSon = tempNode.gerRightSon();
                tempLeftSon = tempNode.getLeftSon();
                if(isList(tempRightSon) && isList(tempLeftSon)){
                    if(tempRightSon.getPriority() < tempLeftSon.getPriority()){
                        tempRightSon.setLeftSon(tempLeftSon);
                        tempLeftSon.setFather(tempRightSon);
                        if(tempNode == root){
                            tempRightSon.setFather(null);
                            root = tempRightSon;
                            tempElement = tempNode.getElement();
                            tempNode = null;
                            size--;
                            return tempElement;
                        }else{
                            tempFather = tempNode.getFather();
                            if(tempFather.getElement().compareTo(tempNode.getElement()) < 0){
                                tempFather.setRightSon(tempRightSon);
                                tempRightSon.setFather(tempFather);
                                tempElement = tempNode.getElement();
                                tempNode = null;
                                size--;
                                return tempElement;
                            }else{
                                tempFather.setLeftSon(tempRightSon);
                                tempRightSon.setFather(tempFather);
                                tempElement = tempNode.getElement();
                                tempNode = null;
                                size--;
                                return tempElement;
                            }
                        }
                    }else{
                        tempLeftSon.setRightSon(tempRightSon);
                        tempRightSon.setFather(tempLeftSon);
                        if(tempNode == root){
                            tempLeftSon.setFather(null);
                            root = tempLeftSon;
                            tempElement = tempNode.getElement();
                            tempNode = null;
                            size--;
                            return tempElement;
                        }else{
                            tempFather = tempNode.getFather();
                            if(tempFather.getElement().compareTo(tempNode.getElement()) < 0){
                                tempFather.setRightSon(tempLeftSon);
                                tempLeftSon.setFather(tempFather);
                                tempElement = tempNode.getElement();
                                tempNode = null;
                                size--;
                                return tempElement;
                            }else{
                                tempFather.setLeftSon(tempLeftSon);
                                tempLeftSon.setFather(tempFather);
                                tempElement = tempNode.getElement();
                                tempNode = null;
                                size--;
                                return tempElement;
                            }
                        }
                    }
                }else{
                    if(tempLeftSon.getPriority() < tempRightSon.getPriority()){
                        rightRotation(tempLeftSon);
                    }else{
                        leftRotation(tempRightSon);
                    }
                }
            }
        }
    }
    
    /**
     * Check if tree is treap
     * 
     * @param iNode 
     */
     public void checkTreap(Node iNode){
        if(iNode.getLeftSon() != null){
            checkTreap(iNode.getLeftSon());
        }
        if(iNode != null){
            if(iNode.getFather() != null){
                if(iNode.getFather().getPriority() > iNode.getPriority()){
                    System.out.println("Strom neni Treapom");
                }
            }
        }
        if(iNode.gerRightSon() != null){
            checkTreap(iNode.gerRightSon());
        }        
    }
}
