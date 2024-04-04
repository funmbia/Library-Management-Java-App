package testModule;

import static org.junit.jupiter.api.Assertions.*;
import observer.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Singleton.registerUser;
import factory.HardcoverBook;
import factory.PhysicalItem;

import java.io.ByteArrayOutputStream;
import java.io.*;
import java.util.*;

class TestObserver {
//	private LibraryManagementSysInfo library;
//	private Faculty faculty;
//	private Student student;
//	private NonFaculty nonFaculty;
//	private Textbook textbook;
//	private MaintainUser maintainUser;
//	
//    private Visitor visitor;
//    private Courses course;
//    private MaintainCourses maintainCourses;
//    private MaintainTextbook maintainTextbook;
      

//    @BeforeEach
//    void setUp() {
//        // setup common objects used across multiple tests
//        library = new LibraryManagementSysInfo();
//        faculty = new Faculty(library);
//        student = new Student(library);
//        nonFaculty = new NonFaculty(library);
//        visitor = new Visitor(library);
//        course = new Courses("Software Design", faculty, "SD101");
//        textbook = new Textbook("Introduction to Java", "ISBN12345", "1", "https://www.yorku.com");
//        maintainCourses = new MaintainCourses("courses.csv");
//        maintainTextbook = new MaintainTextbook("textbooks.csv");
//        maintainUser = new MaintainUser("users.csv");
//        
//        // simulating adding to library management system
//        library.attachRegisteredClient(faculty);
//        library.attachRegisteredClient(student);
//        library.attachRegisteredClient(nonFaculty);
//    }
    
 /*
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

*/
	
/* Visitor.java */
	@Test
	public void testVisitorCreation() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		assertTrue(visitor instanceof Visitor);	
	}
	
	//Instance of User
	@Test
	public void testVisitorCreation1() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		assertTrue(visitor instanceof User);	
	}
	
	@Test
	public void testVisitorCreation2() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		assertTrue(visitor instanceof Visitor);	
		assertTrue(visitor instanceof User);	
	}
	
	@Test
	public void testVisitorCreation3() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		assertTrue(subject.users.contains(visitor));	
	}
	
	@Test
	public void testVisitorCreation4() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		LibraryManagementSysInfo subject1 = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		assertFalse(subject1.users.contains(visitor));	
	}
	
	@Test
	public void testVisitorCreation5() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		LibraryManagementSysInfo subject1 = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		assertFalse(subject1.users.contains(visitor));	
		assertTrue(subject.users.contains(visitor));	
	}
	
	@Test
	public void testVisitorCreation6() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		assertEquals(visitor.subject, subject);	
	}
	
	@Test
	public void testVisitorCreation7() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		LibraryManagementSysInfo subject1 = new LibraryManagementSysInfo();
		assertFalse(visitor.subject.equals(subject1));	
	}
	
	@Test
	public void testVisitorCreation8() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
	    User user = new User();
		assertFalse(user instanceof Visitor);	
		assertTrue(visitor instanceof Visitor);	
	}
	
	@Test
	public void testUpdate() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
	    visitor.update();
        assertEquals("Visitor's info is updated.\n", outContent.toString());
	}
	
	//Did not call update
	@Test
	public void testUpdate1() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor =  new Visitor(subject);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertFalse("Visitor's info is updated.\n".equals(outContent.toString()));
	}
	
/* Courses.java */
	    @Test
	    public void testCreatingCourse() {
	        Faculty faculty = new Faculty("John Doe");
	        Courses course = new Courses("Introduction to Programming", faculty, "CS101");
	        assertNotNull(course);
	    }

	    @Test
	    public void testGetCourseID() {
	        Faculty faculty = new Faculty("John Doe");
	        Courses course = new Courses("Introduction to Programming", faculty, "CS101");
	        assertEquals("CS101", course.getCourseID());
	    }

	    @Test
	    public void testGetCourseName() {
	        Faculty faculty = new Faculty("John Doe");
	        Courses course = new Courses("Introduction to Programming", faculty, "CS101");
	        assertEquals("Introduction to Programming", course.getCourseName());
	    }
	    @Test
	    public void testGetCourseNameWithEmptyName() {
	        Faculty faculty = new Faculty("John Doe");
	        Courses course = new Courses("", faculty, "CS101");
	        assertEquals("", course.getCourseName());
	    }


	    @Test
	    public void testGetFaculty() {
	        Faculty faculty = new Faculty("John Doe");
	        Courses course = new Courses("Introduction to Programming", faculty, "CS101");
	        assertEquals(faculty, course.getFaculty());
	    }

	    @Test
	    public void testAddCourseToList() {
	        // Create a faculty for the course
	        Faculty faculty = new Faculty("John Doe");

	        // Create a new course
	        Courses course = new Courses("Introduction to Computer Science", faculty, "CS101");
	        
	        // Add the course to the list of courses
	        Courses courses = new Courses();
	        courses.add(courses);
	    

	        
			// Check if the course is added successfully
	        assertNotNull(courses);
	    }
	
	    @Test
	    public void testAddMultipleCourses() {
	        Courses course1 = new Courses("Physics", new Faculty("Jane Smith"), "PHYS101");
	        Courses course2 = new Courses("Biology", new Faculty("Alice Johnson"), "BIO101");
	        Courses course3 = new Courses("Chemistry", new Faculty("Bob Brown"), "CHEM101");
	        List<Courses> coursesList =new  ArrayList<>();
	        coursesList.add(course1);
	        coursesList.add(course2);
	        coursesList.add(course3);
	        
	        assertEquals(3, coursesList.size());
	        assertTrue(coursesList.contains(course1));
	        assertTrue(coursesList.contains(course2));
	        assertTrue(coursesList.contains(course3));
	    }


	    @Test
	    public void testAddToExistingList() {
	        // Create a faculty for the course
	        Faculty faculty = new Faculty("John Doe");

	        // Create a new course
	        Courses course = new Courses("Introduction to Computer Science", faculty, "CS101");
	        
	        // Create a list of courses and add an existing course
	        List<Courses> coursesList = new ArrayList<>();
	        Courses existingCourse = new Courses("Mathematics", faculty, "MATH101");
	        coursesList.add(existingCourse);
	        
	        // Add the new course to the list of courses
	        coursesList.add(course);
	        
	        // Check if the new course is added successfully
	        assertTrue(coursesList.contains(course));
	        assertEquals(2, coursesList.size());
	    }

	    @Test
	    public void testAddToEmptyList() {
	        // Create a faculty for the course
	        Faculty faculty = new Faculty("John Doe");

	        // Create a new course
	        Courses course = new Courses("Introduction to Computer Science", faculty, "CS101");
	        
	        // Create an empty list of courses and add the new course
	        List<Courses> coursesList = new ArrayList<>();
	        coursesList.add(course);
	        
	        // Check if the new course is added successfully
	        assertTrue(coursesList.contains(course));
	        assertEquals(1, coursesList.size());
	    }
	    @Test
	    public void testAddCourseWithNullFaculty() {
	        // Create a new course with null faculty
	        Courses course = new Courses("Introduction to Computer Science", null, "CS101");
	        
	        // Add the course to the list of courses
	        Courses courses = new Courses();
	        courses.add(course);
	        
	        // Check if the course is added successfully
	        assertNotNull(courses);
	    }


/* Faculty.java */
	    @Test
	    public void testTeachCourse() {
	        Faculty faculty = new Faculty("John Doe");
	        Courses course = new Courses("Introduction to Programming", faculty, "CS101");
	        faculty.teachCourse(course);
	        assertTrue(faculty.getCoursesTeaching().contains(course));
	    }

	    @Test
	    public void testStopTeaching() {
	        Faculty faculty = new Faculty("John Doe");
	        Courses course = new Courses("Introduction to Programming", faculty, "CS101");
	        faculty.teachCourse(course);
	        faculty.stopTeaching(course);
	        assertFalse(faculty.getCoursesTeaching().contains(course));
	    }

	  

	    @Test
	    public void testSetTextbookAndGetTextbook() {
	        Faculty faculty = new Faculty("John Doe");
	        Courses course = new Courses("Introduction to Programming", faculty, "CS101");
	        Textbook textbook = new Textbook("Introduction to Programming", "978-0134675874", "1st Edition", "http://example.com");
	        faculty.setTextbook(course, textbook);
	        assertEquals(textbook, faculty.getTextbook(course));
	    }

	   

	    @Test
	    public void testCreateNotification() {
	        Faculty faculty = new Faculty("John Doe");
	        Textbook textbook = new Textbook("Introduction to Programming", "978-0134675874", "1st Edition", "http://example.com");
	        faculty.createNotification(textbook); // Just testing if it throws an exception or not
	    }

	    @Test
	    public void testNotifyOfUnavailability() {
	        Faculty faculty = new Faculty("John Doe");
	        Textbook textbook = new Textbook("Introduction to Programming", "978-0134675874", "1st Edition", "http://example.com");
	        faculty.notifyOfUnavailability(textbook); // Just testing if it throws an exception or not
	    }

	  

	    @Test
	    public void testNotifyNewTextbookEdition() {
	        Faculty faculty = new Faculty("John Doe");
	        faculty.trackCourseAndTextbook("CS101", "978-0134675874");
	        faculty.notifyNewTextbookEdition("CS101", "978-0134675874", "2nd Edition"); // Just testing if it throws an exception or not
	    }
	
	    @Test
	    public void testFacultyConstructor() {
	        LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	        Faculty faculty = new Faculty(subject);
	        
	        // Check if the subject of the faculty is set correctly
	        assertEquals(subject, faculty.subject);
	       
	    }
	    @Test
	    public void testFacultyConstructorWithUser() {
	        // Create a sample User object
	    	
	    	User user = new User(1, 10, new ArrayList<>(), null, null, "John Doe", "john@example.com", "password", "faculty", null);


	        // Create a LibraryManagementSysInfo object
	        LibraryManagementSysInfo subject = new LibraryManagementSysInfo();

	        // Create a Faculty object using the second constructor
	        Faculty faculty = new Faculty(subject, user);

	        // Check if the subject of the faculty is set correctly
	        assertEquals(subject, faculty.subject);

	     

	        // Check if the attributes are copied correctly from the user
	        assertEquals("John Doe", faculty.getName());
	        assertEquals("john@example.com", faculty.getEmail());
	        assertEquals("password", faculty.getPassword());
	        assertEquals("faculty", faculty.getAccountType());
	      
	        assertEquals(1, faculty.getItemsOverdue());
	        assertEquals(10.0, faculty.getPenalty(), 0.001);

	    

}



	    @Test
	    public void testUpdate_NotificationCreation() {
	        // Create a Faculty instance
	        Faculty faculty = new Faculty("John Doe");
	        
	        // Create some Textbook instances
	        Textbook textbook1 = new Textbook("Textbook 1", "ISBN1", "1st Edition", "URL1");
	        Textbook textbook2 = new Textbook("Textbook 2", "ISBN2", "2nd Edition", "URL2");
	        Textbook textbook3 = new Textbook("Textbook 3", "ISBN3", "3rd Edition", "URL3");
	        Textbook textbook4 = new Textbook("Textbook 4", "ISBN4", "4th Edition", "URL4");
	        
	        // Add textbooks to the faculty's usedTextbooks list
	        List<Textbook> usedTextbooks = new ArrayList<>();
	        usedTextbooks.add(textbook1);
	        usedTextbooks.add(textbook2);
	        usedTextbooks.add(textbook3);
	        faculty.setUsedTextbooks(usedTextbooks);
	        
	        // Mark one textbook as not available
	       
	        
	        // Call the update method
	        faculty.update();
	        
	        // Check if notifications are created for textbooks with new editions
	        assertTrue(textbook1.isNewEditionAvailable());
	        assertTrue(textbook2.isNewEditionAvailable());
	        assertTrue(textbook3.isNewEditionAvailable());
	        
	        // Check if notifications are created for textbooks that are not available
	        assertTrue(textbook1.isAvailable());
	        assertTrue(textbook2.isAvailable());
	      
	    }
	 
	
	        @Test
	        public void testGetTextbooks() {
	            // Create a Faculty instance
	            Faculty faculty = new Faculty("John Doe");

	            // Create some Textbook instances
	            Textbook textbook1 = new Textbook("Textbook 1", "ISBN1", "1st Edition", "URL1");
	            Textbook textbook2 = new Textbook("Textbook 2", "ISBN2", "2nd Edition", "URL2");
	            Textbook textbook3 = new Textbook("Textbook 3", "ISBN3", "3rd Edition", "URL3");

	            // Set courses for the textbooks
	            Courses course1 = new Courses("Course A", faculty, "CS101");
	            Courses course2 = new Courses("Course B", faculty, "CS102");
	            textbook1.setCourse(course1);
	            textbook2.setCourse(course2);
	            textbook3.setCourse(course1); // Associate this textbook with the same course as textbook1

	            // Add textbooks to the faculty's usedTextbooks list
	            List<Textbook> usedTextbooks = new ArrayList<>();
	            usedTextbooks.add(textbook1);
	            usedTextbooks.add(textbook2);
	            usedTextbooks.add(textbook3);
	            faculty.setUsedTextbooks(usedTextbooks);

	            // Retrieve textbooks for a specific course
	            List<Textbook> textbooksForCourse = faculty.getTextbooks(course1);

	            // Check if the correct textbooks are retrieved for the course
	            assertEquals(2, textbooksForCourse.size());
	            assertTrue(textbooksForCourse.contains(textbook1));
	            assertFalse(textbooksForCourse.contains(textbook2)); // This textbook is associated with a different course
	            assertTrue(textbooksForCourse.contains(textbook3));
	        }

	        
/* NonFaculty */
	        @Test
	    	public void TestNonfacultyCreation() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		NonFaculty nf =  new NonFaculty(subject);	
	    		assertTrue(nf instanceof User);	
	    	}
	        
	        @Test
	    	public void TestNonfacultyCreation2() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		NonFaculty nf =  new NonFaculty(subject);
	    		assertTrue(nf instanceof NonFaculty);	
	    	}
	        
	        @Test
	    	public void TestNonfacultyCreation3() {
	    		NonFaculty nf =  new NonFaculty();
	    		assertTrue(nf instanceof User);	
	    	}
	        
	        @Test
	    	public void TestNonfacultyCreation4() {
	    		NonFaculty nf =  new NonFaculty();
	    		assertTrue(nf instanceof NonFaculty);	
	    	}
	        
	        @Test
	    	public void TestNonfacultySubject() { 
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		NonFaculty nf =  new NonFaculty(subject);
	    		assertTrue(nf.subject.equals(subject));	
	    	}
	    	
	    	@Test
	    	public void TestNonfacultySubject2() { 
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		NonFaculty nf =  new NonFaculty(subject);
	    		assertTrue(subject.users.contains(nf));	
	    	}
	    	
	    	@Test
	    	public void TestNonfacultyAttachment() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		NonFaculty nf =  new NonFaculty();
	    		subject.attachRegisteredClient(nf);
	    		assertTrue(subject.users.contains(nf));	
	    	}
	    	
	    	@Test
	    	public void TestNonfacultyDetachment() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		NonFaculty nf =  new NonFaculty(subject);
	    		subject.deatachRegisteredClient(nf);
	    		assertFalse(subject.users.contains(nf));	
	    	}
	    	
	    	@Test
	    	public void TestNonfacultyUpdate() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		NonFaculty nf =  new NonFaculty(subject);
	    	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	            System.setOut(new PrintStream(outContent));
	            subject.notifyAllObservers();
	            assertEquals("Nonfaculty user has been updated.\n", outContent.toString());
	    	}
	    	
	    	@Test
	    	public void TestNonfacultyEmptyUpdate() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		NonFaculty nf =  new NonFaculty();
	    	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	            System.setOut(new PrintStream(outContent));
	            subject.notifyAllObservers();
	            assertEquals("", outContent.toString());
	    	} 
	    	
	        
/* Student */
	    	@Test
	    	void testStudentConstructor() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		assertTrue(student instanceof User);
	    		assertTrue(student instanceof Student);
	    	}
	    	
	    	@Test
	    	void testStudentConstructor2() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		assertTrue(subject.users.contains(student));	
	    		assertTrue(student.subject.equals(subject));
	    	}
	    	
	    	@Test
	    	void testStudentConstructor3() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		User user = new User(0, 0, null, null, null, "Jane Doe", "janedoe@yorku.ca", "Jane@1234", "student", null);
	    		Student student = new Student(subject, user);
	    		assertTrue(subject.users.contains(student));
	    		assertEquals(student.getName(), "Jane Doe");
	    		assertEquals(student.getEmail(), "janedoe@yorku.ca");
	    		assertEquals(student.getPassword(), "Jane@1234");
	    		assertEquals(student.getAccountType(), "student");
	    	}
	    	
	    	@Test
	    	void testStudentEnroll() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		Courses course = new Courses();
	    		student.enroll(course);
	    		assertTrue(student.viewEnrolledCourses().contains(course));
	    	}
	    	
	    	@Test
	    	void testStudentWithdraw() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		Courses course = new Courses();
	    		student.enroll(course);
	    		assertTrue(student.viewEnrolledCourses().contains(course));
	    		student.withdraw(course);
	    		assertFalse(student.viewEnrolledCourses().contains(course));
	    	}
	    	
	    	@Test
	    	void testStudentGetTextbook() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		assertTrue(subject.users.contains(student));	
	    		assertTrue(student.subject.equals(subject));
	    	}
	    	
	    	@Test
	    	void testStudentVirtualCopies() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		Courses course = new Courses();
	    		Textbook textbook = new Textbook();
	    		
	    		student.createVirtualCopies(course, textbook);
	    		assertTrue(student.getTextbook(course).equals(textbook));
	    		student.removeExtraCopies(course);
	    		assertTrue(student.virtualTextbooks.isEmpty());
	    		
	    		student.enroll(course);
	    		student.createVirtualCopies(course, textbook);
	    		assertFalse(student.virtualTextbooks.isEmpty());
	    		student.withdraw(course);
	    		assertTrue(student.virtualTextbooks.isEmpty());
	    	}
	    	
	    	@Test
	    	void testStudentBorrowPhysicalItem() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		HardcoverBook book = new HardcoverBook();
	    		book.setCopiesAvail(2);
	    		book.setRentalStatus(true);
	    		
	    		Calendar calendar = Calendar.getInstance();
	    		calendar.add(Calendar.DATE, 7);
	    		Date nextWeek = calendar.getTime();
	    		book.dueDate = nextWeek; //canborrowitem = overdue < max ; not overdue
	    		
	    		assertTrue(  student.borrowPhysicalItem(book)  );
	    		assertTrue(student.getBorrowedHardcoverBooks().contains(book));
	    		assertFalse(book.getRentable());
	    		assertTrue(book.getCopiesAvail() == 1);
	    		
	    		student.borrowPhysicalItem(book); 
	    		assertFalse(  student.borrowPhysicalItem(book)  ); //now 0 copies
	    	}
	    	
	    	@Test
	    	void testStudentReturnPhysicalItem() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		HardcoverBook book = new HardcoverBook();
	    		book.setCopiesAvail(1);
	    		book.setRentalStatus(true);
	    		Calendar calendar = Calendar.getInstance();
	    		calendar.add(Calendar.DATE, 7);
	    		Date nextWeek = calendar.getTime();
	    		book.dueDate = nextWeek;
	    		
	    		student.borrowPhysicalItem(book);
	    		student.returnPhysicalItem(book);
	    		
	    		assertFalse(student.getBorrowedHardcoverBooks().contains(book));
	    		assertEquals(1, book.getCopiesAvail());
	    		assertFalse(book.getRentable());
	    		
	    	}
	    	
	    	@Test
	    	void testStudentUpdate() {
	    		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
	    		Student student = new Student(subject);
	    		
	    		//no borrowed items
	    		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	            System.setOut(new PrintStream(outContent));
	            subject.notifyAllObservers();
	            assertEquals("", outContent.toString());
	            
	            HardcoverBook book = new HardcoverBook();
	            book.setTitle("Make It Work");
	    		book.setCopiesAvail(1);
	    		book.setRentalStatus(true);
	    		book.dueDate = new Date();
	    		student.borrowPhysicalItem(book);
	    		outContent = new ByteArrayOutputStream();
	            System.setOut(new PrintStream(outContent));
	            subject.notifyAllObservers();
	            assertEquals("Warning: Item Make It Work is overdue!\n", outContent.toString());
	    	}	
	        
/* Textbook */
	    	@Test
	        public void testGetEdition() {
	            Textbook textbook = new Textbook("", "", "5th Edition", "");
	            assertEquals("5th Edition", textbook.getEdition());
	        }

	        @Test
	        public void testGetTextName() {
	            Textbook textbook = new Textbook("Math Textbook", "", "", "");
	            assertEquals("Math Textbook", textbook.getTextName());
	        }

	        @Test
	        public void testGetISBN() {
	            Textbook textbook = new Textbook("", "978-0135166308", "", "");
	            assertEquals("978-0135166308", textbook.getISBN());
	        }
	        
	        @Test
	        public void testGetDueDate() {
	            Textbook textbook = new Textbook();
	            textbook.dueDate = new Date();
	            Date today = new Date();
	            assertEquals(today, textbook.getDueDate());
	        }
	        

	        @Test
	        public void testIsNewEditionAvailable() {
	            Textbook textbook1 = new Textbook("", "", "4th Edition", "");
	            Textbook textbook2 = new Textbook("", "", "5th Edition", "");
	            textbook1.add(textbook2);
	            assertTrue(textbook1.isNewEditionAvailable());
	        }

	        @Test
	        public void testIsAvailable() {
	            Textbook textbook = new Textbook();
	            assertTrue(textbook.isAvailable());
	        }

	        @Test
	        public void testGetCourse() {
	            Textbook textbook = new Textbook();
	            Courses course = new Courses();
	            textbook.setCourse(course);
	            assertEquals(course, textbook.getCourse());
	        }

	        @Test
	        public void testIsOverdue() {
	            Textbook textbook = new Textbook();
	            Date dueDate = new Date(System.currentTimeMillis() - 86400000); // 1 day ago
	            textbook.dueDate = dueDate;
	            assertTrue(textbook.isOverdue());
	            
	            textbook.dueDate = null;
	            assertFalse(textbook.isOverdue());
	        }

	        @Test
	        public void testGetURL() {
	            Textbook textbook = new Textbook("", "", "", "http://example.com");
	            assertEquals("http://example.com", textbook.getURL());
	        }
	
}
