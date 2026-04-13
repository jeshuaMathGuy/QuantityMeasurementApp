import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UC1 {

    // Inner class representing Feet measurement
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Feet feet = (Feet) obj;

            return Double.compare(this.value, feet.value) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {
        Feet value1 = new Feet(1.0);
        Feet value2 = new Feet(1.0);

        boolean result = value1.equals(value2);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }

    // ------------------- TESTS -------------------

    public static class QuantityMeasurementAppTest {

        @Test
        void testEquality_SameValue() {
            Feet a = new Feet(1.0);
            Feet b = new Feet(1.0);

            assertTrue(a.equals(b), "1.0 ft should be equal to 1.0 ft");
        }

        @Test
        void testEquality_DifferentValue() {
            Feet a = new Feet(1.0);
            Feet b = new Feet(2.0);

            assertFalse(a.equals(b), "1.0 ft should not be equal to 2.0 ft");
        }

        @Test
        void testEquality_NullComparison() {
            Feet a = new Feet(1.0);

            assertFalse(a.equals(null), "Value should not be equal to null");
        }

        @Test
        void testEquality_NonNumericInput() {
            Feet a = new Feet(1.0);
            String nonNumeric = "1.0";

            assertFalse(a.equals(nonNumeric), "Feet should not equal non-numeric input");
        }

        @Test
        void testEquality_SameReference() {
            Feet a = new Feet(1.0);

            assertTrue(a.equals(a), "Object should be equal to itself");
        }
    }
}