# security

While this website is basic, it does have a security system. Here is a breakdown of the security features it employs.

<br>

## Forbidden Commands

These are the commands that are not allowed no matter what:

- DROP
- TRUNCATE
- DELETE (without a WHERE clause)
- ALTER
- RENAME
- GRANT
- REVOKE

These commands are forbidden because they have been deemed too dangerous. If someone manages to get past the PIN requirement, these still won't be useable to them. They will still be able to do damage if they want to, but eliminating these commands will be a deterrent.

<br>

## Restricted Commands

These are the commands that are not allowed unless the user inputs the PIN:

- CREATE
- INSERT
- UPDATE
- DELETE
- MERGE
- COMMENT
- EXECUTE
- CALL
- LOCK

These commands are restricted because random people shouldn't be manipulating the database. This keeps database edits within a trusted circle of people. Random people should only be allowed to view the database.

### PIN

The PIN is: **319508**

The reason the PIN can be shown here is because this Github repo is restricted, and only trusted people are allowed in. (Also, the site and database are no longer live)

This PIN was chosen based on the initial phrase "CS 508". C is the third letter of the alphabet; so, it starts with 3. S is the 19th; so, now it is 319. Finally, add the 508, and there is the PIN.

<br>

## Implementation

The security of this web interface was achieved by using two different methods in conjunction with one another. First, three different users were created with different permissions; there is an admin user, a user with access to the "restricted" commands, and a user that doesn't have access to the "restricted" commands. Not all of the "restricted" commands or "forbidden" commands can be blocked with the REVOKE command; so, the website itself was also used to block some commands.
