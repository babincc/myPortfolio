# Checkpoint 3

In the third and final part of this project, the last three [user stories](../user%20stories.md) "See achievement", “Play again", and “Play a prank" were added to the app.

The long awaited “easy mode” has finally been added! Now, the secret gesture (w key) will toggle the game in and out of easy mode. The game is now complete!

Unfixable Lint errors (and known warnings):

- It is known that the program’s fonts will only be used in Android API level 26 and higher.
- Lint believes that there are 79 spelling mistakes; however, it is talking about abbreviations and a few false positives.
- There is an error on line 768 of gamePlay_screen.java that says the if condition will always be true. This is a false flag, it has been tested and proven that it can be false.
- Lint is saying that the espresso API is deprecated, but there isn't a way to fix that. The tests work perfectly; so, it is a warning that is fine to keep.
- Lint is saying that certain methods need to be private. This is false, they will not work if they are private.
- Lint is saying that certain buttons and variables aren’t used, when in fact they are. More false flags.
- Lint is saying that certain fields can be converted to local variables, but they cannot. This is a false flag.
- Lint is saying that the app is not compatible with Firebase and Google indexing. That is fine, because the app does not need that.
- Lint says that there is an overdraw. The background is being colored in twice. Once with the theme turning it white and once because it needs to be dark blue instead of white. To fix the overdraw error, the theme would have to be removed; however, the theme is what gives the app its structure. Removing the theme breaks the app; so, since it is a small app and this error makes no real performance issues, it will be allowed to stay in the app. It would take a whole re-design to fix it, and there is no time for that.
