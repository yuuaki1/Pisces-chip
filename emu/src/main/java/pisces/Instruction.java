package pisces;

public class Instruction {
    public enum Instructions {
        CLS,
        RET,
        JP, 
        CALL,
        RTS, 
        SE, SNE, ADD, OR, AND, XOR, SUB, SHR, SUBN, SHL, RND, DRW, SKP, SKNP, LD
    }

    public enum Operand {
        V0,
        V1,
        V2,
        V3,
        V4,
        V5,
        V6,
        V7,
        V8,
        V9,
        VA,
        VB,
        VC,
        VD,
        VE,
        VF,
        I,   // The 'I' register
        DT, // Delay timer value
        K, // Key pressed by user
        ST, // Sound timer value
        F, // Font
        B, // Binary coded decimal
        I_ARRAY; // Represents registers V0 to Vx starting at memory location I

        @Override
        public String toString() {
            if (this == I_ARRAY) {
                return "[I]";
            } else {
                return super.toString();
            }
        }
    }

    protected Instructions instruction;
    protected Operand op1, op2;
    protected byte val;
    protected boolean is_val_2_bytes = false;
    protected short extended_value; // is value 2 bytes (to be stored in memory registers)
    protected boolean no_value = false;  // flags the instruction if it has no immediate valule

    Instruction(Instructions i) { // Just the OPCODE (CLS (clear), RET (return the subroutine))
        this.instruction = i;
        this.no_value = true;
    }

    Instruction(Instructions i, Operand op1, Operand op2, byte val) { // OPCODE + operands + 1 byte value
        // some instructons need registers and a small immediate value
        this.instruction = i;
        this.op1 = op1;
        this.op2 = op2;
        this.val = val;
    }

    Instruction(Instructions i, Operand op1, Operand op2, short value) { // OPCODE + operands + 2byte val
        // Some instructions requre a larger value, like an address (12 or 16 bit)
        this.instruction = i;
        this.op1 = op1;
        this.op2 = op2;
        this.extended_value = value;
        this.is_val_2_bytes = true;
    }

    Instruction(Instructions i, short value) { // OPCODE + 2byte val
        // Some instructions dont use registers, just an address or big number
        this.instruction = i;
        this.extended_value = value;
        this.is_val_2_bytes = true;
    }

    public Instruction (Instructions i, Operand op1, Operand op2) { // OPCODE + operands without any value
        // Some instructions only operate on registers, no immediate value
        this.instruction = i;
        this.op1 = op1;
        this.op2 = op2;
        this.no_value = true; // No value provided
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.instruction.name()).append(" ");

        if (this.op1 != null) {
            sb.append(this.op1).append(", ");
            if (this.op2 != null) {
                sb.append(this.op2).append(", ");
            }
        }

        sb.append(String.format("0x%02X", is_val_2_bytes ? this.extended_value : this.val));
        sb.append(" ("+(is_val_2_bytes ? this.extended_value : this.val)+")");
        return sb.toString();
    }
}
