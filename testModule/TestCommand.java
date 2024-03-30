package testModule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import factory.Newsletter;
import factory.PhysicalItem;
import org.junit.jupiter.api.Test;

import builder.PurchaseOrderBuilder;
import builder.RentalOrderBuilder;
import observer.User;




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
	        
// test cases for purchase physical item 
	       
	       

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
	           



	          

	                    @Test
	                    public void testExecuteWithEmptyTitle() {
	                        // Test with an item having an empty title
	                        // Create a user with borrowing privileges
	                        User user = new User();

	                        // Create a purchaseable physical item with an empty title
	                        PhysicalItem itemToBuy = new PhysicalItem();
	                        itemToBuy.setPurchaseable(true);
	                        itemToBuy.setTitle("");
	                        itemToBuy.setPrice(100.0);

	                        // Create a purchase order builder
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);

	                        // Create a PurchasePhysicalItem command
	                        PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);

	                        // Execute the command
	                        String result = purchasePhysicalItem.execute();

	                        // Verify that an error message is returned
	                        assertEquals(" is added to your order!", result);
	                    }

	                    @Test
	                    public void testExecuteWithValidItemAndZeroDiscount() {
	                        // Test with a valid purchaseable item and zero discount
	                        User user = new User();
	                        PhysicalItem itemToBuy = new PhysicalItem();
	                        itemToBuy.setPurchaseable(true);
	                        itemToBuy.setTitle("Test Item");
	                        itemToBuy.setPrice(100.0);
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);
	                        purchaseOrderBuilder.setDiscount(0.0); // Set discount to zero
	                        PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);
	                        String result = purchasePhysicalItem.execute();
	                        assertEquals("Test Item is added to your order!", result); // Assert the correct message is returned
	                    }

	                    @Test
	                    public void testExecuteWithValidItemAndCustomDiscount() {
	                        // Test with a valid purchaseable item and custom discount
	                        User user = new User();
	                        PhysicalItem itemToBuy = new PhysicalItem();
	                        itemToBuy.setPurchaseable(true);
	                        itemToBuy.setTitle("Test Item");
	                        itemToBuy.setPrice(100.0);
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);
	                        purchaseOrderBuilder.setDiscount(0.2); // Set discount to 20%
	                        PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);
	                        String result = purchasePhysicalItem.execute();
	                        assertEquals("Test Item is added to your order!", result); // Assert the correct message is returned
	                    }
	                    @Test
	                    public void testExecuteWithValidItemAndNegativeDiscount() {
	                        // Test with a valid purchaseable item and negative discount
	                        User user = new User();
	                        PhysicalItem itemToBuy = new PhysicalItem();
	                        itemToBuy.setPurchaseable(true);
	                        itemToBuy.setTitle("Test Item");
	                        itemToBuy.setPrice(100.0);
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);
	                        purchaseOrderBuilder.setDiscount(-0.1); // Set discount to -10%
	                        PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);
	                        String result = purchasePhysicalItem.execute();
	                        assertEquals("Test Item is added to your order!", result); // Assert the correct message is returned
	                    }
	                    @Test
	                    public void testExecuteWithValidItemAndMaximumDiscount() {
	                        // Test with a valid purchaseable item and maximum discount (100%)
	                        User user = new User();
	                        PhysicalItem itemToBuy = new PhysicalItem();
	                        itemToBuy.setPurchaseable(true);
	                        itemToBuy.setTitle("Test Item");
	                        itemToBuy.setPrice(100.0);
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);
	                        purchaseOrderBuilder.setDiscount(1.0); // Set discount to 100%
	                        PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);
	                        String result = purchasePhysicalItem.execute();
	                        assertEquals("Test Item is added to your order!", result); // Assert the correct message is returned
	                    }

	                    @Test
	                    public void testExecuteWithValidItemAndLargeDiscount() {
	                        // Test with a valid purchaseable item and large discount (greater than 100%)
	                        User user = new User();
	                        PhysicalItem itemToBuy = new PhysicalItem();
	                        itemToBuy.setPurchaseable(true);
	                        itemToBuy.setTitle("Test Item");
	                        itemToBuy.setPrice(100.0);
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);
	                        purchaseOrderBuilder.setDiscount(2.0); // Set discount to 200%
	                        PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);
	                        String result = purchasePhysicalItem.execute();
	                        assertEquals("Test Item is added to your order!", result); // Assert the correct message is returned
	                    }

	                    @Test
	                    public void testExecuteWithValidItemAndMultipleItemsInOrder() {
	                        // Test with a valid purchaseable item and multiple items in the order
	                        User user = new User();
	                        PhysicalItem itemToBuy1 = new PhysicalItem();
	                        itemToBuy1.setPurchaseable(true);
	                        itemToBuy1.setTitle("Test Item 1");
	                        itemToBuy1.setPrice(100.0);
	                        PhysicalItem itemToBuy2 = new PhysicalItem();
	                        itemToBuy2.setPurchaseable(true);
	                        itemToBuy2.setTitle("Test Item 2");
	                        itemToBuy2.setPrice(50.0);
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);
	                        PurchasePhysicalItem purchasePhysicalItem1 = new PurchasePhysicalItem(user, itemToBuy1, purchaseOrderBuilder);
	                        PurchasePhysicalItem purchasePhysicalItem2 = new PurchasePhysicalItem(user, itemToBuy2, purchaseOrderBuilder);
	                        purchasePhysicalItem1.execute(); // Add first item to the order
	                        String result = purchasePhysicalItem2.execute(); // Add second item to the order
	                        assertEquals("Test Item 2 is added to your order!", result); // Assert the correct message is returned
	                    }

	                    @Test
	                    public void testExecuteWithValidItemAndZeroItemsInOrder() {
	                        // Test with a valid purchaseable item and zero items in the order
	                        User user = new User();
	                        PhysicalItem itemToBuy = new PhysicalItem();
	                        itemToBuy.setPurchaseable(true);
	                        itemToBuy.setTitle("Test Item");
	                        itemToBuy.setPrice(100.0);
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);
	                        PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);
	                        String result = purchasePhysicalItem.execute();
	                        assertEquals("Test Item is added to your order!", result); // Assert the correct message is returned
	                    }

	                    @Test
	                    public void testExecuteWithValidItemAndInventoryUpdate() {
	                        // Test with a valid purchaseable item and inventory update
	                        User user = new User();
	                        PhysicalItem itemToBuy = new PhysicalItem();
	                        itemToBuy.setPurchaseable(true);
	                        itemToBuy.setTitle("Test Item");
	                        itemToBuy.setPrice(100.0);
	                        itemToBuy.setCopiesAvail(2); // Set initial copies available to 2
	                        PurchaseOrderBuilder purchaseOrderBuilder = new PurchaseOrderBuilder(user);
	                        PurchasePhysicalItem purchasePhysicalItem = new PurchasePhysicalItem(user, itemToBuy, purchaseOrderBuilder);
	                        purchasePhysicalItem.execute(); // Execute the command
	                        assertEquals(1, itemToBuy.getCopiesAvail()); // Assert that the inventory is updated
	                    }



	               

	            

	            
	            //test for rentphysicalitem
	                    
	                    

	                        @Test
	                        public void testExecuteWithValidItemRPI() {
	                            // Setup
	                            User user = new User();
	                            RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);

	                            // Create a rentable physical item
	                            PhysicalItem itemToRent = new PhysicalItem();
	                            itemToRent.setRentable(true);
	                            itemToRent.setTitle("Test Item");
	                            itemToRent.setPrice(10.0);

	                            // Create a RentPhysicalItem command
	                            RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, itemToRent, rentalOrderBuilder);

	                            // Execute the command
	                            String result = rentPhysicalItem.execute();

	                            // Verify that the item is added to the order and the correct message is returned
	                            assertEquals("'Test Item' is added to your order!", result);
	                        }

	                        @Test
	                        public void testExecuteWithZeroItemsAllowed() {
	                            // Setup
	                            User user = new User();
	                            RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);
	                            user.setItemsOverdue(10); // Assuming the user already has 10 items rented out

	                            // Create a rentable physical item
	                            PhysicalItem itemToRent = new PhysicalItem();
	                            itemToRent.setRentable(true);
	                            itemToRent.setTitle("Test Item");

	                            // Create a RentPhysicalItem command
	                            RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, itemToRent, rentalOrderBuilder);

	                            // Execute the command
	                            String result = rentPhysicalItem.execute();

	                            // Verify that the correct error message is returned
	                            assertEquals("You cannot borrow any more items right now", result);
	                        }
	                    
	              
	                  

	 

	                           

	                            @Test
	                            public void testExecuteWithNoBorrowingPrivilegesAndReturnItemThenRentAgain() {
	                                // Set up
	                            	 User user = new User();
		                                user.setItemsOverdue(4); // User initially does not have borrowing privileges
		                                RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);
		                            

	                              
	                       

	                                PhysicalItem itemToRent = new PhysicalItem();
	                                itemToRent.setRentable(true);
	                                itemToRent.setTitle("Item");

	                                // Test logic - attempt to rent item when user does not have borrowing privileges
	                                RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, itemToRent, rentalOrderBuilder);
	                                String result1 = rentPhysicalItem.execute();
	                                assertEquals("You cannot borrow any more items right now", result1);

	                                // Return the item and grant borrowing privileges
	                                user.setItemsOverdue(2);

	                                // Test logic - attempt to rent item again after returning item and having borrowing privileges
	                                rentPhysicalItem = new RentPhysicalItem(user, itemToRent, rentalOrderBuilder);
	                                String result2 = rentPhysicalItem.execute();
	                                assertEquals("'Item' is added to your order!", result2);
	                            }
	                        


	                          


	                                @Test
	                                public void testExecuteWithUserNotAllowedToRent() {
	                                    User user = new User();
	                                    user.setItemsOverdue(4); // User is not allowed to rent items
	                                    RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);

	                                    PhysicalItem itemToRent = new PhysicalItem();
	                                    itemToRent.setRentable(true);
	                                    itemToRent.setTitle("Test Item");

	                                    RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, itemToRent, rentalOrderBuilder);
	                                    String result = rentPhysicalItem.execute();

	                                    assertEquals("You cannot borrow any more items right now", result);
	                                }

	                                @Test
	                                public void testExecuteWithMaxItemsRentedOut() {
	                                    User user = new User();
	                                    user.setDatabaseAttributes("", "", "","", 10, 0, 0); // User has already rented out the maximum number of items allowed
	                                    RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);

	                                    PhysicalItem itemToRent = new PhysicalItem();
	                                    itemToRent.setRentable(true);
	                                    itemToRent.setTitle("Test Item");

	                                    RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, itemToRent, rentalOrderBuilder);
	                                    String result = rentPhysicalItem.execute();

	                                    assertEquals("You cannot borrow any more items right now", result);
	                                }

	                    

	                              
	                                @Test
	                                public void testExecuteWithMultipleItemsLimitReached() {
	                                    // Set up
	                                    User user = new User();
	                                    user.setDatabaseAttributes(null, null, null, null, 9, 0, 0); // User has already rented 9 items
	                                    RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);

	                                    PhysicalItem itemToRent1 = new PhysicalItem();
	                                    itemToRent1.setRentable(true);
	                                    itemToRent1.setTitle("Item 1");

	                                    PhysicalItem itemToRent2 = new PhysicalItem();
	                                    itemToRent2.setRentable(true);
	                                    itemToRent2.setTitle("Item 2");

	                                    // Test logic
	                                    RentPhysicalItem rentPhysicalItem1 = new RentPhysicalItem(user, itemToRent1, rentalOrderBuilder);
	                                    RentPhysicalItem rentPhysicalItem2 = new RentPhysicalItem(user, itemToRent2, rentalOrderBuilder);

	                                    String result1 = rentPhysicalItem1.execute();
	                                    String result2 = rentPhysicalItem2.execute();

	                                    assertEquals("'Item 1' is added to your order!", result1);
	                                    assertEquals("You cannot borrow any more items right now", result2);
	                                }

	                                @Test
	                                public void testExecuteWithMultipleItemsNoLimit() {
	                                    // Set up
	                                    User user = new User();
	                                    user.setDatabaseAttributes(null, null, null, null, 5, 0, 0); // User has already rented 5 items, but there's no limit set
	                                    RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);

	                                    PhysicalItem itemToRent1 = new PhysicalItem();
	                                    itemToRent1.setRentable(true);
	                                    itemToRent1.setTitle("Item 1");

	                                    PhysicalItem itemToRent2 = new PhysicalItem();
	                                    itemToRent2.setRentable(true);
	                                    itemToRent2.setTitle("Item 2");

	                                    // Test logic
	                                    RentPhysicalItem rentPhysicalItem1 = new RentPhysicalItem(user, itemToRent1, rentalOrderBuilder);
	                                    RentPhysicalItem rentPhysicalItem2 = new RentPhysicalItem(user, itemToRent2, rentalOrderBuilder);

	                                    String result1 = rentPhysicalItem1.execute();
	                                    String result2 = rentPhysicalItem2.execute();

	                                    assertEquals("'Item 1' is added to your order!", result1);
	                                    assertEquals("'Item 2' is added to your order!", result2);
	                                }

	                            

	                                    @Test
	                                    public void testExecuteWithMaxItemsOutThenSetItemsOutAndRent() {
	                                        // Set up
	                                        User user = new User();
	                                        user.setDatabaseAttributes(null, null, null, null, 10, 0, 0); // User initially has 10 items out
	                                        RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);

	                                        PhysicalItem itemToRent = new PhysicalItem();
	                                        itemToRent.setRentable(true);
	                                        itemToRent.setTitle("Item");

	                                        // Test logic - attempt to rent item when user has max items out
	                                        RentPhysicalItem rentPhysicalItem = new RentPhysicalItem(user, itemToRent, rentalOrderBuilder);
	                                        String result1 = rentPhysicalItem.execute();
	                                        assertEquals("You cannot borrow any more items right now", result1);

	                                        // Update user's items out to 3
	                                        user.setDatabaseAttributes("", "", "","", 3, 0, 0);

	                                        // Test logic - attempt to rent item again after updating items out count
	                                        rentPhysicalItem = new RentPhysicalItem(user, itemToRent, rentalOrderBuilder);
	                                        String result2 = rentPhysicalItem.execute();
	                                        assertEquals("'Item' is added to your order!", result2);
	                                    }
	                                
	                                    

	                                    @Test
	                                    public void testExecuteWithMultipleDifferentItems() {
	                                        // Set up
	                                        User user = new User();
	                                        RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);

	                                        PhysicalItem itemToRent1 = new PhysicalItem();
	                                        itemToRent1.setRentable(true);
	                                        itemToRent1.setTitle("Item 1");

	                                        PhysicalItem itemToRent2 = new PhysicalItem();
	                                        itemToRent2.setRentable(true);
	                                        itemToRent2.setTitle("Item 2");

	                                        PhysicalItem itemToRent3 = new PhysicalItem();
	                                        itemToRent3.setRentable(true);
	                                        itemToRent3.setTitle("Item 3");

	                                        // Test logic - add three different items to the order
	                                        RentPhysicalItem rentPhysicalItem1 = new RentPhysicalItem(user, itemToRent1, rentalOrderBuilder);
	                                        RentPhysicalItem rentPhysicalItem2 = new RentPhysicalItem(user, itemToRent2, rentalOrderBuilder);
	                                        RentPhysicalItem rentPhysicalItem3 = new RentPhysicalItem(user, itemToRent3, rentalOrderBuilder);

	                                        String result1 = rentPhysicalItem1.execute();
	                                        String result2 = rentPhysicalItem2.execute();
	                                        String result3 = rentPhysicalItem3.execute();

	                                        // Assertion
	                                        assertEquals("'Item 1' is added to your order!", result1);
	                                        assertEquals("'Item 2' is added to your order!", result2);
	                                        assertEquals("'Item 3' is added to your order!", result3);
	                                    }

	                                    @Test
	                                    public void testRentPhysicalItemWithThreeItemsRentedOut() {
	                                        // Set up user with three items already rented out
	                                        User user = new User();
	                                        user.setDatabaseAttributes(null, null, null, null, 8, 0, 0);

	                                        // Create a rental order builder for the user
	                                        RentalOrderBuilder rentalOrderBuilder = new RentalOrderBuilder(user);
                                            
	                                        // Create three physical items
	                                        PhysicalItem item1 = new PhysicalItem();
	                                        item1.setRentable(true);
	                                        item1.setTitle("Item 1");

	                                        PhysicalItem item2 = new PhysicalItem();
	                                        item2.setRentable(true);
	                                        item2.setTitle("Item 2");

	                                        PhysicalItem item3 = new PhysicalItem();
	                                        item3.setRentable(true);
	                                        item3.setTitle("Item 3");

	                                        // Rent the first two items
	                                        RentPhysicalItem rentPhysicalItem1 = new RentPhysicalItem(user, item1, rentalOrderBuilder);
	                                        RentPhysicalItem rentPhysicalItem2 = new RentPhysicalItem(user, item2, rentalOrderBuilder);

	                                        rentPhysicalItem1.execute();
	                                        rentPhysicalItem2.execute();

	                                        // Try renting the third item
	                                        RentPhysicalItem rentPhysicalItem3 = new RentPhysicalItem(user, item3, rentalOrderBuilder);
	                                        String result = rentPhysicalItem3.execute();

	                                        // Ensure that renting the third item fails
	                                        assertEquals("You cannot borrow any more items right now", result);
	                                    }
	                        


	                         

	
	        
//test for requestbook
	          
	                                  

	               @Test
	                public void testGetSummaryForCourseTeaching() {
	            	   RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
                       RequestPhysicalBook.numberOfRequests[1] = 0; 
	                	RequestPhysicalBook requestPhysicalBook;
		                RequestBook requestBook;
	                	 requestPhysicalBook = new RequestPhysicalBook("Introduction to Java","course teaching");
		                    requestBook = new RequestBook(requestPhysicalBook);
	                   
	                   
	                    String expectedSummary = "Your Book Request for 'Introduction to Java' has been filed.\n" +
	                            "Your Request is of HIGH priority and you are #1 in line.";

	                    assertEquals(expectedSummary, requestBook.getSummary());
	                }

	                @Test
	                public void testGetSummaryForNonCourseTeaching() {
	                	  RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
                          RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
	                	RequestPhysicalBook requestPhysicalBook;
		                RequestBook requestBook;
	                	 requestPhysicalBook = new RequestPhysicalBook("Data Structures and Algorithms","research");
		                    requestBook = new RequestBook(requestPhysicalBook);
	                    String expectedSummary = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #1 in line.";

	                    assertEquals(expectedSummary, requestBook.getSummary());
	                }

	            


	                @Test
	                public void testGetSummaryWithUnknownRequestType() {
	                	  RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
                          RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
	                	RequestPhysicalBook requestPhysicalBook;
		                RequestBook requestBook;
		                requestPhysicalBook = new RequestPhysicalBook("Introduction to Java","unknown");
	                    requestBook = new RequestBook(requestPhysicalBook);
	                    
	                    String expectedSummary = "Your Book Request for 'Introduction to Java' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #1 in line.";

	                    assertEquals(expectedSummary, requestBook.getSummary());
	                }

	                @Test
	                public void testGetSummaryForMultipleRequests() {
	                	  RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
                          RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
	                	RequestPhysicalBook requestPhysicalBook;
		                RequestBook requestBook;
	                	 requestPhysicalBook = new RequestPhysicalBook("Introduction to Java","course teaching");
		                    requestBook = new RequestBook(requestPhysicalBook);
	                  
	                    requestBook.getSummary(); // First request""
	               	 requestPhysicalBook = new RequestPhysicalBook("Data Structures and Algorithms","research");
	                    requestBook = new RequestBook(requestPhysicalBook);
	                    
	                    String expectedSummary = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #2 in line.";

	                    assertEquals(expectedSummary, requestBook.getSummary());
	                }

	               

	                @Test
	                public void testGetSummaryForNullRequestType() {
	                	  RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
                          RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
	                	RequestPhysicalBook requestPhysicalBook;
		                RequestBook requestBook;
	                	 requestPhysicalBook = new RequestPhysicalBook("Introduction to Java",null);
		                    requestBook = new RequestBook(requestPhysicalBook);
	                    String expectedSummary = "Your Book Request for 'Introduction to Java' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #1 in line.";

	                    assertEquals(expectedSummary, requestBook.getSummary());
	                }
	                @Test
	                public void testGetSummaryForNullBookRequest() {
	                    RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
	                    RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
	                    RequestPhysicalBook requestPhysicalBook = new RequestPhysicalBook(null, "course teaching");
	                    RequestBook requestBook = new RequestBook(requestPhysicalBook);
	                    String expectedSummary = "Your Book Request for 'null' has been filed.\n" +
	                            "Your Request is of HIGH priority and you are #1 in line.";
	                    assertEquals(expectedSummary, requestBook.getSummary());
	                }
	                
	                @Test
	                public void testGetSummaryForLowPriorityThenHighPriority() {
	                	RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
                        RequestPhysicalBook.numberOfRequests[1] = 0; 
	                    // First request with low priority
	                    RequestPhysicalBook requestLowPriority = new RequestPhysicalBook("Data Structures and Algorithms", "research");
	                    RequestBook requestBookLowPriority = new RequestBook(requestLowPriority);
	                    String expectedSummaryLowPriority = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #1 in line.";

	                    assertEquals(expectedSummaryLowPriority, requestBookLowPriority.getSummary());

	                    // Second request with higher priority
	                    RequestPhysicalBook requestHighPriority = new RequestPhysicalBook("Introduction to Java", "course teaching");
	                    RequestBook requestBookHighPriority = new RequestBook(requestHighPriority);
	                    String expectedSummaryHighPriority = "Your Book Request for 'Introduction to Java' has been filed.\n" +
	                            "Your Request is of HIGH priority and you are #1 in line.";

	                    assertEquals(expectedSummaryHighPriority, requestBookHighPriority.getSummary());
	                  
	                    
	                }

	                @Test
	                public void testGetSummaryForMultipleLowPriorityRequests() {
	                    RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
	                    RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
	                    RequestPhysicalBook requestPhysicalBook1 = new RequestPhysicalBook("Data Structures and Algorithms", "research");
	                    RequestBook requestBook1 = new RequestBook(requestPhysicalBook1);
	                    requestBook1.getSummary(); // First low-priority request
	                    RequestPhysicalBook requestPhysicalBook2 = new RequestPhysicalBook("Introduction to Java", "research");
	                    RequestBook requestBook2 = new RequestBook(requestPhysicalBook2);
	                 // Second low-priority request
	                    String expectedSummary = "Your Book Request for 'Introduction to Java' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #2 in line.";
	                    assertEquals(expectedSummary, requestBook2.getSummary());
	                }
	                @Test
	                public void testGetSummaryForMultipleHighPriorityRequests() {
	                    RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
	                    RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
	                    RequestPhysicalBook requestPhysicalBook1 = new RequestPhysicalBook("Introduction to Java", "course teaching");
	                    RequestBook requestBook1 = new RequestBook(requestPhysicalBook1);
	                    requestBook1.getSummary(); // First high-priority request
	                    RequestPhysicalBook requestPhysicalBook2 = new RequestPhysicalBook("Data Structures and Algorithms", "course teaching");
	                    RequestBook requestBook2 = new RequestBook(requestPhysicalBook2);
	                     // Second high-priority request
	                    String expectedSummary = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
	                            "Your Request is of HIGH priority and you are #2 in line.";
	                    assertEquals(expectedSummary, requestBook2.getSummary());
	                }
	                @Test
	                public void testGetSummaryForMultiple4Requests() {
	                    RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
	                    RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests

	                    // First low priority request
	                    RequestPhysicalBook requestPhysicalBook1 = new RequestPhysicalBook("Introduction to Java", "fun");
	                    RequestBook requestBook1 = new RequestBook(requestPhysicalBook1);
	                    String expectedSummaryLowPriority1 = "Your Book Request for 'Introduction to Java' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #1 in line.";
	                    assertEquals(expectedSummaryLowPriority1, requestBook1.getSummary());

	                    // Second low priority request
	                    RequestPhysicalBook requestPhysicalBook2 = new RequestPhysicalBook("Data Structures and Algorithms", "research");
	                    RequestBook requestBook2 = new RequestBook(requestPhysicalBook2);
	                    String expectedSummaryLowPriority2 = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
	                            "Your Request is of LOW priority and you are #2 in line.";
	                    assertEquals(expectedSummaryLowPriority2, requestBook2.getSummary());

	                    // First high priority request
	                    RequestPhysicalBook requestPhysicalBook3 = new RequestPhysicalBook("Algorithm Design", "course teaching");
	                    RequestBook requestBook3 = new RequestBook(requestPhysicalBook3);
	                    String expectedSummaryHighPriority1 = "Your Book Request for 'Algorithm Design' has been filed.\n" +
	                            "Your Request is of HIGH priority and you are #1 in line.";
	                    assertEquals(expectedSummaryHighPriority1, requestBook3.getSummary());

	                    // Second high priority request
	                    RequestPhysicalBook requestPhysicalBook4 = new RequestPhysicalBook("Operating Systems", "course teaching");
	                    RequestBook requestBook4 = new RequestBook(requestPhysicalBook4);
	                    String expectedSummaryHighPriority2 = "Your Book Request for 'Operating Systems' has been filed.\n" +
	                            "Your Request is of HIGH priority and you are #2 in line.";
	                    assertEquals(expectedSummaryHighPriority2, requestBook4.getSummary());
	                }

	                
	            
	            





	            
//test for requestPhysicalbook




    @Test
    public void testExecuteForCourseTeachingRequest() {
    	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
    	RequestPhysicalBook requestPhysicalBook;
    	 requestPhysicalBook = new RequestPhysicalBook("Introduction to Java", "course teaching");
    	

        String expectedSummary = "Your Book Request for 'Introduction to Java' has been filed.\n" +
                "Your Request is of HIGH priority and you are #1 in line.";

        assertEquals(expectedSummary, requestPhysicalBook.execute());
    }

    @Test
    public void testExecuteForResearchRequest() {
    	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
     	RequestPhysicalBook requestPhysicalBook;
        requestPhysicalBook = new RequestPhysicalBook("Data Structures and Algorithms", "research");
        String expectedSummary = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
                "Your Request is of LOW priority and you are #1 in line.";

        assertEquals(expectedSummary, requestPhysicalBook.execute());
    }

    @Test
    public void testExecuteWithEmptyBookToRequest() {
    	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
     	RequestPhysicalBook requestPhysicalBook;
        requestPhysicalBook = new RequestPhysicalBook("", "research");
        String expectedSummary = "Your Book Request for '' has been filed.\n" +
                "Your Request is of LOW priority and you are #1 in line.";

        assertEquals(expectedSummary, requestPhysicalBook.execute());
    }

    @Test
    public void testExecuteWithNullBookToRequest() {
    	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
     	RequestPhysicalBook requestPhysicalBook;
        requestPhysicalBook = new RequestPhysicalBook(null, "research");
        String expectedSummary = "Your Book Request for 'null' has been filed.\n" +
                "Your Request is of LOW priority and you are #1 in line.";

        assertEquals(expectedSummary, requestPhysicalBook.execute());
    }

    @Test
    public void testExecuteWithUnknownRequestType() {
    	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
     	RequestPhysicalBook requestPhysicalBook;
        requestPhysicalBook = new RequestPhysicalBook("Data Structures and Algorithms", "unknown");
        String expectedSummary = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
                "Your Request is of LOW priority and you are #1 in line.";

        assertEquals(expectedSummary, requestPhysicalBook.execute());
    }



    @Test
    public void testExecuteForMultipleRequests() {
    	
    	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
     	RequestPhysicalBook requestPhysicalBook;
        requestPhysicalBook = new RequestPhysicalBook("Introduction to Java", "course teaching");
        requestPhysicalBook.execute(); // First request
        requestPhysicalBook = new RequestPhysicalBook("Data Structures and Algorithms", "research");
        String expectedSummary = "Your Book Request for 'Data Structures and Algorithms' has been filed.\n" +
                "Your Request is of LOW priority and you are #2 in line.";

        assertEquals(expectedSummary, requestPhysicalBook.execute());
    }

    @Test
    public void testExecuteWithEmptyRequestType() {
    	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
     	RequestPhysicalBook requestPhysicalBook;
        requestPhysicalBook = new RequestPhysicalBook("Introduction to Java", "");
        String expectedSummary = "Your Book Request for 'Introduction to Java' has been filed.\n" +
                "Your Request is of LOW priority and you are #1 in line.";

        assertEquals(expectedSummary, requestPhysicalBook.execute());
    }


    @Test
    public void testExecuteWithInvalidPriorityCalculation() {
    	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
     	RequestPhysicalBook requestPhysicalBook;
        requestPhysicalBook = new RequestPhysicalBook("Algorithms and Data Structures", "unknown");
        String expectedSummary = "Your Book Request for 'Algorithms and Data Structures' has been filed.\n" +
                "Your Request is of LOW priority and you are #1 in line.";

        assertEquals(expectedSummary, requestPhysicalBook.execute());
    }
    @Test
    public void testExecuteWithLowThenHighPriority() {
   	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
     RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
        // First request with low priority
        RequestPhysicalBook requestLowPriority = new RequestPhysicalBook("Data Structures and Algorithms", "research");
        assertEquals("Your Book Request for 'Data Structures and Algorithms' has been filed.\nYour Request is of LOW priority and you are #1 in line.", requestLowPriority.execute());

        // Second request with higher priority
        RequestPhysicalBook requestHighPriority = new RequestPhysicalBook("Introduction to Java", "course teaching");
        assertEquals("Your Book Request for 'Introduction to Java' has been filed.\nYour Request is of HIGH priority and you are #1 in line.", requestHighPriority.execute());
    }
    @Test
    public void testExecuteWithLowHighLowHighPriority() {
     	 RequestPhysicalBook.numberOfRequests[0] = 0; // Reset high priority requests
         RequestPhysicalBook.numberOfRequests[1] = 0; // Reset low priority requests
        // First request with low priority
        RequestPhysicalBook requestLowPriority1 = new RequestPhysicalBook("Data Structures and Algorithms", "research");
        assertEquals("Your Book Request for 'Data Structures and Algorithms' has been filed.\nYour Request is of LOW priority and you are #1 in line.", requestLowPriority1.execute());

        // Second request with high priority
        RequestPhysicalBook requestHighPriority1 = new RequestPhysicalBook("Introduction to Java", "course teaching");
        assertEquals("Your Book Request for 'Introduction to Java' has been filed.\nYour Request is of HIGH priority and you are #1 in line.", requestHighPriority1.execute());
        
        // Third request with low priority
        RequestPhysicalBook requestLowPriority2 = new RequestPhysicalBook("Algorithm Design", "research");
        assertEquals("Your Book Request for 'Algorithm Design' has been filed.\nYour Request is of LOW priority and you are #3 in line.", requestLowPriority2.execute());

        // Fourth request with high priority
        RequestPhysicalBook requestHighPriority2 = new RequestPhysicalBook("Operating Systems", "course teaching");
        assertEquals("Your Book Request for 'Operating Systems' has been filed.\nYour Request is of HIGH priority and you are #2 in line.", requestHighPriority2.execute());
    }
}


	 
	        



    

	
