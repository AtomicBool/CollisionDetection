package Collision;

import Collision.structures.OBB;
import Collision.structures.Vector2D;

import java.util.ArrayList;

/**
 * separating axis theorem are copied From
 * https://blog.csdn.net/qq_42978535/article/details/142689947
 */
public class CollisionOBB {
    public static boolean debug = false;
    public static ArrayList<Vector2D> pointList = new ArrayList<>();

    static public ArrayList<Vector2D> getOBBVertices(OBB box) {
        ArrayList<Vector2D> res = new ArrayList<>(4);

        double cosGlobal = Math.cos(box.globalRotation);
        double sinGlobal = Math.sin(box.globalRotation);
        double cosTotal = Math.cos(box.localRotation + box.globalRotation);
        double sinTotal = Math.sin(box.localRotation + box.globalRotation);

        Vector2D rotated_center = box.center.rotate(sinGlobal, cosGlobal);


        Vector2D v1 = new Vector2D(-box.lengths.x, box.lengths.y); // LT
        // box.lengths -> RT
        Vector2D v3 = new Vector2D(box.lengths.x, -box.lengths.y); // RB
        Vector2D v4 = new Vector2D(-box.lengths.x, -box.lengths.y); // LB

        // index 0 -> LT, 1 -> RT, 2 -> RB, 3 -> LB
        res.add(rotated_center.add(v1.rotate(sinTotal, cosTotal)));
        res.add(rotated_center.add(box.lengths.rotate(sinTotal, cosTotal)));
        res.add(rotated_center.add(v3.rotate(sinTotal, cosTotal)));
        res.add(rotated_center.add(v4.rotate(sinTotal, cosTotal)));

        // add global offset to each point
        res.set(0, res.get(0).add(box.globalOffset));
        res.set(1, res.get(1).add(box.globalOffset));
        res.set(2, res.get(2).add(box.globalOffset));
        res.set(3, res.get(3).add(box.globalOffset));

        return res;
    }


    static private boolean isSeparatedOnAxis(Vector2D axis, ArrayList<Vector2D> verticesA, ArrayList<Vector2D> verticesB){
        double minA = Vector2D.dotProduct(axis, verticesA.get(0));
        double maxA = minA;

        for(int i = 1; i < 4; i++){
            double projection = Vector2D.dotProduct(axis, verticesA.get(i));
            minA = Math.min(minA, projection);
            maxA = Math.max(maxA, projection);
        }

        double minB = Vector2D.dotProduct(axis, verticesB.get(0));
        double maxB = minB;

        for(int i = 1; i < 4; i++){
            double projection = Vector2D.dotProduct(axis, verticesB.get(i));
            minB = Math.min(minB, projection);
            maxB = Math.max(maxB, projection);
        }

        return !(minA <= maxB && maxA >= minB);
    }

    static public boolean colliding(OBB box_alpha, OBB box_beta){
        ArrayList<Vector2D> verticesA = getOBBVertices(box_alpha), verticesB = getOBBVertices(box_beta);
        ArrayList<Vector2D> axes = new ArrayList<Vector2D>();

        if(debug){
            pointList.addAll(verticesA);
        }

        axes.add(new Vector2D(verticesA.get(1).x - verticesA.get(0).x, verticesA.get(1).y - verticesA.get(0).y));
        axes.add(new Vector2D(verticesA.get(3).x - verticesA.get(0).x, verticesA.get(3).y - verticesA.get(0).y));
        axes.add(new Vector2D(verticesB.get(1).x - verticesB.get(0).x, verticesB.get(1).y - verticesB.get(0).y));
        axes.add(new Vector2D(verticesB.get(3).x - verticesB.get(0).x, verticesB.get(3).y - verticesB.get(0).y));

        for(Vector2D axis: axes){
            if(isSeparatedOnAxis(axis, verticesA, verticesB)){
                return false;
            }
        }

        return true;
    }
}
