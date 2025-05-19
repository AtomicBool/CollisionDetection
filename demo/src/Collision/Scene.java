package Collision;

import Collision.structures.AOR;
import Collision.structures.OBB;
import Collision.structures.Vector2D;

import java.util.ArrayList;

/**
 * Scene with set of aors and obstacles
 */
public class Scene {
    public ArrayList<AOR> aors;
    public ArrayList<OBB> obstacles;

    /**
     * Default Constructor for {@link Scene}
     */
    public Scene(){
        aors = new ArrayList<>();
        obstacles = new ArrayList<>();
    }

    /**
     * Adding {@link AOR} into the scene
     * @param aor Axis of Rotation Object
     */
    public void addAOR(AOR aor){
        this.aors.add(aor);
    }

    /**
     * Adding {@link OBB} obstacle into the scene
     * @param box obstacle Object
     */
    public void addOBS(OBB box){
        this.obstacles.add(box);
    }

    /**
     * Function determins if the AOR with given params collide with obstacles in the scene
     * @param aorAngles in sequence AOR are added
     * @param offsets same, in sequence AOR are added
     * @return true if colliding
     */
    public boolean colliding(double[] aorAngles, Vector2D[] offsets){
        for(OBB obstacle: obstacles){
            for(int i = 0; i < aors.size(); i++) {
                aors.get(i).rotateAOR(aorAngles[i]);
                aors.get(i).updateOffset(offsets[i]);

                for(OBB box: aors.get(i).boundingBoxes){
                    if(CollisionOBB.colliding(box, obstacle)) return true;
                }
            }
        }
        return false;
    }
}
