-- create the web user that has no special privileges 
CREATE USER 'restrictedUser'@'%' IDENTIFIED BY 'viewer508';

-- create the web user that has a PIN
CREATE USER 'authUser'@'%' IDENTIFIED BY 'editor508';


-- grant privileges
GRANT ALL PRIVILEGES ON DisasterDB . * TO 'restrictedUser'@'%';
GRANT ALL PRIVILEGES ON DisasterDB . * TO 'authUser'@'%';


-- revoke privileges based on security clearance
REVOKE DROP, DELETE, ALTER, REFERENCES, INSERT, CREATE, UPDATE, LOCK TABLES ON DisasterDB . * FROM 'restrictedUser'@'%';

REVOKE DROP, ALTER, REFERENCES ON DisasterDB . * FROM 'authUser'@'%';
