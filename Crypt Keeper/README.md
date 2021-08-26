# Crypt Keeper

Current Version: v3.3.0

*NOTE: All of Crypt Keeper v3.3.0 has been declassified. Crypt Keeper v4.0.0+ is out now and the encryption algorithm is completely different; so, it is no longer a security risk to give out the source code for older versions of Crypt Keeper.*

<img src="crypt_keeper_logo.png" alt="Crypt Keeper Logo" style="width:200px; height:auto;"/>

<br>

This program is designed to encrypt and decrypt information. The user tells the program if they are encrypting or decrypting. Then, they give the program a string. If they are encrypting, the program will give them a complex ciphertext. If they are decrypting, the program will give them the original plaintext.

<br>

#### Repo Layout
- [algorithms](algorithms) - Details about how the strings are encrypted and decrypted.
- [downloads](downloads) - This is where you go to download Crypt Keeper to use on your computer.
- [src](src) - The source code for the program.
- [dev_notes](dev_notes.md) - Instructions for developers.
	- [Updating Version](dev_notes.md#updating-version) - Tells the dev how to properly roll out a new update.
	- [Error Code Manual](src/utils/errors/error_codes.md) - A table for seeing what the different error codes mean.
- [version_history](version_history.md) - A log of what was added at each update.
