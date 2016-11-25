/**
 * Created by Cat on 10/30/2016.
 */

import java.lang.Math;

public class Maths {
    final double pi = 3.1415926535897932384626433832795;
    final double e_constant = 2.71828182845904523536;

    public double square(double n) {
        return n*n;
    }
    public double cube(double n) {
        return n*n*n;
    }

    /* Angle operations */
    public double degreeToRadians(double degrees) {
        return degrees*2.0*pi/360.0;
    }

    public double radiansToDegrees(double radians) {
        return radians*360.0/(2.0*pi);
    }

    public double getDifferenceRadians(double from, double to) {
        return to-from;
    }

    public double signedNormalizedDegrees(double degrees) {
        // NOTE: this can return both -180 and +180, the sign will be unchanged for these angles
        return degrees - 360 * Math.floor(degrees/360+0.5);
    }

    double boundAngleNegPiToPiRadians(double angle) {
        // Naive algorithm
        while (angle >= pi) {
            angle -= 2.0 * pi;
        }
        while (angle < -pi) {
            angle += 2.0 * pi;
        }
        return angle;
    }

    double boundAngle0to2PiRadians(double angle) {
        // Naive algorithm
        while (angle >= 2.0 * pi) {
            angle -= 2.0 * pi;
        }
        while (angle < 0.0) {
            angle += 2.0 * pi;
        }
        return angle;
    }
    /* End of angle operations */

    /* 2D Vector Operations */
    vector2d addV2(vector2d v1, vector2d v2) {
        double x = v1.getX() + v2.getX();
        double y = v1.getY() + v2.getY();
        return new vector2d(x, y);
    }

    vector2d subtractV2(vector2d v1, vector2d v2) {
        double x = v1.getX() - v2.getX();
        double y = v1.getY() - v2.getY();
        return new vector2d(x, y);
    }

    vector2d multiplyV2byScalar(vector2d v, double a) {
        double x = v.getX() * a;
        double y = v.getY() * a;
        return new vector2d(x, y);
    }

    vector2d multiplyV2byScalar(double a, vector2d v) {
        double x = v.getX() * a;
        double y = v.getY() * a;
        return new vector2d(x, y);
    }
    /* End of 2D vector operations */

}

// 2D vectors
class vector2d {
    private double x;
    private double y;

    public vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}