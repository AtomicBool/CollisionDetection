package Collision;

import Collision.structures.AOR;
import Collision.structures.OBB;
import Collision.structures.Vector2D;

import java.util.ArrayList;

public class Scene {
    public ArrayList<AOR> aors;
    public ArrayList<OBB> obstacles;

    public Scene(){
        aors = new ArrayList<>();
        obstacles = new ArrayList<>();
    }

    public void addAOR(AOR aor){
        this.aors.add(aor);
    }

    public void addOBS(OBB box){
        this.obstacles.add(box);
    }

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
