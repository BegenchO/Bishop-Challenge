/*

Requirements:
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

object BishopChallenge {

    def main(args: Array[String]): Unit = {

        // Display menu
        var menuOn = true

        while (menuOn) {

            displayMenu()

            // Get user response
            val response = getResponse("Please select an option")

            response match {
                case "1" => calculateMoves()
                case "0" => { 
                    displayText("Exiting...")
                    menuOn = false
                }
                case _ => displayText("ERROR: Please input a valid command (0-1)")
            } // end match case

        } // end while
       
    } // end main


    def displayMenu(): Unit = {
        
        println("\n---------------------------")
        println(s"Welcome!")
        println("---------------------------")
        println("1. Calculate Number of Bishop Moves Required to Move from one square to another")
        println("0. Exit")

    } // end displayMenu


    def displayText(text: String, delay: Int = 2000): Unit = {

        println(text)
        Thread.sleep(delay)

    } // end displayText


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

    } // end letterToNum


    def convertToSquare(x: Int, y: Int): String = {

        val xLetter = numToLetter(x)

        return s"${xLetter}${y}"

    } // end convertToSquare


    def numToLetter(num: Int): String = {

        val letter = num match {
            case 1 => "a"
            case 2 => "b"
            case 3 => "c"
            case 4 => "d"
            case 5 => "e"
            case 6 => "f"
            case 7 => "g"
            case 8 => "h"
            case _ => ""
        }
        return letter

    } // end numToLetter


    def getResponse(text: String): String = {

        println(text)
        print("--> ")
        val response = scala.io.StdIn.readLine()
        
        return response

    } // end getResponse


    def calculateMoves(): Boolean = {

        var allInputValidated = false
        var start = ""
        var end = ""
        var requestedMoveNum = ""
        var moves = new ListBuffer[String]()

        // Validate user input to match requirements
        while (!allInputValidated) {

            var startValidated = false
            var endValidated = false
            var moveNumValidated = false

            // Get user input
            start = getResponse("What is the starting square? (a1-h8)")
            end = getResponse("What is the ending square?  (a1-h8)")
            requestedMoveNum = getResponse("What is the number of moves? (1-9)")

            // Check if start square meets requirements
            if (start.length() == 2 && "abcdefgh".contains(start(0)) && "12345678".contains(start(1))) {
                startValidated = true
            }

            // Check if end square meets requirements
            if (end.length() == 2 && "abcdefgh".contains(end(0)) && "12345678".contains(end(1))) {
                endValidated = true
            }

            // Check if move number is within 1-10 range
            if ("123456789".contains(requestedMoveNum)) {
                moveNumValidated = true
            }

            // Final check if all validated
            if (startValidated && endValidated && moveNumValidated) {
                allInputValidated = true
            } else {
                displayText("ERROR: Please make sure all of your responses match requirements!")
            }

        } // end while
        
        // Convert letters to num to use as indeces
        var startX = letterToNum(start)
        var startY = start.substring(1,2).toInt
        var endX = letterToNum(end)
        var endY = end.substring(1,2).toInt

        // Show user that their request is being processed
        displayText(s"Checking if ${requestedMoveNum} moves are enough for a bishop to move from ${start} square to ${end}...", 1000)

        // First check if same square
        if (startX == endX && startY == endY) {
            displayText("Both start and destination squares are the same!")
            return true
        }

        // Next check if start and end square of different color and impossible to move there
        if (startX % 2 == endX % 2) {

            if (startY % 2 != endY % 2) {
                displayText(s"Bishop cannot move from ${start} square to ${end}")
                return false
            }
        } else {
            if (startY % 2 == endY % 2) {
                displayText(s"Bishop cannot move from ${start} square to ${end}")
                return false
            }

        } // end if else

        // Finally check if enough moves to get there
        var currentX = startX
        var currentY = startY
        var currentMoveNum = 1
        var prevDirectionX = ""
        var prevDirectionY = ""
        var currentDirectionX = ""
        var currentDirectionY = ""


        // Continue moving until you get there or run out of moves requested, whichever comes first
        while (currentX != endX || currentY != endY) {

            // Check direction to move
            
            // First check if left or right
            if (currentX > endX && currentX > 1) {
                // move left
                currentX -= 1
                currentDirectionX = "left"
            } else if (currentX <= endX && currentX < 8) {
                // move right
                currentX += 1
                currentDirectionX = "right"
            } // end if else

            // Then check if down or up
            if (currentY >= endY && currentY > 1) {
                // go down
                currentY -= 1
                currentDirectionY = "down"
            } else if (currentY <= endY && currentY < 8) {
                // go up
                currentY += 1
                currentDirectionY = "up"
            }

            // Append each move to the list to display all moves from start to end
            val moveTaken = convertToSquare(currentX, currentY)
            moves.addOne(moveTaken)
            
            // Check if direction changed to count moves
            if (prevDirectionX.length() > 0) {
                if (currentDirectionX != prevDirectionX || currentDirectionY != prevDirectionY) {
                    currentMoveNum += 1
                }
            } // end if

            prevDirectionX = currentDirectionX
            prevDirectionY = currentDirectionY

        } // end while

        // Display results
        if (requestedMoveNum.toInt >= currentMoveNum) {
            displayText(s"SUCCESS: Number of moves required to move bishop from ${start} to ${end} was under ${requestedMoveNum}: ${currentMoveNum}")

            // Display moves taken
            moves.foreach(move => print(move + " -> "))
            displayText("Moves taken from start to end")

            return true
        } else {
            displayText(s"BAD NEWS: Number of moves required to move bishop from ${start} to ${end} was not enough as requested was ${requestedMoveNum} and the actual number it took was ${currentMoveNum}")
            return false
        }

    } // end calculateMoves


} // end Main