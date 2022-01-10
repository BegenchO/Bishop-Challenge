/*

Weekly Coding Challenge: Bishop Challenge:

Your chess teacher wants to know if a bishop can reach a certain spot on the board in the given amount of moves.
Given a starting square start, ending square end and the maximum number of moves allowed n. Return true if the ending square can be reached from the starting square within the given amount of moves. Keep in mind the chessboard goes from a1 to h8 (8x8).
Examples
<code>bishop("a1", "b4", 2) ➞ truebishop("a1", "b5", 5) ➞ falsebishop("f1", "f1", 0) ➞ true</code>Notes
Chessboard is always empty (only the bishop is there).
Bishop can move in any direction diagonally.
If the starting and ending square are the same, return true (even if the move is 0).
Square names will always be lowercase and valid.

*/

package bishopChallenge

import scala.collection.mutable.ListBuffer

object Main {

    def main(args: Array[String]): Unit = {

        print(newChessBoard.foreach(println))

    } // end main


    def newChessBoard(): List[List[String]] = {
        var chessBoard = new ListBuffer[List[String]]

        val letters = List("a", "b", "c", "d", "e", "f", "g", "h")
        
        letters.foreach(letter => {
            var tempList = new ListBuffer[String]()

            for (n <- 1 to 8) {
                val newCombo = s"${letter}${n}"
                tempList.addOne(newCombo)
            }

            val newList = tempList.toList
            chessBoard.addOne(newList)

        }) // end foreach

        return chessBoard.toList
    
    } // end newChessBoard


} // end Main