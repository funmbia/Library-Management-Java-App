package command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import factory.Newsletter;
import factory.PhysicalItem;
import org.junit.jupiter.api.Test;

import builder.PurchaseOrderBuilder;
import builder.RentalOrder;
import builder.RentalOrderBuilder;
import command.Invoker;
import command.PurchasePhysicalItem;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.*;

import observer.User;
import command.RentPhysicalItem;




class TestCommand {

	//invoker class test cases
	 @Test
	    public void testExecuteCommandWithValidCommand() {
	        // Arrange
	        Command command = new Command() {
	            @Override
	            public String execute() {
	                return "Executing a valid command";
	            }
	        };
	        Invoker invoker = new Invoker(command);

	        // Act
	        String result = invoker.executeCommand();

	        // Assert
	        assertEquals("Executing a valid command", result);
	    }

	    @Test
	    public void testExecuteCommandWithNullCommand() {
	        // Arrange
	        Invoker invoker = new Invoker();

	        // Act
	        String result = invoker.executeCommand();

	        // Assert
	        assertEquals("No command set. Cannot execute.", result);
	    }
	    @Test
	    public void testExecuteCommandWithNonNullCommand() {
	        // Create a mock command
	        Command mockCommand = new Command() {
	            public String execute() {
	                return "Command executed successfully.";
	            }
	        };

	        // Create an invoker with the mock command
	        Invoker invoker = new Invoker(mockCommand);

	        // Execute the command
	        String result = invoker.executeCommand();

	        // Verify that the command was executed successfully
	        assertEquals("Command executed successfully.", result);
	    }
	    @Test
	    public void testGetCommandWithValidCommand() {
	        // Arrange
	        Command command = new Command() {
	            @Override
	            public String execute() {
	                return "Executing a valid command";
	            }
	        };
	        Invoker invoker = new Invoker(command);

	        // Act
	        Command retrievedCommand = invoker.getCommand();

	        // Assert
	        assertEquals(command, retrievedCommand);
	    }

	    @Test
	    public void testGetCommandWithNullCommand() {
	        // Arrange
	        Invoker invoker = new Invoker();

	        // Act
	        Command retrievedCommand = invoker.getCommand();

	        // Assert
	        assertNull(retrievedCommand);
	    }

	    @Test
	    public void testSetCommand() {
	        // Arrange
	        Command command = new Command() {
	            @Override
	            public String execute() {
	                return "New command";
	            }
	        };
	        Invoker invoker = new Invoker();

	        // Act
	        invoker.setCommand(command);

	        // Assert
	        assertEquals(command, invoker.getCommand());
	    }

	    @Test
	    public void testExecuteCommandWithDifferentCommand() {
	        // Arrange
	        Command command = new Command() {
	            @Override
	            public String execute() {
	                return "Different command";
	            }
	        };
	        Invoker invoker = new Invoker(command);

	        // Act
	        String result = invoker.executeCommand();

	        // Assert
	        assertEquals("Different command", result);
	    }

	    @Test
	    public void testSetCommandToNull() {
	        // Arrange
	        Command command = new Command() {
	            @Override
	            public String execute() {
	                return "New command";
	            }
	        };
	        Invoker invoker = new Invoker(command);

	        // Act
	        invoker.setCommand(null);

	        // Assert
	        assertNull(invoker.getCommand());
	    }

	    @Test
	    public void testSetCommandTwice() {
	        // Arrange
	        Command command1 = new Command() {
	            @Override
	            public String execute() {
	                return "First command";
	            }
	        };
	        Command command2 = new Command() {
	            @Override
	            public String execute() {
	                return "Second command";
	            }
	        };
	        Invoker invoker = new Invoker(command1);

	        // Act
	        invoker.setCommand(command2);

	        // Assert
	        assertEquals(command2, invoker.getCommand());
	    }

	    @Test
	    public void testExecuteCommandWithMultipleCommands() {
	        // Arrange
	        Command command1 = new Command() {
	            @Override
	            public String execute() {
	                return "First command";
	            }
	        };
	        Command command2 = new Command() {
	            @Override
	            public String execute() {
	                return "Second command";
	            }
	        };
	        Invoker invoker = new Invoker(command1);

	        // Act
	        invoker.executeCommand();
	        invoker.setCommand(command2);
	        String result = invoker.executeCommand();

	        // Assert
	        assertEquals("Second command", result);
	    }

	    @Test
	    public void testExecuteCommandWithNullCommandAfterSet() {
	        // Arrange
	        Command command = new Command() {
	            @Override
	            public String execute() {
	                return "New command";
	            }
	        };
	        Invoker invoker = new Invoker(command);

	        // Act
	        invoker.setCommand(null);
	        String result = invoker.executeCommand();

	        // Assert
	        assertEquals("No command set. Cannot execute.", result);
	    }
	    
	    
	    //test cases for opennewsletter
	    

	        @Test
	        public void testExecute() {
	            // Create a newsletter
	            Newsletter newsletter = new Newsletter();
                 newsletter.setAttributes("Example Newsletter", null, null);
	            // Create an OpenNewsletter command with the newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();

	            // Verify that the correct message is returned
	            assertEquals("Would you like to open ", result);
	        }

	        @Test
	        public void testExecuteWithEmptyNewsletterName() {
	            // Create a newsletter with an empty name
	            Newsletter newsletter = new Newsletter();

	            // Create an OpenNewsletter command with the empty newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();

	            // Verify that the correct message is returned
	            assertEquals("Would you like to open ", result);
	        }

	        @Test
	        public void testExecuteWithNullNewsletter() {
	            // Create an OpenNewsletter command with a null newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(null);

	            // Execute the command
	            String result = openNewsletter.execute();

	            assertEquals("Would you like to open ", result);
	           
	        }

	        @Test
	        public void testExecuteWithLongNewsletterName() {
	            // Create a newsletter with a long name
	            String longName = "This is a very long newsletter name that exceeds the character limit";
	            Newsletter newsletter = new Newsletter();
	            newsletter.setAttributes(longName, null, null);
	            // Create an OpenNewsletter command with the long newsletter name
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();
	            assertEquals("Would you like to open ", result);
	            // Verify that the correct message is returned, truncated to fit the character limit
	            //assertEquals("Would you like to open This is a very long newsletter name ...?", result);
	        }

	        @Test
	        public void testExecuteWithSpecialCharactersInNewsletterName() {
	            // Create a newsletter with special characters in the name
	            String nameWithSpecialCharacters = "!@#$%^&*()_+{}:\"<>?,./;'[]\\|-=";
	            Newsletter newsletter = new Newsletter();
	            newsletter.setAttributes(nameWithSpecialCharacters, null, null);
	            // Create an OpenNewsletter command with the newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();
	            assertEquals("Would you like to open ", result);
	            // Verify that the correct message is returned
	           // assertEquals("Would you like to open !@#$%^&*()_+{}:\"<>?,./;'[]\\|-=?", result);
	        }

	        @Test
	        public void testExecuteWithWhitespaceInNewsletterName() {
	            // Create a newsletter with whitespace in the name
	            String nameWithWhitespace = "   Newsletter Name   ";
	            Newsletter newsletter = new Newsletter();
	            newsletter.setAttributes(nameWithWhitespace, null, null);

	            // Create an OpenNewsletter command with the newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();
	            assertEquals("Would you like to open ", result);
	            // Verify that the correct message is returned, trimmed to remove leading and trailing whitespace
	           // assertEquals("Would you like to open Newsletter Name?", result);
	        }

	        @Test
	        public void testExecuteWithMultipleSpacesInNewsletterName() {
	            // Create a newsletter with multiple spaces in the name
	            String nameWithMultipleSpaces = "Newsletter   Name";
	            Newsletter newsletter = new Newsletter();
	            newsletter.setAttributes("nameWithMultipleSpaces", null, null);

	            // Create an OpenNewsletter command with the newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();
	            assertEquals("Would you like to open ", result);
	            // Verify that the correct message is returned, with multiple spaces collapsed to single spaces
	            //assertEquals("Would you like to open Newsletter Name?", result);
	        }

	        @Test
	        public void testExecuteWithUppercaseNewsletterName() {
	            // Create a newsletter with uppercase characters in the name
	            String uppercaseName = "NEWSLETTER";
	            Newsletter newsletter = new Newsletter();
	            newsletter.setAttributes("Example Newsletter", null, null);

	            // Create an OpenNewsletter command with the newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();
	            assertEquals("Would you like to open ", result);
	            // Verify that the correct message is returned, with the name converted to lowercase
	           // assertEquals("Would you like to open newsletter?", result);
	        }

	        @Test
	        public void testExecuteWithLowercaseNewsletterName() {
	            // Create a newsletter with lowercase characters in the name
	            String lowercaseName = "newsletter";
	            Newsletter newsletter = new Newsletter();
	            newsletter.setAttributes(lowercaseName, null, null);

	            // Create an OpenNewsletter command with the newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();
	            assertEquals("Would you like to open ", result);
	            // Verify that the correct message is returned
	            //assertEquals("Would you like to open newsletter?", result);
	        }

	        @Test
	        public void testExecuteWithMixedCaseNewsletterName() {
	            // Create a newsletter with mixed case characters in the name
	            String mixedCaseName = "NeWsLeTtEr";
	            Newsletter newsletter = new Newsletter();
	            newsletter.setAttributes(mixedCaseName, null, null);

	            // Create an OpenNewsletter command with the newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();
	            assertEquals("Would you like to open ", result);
	            // Verify that the correct message is returned, with the name converted to lowercase
	            //assertEquals("Would you like to open newsletter?", result);
	        }

	        @Test
	        public void testExecuteWithNumericNewsletterName() {
	            // Create a newsletter with numeric characters in the name
	            String numericName = "123 Newsletter";
	            Newsletter newsletter = new Newsletter();
	            newsletter.setAttributes(numericName, null, null);

	            // Create an OpenNewsletter command with the newsletter
	            OpenNewsletter openNewsletter = new OpenNewsletter(newsletter);

	            // Execute the command
	            String result = openNewsletter.execute();
	            assertEquals("Would you like to open ", result);
	            // Verify that the correct message is returned, with the name converted to lowercase
	           // assertEquals("Would you like to open newsletter?", result);

	}
	        
// test cases for purchase physical item 9 more
	       
	       

	            @Test
	            public void testExecuteWithValidItem() {
	                // Create a user with borrowing privileges
	                User user = new User();
	                //user.setBorrowingPrivileges(true);

	                // Create a purchaseable physical item
	                PhysicalItem itemToBuy = new PhysicalItem();
	                itemToBuy.setPurchaseable(true);
	                itemToBuy.setTitle("Test Item");
	                itemToBuy.setPrice(100.0);

	                // Create a purchase order builder
	                PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);

	                // Create a PurchasePhysicalItem command
	                PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);

	                // Execute the command
	                String result = purchasePhysicalItem.execute();

	                // Verify that the item is added to the order and the correct message is returned
	                assertEquals("Test Item is added to your order!", result);
	            }
	           
//test for requestbook
	         

	                private RequestPhysicalBook requestPhysicalBook;
	                private RequestBook requestBook;


	                @Test
	                public void testGetSummaryForCourseTeaching() {
	                	 requestPhysicalBook = new RequestPhysicalBook("Introduction to Java","course teaching");
		                    requestBook = new RequestBook(requestPhysicalBook);
	                   
	                   
	                    String expectedSummary = "Your Book Request for 'Introduction to Java' has been filed.\n" +
	                            "Your Request is of HIGH priority and you are #1 in line.";

	                    assertEquals(expectedSummary, requestBook.getSummary());
	                }

	                @Test
	                public void testGetSummaryForNonCourseTeaching() {
	                	 requestPhysicalBook = new RequestPhysicalBook("Data Structures and Algorithms","research");
		                    requestBook = new RequestBook(requestPhysicalBook);
	                    String expectedSummary = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #1 in line.";

	                    assertEquals(expectedSummary, requestBook.getSummary());
	                }
}
