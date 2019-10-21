package datapath

import chisel3._

class RegFile extends Module{
	val io = IO(new Bundle{
		val RegWrite = Input(UInt(1.W))
		val rs1 = Input(UInt(5.W))
		val rs2 = Input(UInt(5.W))
		val rd = Input(UInt(5.W))
		val WriteData = Input(SInt(32.W))

		val rd1 = Output(SInt(32.W))
		val rd2 = Output(SInt(32.W))
	})
	
	val registers = Reg(Vec(32,SInt(32.W)))	
	registers(0) := (0.S)
	io.rd1 := registers(io.rs1)
	io.rd2 := registers(io.rs2)
	when(io.RegWrite === 1.U){
		when(io.rd === "b00000".U){
			registers(io.rd) := 0.S
		}.otherwise{
			registers(io.rd) := io.WriteData
		}
	}
}
