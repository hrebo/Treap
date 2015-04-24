package sk.structure.quadTree;

/**
 *
 * @author Tomas Hreben
 */
public class QuadTree {
    
    private double x_coordinate_area_max;
    private double y_coordinate_area_min;
    private NodeQTree root;

    public QuadTree(double x_coordinate_area_max, double y_coordinate_area_min) {
        this.x_coordinate_area_max = x_coordinate_area_max;
        this.y_coordinate_area_min = y_coordinate_area_min;
        this.root = null;
    }
    
    /**
     * Method for insert node into quad tree
     * @param iNode
     * @return 
     */
    public boolean insertNode(NodeQTree iNode){
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
     * Check if exist piont 
     * 
     * @param iX_coordinate
     * @param iY_coordinate
     * @return 
     */
    public boolean existNode(double iX_coordinate, double iY_coordinate){
        return true;
    }
    
    /**
     * Check if node is empty
     * 
     * @param iNode
     * @return 
     */
    public boolean isEmpty(NodeQTree iNode){
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
}
