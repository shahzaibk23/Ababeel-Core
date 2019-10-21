package datapath

import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill

class ImmGen extends Module{
	val io = IO(new Bundle{
		val ins = Input(UInt(32.W))
		val PC = Input(UInt(32.W))
		val S_Imm = Output(SInt(32.W))
		val SB_Imm = Output(SInt(32.W))
		val U_Imm = Output(SInt(32.W))
		val UJ_Imm = Output(SInt(32.W))
		val I_Imm = Output(SInt(32.W))
	})

		val imm7 = io.ins(7)
		val imm11 = io.ins(11,8)
		val imm19 = io.ins(19,12)
		val imm20 = io.ins(20)
		val imm24 = io.ins(24,21)
		val imm30 = io.ins(30,25)
		val imm31 = io.ins(31)
		
		io.I_Imm := Cat(Fill(20,imm31),imm31,imm30,imm24,imm20).asSInt
		io.S_Imm := Cat(Fill(20,imm31),imm31,imm30,imm11,imm7).asSInt
		io.SB_Imm := Cat(Fill(19,imm31),imm31,imm7,imm30,imm11,0.U).asSInt + io.PC.asSInt
		io.U_Imm := (Cat(Fill(12,imm31),imm31,imm30,imm24,imm20,imm19) << "hc".U).asSInt
		io.UJ_Imm := Cat(Fill(11,imm31),imm31,imm19,imm20,imm30,imm24,0.U).asSInt + io.PC.asSInt
		
		/**val sImmUpper = io.instruction(11,7)
		val sImmLower = io.instruction(31,25)
		val sImm12 = Cat(sImmLower, sImmUpper)
		val sImm32 = Cat(Fill(20,sImm12(31)), sImm12)
		io.S_Imm := sImm32.asSInt

		val sbImm11 = io.instruction(7)
		val sbImmUpper = io.instruction(11,8)
		val sbImmLower = io.instruction(30,25)
		val sbImm12 = io.instruction(31)
		val sbImm13 = Cat(sbImm12, sbImm11, sbImmLower, sbImmUpper, 0.U)
		val sbImm32 = Cat(Fill(19,sbImm13(12)), sbImm13)
		io.SB_Imm := sbImm32.asSInt + io.PC

		val uImm20 = io.instruction(31,12)
		val uImm32 = Cat(Fill(12, uImm20(19)), uImm20)
		val uImmShifted = uImm32 << "hc".U
		io.U_Imm := uImmShifted.asSInt

		val ujLower = io.instruction(19,12)
		val uj11 = io.instruction(20)
		val ujUpper = io.instruction(21,30)
		val uj20 = io.instruction(31)
		val uj21 = Cat(uj20, ujLower, uj11, ujUpper, 0.U)
		val uj32 = Cat(Fill(11, uj21(20)), uj21)
		io.UJ_Imm := uj32.asSInt + io.PC

		val iImm12 = io.instruction(31,20)
		val iImm32 = Cat(Fill(20,iImm12(11)), iImm12)
		io.I_Imm := iImm32.asSInt**/
}
