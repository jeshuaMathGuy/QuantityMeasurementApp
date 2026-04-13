import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UC4{

    // ------------------- ENUM: LENGTH UNITS -------------------
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CM(1.0 / 30.48); // 1 cm = 0.393701 inch ≈ 1/30.48 feet

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

        // Convert everything to FEET (common base unit)
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

        System.out.println("1 ft vs 12 inch -> " + q1.equals(q2));

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q4 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("1 yard vs 3 feet -> " + q3.equals(q4));

        QuantityLength q5 = new QuantityLength(2.5, LengthUnit.CM);
        QuantityLength q6 = new QuantityLength(2.5 / 2.54 * 1.0 / 12.0, LengthUnit.INCH);

        System.out.println("CM comparison -> " + q5.equals(q6));
    }

    // ------------------- TEST CASES -------------------
    public static class QuantityMeasurementAppTest {

        // FEET
        @Test
        void testFeetEquality() {
            assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                    .equals(new QuantityLength(1.0, LengthUnit.FEET)));
        }

        // INCH
        @Test
        void testInchEquality() {
            assertTrue(new QuantityLength(1.0, LengthUnit.INCH)
                    .equals(new QuantityLength(1.0, LengthUnit.INCH)));
        }

        // YARD vs FEET
        @Test
        void testYardToFeetEquality() {
            assertTrue(new QuantityLength(1.0, LengthUnit.YARD)
                    .equals(new QuantityLength(3.0, LengthUnit.FEET)));
        }

        // CM vs INCH (cross unit)
        @Test
        void testCmToInchEquality() {
            assertTrue(new QuantityLength(2.54, LengthUnit.CM)
                    .equals(new QuantityLength(1.0, LengthUnit.INCH)));
        }

        // FEET vs INCH
        @Test
        void testFeetToInchEquality() {
            assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                    .equals(new QuantityLength(12.0, LengthUnit.INCH)));
        }

        // DIFFERENT VALUES
        @Test
        void testDifferentFeetValues() {
            assertFalse(new QuantityLength(1.0, LengthUnit.FEET)
                    .equals(new QuantityLength(2.0, LengthUnit.FEET)));
        }

        // YARD DIFFERENT
        @Test
        void testDifferentYardValues() {
            assertFalse(new QuantityLength(1.0, LengthUnit.YARD)
                    .equals(new QuantityLength(2.0, LengthUnit.YARD)));
        }

        // SAME REFERENCE
        @Test
        void testSameReference() {
            QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
            assertTrue(q.equals(q));
        }

        // NULL CHECK
        @Test
        void testNullComparison() {
            QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
            assertFalse(q.equals(null));
        }

        // INVALID TYPE
        @Test
        void testInvalidType() {
            QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
            assertFalse(q.equals("1.0 feet"));
        }
    }
}