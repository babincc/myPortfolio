# Checkpoint 2

In the second piece of the project, from the [user stories](../user%20stories.md) left over from the first checkpoint, "Choose who goes first", "Play tic-tac-toe", and "Quit" were chosen as the next steps.

Single player mode has been added! An algorithm was created that looks at the tic-tac-toe board and determines where the computer should move to keep the player from winning. Its first priority is to keep the player from winning, this could happen with a tie game or a win by the computer. The algorithm will try win the game only if there is no chance that it will give the player an opportunity to win.

Note: The error handling is at about 80%. Error handling can not be completed until the third (and secret) game mode is built, and that is coming in Checkpoint 3. In this checkpoint, only the impossible game mode was added. The secret gesture that puts it into the easy game mode will cause the computer to stop playing since it won't know what to do.
