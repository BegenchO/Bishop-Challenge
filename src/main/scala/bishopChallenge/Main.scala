/*

Weekly Coding Challenge: Bishop Challenge:

Your chess teacher wants to know if a bishop can reach a certain spot on the board in the given amount of moves.
Given a starting square start, ending square end and the maximum number of moves allowed n. Return true if the ending square can be reached from the starting square within the given amount of moves. Keep in mind the chessboard goes from a1 to h8 (8x8).
Examples
<code>bishop("a1", "b4", 2) ➞ true
bishop("a1", "b5", 5) ➞ false
bishop("f1", "f1", 0) ➞ true</code>Notes
Chessboard is always empty (only the bishop is there).
Bishop can move in any direction diagonally.
If the starting and ending square are the same, return true (even if the move is 0).
Square names will always be lowercase and valid.

*/

package bishopChallenge

import scala.collection.mutable.ListBuffer

object Main {

    def main(args: Array[String]): Unit = {

        val start = getResponse("What is the starting square?")
        val end = getResponse("What is the ending square?")
        val requestedMoveNum = getResponse("What is the number of moves?")

        var startX = letterToNum(start)
        var startY = start.substring(1,2).toInt
        var endX = letterToNum(end)
        var endY = end.substring(1,2).toInt

        println(s"start position: x:${startX}, y: ${startY}")
        println(s"end position: x:${endX}, y: ${endY}")

        // check if same square
        if (startX == endX && startY == endY) {
            println("FALSE: Same Cell!")
        }

        // check if start and end square of different color
        if (startX % 2 == endX % 2) {
            if (startY % 2 != endY % 2) {
                println("FALSE: Never can move there!")
            }
        } else {
            if (startY % 2 == endY % 2) {
                println("FALSE: Never can move there!")
            }
        }

        // check if enough moves to get there

        var currentX = startX
        var currentY = startY
        var currentMoveNum = 1
        var prevDirectionX = ""
        var prevDirectionY = ""
        var currentDirectionX = ""
        var currentDirectionY = ""

        // Continue moving until you get there or run out of moves requested, whichever comes first
        //while ((currentMoveNum < (requestedMoveNum.toInt + 1)) || (currentX != endX && currentY != endY)) {
        while (currentX != endX || currentY != endY) {

            println("Checking direction...")
            // Check direction to move

            // First check if left or right
            // Then check if down or up

            if (currentX > endX && currentX > 1) {
                // move left
                currentX -= 1
                currentDirectionX = "left"
                if (currentY >= endY && currentY > 1) {
                    // go down
                    currentY -= 1
                    currentDirectionY = "down"
                } else if (currentY <= endY && currentY < 8) {
                    // go up
                    currentY += 1
                    currentDirectionY = "up"
                }

            } else if (currentX <= endX && currentX < 8) {
                // move right
                currentX += 1
                currentDirectionX = "right"
                if (currentY >= endY && currentY > 1) {
                    // go down
                    currentY -= 1
                    currentDirectionY = "down"
                } else if (currentY <= endY && currentY < 8) {
                    // go up
                    currentY += 1
                    currentDirectionY = "up"
                }

            } // end if else

            
            // Check if direction changed
            println(s"Previous direction x: ${prevDirectionX}")
            println(s"Current direction x: ${currentDirectionX}")
            show("Checking if direction changed...")
            if (prevDirectionX.length() > 0) {
                if (currentDirectionX != prevDirectionX || currentDirectionY != prevDirectionY) {
                    currentMoveNum += 1
                    println("Changed direction!")
                } else {
                    println("Continuing in the same direction")
                }
                
            } // end if

            prevDirectionX = currentDirectionX
            prevDirectionY = currentDirectionY


            show(s"Current x: ${currentX}")
            show(s"Current y: ${currentY}")
            show(s"Current move num: ${currentMoveNum}")

            show(s"Target x: ${endX}")
            show(s"Target y: ${endY}")


        } // end while

        show(s"SUCCESS: Number of moves required to move bishop from ${start} to ${end} was under ${requestedMoveNum}: ${currentMoveNum}")


    } // end main

    def show(text: String): Unit = {
        println(text)
        Thread.sleep(2000)
    }


    def letterToNum(text: String): Int = {
        val num = text.substring(0,1) match {
            case "a" => 1
            case "b" => 2
            case "c" => 3
            case "d" => 4
            case "e" => 5
            case "f" => 6
            case "g" => 7
            case "h" => 8
            case _ => 0
        }
        return num
    }


    def getResponse(text: String): String = {

        println(text)
        print("-->")
        val response = scala.io.StdIn.readLine()
        
        return response

    } // end getResponse


    def newChessBoard(): List[List[String]] = {
        var chessBoard = new ListBuffer[List[String]]

        val letters = List("a", "b", "c", "d", "e", "f", "g", "h")
        
        letters.foreach(letter => {
            var tempList = new ListBuffer[String]()

            for (n <- 1 to 8) {
                val newSquare = s"${letter}${n}"
                tempList.addOne(newSquare)
            }

            val newList = tempList.toList
            chessBoard.addOne(newList)

        }) // end foreach

        return chessBoard.toList
    
    } // end newChessBoard


} // end Main