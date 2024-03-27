package testModule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Singleton.*;


public class TestSingleton{
	
	@Test 
	public void testGetEmail() {
		registerUser user =  new registerUser("david", "david@gmail.com","david#123","student");
		assertEquals("david@gmail.com", user.getEmail());
	}
	
	@Test 
	public void testIsValidEmail() {
		registerUser user =  new registerUser("david", "david@gmail.com","david#123","student");		
		assertTrue(user.isValidEmail(user.getEmail()));
	}
	
	//Test email without "@" (should assert false)
	@Test 
	public void testIsValidEmail1() {
		registerUser user =  new registerUser("david", "davidgmail.com","david#123","student");		
		assertFalse(user.isValidEmail(user.getEmail()));
	}
	
	@Test
	public void testisValidPassword() {
	
	}	
	
}