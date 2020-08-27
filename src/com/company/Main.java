package com.company;

public class Main {

    public static void main(String[] args) {
	    User testuser = new User("testUser", "testPassword");
	    DatabaseUtility databaseUtility = new DatabaseUtility();
	    databaseUtility.viewTable();
	    System.out.println("-----------");
	    databaseUtility.addUser(testuser.getUsername(), testuser.getPassword());
		databaseUtility.viewTable();
	    databaseUtility.deleteUser("testUser1");

    }
}
