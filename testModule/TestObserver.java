package testModule;

import static org.junit.jupiter.api.Assertions.*;

import observer.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

class TestObserver {
	private LibraryManagementSysInfo library;
	private Faculty faculty;
	private Student student;
	private NonFaculty nonFaculty;
	private Textbook textbook;
	private MaintainUser maintainUser;
	
    private Visitor visitor;
    private Courses course;
    private MaintainCourses maintainCourses;
    private MaintainTextbook maintainTextbook;
      

    @BeforeEach
    void setUp() {
        // setup common objects used across multiple tests
        library = new LibraryManagementSysInfo();
        faculty = new Faculty(library);
        student = new Student(library);
        nonFaculty = new NonFaculty(library);
        visitor = new Visitor(library);
        course = new Courses("Software Design", faculty, "SD101");
        textbook = new Textbook("Introduction to Java", "ISBN12345", "1", "https://www.yorku.com");
        maintainCourses = new MaintainCourses("courses.csv");
        maintainTextbook = new MaintainTextbook("textbooks.csv");
        maintainUser = new MaintainUser("users.csv");
        
        // simulating adding to library management system
        library.attachRegisteredClient(faculty);
        library.attachRegisteredClient(student);
        library.attachRegisteredClient(nonFaculty);
    }
    
    //TESTS START HERE
    
 	// test cases for library management system info (4)
    @Test
    void testAttachAndNotifyObservers() {
        library.notifyAllObservers();
        // check if all observers have been notified
    }
    
    @Test
    void testDetachObserver() {
        library.deatachRegisteredClient(visitor);
        // check if the visitor is no longer in the observers list
    }
    
    
 	// test cases for courses (1)
    @Test
    void testAddCourse() {
        Courses newCourse = new Courses("Data Structures", faculty, "DS102");
        course.add(newCourse);
    }
    
    
 	// test cases for textbook (10)
    @Test
    void testNewEditionAvailable() {
        textbook.add(new Textbook("Introduction to Java", "ISBN12345", "2", "http://example.com/new"));
        assertTrue(textbook.isNewEditionAvailable());
    }
    
    
 	// test cases for user (11)
    @Test
    void testUserCreation() {
        User newUser = new Faculty(library);
        assertNotNull(newUser);
    }
    
    
 	// test cases for maintain(ing) courses (5)
    @Test
    void testLoadCourses() throws Exception {
        maintainCourses.load();
    }
    
    
 	// test cases for maintain(ing) textbook (6)
    @Test
    void testUpdateTextbooks() throws Exception {
        maintainTextbook.update();
        // check if the textbook csv file has been updated correctly.
    }
    
    
 	// test cases for maintain(ing) user (7)
    @Test
    void testAddUser() {
        maintainUser.addUser(new Student(library));
        assertEquals(1, maintainUser.getUsers().size());
    }
    

	
	
	////PART 1!!!!!!!!!!!
    ////first test cases
	
	@Test
	public void notifyWithZeroSubscribers() {
	    LibrarySystem library = new LibrarySystem();
	    assertDoesNotThrow(() -> library.notifyObservers("Test Notification"));
	}
	
	@Test
	public void userSubscribesSuccessfully() {
	    LibrarySystem library = new LibrarySystem();
	    User user = new User();
	    library.subscribe(user);
	    assertTrue(library.getObservers().contains(user));
	}

	@Test
	public void userUnsubscribesSuccessfully() {
	    LibrarySystem library = new LibrarySystem();
	    User user = new User();
	    library.subscribe(user);
	    library.unsubscribe(user);
	    assertFalse(library.getObservers().contains(user));
	}

	@Test
	public void allSubscribersNotifiedOnNewBook() {
	    LibrarySystem library = new LibrarySystem();
	    User user1 = new User();
	    User user2 = new User();
	    library.subscribe(user1);
	    library.subscribe(user2);
	    library.addNewBook("New Book");
	    // Assuming User class updates a lastNotification field on update
	    assertEquals("New Book added", user1.getLastNotification());
	    assertEquals("New Book added", user2.getLastNotification());
	}

	@Test
	public void noDuplicateSubscriptionsAllowed() {
	    LibrarySystem library = new LibrarySystem();
	    User user = new User();
	    library.subscribe(user);
	    library.subscribe(user); // Attempt to subscribe again
	    assertEquals(1, library.getObservers().size());
	}

	@Test
	public void notificationContentIsCorrect() {
	    LibrarySystem library = new LibrarySystem();
	    User user = new User();
	    library.subscribe(user);
	    String expectedNotification = "Library will be closed tomorrow";
	    library.notifyObservers(expectedNotification);
	    assertEquals(expectedNotification, user.getLastNotification());
	}

	@Test
	public void subscriptionAfterNotificationReceivesNoPastNotifications() {
	    LibrarySystem library = new LibrarySystem();
	    library.notifyObservers("Past Notification");
	    User newUser = new User();
	    library.subscribe(newUser);
	    assertNull(newUser.getLastNotification());
	}

	@Test
	public void userReceivesMultipleNotifications() {
	    LibrarySystem library = new LibrarySystem();
	    User user = new User();
	    library.subscribe(user);
	    library.notifyObservers("First Notification");
	    library.notifyObservers("Second Notification");
	    List<String> expected = Arrays.asList("First Notification", "Second Notification");
	    assertEquals(expected, user.getNotifications());
	}

	@Test
	public void unsubscribeWithoutSubscription() {
	    LibrarySystem library = new LibrarySystem();
	    User user = new User();
	    assertDoesNotThrow(() -> library.unsubscribe(user));
	}

	@Test
	public void notificationsSentOnSpecificEvent() {
	    LibrarySystem library = new LibrarySystem();
	    User user = new User();
	    library.subscribe(user);
	    library.updateLibraryHours("New hours"); // Should not trigger notification
	    assertNull(user.getLastNotification());
	    library.addNewBook("New Book"); // Should trigger notification
	    assertEquals("New Book added", user.getLastNotification());
	}



}
