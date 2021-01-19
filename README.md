Simple Ludo game written in Java. Project for Java course.

How Ludo works:

Each player tries to get all their pawns "home". Home is the white tile in the middle of the board.
To get there a pawn has to complete a full circle around the board. All pawns are spawned
in "bases". Base is the 2x2 square specific for each player. To be able to get a pawn out of
the base onto the board, player has to roll six on the dice.

Pawns can knock each other out. If a pawn is standing alone on a tile, and then
another pawn (from enemy player) also stands there the previous pawn usually gets "knocked out" which results
in it being sent back to the base (and then the owner has to get it out onto the board again).
Knock-outs can't happen on a tile where there is more than one pawn of any color 
(eg. if there 2 red pawns on a tile, the whole tile is safe) or on the starting tiles
for each colour (the one where pawns go when they make their way out of the base).

Whoever gets all their pawns home first, wins. The game lasts until everyone get all their pawns home
so that end leaderboard can be determined.

Important notes:

Dice is rolled by clicking "roll the dice" button. If there is a move possible, some tiles
will be highlighted on the board. To execute the move click on one of the highlighted tiles.
If there is no move possible, the game automatically changes the current player to the next one.
Starting order is random.

A silver outline around the pawn means there is more than 1 pawn of this colour on this tile.

Rolling a six results in an extra move.