package testModule;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Singleton.*;
import command.Invoker;
import observer.*;
import factory.*;


public class TestSingleton{
	
	//registerUser.java
	@Test
	public void testRegisterUser() {
		registerUser user =  new registerUser("example", "example@gmail.com","Example#12","student");
		assertTrue(user instanceof registerUser);
		
	}
	
	@Test 
	public void testGetEmail() {
		registerUser user =  new registerUser("david", "david@gmail.com","David#12","student");
		assertEquals("david@gmail.com", user.getEmail());
	}
	
	@Test 
	public void testIsValidEmail() {
		registerUser user =  new registerUser("david", "david@gmail.com","David#12","student");		
		assertTrue(user.isValidEmail(user.getEmail()));
	}
	
	//Test email with null (should assert false)
	@Test 
	public void testIsValidEmail2() {
		registerUser user =  new registerUser("david", null,"David#12","student");		
		assertFalse(user.isValidEmail(user.getEmail()));
	}
	
	//Test email without empty String "" (should assert false)
	@Test 
	public void testIsValidEmail3() {
		registerUser user =  new registerUser("david", "","David#12","student");		
		assertFalse(user.isValidEmail(user.getEmail()));
	}
	
	//Test email without "@" (should assert false)
	@Test 
	public void testIsValidEmail4() {
		registerUser user =  new registerUser("david", "davidgmail.com","David#12","student");		
		assertFalse(user.isValidEmail(user.getEmail()));
	}
	
	@Test
	public void testisValidPassword() {
		registerUser user =  new registerUser("david", "davidgmail.com","David#12","student");		
		assertTrue(user.isValidPassword(user.getPassword()));
	}	
	
	@Test
	public void testisValidPassword2() {
		registerUser user =  new registerUser("david", "davidgmail.com","david#12","student");		
		assertFalse(user.isValidPassword(user.getPassword()));
	}
	
	@Test
	public void testisValidPassword3() {
		registerUser user =  new registerUser("david", "davidgmail.com","David12","student");		
		assertFalse(user.isValidPassword(user.getPassword()));
	}
	
	@Test
	public void testisValidPassword4() {
		registerUser user =  new registerUser("david", "davidgmail.com","David#","student");		
		assertFalse(user.isValidPassword(user.getPassword()));
	}
	
	@Test
	public void testisValidPassword5() {
		registerUser user =  new registerUser("david", "davidgmail.com","DAVID#12","student");		
		assertFalse(user.isValidPassword(user.getPassword()));
	}
	
	@Test
	public void testisValidPassword6() {
		registerUser user =  new registerUser("david", "davidgmail.com","","student");		
		assertFalse(user.isValidPassword(user.getPassword()));
	}
	
	@Test
	public void testisValidPassword7() {
		registerUser user =  new registerUser("david", "davidgmail.com",null,"student");		
		assertFalse(user.isValidPassword(user.getPassword()));
	}
	
	@Test
	public void testisValidPassword8() {
		registerUser user =  new registerUser("david", "davidgmail.com","Dav#1","student");		
		assertFalse(user.isValidPassword(user.getPassword()));
	}
	
	@Test
	public void testAddAccount() throws Exception{		
		registerUser rUser =  new registerUser("david", "david@gmail.com","David#12","student");
		User user = new User(); 
		user.setDatabaseAttributes("david", "david@gmail.com","David#12","student",0,0,0);		
		assertTrue(rUser.addAccount(user).getUsers().contains(user));
	}
	
	
	@Test
	public void testCreateAccount() throws Exception {
		registerUser user =  new registerUser("david", "david@gmail.com","David#12","student");		
		assertTrue(user.createAccount());
	}
	
	
	@Test
	public void testGetAccountCreationErrorReason() {
		registerUser user =  new registerUser("david", "david@gmail.com","David#12","student");	
		assertNull(user.getAccountCreationErrorReason());
	}
	
	@Test
	public void testGetAccountCreationErrorReason1() throws Exception {
		registerUser user =  new registerUser("david", "david@gmail.com","David#","student");	
		assertFalse(user.createAccount());
		assertEquals("Your Password is not strong enough. Add a combination of uppercase letters, lowercase letters, numbers, and symbols.", user.getAccountCreationErrorReason());
	}
	
	@Test
	public void testGetAccountCreationErrorReason2() throws Exception {
		registerUser user =  new registerUser("david", "davidgmailcom","David#12","student");	
		assertFalse(user.createAccount());
		assertEquals("Not a valid email format. Try Again", user.getAccountCreationErrorReason());
	}
	
	//should be an error due to duplicate creation of an account
	@Test
	public void testGetAccountCreationErrorReason3() throws Exception {
		registerUser user =  new registerUser("david", "david@gmail.com","David#12","student");	
		assertFalse(user.createAccount());
		assertEquals("Validation as York Member failed. Try Again.", user.getAccountCreationErrorReason());
	}
	
	
	
	@Test
	public void testvalidateNonVisitor() {
		registerUser user =  new registerUser("alex", "alex@gmail.com","Alex@12","student");	
		assertTrue(user.validateNonVisitor());
		registerUser user2 =  new registerUser("alex", "alex@gmail.com","Alex@12","student");
		assertFalse(user.validateNonVisitor());
	}
	
	//yorkMembers.java
	
	@Test
	public void testValidate() {
		User user = new User(); 
		user.setDatabaseAttributes("anne", "anne@gmail.com","Anne#12","student",0,0,0);		
		yorkMembers.members.add(user);
		assertTrue(yorkMembers.validate(user));
		
	}
	
	@Test
	public void testValidate1() {
		User user = new User(); 
		user.setDatabaseAttributes("arnold", "arnold@gmail.com","Arnold#134","student",0,0,0);		
		yorkMembers.members.add(user);
		assertTrue(yorkMembers.validate(user));
	}
	
	//should assert false due to the duplication of a user
	@Test
	public void testValidate2() {
		User user = new User(); 
		user.setDatabaseAttributes("anne", "anne@gmail.com","Anne#12","student",0,0,0);		
		yorkMembers.members.add(user);
		assertFalse(yorkMembers.validate(user));
		
	}
	@Test
	public void testValidate3() {
		User user = new User(); 
		user.setDatabaseAttributes("dan", "dan@gmail.com","Dan#1234","student",0,0,0);		
		yorkMembers.members.add(user);
		assertTrue(yorkMembers.validate(user));
		User user1 = new User(); 
		user1.setDatabaseAttributes("dan", "dan@gmail.com","Dan#1234","student",0,0,0);	
		assertFalse(yorkMembers.validate(user));
	}
	
	@Test
	public void testValidate4() {
		User user = new User(); 
		user.setDatabaseAttributes("ex", "ex@gmail.com","Ex#12345","student",0,0,0);		
		yorkMembers.members.add(user);
		yorkMembers.validate(user);
		assertTrue(yorkMembers.emails.contains(user.getEmail()));
			
	}
	
	@Test
	public void testGetMember() {
		User user = new User(); 
		user.setDatabaseAttributes("ant", "ant@gmail.com","Ant#12","student",0,0,0);	
		yorkMembers.addUserToYorkMembers(user);
		assertEquals(user,yorkMembers.getMember(user));
		
	}
	@Test
	public void testGetMember1() {
		User user = new User(); 
		user.setDatabaseAttributes("example", "example@gmail.com","Example#1","student",0,0,0);		
		yorkMembers.validate(user);
		assertEquals(user,yorkMembers.getMember(user));

	}
	
	@Test
	public void testGetMember2() {
		User user = new User(); 
		user.setDatabaseAttributes("example", "example@gmail.com","Example#1","student",0,0,0);		
		assertEquals(null,yorkMembers.getMember(user));

	}
	
	@Test
	public void testAddUserToYorkMembers() {
		User user = new User(); 
		user.setDatabaseAttributes("eman", "eman@gmail.com","Eman#12","student",0,0,0);
		yorkMembers.addUserToYorkMembers(user);
		assertEquals(user,yorkMembers.getMember(user));
		
	}
	
	@Test
	public void testAddUserToYorkMembers1() {
		User user = new User(); 
		User user1 = new User();
		yorkMembers.addUserToYorkMembers(user1);
		assertEquals(null,yorkMembers.getMember(user));
		
	}
	
	//SystemManagement.java
	
	@Test
	public void testSystemInstance() {
		SystemManagement Sm = SystemManagement.getSystemInstance();
		assertTrue(Sm instanceof SystemManagement);
		
	}
	
	@Test
	public void testGetSystemInstance() {
		SystemManagement Sm = SystemManagement.getSystemInstance();
        assertTrue(Sm != null);
		
	}
    
    @Test
	public void testGetSystemInstance1() {
		SystemManagement Sm = SystemManagement.getSystemInstance();
        assertTrue(Sm != null);
        SystemManagement Sm2 = SystemManagement.getSystemInstance();
        assertEquals(Sm, Sm2);
		
	}
	
	@Test
	public void testSMCreateAccount() {
		User user = new User(); 
		user.setDatabaseAttributes("eman", "eman@gmail.com","Eman#12","student",0,0,0);
		SystemManagement Sm = SystemManagement.getSystemInstance();
		Sm.createAccount(user);		
		assertEquals(user,Sm.getAccount(user.getEmail()));
		
	}
	
	@Test
	public void testLoginUser() throws Exception{
		User user = new User(0, 0, new ArrayList<>(), null, null, "eman", "eman@gmail.com","Eman#12","student", new Invoker());
		SystemManagement Sm = SystemManagement.getSystemInstance();
		Sm.createAccount(user);	
		
		MaintainUser m = new MaintainUser("Library-Management-Java-App-main/csv files/userInfo.csv");
		m.addUser(user);
		m.update();
		assertTrue(SystemManagement.SystemMembers.contains(user));
		
		User given = Sm.loginUser(user.getEmail(), user.getPassword());
		assertEquals(user.getName(), given.getName());
		assertEquals(user.getEmail(), given.getEmail());
		assertEquals(user.getPassword(), given.getPassword());
		assertEquals(user.getAccountType(), given.getAccountType());
	}
	
	@Test
	public void testAdditem() {
		SystemManagement Sm = SystemManagement.getSystemInstance();
		LibraryItem item = new LibraryItem();
		Sm.additem(item);
		assertTrue(Sm.itemList.contains(item));	
	}
	
	//Should assert false; did not add item
	@Test
	public void testAdditem1() {
		SystemManagement Sm = SystemManagement.getSystemInstance();
		LibraryItem newItem = new LibraryItem();
		assertFalse(Sm.itemList.contains(newItem));	
	}
	
	@Test
	public void testEnableItem() {
		SystemManagement Sm = SystemManagement.getSystemInstance();
		PhysicalItem item = new PhysicalItem();
		Sm.enableItem(item);
		assertTrue(item.getRentable());	
	}
	
	@Test
	public void testDisableItem() {
		SystemManagement Sm = SystemManagement.getSystemInstance();
		PhysicalItem item = new PhysicalItem();
		Sm.disableItem(item);
		assertFalse(item.getRentable());
		
	}
	@Test
	public void testEnableAndDisableItem() {
		SystemManagement Sm = SystemManagement.getSystemInstance();
		PhysicalItem item = new PhysicalItem();
		Sm.enableItem(item);
		assertTrue(item.getRentable());	
		Sm.disableItem(item);
		assertFalse(item.getRentable());
		Sm.enableItem(item);
		assertTrue(item.getRentable());	
	}
	

	
	
	
	
	
	
}
