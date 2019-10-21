package datapath

import chisel3._
import chisel3.util.experimental.loadMemoryFromFile

class InsMem extends Module{
	val io = IO(new Bundle{
		val wrAdder = Input(UInt(10.W))
		val rData = Output(UInt(32.W))
	})

	val mem = Mem(1024, UInt(32.W))
	io.rData := mem(io.wrAdder)
	loadMemoryFromFile(mem, "/home/hellcaster/Instruction.txt")
}
