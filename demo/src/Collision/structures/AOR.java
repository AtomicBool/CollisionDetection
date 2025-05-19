package Collision.structures;

import java.util.ArrayList;

/**
 * Axis of Rotation
 */
public class AOR {
    public ArrayList<OBB> boundingBoxes;

    /**
     * Constructor for AOR(Axis of Rotation)
     */
    public AOR(){
        this.boundingBoxes = new ArrayList<>();
    }

    /**
     * Adding {@link OBB} to the AOR
     * @param box the bounding box object
     */
    public void addBox(OBB box){
        this.boundingBoxes.add(box);
    }

    /**
     * Update global rotation
     * @param globalRotation angle in radians with right being 0, CCW
     */
    public void rotateAOR(double globalRotation){
        for(OBB box: boundingBoxes){
            box.setGlobalRotation(globalRotation);
        }
    }

    /**
     * Update global offset
     * @param globalOffset offset to the absolute center of scene
     */
    public void updateOffset(Vector2D globalOffset){
        for(OBB box: boundingBoxes){
            box.setGlobalOffset(globalOffset);
        }
    }
}
