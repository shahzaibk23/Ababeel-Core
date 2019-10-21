// See LICENSE.txt for license details.
package datapath

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
	"ITD" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ITD(), manager) {
          (c) => new ITDTests(c)
        }
      },
	"CD" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new CD(), manager) {
          (c) => new CDTests(c)
        }
      },
	"Control" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Control(), manager) {
          (c) => new ControlTests(c)
        }
      },
	"AluControl" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new AluControl(), manager) {
          (c) => new AluControlTests(c)
        }
      },
	"ALU" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALU(), manager) {
          (c) => new AluTests(c)
        }
      },
	"ImmGen" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ImmGen(), manager) {
          (c) => new ImmGenTests(c)
        }
      },
	"RegFile" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new RegFile(), manager) {
          (c) => new RegFileTests(c)
        }
      },
	"Top" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Top(), manager) {
          (c) => new TopTests(c)
        }
      },
	"JalrTarget" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new JalrTarget(), manager) {
          (c) => new JalrTests(c)
        }
      },
	"PC" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new PC(), manager) {
          (c) => new PCTests(c)
        }
      },
	"InsMem" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new InsMem(), manager) {
          (c) => new InsMemTests(c)
        }
      },
	"MainMem" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new MainMem(), manager) {
          (c) => new MainMemTests(c)
        }
      }
)

  def main(args: Array[String]): Unit = {
    TutorialRunner("examples", examples, args)
  }
}

