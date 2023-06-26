package it.unicam.cs.paduraru.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void add() {
        Vector v1 = new Vector(5,5), v2 = new Vector(7,7);
        assertTrue(v1.add(v2).equals(v2.add(v1))
                && v1.add(v2).equals(new Vector(12,12))
        && v2.add(v1).equals(new Vector(12,12)));
    }

    @Test
    void divScalar() {
        Vector v1 = new Vector(50,50);
        assertTrue(v1.divScalar(2).equals(new Vector(25,25)));
    }

    @Test
    void mulScalar() {
        Vector v1 = new Vector(25,25);
        assertTrue(v1.mulScalar(2).equals(new Vector(50,50)));
    }

    @Test
    void magnitude() {
        Vector v1 = new Vector(2,0), v2 = new Vector(0,2), v3 = new Vector(3,4);
        assertEquals(2,v1.magnitude());
        assertEquals(2,v2.magnitude());
        assertEquals(5,v3.magnitude());
    }

    @Test
    void normalize() {
        Vector v1 = new Vector(2,0), v2 = new Vector(0,2), v3 = new Vector(4,4);
        assertTrue(v1.normalize().equals(new Vector(1,0)));
        assertTrue(v2.normalize().equals(new Vector(0,1)));
       // assertTrue(v3.normalize().equals(new Vector(0.7071067811865475,0.7071067811865475)));
    }

    @Test
    void distance() {
        Vector v1 = new Vector(0,0), v2 = new Vector(10,10);
        assertTrue(v1.distance(v2).equals(new Vector(10,10)));
    }
}