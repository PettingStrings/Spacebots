package it.unicam.cs.paduraru.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PVectorTest {

    @Test
    void add() {
        PVector v1 = new PVector(5,5), v2 = new PVector(7,7);
        assertTrue(v1.add(v2).equals(v2.add(v1))
                && v1.add(v2).equals(new PVector(12,12))
        && v2.add(v1).equals(new PVector(12,12)));
    }

    @Test
    void divScalar() {
        PVector v1 = new PVector(50,50);
        assertEquals(v1.divScalar(2), new PVector(25, 25));
        assertEquals(v1.divScalar(0), new PVector(0, 0));
    }

    @Test
    void mulScalar() {
        PVector v1 = new PVector(25,25);
        assertEquals(v1.mulScalar(2), new PVector(50, 50));
    }

    @Test
    void magnitude() {
        PVector v1 = new PVector(2,0), v2 = new PVector(0,2), v3 = new PVector(3,4);
        assertEquals(2,v1.magnitude());
        assertEquals(2,v2.magnitude());
        assertEquals(5,v3.magnitude());
    }

    @Test
    void normalize() {
        PVector v1 = new PVector(2,0), v2 = new PVector(0,2), v3 = new PVector(4,4);
        assertEquals(v1.normalize(), new PVector(1, 0));
        assertEquals(v2.normalize(), new PVector(0, 1));
       // assertTrue(v3.normalize().equals(new Vector(0.7071067811865475,0.7071067811865475)));
    }

    @Test
    void distance() {
        PVector v1 = new PVector(0,0), v2 = new PVector(10,10);
        assertEquals(v1.distance(v2), new PVector(10, 10));
    }
}