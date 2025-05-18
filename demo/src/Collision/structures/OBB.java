package Collision.structures;

/**
 * Oriented Bounding Box
 */
public class OBB {
    public Vector2D lengths; // should be half-length
    public Vector2D center, globalOffset;
    public double localRotation, globalRotation;

    /**
     * Construct OBB with regular x/y lengths
     * @param center displacement to AOR
     * @param lengths x being length and y being height
     * @param localRotation angle in radians with right being 0, with respect to box itself [CCW]
     */
    public OBB(Vector2D center, Vector2D lengths, double localRotation){
        this.center = center;
        this.lengths = new Vector2D(
                lengths.x / 2.0,
                lengths.y / 2.0
        );
        this.localRotation = localRotation;
        this.globalRotation = 0;
    }

    /**
     * Update global rotation
     * @param globalRotation angle in radians with right being 0, CCW
     */
    public void setGlobalRotation(double globalRotation){
        this.globalRotation = globalRotation;
    }

    /**
     * Update global offset for AOR
     * @param globalOffset AOR offset from actual center of scene
     */
    public void setGlobalOffset(Vector2D globalOffset){
        this.globalOffset = globalOffset;
    }
}


