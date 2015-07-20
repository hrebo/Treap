package sk.structure.quadTree;

/**
 *
 * @author Tomas Hreben
 */
public class QuadTree {
    
    private double x_coordinate_area_max;
    private double y_coordinate_area_max;
    private NodeQTree root;

    public QuadTree(double x_coordinate_area_max, double y_coordinate_area_max) {
        this.x_coordinate_area_max = x_coordinate_area_max;
        this.y_coordinate_area_max = y_coordinate_area_max;
        this.root = null;
    }
    
    /**
     * Method for insert node into quad tree
     * @param iNode
     * @return 
     */
    public boolean insertNode(NodeQTree iNode){
        boolean inserted = false;
        boolean isEmptyArea = false;
        NodeQTree tempNode;
        
        //check if exist root
        if(!existRoot()){
            this.root = new NodeQTree(null, 0.0, this.getX_coordinate_area_max(), 0.0, this.getY_coordinate_area_max());
        }
        
        //check if exist point with x and y coordinate
        if(existNode(iNode.getPoint().getX_coordinate(), iNode.getPoint().getY_coordinate())){
            //point exist
            return false;
        }
        
        tempNode = this.getRoot();
        while(!inserted){
            //find area where we can add node 
            //1. area => NW
            if((iNode.getPoint().getX_coordinate() <= tempNode.getX_coordinate_max() / 2) &&
                    (iNode.getPoint().getY_coordinate() > tempNode.getY_coordinate_max() / 2)){
                if(tempNode.getNW_node() == null){
                    iNode.setX_coordinate_max(tempNode.getX_coordinate_max() / 2);
                    iNode.setX_coordinate_min(tempNode.getX_coordinate_min());
                    
                    iNode.setY_coordinate_max(tempNode.getY_coordinate_max());
                    iNode.setY_coordinate_min(tempNode.getY_coordinate_max() / 2);
                    
                    tempNode.setNW_node(iNode);
                    inserted = true;
                }else{
                    //we need split
                    tempNode = tempNode.getNW_node();
                }
                //2. are => NE
            }else if( (iNode.getPoint().getX_coordinate() > tempNode.getX_coordinate_max() / 2) &&
                    (iNode.getPoint().getY_coordinate() > tempNode.getY_coordinate_max() / 2)){
                if(tempNode.getNE_node() == null){
                    iNode.setX_coordinate_max(tempNode.getX_coordinate_max());
                    iNode.setX_coordinate_min(tempNode.getX_coordinate_max() / 2);
                    
                    iNode.setY_coordinate_max(tempNode.getY_coordinate_max());
                    iNode.setY_coordinate_min(tempNode.getY_coordinate_max() / 2);
                    
                    tempNode.setNW_node(iNode);
                    inserted = true;
                }else{
                    //we need split
                    tempNode = tempNode.getNE_node();
                }
                //3. area => SE
            }else if( (iNode.getPoint().getX_coordinate() > tempNode.getX_coordinate_max() / 2) &&
                    (iNode.getPoint().getY_coordinate() < tempNode.getY_coordinate_max() / 2) ){
                if(tempNode.getSE_node() == null){
                    iNode.setX_coordinate_max(tempNode.getX_coordinate_max());
                    iNode.setX_coordinate_min(tempNode.getX_coordinate_max() / 2);
                    
                    iNode.setY_coordinate_max(tempNode.getY_coordinate_max() / 2);
                    iNode.setY_coordinate_min(tempNode.getY_coordinate_min());
                    
                    tempNode.setSE_node(iNode);
                    inserted = true;
                }else{
                    //we need split
                    tempNode = tempNode.getSE_node();
                }
                //4. area => SW
            }else if( (iNode.getPoint().getX_coordinate() < tempNode.getX_coordinate_max() / 2) &&
                    (iNode.getPoint().getY_coordinate() < tempNode.getY_coordinate_max() / 2) ){
                if(tempNode.getSW_node() == null){
                    iNode.setX_coordinate_max(tempNode.getX_coordinate_max() / 2);
                    iNode.setX_coordinate_min(tempNode.getX_coordinate_min());
                    
                    iNode.setY_coordinate_max(tempNode.getY_coordinate_max() / 2);
                    iNode.setY_coordinate_min(tempNode.getY_coordinate_min());
                    
                    tempNode.setSE_node(iNode);
                    inserted = true;
                }else{
                    //we need split
                    tempNode = tempNode.getSW_node();
                }
            }
        }
        return true;
    }
    
    /**
     * Method for remove node from quad tree
     * @param iNode
     * @return 
     */
    public boolean removeNode(NodeQTree iNode){
        return true;
    }
    
    /**
     * Method for find node
     * 
     * @param iNode
     * @return 
     */
    public NodeQTree finde(NodeQTree iNode){
        return null;
    }
    
    /**
     * Method for find point in the quad tree
     * 
     * @param iPoint
     * @return 
     */
    public NodeQTree FindePoint(Point iPoint){
        return null;
    }
    
    /**
     * Check if exist piont 
     * 
     * @param iX_coordinate
     * @param iY_coordinate
     * @return 
     */
    public boolean existNode(double iX_coordinate, double iY_coordinate){
        NodeQTree tempNode = this.getRoot();
        //TODO - dorobit rozdelenie arealu
        while(true){
            
            if(isEmptyNode(tempNode)){
                return false;
                //1. area
            }else if((iX_coordinate <= (tempNode.getX_coordinate_max() / 2)) &&
                    (iY_coordinate >= (tempNode.getY_coordinate_max() / 2)) &&
                    (iX_coordinate >= (tempNode.getX_coordinate_min())) &&
                    iY_coordinate >= (tempNode.getY_coordinate_min())){
                if(tempNode.getNW_node() == null){
                    return false;
                }else if(tempNode.comparePoint(iX_coordinate, iY_coordinate)){
                    return true;
                }else{
                    tempNode = tempNode.getNW_node();
                }
                //2.area
            }else if((iX_coordinate > (tempNode.getX_coordinate_max() / 2)) &&
                    (iY_coordinate > (tempNode.getY_coordinate_max() / 2)) &&
                    (iX_coordinate >= (tempNode.getX_coordinate_max())) &&
                    iY_coordinate >= (tempNode.getY_coordinate_max())){
                if(tempNode.getNE_node() == null){
                    return false;
                }else if(tempNode.comparePoint(iX_coordinate, iY_coordinate)){
                    return true;
                }else{
                    tempNode = tempNode.getNE_node();
                }
                //3.area
            }else if((iX_coordinate > (tempNode.getX_coordinate_max() / 2)) &&
                    (iY_coordinate > (tempNode.getY_coordinate_max() / 2)) &&
                    (iX_coordinate >= (tempNode.getX_coordinate_min())) &&
                    iY_coordinate >= (tempNode.getY_coordinate_min())){
                if(tempNode.getSE_node() == null){
                    return false;
                }else if(tempNode.comparePoint(iX_coordinate, iY_coordinate)){
                    return true;
                }else{
                    tempNode = tempNode.getSE_node();
                }
                //4.area
            }else if((iX_coordinate > (tempNode.getX_coordinate_max() / 2)) &&
                    (iY_coordinate > (tempNode.getY_coordinate_max() / 2)) &&
                    (iX_coordinate >= (tempNode.getX_coordinate_min())) &&
                    iY_coordinate >= (tempNode.getY_coordinate_min())){
                if(tempNode.getNW_node() == null){
                    return false;
                }else if(tempNode.comparePoint(iX_coordinate, iY_coordinate)){
                    return true;
                }else{
                    tempNode = tempNode.getNW_node();
                }
            }
        }
    }
    
    /**
     * Check if node is empty
     * 
     * @param iNode
     * @return 
     */
    private boolean isEmpty(NodeQTree iNode){        
        if(iNode == null){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Check if exist root
     * 
     * @return 
     */
    public boolean existRoot(){
        if(root == null){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Check if node's sons are empty
     * 
     * @param iNode
     * @return 
     */
    public boolean isEmptyNode(NodeQTree iNode){
        if((iNode.getNW_node() == null) && (iNode.getNE_node() == null) 
                && (iNode.getSE_node() == null) && (iNode.getSW_node() == null)){
            return true;
        }else{
            return false;
        }
    }

    public double getX_coordinate_area_max() {
        return x_coordinate_area_max;
    }

    public void setX_coordinate_area_max(double x_coordinate_area_max) {
        this.x_coordinate_area_max = x_coordinate_area_max;
    }

    public double getY_coordinate_area_max() {
        return y_coordinate_area_max;
    }

    public void setY_coordinate_area_max(double y_coordinate_area_max) {
        this.y_coordinate_area_max = y_coordinate_area_max;
    }

    public NodeQTree getRoot() {
        return root;
    }

    public void setRoot(NodeQTree root) {
        this.root = root;
    }
    
    
}
