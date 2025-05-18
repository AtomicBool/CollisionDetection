package Collision;

import Collision.structures.AOR;
import Collision.structures.OBB;
import Collision.structures.Vector2D;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class drawDomain {
    static Scene s = new Scene();
    static AOR aor = new AOR();

    public static Vector2D calculateNetOffsetAOR(double elevHeightMeters){
        return new Vector2D(
                0.175,
                elevHeightMeters + 0.341
        );
    }

    public static void outputFile(){
        try {
            FileWriter writer = new FileWriter("path_points.csv");
            writer.write("angle,pos\n");

            for(double i = -35; i < 75; i += 1){
                for(double j = 0; j < 0.95; j += 0.01){
                    boolean c = s.colliding(
                            new double[] {Math.toRadians(i)},
                            new Vector2D[] {calculateNetOffsetAOR(j)}
                    );
                    if(!c) writer.write(Math.toRadians(i) + "," + j + "\n");
                }
            }

            writer.close();
            System.out.println("✅ Exported to path_points.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testPoints(double angle, double height){
        CollisionOBB.debug = true;

        boolean isColliding = s.colliding(
                new double[] {Math.toRadians(angle)},
                new Vector2D[] {calculateNetOffsetAOR(height)}
        );

        System.out.println(isColliding);

        try {
            FileWriter writer = new FileWriter("points.csv");
            writer.write("x,y\n");

            for(Vector2D v: CollisionOBB.pointList){
                writer.write(v.x + "," + v.y + "\n");
            }

            for(OBB box: s.obstacles){
                ArrayList<Vector2D> vertices = CollisionOBB.getOBBVertices(box);
                for(Vector2D v: vertices) writer.write(v.x + "," + v.y + "\n");
            }

            writer.close();
            System.out.println("✅ Exported to points.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        OBB b1 = new OBB(
                new Vector2D(0.064, 0.006),
                new Vector2D(0.137, 0.1),
                Math.toRadians(0)
        );

        OBB b2 = new OBB(
                new Vector2D(-0.028, 0.062),
                new Vector2D(0.06, 0.1),
                Math.toRadians(45)
        );

        OBB b3 = new OBB(
                new Vector2D(-0.081, 0.15),
                new Vector2D(0.06, 0.1),
                Math.toRadians(25)
        );

        OBB b4 = new OBB(
                new Vector2D(-0.098, 0.245),
                new Vector2D(0.06, 0.1),
                Math.toRadians(0)
        );

        OBB b5 = new OBB(
                new Vector2D(-0.077, 0.346),
                new Vector2D(0.06, 0.1),
                Math.toRadians(-20)
        );

        OBB b6 = new OBB(
                new Vector2D(0.036, 0.408),
                new Vector2D(0.18, 0.06),
                Math.toRadians(10)
        );

        OBB topShaft = new OBB(
                new Vector2D(0, 0),
                new Vector2D(0.04, 0.1),
                Math.toRadians(0)
        );
        topShaft.globalOffset = new Vector2D(-0.070, 0.794 + 0.06);

        aor.addBox(b1);
        aor.addBox(b2);
        aor.addBox(b3);
        aor.addBox(b4);
        aor.addBox(b5);
        aor.addBox(b6);

        s.addAOR(aor);
        s.addOBS(topShaft);

        // outputFile();
        testPoints(20, 0.1674);
    }
}
