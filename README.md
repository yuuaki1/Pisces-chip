# Pisces-8 Emulator

Pisces-8 is a simple CHIP-8 emulator written in Java. It allows you to run classic CHIP-8 programs and games, like Pong, Tetris, and Space Invaders.  

This project helped me learn **how emulators work**, **how instructions are executed**, and how old-school graphics were handled.

---

## Features

- Implements most of the CHIP-8 instructions:
  - **Flow control:** `JP`, `CALL`, `RET`, `SE`, `SNE`  
  - **Arithmetic:** `ADD`, `SUB`, `SUBN`, `OR`, `AND`, `XOR`  
  - **Display:** `CLS`, `DRW` (draw sprites on a 64x32 monochrome screen)  
  - **Timers:** `LD DT, Vx`, `LD ST, Vx`  
  - **Keyboard input:** `SKP`, `SKNP`, `LD Vx, K`  
  - **Memory operations:** `LD [I], V0..Vx`, `LD V0..Vx, [I]`  
- 64x32 display with dynamic resizing  
- Keyboard support for CHIP-8 keycodes  
- Random number generation for instructions like `RND`  
- Stack for subroutine calls  
- Logging for debugging  

---

## How It Works

- **Registers:** 16 registers V0–VF, each 8-bit.  
  - **VF** is a special “flag” register used for carry, borrow, and collisions.  
- **I register:** A 16-bit register used as a memory pointer.  
- **Memory:** 4 KB of RAM (0x000–0xFFF).  
- **Program Counter (PC):** Points to the current instruction.  
- **Stack Pointer (SP):** Points to the top of the call stack.  
- **Sprites:** Drawn using XOR; collisions set VF = 1.  

**Important Bug:**  
In some games like Pong, when VF (the carry/flag register) is updated during arithmetic or shifts, it can **incorrectly change the score** if it goes above 10. I don't know why it does this, this is a "passion" project made purely on caffeine and regrets late at night.

---

## Images 
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/78c9d8a3-224a-4365-9400-5698e273fe90" />
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/99c70d91-250c-4e2f-bc02-0fe7178a0e8a" />

