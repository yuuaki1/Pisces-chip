package pisces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HexFormat;

public class Main {
    public static byte[] hexStringToByteArray(String s) { //CHIP-8 ROM is a sequence of raw bytes. 
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i+= 5) { // 5 because every hex pair is 5 characters apart, eg: 0xAB -> indices 2 and 3 are A and B
            sb.append(s.charAt(i + 2));
            sb.append(s.charAt(i + 3)); // these two grab the important two hex digits from 0x?? and build a continuous string.
        }

        return HexFormat.of().parseHex(sb.toString()); // this parses the HEX String into byte array
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Logger logger = LoggerFactory.getLogger(Main.class);

        // Read from ROM file

        String ch8Program = "test_rom/test_opcode.ch8"; // This is the CHIP-8 program file, it contains the instructions to be executed by the emulator.

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(ch8Program);
        byte[] program = new byte[4096];
        int bytes_read = inputStream.read(program);

        logger.debug("Loading ROM, bytes: " + bytes_read);

        StringBuilder program_bytes_str = new StringBuilder();
        for (int i = 0; i < bytes_read; i++) {  // This is the program counter loop! CHIP-8 follows a fetch -> decode -> execute loop
            byte msb = program[i]; // most significant byte
            byte lsb = program[i + 1]; // least significant byte 
            
            // The above was fetchingare 

            program_bytes_str // to make instructions human readable, %02X formats the byte to a two-digit uppercase hexadecimal string
                    .append("0x")
                    .append(String.format("%02X", msb))
                    .append(String.format("%02X", lsb))
                    .append(" ");
        }

        if (bytes_read % 2 != 0) {
            program_bytes_str.append(String.format("0x%02X", program[bytes_read - 1]));
        }
        logger.debug("Program bytes: " + program_bytes_str);

        Display display = new Display();
        Input input = new Input();
        Window window = new Window(display, input);

        CPU cpu = new CPU(program, bytes_read, display, window, input);

        while (true) { // this loop keeps the emulator running
            Thread.sleep(1); // small pause to program doesnt hog CPU at 100%
            cpu.tick(); // execute one instruction at a time
        }
    }
}