package datapath

import chisel3.iotesters.PeekPokeTester

class TopTests(c: Top) extends PeekPokeTester(c){
  //poke(c.io.main_Instruction, 5243155)
  //poke(c.io.main_PC, 0)
  step(1)
  step(1)
  step(1)
  step(1)
  step(1)

}
