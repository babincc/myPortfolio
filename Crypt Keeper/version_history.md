# Version History

This document shows what what updated in each version of Crypt Keeper. The most recent version is at the top of the list.

| Version | What changed |
| :-: | :-- |
| v3.3.0 | Reformatted the output so the user will be able to read it better in the console. |
| v3.2.0 | Added password confirmation when encrypting. Now the user will have to confirm the password they use to encrypt their message. This way, they don't encrypt it with a typo and can never decrypt it. |
| v3.1.1 | Added user input error checking. |
| v3.1.0 | Added an end cap so the length of the string can not be tampered with. The end cap contains the ciphertext length. If the ciphertext does not match that length, the program will not decrypt it. |
| v3.0.0 | Added the use of a password to encrypt and decrypt. |
| v2.0.0 | Added salt so the ciphertext length will be random. |
| v1.0.2 | Made error message codes instead of messages. This way, they don't reveal any information to potential hackers about how the program works. |
| v1.0.1 | Got rid of spaces in the ciphertext. |
| v1.0.0 | First iteration of the program. Basic proof of concept for the encryption and decryption algorithms. |
