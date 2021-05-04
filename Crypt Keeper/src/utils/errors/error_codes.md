# Error Code Manual

This manual is used for debugging. Rather than give error codes in the program that could lead attackers to understand how the code works, the program gives an error code when there is a problem. This page shows each error as it would appear in the program if security was not at risk.

| code | message |
| --: | :-- |
| ND1 | ERROR: There is nothing to decrypt! |
| VNW2 | ERROR: Not a valid version! |
| NE3 | ERROR: There is nothing to encrypt! |
| TLMB4 | ERROR: Too long! Must be exactly four chars! |
| SE5 | ERROR: String cannot be null! |
| RGMSM6 | ERROR: min must be smaller than max! |
| NV7 | ERROR: This ciphertext is not a verified Crypt Keeper ciphertext! |
| WP8 | ERROR: Either the wrong password was entered or the encrypted text has had characters added/removed/changed! |
| TL9 | ERROR: The length of the ciphertext has been tampered with! |
