# Ababeel-Core
## A RISC-V based Single Cycle Core
### Designed by Shahzaib Kashif

First of all get started by Cloning this repository in your machine.
```ruby
git clone https://github.com/merledu/Ababeel-Core.git
```

Create a .txt file and place the ***hexadecimal*** code of your instructions simulated on ***Venus*** (RISC-V Simulator)\
Each instruction's hexadecimal code must be on seperate line as following. This program consists of 9 instructions.
```
00400113
00500193
014000EF
00120293
00402223
00402283
00520663
40310233
00008067
```
Then perform the following step
```ruby
cd /Ababeel/src/main/scala/datapath
```
Open **InsMem.scala** with this command. You can also manually go into the above path and open the file in your favorite text editor.
```ruby
open InsMem.scala
```
Find the following line
``` python
loadMemoryFromFile(mem, "/home/hellcaster/Instruction.txt")
```
Change the .txt file path to match your file that you created above storing your own program instructions.\
After setting up the InsMem.scala file, go inside the Ababeel folder.
```ruby
cd Ababeel
```
And enter
```ruby
sbt
```
When the terminal changes to this type
```ruby
sbt:A>
```
Enter this command
```ruby
sbt:Ababeel-Core> test:runMain datapath.Launcher Top
```Change the .txt file path to match your file that you created above storing your own program instructions.
After setting up the InsMem.scala file, go inside the Ababeel folder.

cd Ababeel

And enter

sbt
After success you will get a folder ***test_run_dir*** on root of your folder. Go into the examples folder inside.\
There you will find the folder named Top. Enter in it and you can find the Top.vcd file which you visualise on **gtkwave**
