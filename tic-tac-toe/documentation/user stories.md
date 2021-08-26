# User Stories

These [user stories (external link)](https://en.wikipedia.org/wiki/User_story) are important features that need to be part of the app. Each user story is broken down into scenarios to help visualize each problem better. Once a scenario has an Espresso test attached to it, it has been added to the app.

### Table of Contents

- [Learn how to play](#learn-how-to-play)
- [Start game](#start-game)
- [Multiplayer](#multiplayer)
- [Keep score](#keep-score)
- [Choose who goes first](#choose-who-goes-first)
- [Play tic-tac-toe](#play-tic-tac-toe)
- [Quit](#quit)
- [See achievement](#see-achievement)
- [Play again](#play-again)
- [Play a prank](#play-a-prank)

## Learn how to play

Learn how to play <br>
As a game player <br>
So that I can understand what I am supposed to do <br>
I want to be able to look at instructions if I have questions

<hr>

Scenario: Player has never played before <br>
Given they want to learn <br>
When they click the “Instructions” button <br>
Then they will learn how to play

Espresso test: [testInstructions()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L52)

<hr>

Scenario: Argument over win/loss <br>
Given there is a discrepancy over the score <br>
When the players look at the instructions <br>
Then they will be able to tell who is right

Espresso test: [testInstructions()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L52)

<hr>

Scenario: See how the machine will play <br>
Given you have played tic-tac-toe before <br>
When you click the “instructions” button <br>
Then you will see the exact set of rules the machine will be following

Espresso test: [testInstructions()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L52)

<hr>

Scenario: Finished looking at instructions <br>
Given you have gotten everything you need out of the instruction page <br>
And you are ready to go back <br>
When you click the “Back” button <br>
Then you will go back to the home/welcome screen

Espresso test: [testInstructionsBack()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L62)

## Start game

Start game <br>
As a game player <br>
So that I can play <br>
I want a way to join a game

<hr>

Scenario: Giving it to a friend <br>
Given they hit the play button <br>
When the game starts <br>
Then they will be playing against the machine in hard mode

Espresso test: [testStartGame_singlePlayerHard()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L90)

<hr>

Scenario: Showing I’m a genius <br>
Given I hit the play button <br>
And use the secret gesture <br>
When the game starts <br>
Then I will be playing against the machine in easy mode

Espresso test: [testStartGame_singlePlayerEasy()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L98)

<hr>

Scenario: Multiplayer <br>
Given I chose two-player mode <br>
When the game starts <br>
Then I will be playing against my friend on the same device

Espresso test: [testStartGame_Multiplayer()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L113)

## Multiplayer

Multiplayer <br>
As a game player <br>
So that I can play with a friend <br>
I want to be able to select between player 1 or player 2

<hr>

Scenario: Choosing multiplayer <br>
Given I want to play a real game with a friend <br>
When I click the “multiplayer” button <br>
Then I will be able to set up a 2 player game (with no gimmicks)

Espresso test: [testStartGame_Multiplayer()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L113)

<hr>

Scenario: Player 1 <br>
Given I chose “Player 1” <br>
When I enter my name <br>
And click “Start” <br>
Then my name will be displayed on the scoreboard <br>
And I will go first

Espresso test: [testMultiplayer_Player1()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L139)

<hr>

Scenario: Player 2 <br>
Given I chose “Player 2” <br>
When I enter my name <br>
And click “Start” <br>
Then my name will be displayed on the scoreboard <br>
And I will go after player 1

Espresso test: [testMultiplayer_Player2()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L153)

## Keep score

Keep score <br>
As a game player <br>
So that I can see my success rate <br>
I want to see how many times I have won vs lost

<hr>

Scenario: Player wins <br>
Given the player makes a move <br>
When they get three in a row <br>
Then they get a “Congrats!” <br>
And their score goes up

Espresso tests: [testKeepScore_PlayerWins()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L177) && [testKeepScore_PlayerWins2()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L197)

<hr>

Scenario: The computer wins <br>
Given the computer makes a move <br>
When it gets three in a row <br>
Then the player sees a “you lose” screen <br>
And the computer’s score goes up

Espresso test: [testKeepScore_computerWins()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L224)

<hr>

Scenario: It is a tie <br>
Given all spaces are filled <br>
When no one has three in a row <br>
Then the player sees a “tie” screen <br>
And the score stays the same

Espresso test: [testKeepScore_Tie()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L244)

## Choose who goes first

Choose who goes first <br>
As a game player <br>
So that I can have my best chance of winning <br>
I want to choose whether I go first or second each round

<hr>

Scenario: Player goes first <br>
Given the player wants to go first <br>
When they click the “first” button <br>
Then they will be allowed to go first

Espresso test: [testPlayerOrder_playerFirst()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L281)

<hr>

Scenario: Computer goes first <br>
Given the player wants to go second <br>
When they click the “second” button <br>
Then the computer will go first

Espresso test: [testPlayerOrder_computerFirst()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L295)

<hr>

Scenario: Multiplayer (player 1 & 2) <br>
Given you are playing against another person <br>
When the players enter their names under “Player 1” and “Player 2” <br>
Then that is the order they will play in

Espresso tests: [testPlayerOrder_player1First()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L310) && [testPlayerOrder_player2First()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L325)

## Play tic-tac-toe

Play tic-tac-toe <br>
As a gamer <br>
So that I can test my skill <br>
I want to be challenged when playing tic-tac-toe

<hr>

Scenario: Challenge myself <br>
Given I know it is going to be impossible to win <br>
When I play the game by myself <br>
Then I will have a “worthy opponent” to really challenge me

Espresso tests: [testGamePlay_hard1()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L356) && [testGamePlay_hard2()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L375) && [testGamePlay_hard3()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L394) && [testGamePlay_hard4()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L394) && [testGamePlay_hard5()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L431) && [testGamePlay_hard6()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L447) && [testGamePlay_hard7()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L465)

<hr>

Scenario: Practice <br>
Given I put the game into easy mode with the secret gesture <br>
When I play against it more and more <br>
Then I will get familiar with it <br>
And later <br>
When I play a prank on a friend <br>
Then I won’t lose <br>

No Espresso test (the order that everything was programmed in didn't allow for this to be tested until the end, and at that point there was no need to test)

<hr>

Scenario: Multiplayer <br>
Givin I want to play with a friend <br>
When I play multiplayer <br>
Then I will be able to play a game with a friend <br>
And it will keep score <br>
And the loser will get to go first next round

Espresso test: [testGamePlay_multiplayer()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L492)

## Quit

Quit <br>
As a game player <br>
So that I can give other people a turn with the game <br>
I want to be able to reset the game without having to close the app and re-open it

<hr>

Scenario: Quit mid-game <br>
Given the player is in the middle of a game <br>
When they want to leave <br>
And they press “Quit” <br>
Then they will be told that the score and moves will be erased if they quit <br>
And they can choose to quit, or not <br>
When they do quit <br>
Then they will be brought back to the start screen

Espresso tests: [testQuit_duringGamePlayMulti()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L520) && [testQuit_duringGamePlaySingle()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L533)

<hr>

Scenario: Quit from victory screen <br>
Given the player has just won a game <br>
When they want to leave <br>
And they press “Quit” <br>
Then they will be told that the score will be erased if they quit <br>
And they can choose to quit, or not <br>
When they do quit <br>
Then they will be given a “Quit while you are ahead” message <br>
And brought back to the start screen

Espresso tests: [testQuit_afterGameFinishesWin()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L552)

<hr>

Scenario: Quit from loss and tie screen <br>
Given the player has just lost or tied a game <br>
When they want to leave <br>
And they press “Quit” <br>
Then they will be told that the score will be erased if they quit <br>
And they can choose to quit, or not <br>
When they do quit <br>
Then they will be given a “better luck next time” message <br>
And brought back to the start screen

Espresso tests: [testQuit_afterGameFinishesLossOrTie()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L572)

## See achievement

See achievement <br>
As a game player <br>
So that I feel good if I win <br>
I want a “congratulations” from the app

<hr>

Scenario: Win <br>
Given I win the game <br>
When I have made the winning move <br>
Then I will be taken to the victory screen <br>
And I will see a “congratulations” <br>
And I will be given the option to keep going or quit

Espresso test: [testAffirmationCongrats()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L723)

<hr>

Scenario: Lose <br>
Given I lose the game <br>
When the computer makes the winning move <br>
Then I will be taken to the loss screen <br>
And I will see a “you lose” message <br>
And I will be given the option to keep going or quit

Espresso test: [testAffirmationYouLose()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L745)

<hr>

Scenario: Tie <br>
Given it is a tie game <br>
When the last spot has been filled <br>
And there are not three of the same mark in a row <br>
Then I will be taken to the tie screen <br>
And I will see “tie” <br>
And I will be given the option to keep going or quit

Espresso test: [testAffirmationTieGame()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L765)

## Play again

Play again <br>
As a game player <br>
So that I can try again <br>
I want to be able to play another round without having to go back to the home screen every time and lose my score status

<hr>

Scenario: On a loss <br>
Given the player loses the game <br>
When the loss screen comes up <br>
Then they can choose to play again <br>
And the score will be adjusted and saved accordingly

Espresso test: [testTryAgainAfterLoss()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L796)

<hr>

Scenario: On a win <br>
Given the player wins the game <br>
When the victory screen comes up <br>
Then they can choose to play again <br>
And the score will be adjusted and saved accordingly

Espresso test: [testTryAgainAfterWin()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L818)

<hr>

Scenario: On a tie <br>
Given the nobody wins the game <br>
When the tie screen comes up <br>
Then the player can choose to play again <br>
And the score will not be changed

Espresso test: [testTryAgainAfterTie()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L842)

## Play a prank

Play a prank <br>
As a jokester <br>
So that I can make my friends laugh <br>
I want to have an app that makes me look like a genius

<hr>

Scenario: Give the game to a friend <br>
Given they play the game <br>
When they make a move <br>
Then the computer will know the best place to go to keep them from winning

Espresso test: [testPrankFriendStep1()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L617)

<hr>

Scenario: Showing the friend that I am a genius <br>
Given they have tried the game and know how hard it is <br>
When I do the secret gesture (like 3 finger swipe) it becomes easy <br>
Then I win every game <br>
And I look like a genius

Espresso test: [testPrankFriendStep2()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L639)

<hr>

Scenario: They try again <br>
Given the friend has seen that it is beatable <br>
When I give it back to them to retry <br>
Then I will do the secret gesture again <br>
And they will not be able to beat it

Espresso test: [testPrankFriendStep3()](https://github.com/babincc/myPortfolio/blob/main/tic-tac-toe/app/src/androidTest/java/com/example/tic_tac_toe/EspressoTest.java#L671)
