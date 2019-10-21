package datapath

import chisel3.iotesters.PeekPokeTester

class RegFileTests(c: RegFile) extends PeekPokeTester(c){
  poke(c.io.RegWrite, 1)
  poke(c.io.rs1, 2)
  poke(c.io.rs2, 3)
  poke(c.io.rd, 5)
  poke(c.io.WriteData, 10)
  step(1)
}
