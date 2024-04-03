package ie.atu.modugrip_backend.InverseKinematics;

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

    public InverseKinematics(double a1_, double a2_, double x_, double y_){
        a1 = a1_;
        a2 = a2_;
        x = x_;
        y = y_;
    }

    public void negPosAngleCalc(){
        double q1, q2;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("\n*** Negative and positive version ***");
        q2 = -acos(((x*x)+(y*y)-(a1*a1)-(a2*a2))/(2*a1*a2));
        q1 = (atan(y/x) - (atan((a2*sin(q2))/(a1+(a2*cos(q2))))));
//        System.out.println("Base Angle in Radians: " + q1);
//        System.out.println("Joint Angle in Radians: " + q2);
        System.out.println("Base Angle in Degrees: " + df.format((q1 * 180/PI))); //This
//        System.out.println("Joint Angle Without Addition in Degrees: " + df.format((q2 * 180/PI)));
        System.out.println("Joint Angle With Addition in Degrees: " + df.format((q2 * 180/PI + (q1 * 180/PI)))); //This
        boolean result = forwardKinematics(parseDouble(df.format((q1 * 180/PI))), parseDouble(df.format((q2 * 180/PI + (q1 * 180/PI)))), a1, a2, parseDouble(df.format(x)), parseDouble(df.format(y)));
        System.out.println("Result:" + result);
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
}
