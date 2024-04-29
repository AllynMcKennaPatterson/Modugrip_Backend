package ie.atu.modugrip_backend.InverseKinematics;

import ie.atu.modugrip_backend.Models.CoordsAngles;
import ie.atu.modugrip_backend.Models.SliderData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Math.*;
import static java.lang.Math.PI;

@Data
@NoArgsConstructor
public class InverseKinematics {
    double a1;
    double a2;
    double x;
    double y;
    double z;
    double hyp;

    public InverseKinematics(double a1_, double a2_, double x_, double y_, double z_, double hyp_){
        a1 = a1_;
        a2 = a2_;
        x = x_;
        y = y_;
        z = z_;
        hyp = hyp_;
    }

    public CoordsAngles negPosAngleCalc(){
        CoordsAngles ca = new CoordsAngles();
        double q1, q2;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("\n*** Negative and positive version ***");
        q2 = -acos(((hyp*hyp)+(y*y)-(a1*a1)-(a2*a2))/(2*a1*a2));
        q1 = (atan(y/hyp) - (atan((a2*sin(q2))/(a1+(a2*cos(q2))))));
//        System.out.println("Base Angle in Radians: " + q1);
//        System.out.println("Joint Angle in Radians: " + q2);
        System.out.println("Base Angle in Degrees: " + df.format((q1 * 180/PI))); //This
        ca.setServo1((int) (100 - ((q1 * 180/PI) * 0.445)));
//        System.out.println("Joint Angle Without Addition in Degrees: " + df.format((q2 * 180/PI)));
        System.out.println("Joint Angle With Addition in Degrees: " + df.format((q2 * 180/PI + (q1 * 180/PI)))); //This
        ca.setServo2((int) (130 - ((q2 * 180/PI + (q1 * 180/PI))*2.5)));
        boolean result = forwardKinematics(parseDouble(df.format((q1 * 180/PI))), parseDouble(df.format((q2 * 180/PI + (q1 * 180/PI)))), a1, a2, parseDouble(df.format(hyp)), parseDouble(df.format(y)));
        System.out.println("Result:" + result);
        return ca;
    }

    public static boolean forwardKinematics(double baseAngle, double jointAngle, double len1, double len2, double x, double y){
        double x1, x2, y1, y2;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("\n*** Forward Kinematics ***");
        x1 = sin(baseAngle * PI/180) * len1;
        y1 = cos(baseAngle* PI/180) * len1;
        x2 = sin(jointAngle * PI/180) * len2;
        y2 = cos(jointAngle* PI/180) * len2;
        System.out.println("Endpoint coordinates: (" + df.format((y1 + y2)) + ", " + df.format((x1 + x2)) + ")");
        System.out.println("Real coords: (" + x + ", " + y + ")");


        if((x == parseDouble(df.format((y1 + y2))) && (y == parseDouble(df.format((x1 + x2)))))){
            return true;
        }
        else{
            return false;
        }
    }

    public int calcBaseAngle(){
        int baseAngle = 0;
        double hypotenuse = 0;
        if(x <= 0 && z <=0){
            hypotenuse = sqrt(((z*z) + (x*x)));
            baseAngle = (int) (((asin(abs(x)/hypotenuse) * (180/PI)) / 1.8) + 100);
            System.out.println("Z: " + z);
            System.out.println("X: " + x);
            System.out.println("hyp: " + hypotenuse);
            System.out.println("Q4: " + baseAngle);
        }
        else if (x <= 0 && z >=0){
            setZ(getZ()-10);
            hypotenuse = sqrt(((z*z) + (x*x)));
            baseAngle = (int) (((asin(abs(z)/hypotenuse) * (180/PI)) / 1.8) + 150);
            System.out.println("Z: " + z);
            System.out.println("X: " + x);
            System.out.println("hyp: " + hypotenuse);
            System.out.println("Q1: " + baseAngle);
        }
        else if (x >= 0 && z >=0){
            setZ(getZ()-10);
            setX(getX()-10);
            hypotenuse = sqrt(((z*z) + (x*x)));
            baseAngle = (int) (50 -((asin(abs(z)/hypotenuse) * (180/PI)) / 1.8));
            System.out.println("Z: " + z);
            System.out.println("X: " + x);
            System.out.println("hyp: " + hypotenuse);
            System.out.println("Q2: " + baseAngle);
        }
        else if (x >= 0 && z <=0){
            hypotenuse = sqrt(((z*z) + (x*x)));
            baseAngle = (int) (50 + ((asin(abs(z)/hypotenuse) * (180/PI)) / 1.8));
            System.out.println("Z: " + z);
            System.out.println("X: " + x);
            System.out.println("hyp: " + hypotenuse);
            System.out.println("Q3: " + baseAngle);
        }
        return baseAngle;
    }
}
