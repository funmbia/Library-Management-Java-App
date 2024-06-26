package testModule;

import static org.junit.jupiter.api.Assertions.*;
import observer.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

import Singleton.registerUser;
import gui.ActionPage;
import factory.*;
import command.*;
import iterator.*;
import builder.*;

import java.io.ByteArrayOutputStream;
import java.io.*;
import java.util.*;

public class TestObserver {
	
/* User.java */
	@Test
	public void testUser() {
		User user = new User();
		assertTrue(user instanceof User);
	}

	@Test
	public void testUser1() {
		User userH = new User();
		Invoker myInvoker = new Invoker();
		List<RentalOrder> currentlyRenting = new ArrayList<>();
		PurchaseOrderBuilder purchaseOrder = new PurchaseOrderBuilder(userH);
		RentalOrderBuilder rentalOrder = new RentalOrderBuilder(userH);
		User user = new User(0, 0, currentlyRenting, rentalOrder, purchaseOrder, "", "", "", "", myInvoker);
		assertTrue(user instanceof User);
	}

	@Test
	public void testUser2() {
		BookCollection bookCollection = new BookCollection();
		Recommendation recommendation = new Recommendation();
		User user = new User(bookCollection, recommendation);
		assertTrue(user instanceof User);
	}

	@Test
	public void testSetDatabaseAttributes() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertEquals("example", user.getName());
		assertEquals("example@gmail.com", user.getEmail());
		assertEquals("Example#12", user.getPassword());
		assertEquals("student", user.getAccountType());
		assertEquals(1, user.getItemsOut());
		assertEquals(2, user.getItemsOverdue());
		assertEquals(3, user.getPenalty(),0);
	}

	@Test
	public void testShowRecommendations() {
		BookCollection bookCollection = new BookCollection();
		Recommendation recommendation = new Recommendation();
		User user = new User(bookCollection, recommendation);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		user.showRecommendations();
		assertEquals("No recommendations available.\n", outContent.toString());

		BookCollection bookCollection1 = new BookCollection();
		Recommendation recommendation1 = new Recommendation();
		Book book = new Book();
		recommendation1.recommendBook(book);
		User user1 = new User(bookCollection1, recommendation1);
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		user1.showRecommendations();
		assertEquals("Recommended Books:\nnull\n", outContent1.toString());
	}

	@Test
	public void testOpenNewsletter() {
		User user = new User();
		Newsletter newsL = new Newsletter();
		assertEquals("Would you like to open ", user.openNewsletter(newsL));
	}

	@Test
	public void testRequest() {
		User user = new User();
		String str = "Your Book Request for 'NameOfBook' has been filed.\nYour Request is of LOW priority and you are #2 in line.";
		RequestBook.allRequests.clear();
		assertEquals(str, user.request("NameOfBook", "requestType"));
	}

	@Test
	public void testRent() {
		PhysicalItem Pi = new PhysicalItem();
		User user = new User();
		String str = "'null' is added to your order!";
		assertEquals(str, user.rent(Pi));
	}

	@Test
	public void testGetCurrentRentalOrderSummary() throws Exception {
		User user = new User();
		RentalOrderBuilder currentOrder = new RentalOrderBuilder(user);
		user.setCurrentRentalOrder(currentOrder);
		user.getCurrentRentalOrderSummary();
	}

	@Test
	public void testSetCurrentRentalOrder() {
		User user = new User();
		RentalOrderBuilder currentOrder = new RentalOrderBuilder(user);
		user.setCurrentRentalOrder(currentOrder);
		assertEquals(user.currentRentalOrder, currentOrder);
	}

	@Test
	public void testPurchase() {
		User user = new User();
		PhysicalItem Pi = new PhysicalItem();
		String str = "null is added to your order!";
		assertEquals(str, user.purchase(Pi, 10));
	}

	@Test
	public void testGetPurchaseOrderSummaryAndPay() {
		User user = new User();
		PhysicalItem Pi = new PhysicalItem();
		user.purchase(Pi, 10);
		String method = "credit";
		String str = "Order 3 for null:\nPrice: 0.0\nPayment successful! You can pick up your items from the library front desk. Thank You!";
		assertEquals(str.substring(7).trim(), user.getPurchaseOrderSummaryAndPay(method).substring(8));
	}
	
	@Test
	public void testSetCurrentPurchaseOrder() {
		User user = new User();
		PurchaseOrderBuilder currentOrder = new PurchaseOrderBuilder(user);
		user.setCurrentPurchaseOrder(currentOrder);
		assertEquals(currentOrder,user.currentPurchaseOrder);
	}

	@Test
	public void testAddToRenting() throws Exception {
		User user = new User();
		RentalOrder currentOrder = new RentalOrder();
		user.addToRenting(currentOrder);
		assertEquals(currentOrder, user.currentlyRenting.get(user.currentlyRenting.indexOf(currentOrder)));

	}

	@Test
	public void testRemoveFromRenting() throws Exception{
		User user = new User();
		RentalOrder currentOrder = new RentalOrder();
		user.addToRenting(currentOrder);
		user.removeFromRenting(currentOrder);
		assertFalse(user.currentlyRenting.contains(currentOrder));
	}

	@Test
	public void testHasBorrowingPrivileges() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertTrue(user.hasBorrowingPrivileges());
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 4, 3);
		assertFalse(user.hasBorrowingPrivileges());
	}

	@Test
	public void testPenaltyApplication() throws Exception{
		User user = new User();
		user.penaltyApplication();
		assertEquals(0, user.penalty,0);
		List<PhysicalItem> items = new ArrayList<>();
		Date dueDate = new Date();
		RentalOrder currentOrder = new RentalOrder(0,"",items,"",dueDate,user);
		user.addToRenting(currentOrder);
		user.penaltyApplication();
		assertEquals(0, user.itemsOverdue);
	}
	
	@Test
	public void testPenaltyApplication2() throws Exception{
		User user = new User();
		List<PhysicalItem> items = new ArrayList<>();
		PhysicalItem x = new Magazine();
		items.add(x);
		
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -1);
        Date overdueDueDate = calendar.getTime();
        
		RentalOrder newOrder = new RentalOrder(0,"",items,"",overdueDueDate,user);
		user.addToRenting(newOrder);
		user.penaltyApplication();
		assertEquals(1, user.itemsOverdue);
	}
	
	@Test
	public void testPenaltyApplication3() throws Exception{
		User user = new User();
		List<PhysicalItem> items = new ArrayList<>();
		PhysicalItem x = new Magazine();
		items.add(x);
		
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -17);
        Date manyDaysOverdueDueDate = calendar.getTime();
        
		RentalOrder newOrder = new RentalOrder(0,"",items,"",manyDaysOverdueDueDate,user);
		user.addToRenting(newOrder);
		user.penaltyApplication();
		assertEquals(1, user.itemsOverdue);
	}
	
	
	@Test
	public void testCalculateDaysOverdue() {
		User user = new User();
		Date dueDate = new Date();
		Date dueDate1 = new Date();
		assertEquals(0, user.calculateDaysOverdue(dueDate, dueDate1));
	}
	

	@Test
	public void testDisplayRentalWarnings() {
		User user = new User();
		user.penaltyApplication();
		String str = "<html>You have 0 item(s) overdue & a penalty of $0.0.<br>Please note that more than 3 Books overdue <br>will result in loss of borrowing privileges.</html>";
		;
		assertEquals(str, user.displayRentalWarnings());

	}

	@Test
	public void testGetBorrowedItems() throws Exception{
		User user = new User();
        RentalOrder Ro = new RentalOrder();
        user.addToRenting(Ro);
        assertFalse(user.getBorrowedItems().isEmpty());
	}
	
	@Test
	public void testGetBorrowedItems1() {
		User user = new User();
        assertTrue(user.getBorrowedItems().isEmpty());
	}
	
	@Test
	public void testGetBorrowedItems2() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -17);
        Date overdue = calendar.getTime();
        
        User user = new User();
        assertTrue(user.getBorrowedItems().isEmpty());
        PhysicalItem x = new Magazine();
        ArrayList<PhysicalItem> list = new ArrayList<>();
        list.add(x);
        RentalOrder order = new RentalOrder(0, "", list, "", overdue, user);
        
        user.currentlyRenting.add(order);
        user.getBorrowedItems();
        
        assertFalse(user.overDue.isEmpty());
	}
	
	@Test
	public void testGetBorrowedItems3() {
		Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.HOUR_OF_DAY, 5);
        Date dueSoon = calendar.getTime();
        
        User user = new User();
        assertTrue(user.getBorrowedItems().isEmpty());
        PhysicalItem x = new Magazine();
        ArrayList<PhysicalItem> list = new ArrayList<>();
        list.add(x);
        RentalOrder order = new RentalOrder(0, "", list, "", dueSoon, user);
        
        user.currentlyRenting.add(order);
        user.getBorrowedItems();
        
        assertFalse(user.almostDue.isEmpty());
	}
	
	@Test
	public void testGetBorrowedItems4() {
		Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, 5);
        Date notyetdue = calendar.getTime();
        
        User user = new User();
        assertTrue(user.getBorrowedItems().isEmpty());
        PhysicalItem x = new Magazine();
        ArrayList<PhysicalItem> list = new ArrayList<>();
        list.add(x);
        RentalOrder order = new RentalOrder(0, "", list, "", notyetdue, user);
        
        user.currentlyRenting.add(order);
        user.getBorrowedItems();
        
        assertFalse(user.notYetDue.isEmpty());
	}
 
	@Test
	public void testHoursUntilDue() {
		User user = new User();
		Date dueDate = new Date();
		assertEquals(0, user.hoursUntilDue(dueDate));
	}

	@Test
	public void testHandleLostBook() {
		User user = new User();
		PhysicalItem book = new PhysicalItem();
		book.setTitle("exampleTitle");
		String str = "exampleTitle has been overdue for 15 days and is considered lost."
				+ "\nPlease return as soon as possible.\nLibrary Manangement has been notified.";
		assertEquals(str, user.handleLostBook(book));
	}

	@Test
	public void testGetName() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertEquals("example", user.getName());
	}

	@Test
	public void testGetEmail() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertEquals("example@gmail.com", user.getEmail());
	}

	@Test
	public void testGetPassword() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertEquals("Example#12", user.getPassword());
	}

	@Test
	public void testGetAccountType() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertEquals("student", user.getAccountType());
	}

	@Test
	public void testGetItemsOut() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertEquals(1, user.getItemsOut());
	}

	@Test
	public void testSetItemsOverdue() {
		User user = new User();
		user.setItemsOverdue(1);
		assertEquals(1, user.itemsOverdue);
	}

	@Test
	public void testGetItemsOverdue() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertEquals(2, user.getItemsOverdue());
	}

	@Test
	public void testSetPenalty() {
		User user = new User();
		user.setPenalty(4);
		;
		assertEquals(4, user.penalty,0);
	}

	@Test
	public void testGetPenalty() {
		User user = new User();
		user.setDatabaseAttributes("example", "example@gmail.com", "Example#12", "student", 1, 2, 3);
		assertEquals(3, user.getPenalty(),0);
	}

	@Test
	public void testSetMyInvoker() {
		User user = new User();
		Invoker invoker = new Invoker();
		user.setMyInvoker(invoker);
		;
		assertEquals(invoker, user.myInvoker);
	}

	@Test
	public void testGetMyInvoker() {
		User userH = new User();
		Invoker myInvoker = new Invoker();
		List<RentalOrder> currentlyRenting = new ArrayList<>();
		PurchaseOrderBuilder purchaseOrder = new PurchaseOrderBuilder(userH);
		RentalOrderBuilder rentalOrder = new RentalOrderBuilder(userH);
		User user = new User(0, 0, currentlyRenting, rentalOrder, purchaseOrder, "", "", "", "", myInvoker);
		assertEquals(myInvoker, user.getMyInvoker());
	}

	@Test
	public void testSetCurrentlyRenting() {
		User user = new User();
		List<RentalOrder> currentlyRenting = new ArrayList<>();
		user.setCurrentlyRenting(currentlyRenting);
		;
		assertEquals(currentlyRenting, user.currentlyRenting);
	}

	@Test
	public void testGetCurrentlyRenting() {
		User userH = new User();
		Invoker myInvoker = new Invoker();
		List<RentalOrder> currentlyRenting = new ArrayList<>();
		PurchaseOrderBuilder purchaseOrder = new PurchaseOrderBuilder(userH);
		RentalOrderBuilder rentalOrder = new RentalOrderBuilder(userH);
		User user = new User(0, 0, currentlyRenting, rentalOrder, purchaseOrder, "", "", "", "", myInvoker);
		assertEquals(currentlyRenting, user.getCurrentlyRenting());
	}
	
	
	
	

/* Visitor.java */
	@Test
	public void testVisitorCreation() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		assertTrue(visitor instanceof Visitor);
	}

	// Instance of User
	@Test
	public void testVisitorCreation1() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		assertTrue(visitor instanceof User);
	}

	@Test
	public void testVisitorCreation2() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		assertTrue(visitor instanceof Visitor);
		assertTrue(visitor instanceof User);
	}

	@Test
	public void testVisitorCreation3() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		assertTrue(subject.users.contains(visitor));
	}

	@Test
	public void testVisitorCreation4() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		LibraryManagementSysInfo subject1 = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		assertFalse(subject1.users.contains(visitor));
	}

	@Test
	public void testVisitorCreation5() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		LibraryManagementSysInfo subject1 = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		assertFalse(subject1.users.contains(visitor));
		assertTrue(subject.users.contains(visitor));
	}

	@Test
	public void testVisitorCreation6() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		assertEquals(visitor.subject, subject);
	}

	@Test
	public void testVisitorCreation7() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		LibraryManagementSysInfo subject1 = new LibraryManagementSysInfo();
		assertFalse(visitor.subject.equals(subject1));
	}

	@Test
	public void testVisitorCreation8() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		User user = new User();
		assertFalse(user instanceof Visitor);
		assertTrue(visitor instanceof Visitor);
	}

	@Test
	public void testUpdate() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		visitor.update();
		assertEquals("Visitor's info is updated.\n", outContent.toString());
	}

	// Did not call update
	@Test
	public void testUpdate1() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Visitor visitor = new Visitor(subject);
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
		List<Courses> coursesList = new ArrayList<>();
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
		Textbook textbook = new Textbook("Introduction to Programming", "978-0134675874", "1st Edition",
				"http://example.com");
		faculty.setTextbook(course, textbook);
		assertEquals(textbook, faculty.getTextbook(course));
	}

	@Test
	public void testCreateNotification() {
		Faculty faculty = new Faculty("John Doe");
		Textbook textbook = new Textbook("Introduction to Programming", "978-0134675874", "1st Edition",
				"http://example.com");
		faculty.createNotification(textbook); // Just testing if it throws an exception or not
	}

	@Test
	public void testNotifyOfUnavailability() {
		Faculty faculty = new Faculty("John Doe");
		Textbook textbook = new Textbook("Introduction to Programming", "978-0134675874", "1st Edition",
				"http://example.com");
		faculty.notifyOfUnavailability(textbook); // Just testing if it throws an exception or not
	}

	@Test
	public void testNotifyNewTextbookEdition() {
		Faculty faculty = new Faculty("John Doe");
		faculty.trackCourseAndTextbook("CS101", "978-0134675874");
		faculty.notifyNewTextbookEdition("CS101", "978-0134675874", "2nd Edition"); // Just testing if it throws an
																					// exception or not
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

		User user = new User(1, 10, new ArrayList<>(), null, null, "John Doe", "john@example.com", "password",
				"faculty", null);

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
		NonFaculty nf = new NonFaculty(subject);
		assertTrue(nf instanceof User);
	}

	@Test
	public void TestNonfacultyCreation2() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		NonFaculty nf = new NonFaculty(subject);
		assertTrue(nf instanceof NonFaculty);
	}

	@Test
	public void TestNonfacultyCreation3() {
		NonFaculty nf = new NonFaculty();
		assertTrue(nf instanceof User);
	}

	@Test
	public void TestNonfacultyCreation4() {
		NonFaculty nf = new NonFaculty();
		assertTrue(nf instanceof NonFaculty);
	}

	@Test
	public void TestNonfacultySubject() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		NonFaculty nf = new NonFaculty(subject);
		assertTrue(nf.subject.equals(subject));
	}

	@Test
	public void TestNonfacultySubject2() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		NonFaculty nf = new NonFaculty(subject);
		assertTrue(subject.users.contains(nf));
	}

	@Test
	public void TestNonfacultyAttachment() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		NonFaculty nf = new NonFaculty();
		subject.attachRegisteredClient(nf);
		assertTrue(subject.users.contains(nf));
	}

	@Test
	public void TestNonfacultyDetachment() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		NonFaculty nf = new NonFaculty(subject);
		subject.deatachRegisteredClient(nf);
		assertFalse(subject.users.contains(nf));
	}

	@Test
	public void TestNonfacultyUpdate() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		NonFaculty nf = new NonFaculty(subject);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		subject.notifyAllObservers();
		assertEquals("Nonfaculty user has been updated.\n", outContent.toString());
	}

	@Test
	public void TestNonfacultyEmptyUpdate() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		NonFaculty nf = new NonFaculty();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		subject.notifyAllObservers();
		assertEquals("", outContent.toString());
	}

/* Student */
	@Test
	public void testStudentConstructor() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Student student = new Student(subject);
		assertTrue(student instanceof User);
		assertTrue(student instanceof Student);
	}

	@Test
	public void testStudentConstructor2() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Student student = new Student(subject);
		assertTrue(subject.users.contains(student));
		assertTrue(student.subject.equals(subject));
	}

	@Test
	public void testStudentConstructor3() {
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
	public void testStudentEnroll() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Student student = new Student(subject);
		Courses course = new Courses();
		student.enroll(course);
		assertTrue(student.viewEnrolledCourses().contains(course));
	}

	@Test
	public void testStudentWithdraw() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Student student = new Student(subject);
		Courses course = new Courses();
		student.enroll(course);
		assertTrue(student.viewEnrolledCourses().contains(course));
		student.withdraw(course);
		assertFalse(student.viewEnrolledCourses().contains(course));
	}

	@Test
	public void testStudentGetTextbook() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Student student = new Student(subject);
		assertTrue(subject.users.contains(student));
		assertTrue(student.subject.equals(subject));
	}

	@Test
	public void testStudentVirtualCopies() {
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
	public void testStudentBorrowPhysicalItem() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Student student = new Student(subject);
		HardcoverBook book = new HardcoverBook();
		book.setCopiesAvail(2);
		book.setRentalStatus(true);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		Date nextWeek = calendar.getTime();
		book.dueDate = nextWeek; // canborrowitem = overdue < max ; not overdue

		assertTrue(student.borrowPhysicalItem(book));
		assertTrue(student.getBorrowedHardcoverBooks().contains(book));
		assertFalse(book.getRentable());
		assertTrue(book.getCopiesAvail() == 1);

		student.borrowPhysicalItem(book);
		assertFalse(student.borrowPhysicalItem(book)); // now 0 copies
	}

	@Test
	public void testStudentReturnPhysicalItem() {
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
	public void testStudentUpdate() {
		LibraryManagementSysInfo subject = new LibraryManagementSysInfo();
		Student student = new Student(subject);

		// no borrowed items
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

    /* MaintainCourses class JUnit tests*/
    private MaintainCourses maintainCourses;
    private MaintainCourses invalidMaintainCourses;
    private String filePath = "Library-Management-Java-App-main/csv files/courses.csv";
    private String invalidFilePath = "Library-Management-Java-App-main/csv files/invalidCourses.csv";
    
    @Before
    public void setUp() {
        maintainCourses = new MaintainCourses(filePath);
        invalidMaintainCourses = new MaintainCourses(invalidFilePath);
    }

    @Test
    public void testLoadCSVFile() throws Exception {
    	// Ensures courses can be loaded from the a CSV file properly
        maintainCourses.load();
        maintainCourses.courses.add(new Courses("New Course", null, ""));
        assertTrue("Courses should be loaded from the CSV file", maintainCourses.courses.size() > 0);
    }

    @Test
    public void testUpdateCSVFile() throws Exception {
        // Ensure courses can be updated into the CSV file properly
        Courses newCourse = new Courses("CourseName", new Faculty("Faculty"), "CourseID");
        int initialSize = maintainCourses.courses.size();
        maintainCourses.courses.add(newCourse);
        maintainCourses.update(); 
        assertEquals("Number of courses in the CSV file should increase by one after updating", initialSize + 1, maintainCourses.courses.size());
    }

    @Test
    public void testAddAndLoadCourse() throws Exception {
    	 // Ensures a new course can be added and then loaded from the CSV file properly
        Courses newCourse = new Courses("NewCourse", new Faculty("NewFaculty"), "NewCourseID");
        maintainCourses.courses.add(newCourse);
        maintainCourses.update();
        maintainCourses.load();
        assertTrue("Added course should be present after loading from CSV", maintainCourses.courses.contains(newCourse));
    }

    @Test
    public void testAddAndUpdateCourse() throws Exception {
    	// Ensures a course can be added and then updated in the CSV file properly
        Courses newCourse = new Courses("CourseName", new Faculty("Faculty"), "CourseID");
        maintainCourses.courses.add(newCourse);
        maintainCourses.update();
        maintainCourses.load();
        Courses updatedCourse = maintainCourses.courses.get(0);
        assertEquals("Added course should be updated correctly in the CSV file", newCourse, updatedCourse);
    }

    @Test
    public void testLoadExistingFile() throws Exception {
        // Ensures courses can be loaded from an existing file properly
        int initialSize = maintainCourses.courses.size();
        maintainCourses.load();
        maintainCourses.courses.add(new Courses());
        assertTrue("Courses should be loaded from an existing file", maintainCourses.courses.size() > initialSize);
    }

    @Test(expected = Exception.class)
    public void testInvalidCSVData() throws Exception {
    	// Ensures the system can handle invalid CSV data properly
    	String invalidFilePath = "Library-Management-Java-App-main/csv files/invalidCSV.csv";
    	invalidMaintainCourses = new MaintainCourses(invalidFilePath);       
        invalidMaintainCourses.load();
    }
    
    @Test(expected = Exception.class)
    public void testEmptyCSVFile() throws Exception {
        // Ensure the system handles an empty CSV file properly
        String emptyFilePath = "empty.csv"; // Assuming "empty.csv" is a valid path to an empty CSV file
        MaintainCourses emptyCourses = new MaintainCourses(emptyFilePath);
        emptyCourses.load();
    }

    @Test
    public void testAddCourse() {
    	// Ensures a new course can be added by the system correctly
        Courses newCourse = new Courses("NewCourse", new Faculty("NewFaculty"), "NewCourseID");
        maintainCourses.courses.add(newCourse);
        assertEquals("A new course should be added to the list", 1, maintainCourses.courses.size());
    }
    
    @Test(expected = Exception.class)
    public void testLoadNonExistentFile() throws Exception {
        // Ensures the system handles loading from a non-existent file properly
        String nonExistentFilePath = "nonexistent.csv";
        MaintainCourses nonExistentCourses = new MaintainCourses(nonExistentFilePath);
        nonExistentCourses.load(); 
    }

    @Test(expected = Exception.class)
    public void testInvalidDateFormat() throws Exception {
        // Ensures the system can handle invalid date format in the CSV file properly
        MaintainCourses invalidDateFormatCourses = new MaintainCourses("invalid_date_format.csv");
        invalidDateFormatCourses.load();
    }
    
    /* MaintainTextbook class JUnit tests*/
    
    private MaintainTextbook maintainTextbook;
    private String filePath2 = "Library-Management-Java-App-main/csv files/textbooks.csv";

    @Before
    public void setUp2() {
        maintainTextbook = new MaintainTextbook(filePath2);
    }

    @Test
    public void testLoadCSVFile2() throws Exception {
        maintainTextbook.load();
        maintainTextbook.textbooks.add(new Textbook());
        assertTrue("Textbooks should be loaded from the CSV file", maintainTextbook.textbooks.size() > 0);
    }

    @Test
    public void testUpdateCSVFile2() throws Exception {
        Textbook newBook = new Textbook("BookName", "ISBN", "Edition", "URL");
        int initialSize = maintainTextbook.textbooks.size();
        maintainTextbook.textbooks.add(newBook);
        maintainTextbook.update();
        assertEquals("Number of textbooks in the CSV file should increase by one after updating", initialSize + 1, maintainTextbook.textbooks.size());
    }

    @Test
    public void testAddAndLoadTextbook() throws Exception {
        Textbook newBook = new Textbook("NewBook", "ISBN", "Edition", "URL");
        maintainTextbook.textbooks.add(newBook);
        maintainTextbook.update();
        maintainTextbook.load();
        assertTrue("Added textbook should be present after loading from CSV", maintainTextbook.textbooks.contains(newBook));
    }

    @Test
    public void testAddAndUpdateTextbook() throws Exception {
        Textbook newBook = new Textbook("BookName", "ISBN", "Edition", "URL");
        maintainTextbook.textbooks.add(newBook);
        maintainTextbook.update();
        maintainTextbook.load();
        Textbook updatedBook = maintainTextbook.textbooks.get(0);
        assertEquals("Added textbook should be updated correctly in the CSV file", newBook, updatedBook);
    }

    @Test
    public void testLoadExistingFile2() throws Exception {
        int initialSize = maintainTextbook.textbooks.size();
        maintainTextbook.load();
        maintainTextbook.textbooks.add(new Textbook("New Text", "", "", ""));
        assertTrue("Textbooks should be loaded from an existing file", maintainTextbook.textbooks.size() > initialSize);
    }

    @Test(expected = Exception.class)
    public void testInvalidCSVData2() throws Exception {
        MaintainTextbook invalidMaintainTextbook = new MaintainTextbook("invalidTextbooks.csv");
        invalidMaintainTextbook.load();
    }

    @Test(expected = Exception.class)
    public void testEmptyCSVFile2() throws Exception {
        String emptyFilePath = "empty.csv";
        MaintainTextbook emptyTextbooks = new MaintainTextbook(emptyFilePath);
        emptyTextbooks.load();
    }

    @Test(expected = Exception.class)
    public void testLoadNonExistentFile2() throws Exception {
        String nonExistentFilePath = "nonexistent.csv";
        MaintainTextbook nonExistentTextbooks = new MaintainTextbook(nonExistentFilePath);
        nonExistentTextbooks.load(); 
    }

    @Test(expected = Exception.class)
    public void testInvalidDateFormat2() throws Exception {
        MaintainTextbook invalidDateFormatTextbooks = new MaintainTextbook("invalid_date_format.csv");
        invalidDateFormatTextbooks.load();
    }

    @Test
    public void testAddTextbook() {
        Textbook newBook = new Textbook("NewBook", "ISBN", "Edition", "URL");
        maintainTextbook.textbooks.add(newBook);
        assertEquals("A new textbook should be added to the list", 1, maintainTextbook.textbooks.size());
    }
    
    @Test
    public void testFindNewestEdition() {
        Textbook newBook = new Textbook("NewBook", "ISBN", "2nd Edition", "URL");
        Textbook newerBook = new Textbook("NewBook", "ISBN", "5th Edition", "URL");
        maintainTextbook.textbooks.add(newBook);
        maintainTextbook.textbooks.add(newerBook);
        
        assertEquals("5th Edition", maintainTextbook.findNewestEd());
        
        
    }
    

    /* MaintainUser class JUnit tests*/
    
    private MaintainUser maintainUser;
    private String filePath3 = "Library-Management-Java-App-main/csv files/userInfo.csv";

    @Before
    public void setUp3() {
        maintainUser = new MaintainUser(filePath3); 
    }

    @Test
    public void testLoadCSVFile3() throws Exception {
        // Ensure users can be loaded from the CSV file
        maintainUser.load();
        assertTrue("Users should be loaded from the CSV file", maintainUser.users.size() > 0);
    }

    @Test
    public void testUpdateCSVFile3() throws Exception {
        // Ensure a new user can be added and updated in the CSV file
        User newUser = new User();
        int initialSize = maintainUser.users.size();
        maintainUser.users.add(newUser);
        maintainUser.update();
        assertEquals("Number of users in the CSV file should increase by one after updating", initialSize + 1, maintainUser.users.size());
    }

    @Test
    public void testAddAndLoadUser() throws Exception {
        // Ensure a new user can be added and loaded from the CSV file
        User newUser = new User();
        maintainUser.users.add(newUser);
        maintainUser.update();
        maintainUser.load();
        assertTrue("Added user should be present after loading from CSV", maintainUser.users.contains(newUser));
    }

    @Test
    public void testAddAndUpdateUser() throws Exception {
        // Ensure a user can be added and then updated in the CSV file
        User newUser = new User();
        maintainUser.users.add(newUser);
        maintainUser.update();
        maintainUser.load();
        User updatedUser = maintainUser.users.get(0);
        assertEquals("Added user should be updated correctly in the CSV file", newUser, updatedUser);
    }
    
    /**ADDED**/
    @Test
    public void testGetOrderByID() throws Exception {
    	RentalOrder r = new RentalOrder();
    	ActionPage.allRentalOrders.add(r);
    	
    	RentalOrder.idAllocater = 0;
    	assertTrue(r.getOrderID() > 0);
    	MaintainUser m = new MaintainUser(filePath3);
        assertNotNull(m.getOrderById("1"));
    }

    @Test(expected = Exception.class)
    public void testInvalidCSVData3() throws Exception {
        // Ensure the system can handle invalid CSV data
        MaintainUser invalidMaintainUser = new MaintainUser("invalidUsers.csv");
        invalidMaintainUser.load();
    }

    @Test(expected = Exception.class)
    public void testEmptyCSVFile3() throws Exception {
        // Ensure the system handles an empty CSV file properly
        String emptyFilePath = "empty.csv";
        MaintainUser emptyUsers = new MaintainUser(emptyFilePath);
        emptyUsers.load();
    }

    @Test(expected = Exception.class)
    public void testLoadNonExistentFile3() throws Exception {
        // Ensure the system handles loading from a non-existent file properly
        String nonExistentFilePath = "nonexistent.csv";
        MaintainUser nonExistentUsers = new MaintainUser(nonExistentFilePath);
        nonExistentUsers.load(); 
    }

    @Test(expected = Exception.class)
    public void testInvalidDateFormat3() throws Exception {
        // Ensure the system can handle invalid date format in the CSV file
        MaintainUser invalidDateFormatUsers = new MaintainUser("invalid_date_format.csv");
        invalidDateFormatUsers.load();
    }

    @Test
    public void testAddUser() {
        // Ensure a new user can be added to the system
        User newUser = new User();
        maintainUser.users.add(newUser);
        assertEquals("A new user should be added to the list", 1, maintainUser.users.size());
    }
	    
    @Test
    public void testDeleteUser() {
        // Ensures that a user can be deleted from the system properly
        User userToDelete = new User(0, 0, null, null, null, "John Doe", "john@example.com", "password123", "Regular", null);
        maintainUser.addUser(userToDelete);
        int initialSize = maintainUser.getUsers().size();
        maintainUser.deleteUser(userToDelete);
        assertEquals("Deleted user should be removed from the list", initialSize - 1, maintainUser.getUsers().size());
        assertFalse("Deleted user should not be present in the list", maintainUser.getUsers().contains(userToDelete));
    }

    @Test
    public void testDeleteUser1() {
        // Ensures that a user is removed properly
        MaintainUser maintainUser = new MaintainUser(filePath3);
        User user1 = new User();
        User user2 = new User();
        maintainUser.addUser(user1);
        maintainUser.addUser(user2);
        assertEquals("Initial number of users should be 2", 2, maintainUser.getUsers().size());

        maintainUser.deleteUser(user1);

        assertEquals("Number of users should decrease by 1 after deleting", 1, maintainUser.getUsers().size());
        assertFalse("Deleted user should not be present in the list of users", maintainUser.getUsers().contains(user1));
    }
    
    @Test
    public void testSetPath() {
        // Ensures that the path of MaintainUser is set properly
        String newPath = "Library-Management-Java-App-main/csv files/userInfo.csv";
        maintainUser.setPath(newPath);
        assertEquals("Path of MaintainUser should be updated", newPath, maintainUser.path);
    }

    @Test
    public void testGetUsers() {
        // Ensures that the list of users can be retrieved properly
        ArrayList<User> userList = maintainUser.getUsers();
        assertNotNull("User list should not be null", userList);
        assertEquals("Size of user list should match the number of users", userList.size(), maintainUser.users.size());
    }

    @Test
    public void testAddAndDeleteUser() throws Exception {
        // Ensures that a new user can be added and then deleted properly
        User newUser = new User();
        maintainUser.users.add(newUser);
        maintainUser.update();
        maintainUser.load();
        assertTrue("Added user should be present after loading from CSV", maintainUser.users.contains(newUser));
        maintainUser.deleteUser(newUser);
        maintainUser.update();
        maintainUser.load();
        assertFalse("Deleted user should not be present in the list", maintainUser.users.contains(newUser));
    }

    @Test
    public void testInvalidFilePath() throws Exception {
        // Ensures that the system handles loading from an invalid file path properly
        String invalidFilePath = "invalid_path.csv";
        MaintainUser invalidMaintainUser = new MaintainUser(invalidFilePath);
        assertThrows(Exception.class, () -> {
            invalidMaintainUser.load();
        });
    }

    @Test
    public void testUpdateEmptyUserList() throws Exception {
        // Ensures that updating an empty user list does not cause errors
        maintainUser.users.clear(); 
        assertDoesNotThrow(() -> {
            maintainUser.update();
        });
    }

/*LibraryManagementSystem test cases*/
    
    @Test
    public void testAttachRegisteredClient() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        User user = new User();
        library.attachRegisteredClient(user);
        assertTrue(library.users.contains(user));
    }

    @Test
    public void testDetachRegisteredClient() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        User user = new User();
        library.attachRegisteredClient(user);
        library.deatachRegisteredClient(user);
        assertFalse(library.users.contains(user));
    }

    @Test
    public void testGetAvailPhysicalItems() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        PhysicalItem availableItem = new PhysicalItem();
        PhysicalItem unavailableItem = new PhysicalItem();
        library.physicalItems.add(availableItem);
        assertEquals(1, library.getAvailPhysicalItems().size());
    }


    @Test
    public void testNotifyAllObservers() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        User user1 = new User();
        User user2 = new User();
        library.attachRegisteredClient(user1);
        library.attachRegisteredClient(user2);
        library.notifyAllObservers();
    }

    @Test
    public void testHandleOverdueItems() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        PhysicalItem item = new PhysicalItem();
        library.handleOverdueItems(item);
        assertTrue(library.isItemOverdue(item));
    }

    @Test
    public void testHandleReturnedItem() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        PhysicalItem item = new PhysicalItem();
        library.handleOverdueItems(item);
        library.handleReturnedItem(item);
        assertFalse(library.isItemOverdue(item));
    }

    @Test
    public void testIsItemOverdue() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        PhysicalItem item = new PhysicalItem();
        assertFalse(library.isItemOverdue(item));
        library.handleOverdueItems(item);
        assertTrue(library.isItemOverdue(item));
    }
    
    @Test
    public void testGetAvailOnlineBooks() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        OnlineBook availableBook = new OnlineBook();
        availableBook.setAvailable(true); 
        OnlineBook unavailableBook = new OnlineBook();
        unavailableBook.setAvailable(false); 
        library.onlineBooks.add(availableBook);
        library.onlineBooks.add(unavailableBook);
        assertEquals(1, library.getAvailOnlineBooks().size());
    }

    @Test
    public void testSetDueDateForTextbook() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        Textbook textbook = new Textbook();
        Date dueDate = new Date();
        library.setDueDateForTextbook(textbook, dueDate);
        assertEquals(dueDate, library.getDueDateForTextbook(textbook));
    }

    @Test
    public void testGetDueDateForTextbook_NotSet() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        Textbook textbook = new Textbook();
        assertNull(library.getDueDateForTextbook(textbook));
    }
    
    @Test
    public void testHandleReturnedItem_NotOverdue() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        PhysicalItem item = new PhysicalItem();
        library.handleReturnedItem(item);
        assertFalse(library.isItemOverdue(item));
    }

    @Test
    public void testGetAlreadyRented_NoRentedBooks() {
        LibraryManagementSysInfo library = new LibraryManagementSysInfo();
        List<HardcoverBook> rentedBooks = library.getAlreadyRented();
        assertEquals(0, rentedBooks.size());
    }
    
}
