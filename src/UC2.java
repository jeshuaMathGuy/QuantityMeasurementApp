import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UC2 {

    // ------------------- FEET CLASS -------------------
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet feet = (Feet) obj;
            return Double.compare(this.value, feet.value) == 0;
        }
    }

    // ------------------- INCHES CLASS -------------------
    static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches inches = (Inches) obj;
            return Double.compare(this.value, inches.value) == 0;
        }
    }

    // ------------------- STATIC METHODS -------------------

    public static boolean compareFeet(double a, double b) {
        Feet f1 = new Feet(a);
        Feet f2 = new Feet(b);
        return f1.equals(f2);
    }

    public static boolean compareInches(double a, double b) {
        Inches i1 = new Inches(a);
        Inches i2 = new Inches(b);
        return i1.equals(i2);
    }

    // ------------------- MAIN METHOD -------------------

    public static void main(String[] args) {

        boolean inchResult = compareInches(1.0, 1.0);
        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + inchResult + ")");

        boolean feetResult = compareFeet(1.0, 1.0);
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + feetResult + ")");
    }

    // ------------------- TESTS -------------------

    public static class QuantityMeasurementAppTest {

        // -------- FEET TESTS --------

        @Test
        void testFeetEquality_SameValue() {
            assertTrue(compareFeet(1.0, 1.0));
        }

        @Test
        void testFeetEquality_DifferentValue() {
            assertFalse(compareFeet(1.0, 2.0));
        }

        @Test
        void testFeetEquality_NullComparison() {
            Feet f = new Feet(1.0);
            assertFalse(f.equals(null));
        }

        @Test
        void testFeetEquality_NonNumericInput() {
            Feet f = new Feet(1.0);
            assertFalse(f.equals("1.0"));
        }

        @Test
        void testFeetEquality_SameReference() {
            Feet f = new Feet(1.0);
            assertTrue(f.equals(f));
        }

        // -------- INCHES TESTS --------

        @Test
        void testInchesEquality_SameValue() {
            assertTrue(compareInches(1.0, 1.0));
        }

        @Test
        void testInchesEquality_DifferentValue() {
            assertFalse(compareInches(1.0, 2.0));
        }

        @Test
        void testInchesEquality_NullComparison() {
            Inches i = new Inches(1.0);
            assertFalse(i.equals(null));
        }

        @Test
        void testInchesEquality_NonNumericInput() {
            Inches i = new Inches(1.0);
            assertFalse(i.equals("1.0"));
        }

        @Test
        void testInchesEquality_SameReference() {
            Inches i = new Inches(1.0);
            assertTrue(i.equals(i));
        }
    }
}