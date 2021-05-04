# Notes for Developers

Here is a detailed list of instructions for developers.

## Updating Version

When creating a new version, here is what needs to be changed in the code:

- The "@version" number in the Javadocs on the classes being updated.
- The "Current version" number in the README file in the root directory
- The "Version History" list in the README file in the algorithms folder. Either extend the range of the topmost item in the list and update the corresponding file's header; or, add a new item to the list and a new file to go with it.
- In the Decrypter.java file:
  - Change the if statements for what version is being used in the decrypt() method.
  - Either change the name of the decrypt_v#\_#\_#\_to_v#\_#\_#() method and update it in the if statements from the previous bullet point; or, add a new method for the current version of encryption, "deprecate" the old method, and add the old version number into the range of the DEPRICATION_WARNING if statement.
  - Comment, beside the if tree in the decrypt() method, where the new version number is covered.
- Change the numbers in the the Version() constructor in the Version.java class (and the "current version" in the Javadocs of that method).
- Add the new version information to the version history log.

## Error Code Manual

[This manual](src/utils/errors/error_codes.md) will tell you what the different error codes mean.
