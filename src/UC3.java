import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UC3 {

    // ------------------- ENUM (UNIT SYSTEM) -------------------
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double getToFeetFactor() {
            return toFeetFactor;
        }
    }

    // ------------------- GENERIC QUANTITY CLASS -------------------
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return this.value * this.unit.getToFeetFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength that = (QuantityLength) obj;

            return Double.compare(this.toFeet(), that.toFeet()) == 0;
        }
    }

    // ------------------- MAIN METHOD -------------------
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, feet) and Quantity(12.0, inch)");
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, inch) and Quantity(1.0, inch)");
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");
    }

    // ------------------- TEST CASES -------------------
    public static class QuantityMeasurementAppTest {

        // SAME UNIT - FEET
        @Test
        void testEquality_FeetToFeet_SameValue() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
            QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

            assertTrue(q1.equals(q2));
        }

        @Test
        void testEquality_FeetToFeet_DifferentValue() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
            QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

            assertFalse(q1.equals(q2));
        }

        // SAME UNIT - INCH
        @Test
        void testEquality_InchToInch_SameValue() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
            QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);

            assertTrue(q1.equals(q2));
        }

        @Test
        void testEquality_InchToInch_DifferentValue() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.INCH);
            QuantityLength q2 = new QuantityLength(2.0, LengthUnit.INCH);

            assertFalse(q1.equals(q2));
        }

        // CROSS UNIT COMPARISON
        @Test
        void testEquality_FeetToInch_EquivalentValue() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
            QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

            assertTrue(q1.equals(q2));
        }

        @Test
        void testEquality_InchToFeet_EquivalentValue() {
            QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
            QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

            assertTrue(q1.equals(q2));
        }

        // SAME REFERENCE
        @Test
        void testEquality_SameReference() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

            assertTrue(q1.equals(q1));
        }

        // NULL CHECK
        @Test
        void testEquality_NullComparison() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

            assertFalse(q1.equals(null));
        }

        // INVALID TYPE CHECK
        @Test
        void testEquality_InvalidType() {
            QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

            assertFalse(q1.equals("1.0 feet"));
        }
    }
}