# Java Library Management System  
A GUI-based Java Application designed to manage all kinds of University clients, including students, faculty members, non-faculty staff, and visitors. Amongst other things, the application allows users to register, rent physical and online items, receive recommendations, and manage courses The system is currently simulated using Csv/Excel files for data storage, with plans to transition to a DBMS for scalability.   

- [Process](#💭-Process)
- [Features](#👩🏻‍💻-Features)
- [Installation](#⚙️-Installation-&-Running)
- [Improvements](#-improvements)
- [Authors](#authors)

### 💭 Process   
In the initial phase (Design), we created detailed diagrams including use case, sequence, activity, and class diagrams to outline the structure and behavior of our Library Management App. These diagrams helped us visualize the flow of the application and identify key components.   

We incorporated six design patterns (Singleton, Factory, Command, Observer, Iterator, and Builder) into our application to ensure scalability, maintainability, and flexibility. Each pattern was selected to address specific challenges in our application architecture.   
Using Java and JavaSwing for the GUI, we implemented the YorkU Library Management Java App based on our design specifications. We also wrote approximately 500 JUnit Tests to ensure the reliability and functionality of our application.   


### 👩🏻‍💻 Features 
- **User Registration:** Clients can register with a unique email and strong password, with further validation for students, faculty, and non-faculty staff.
  
- **Item Rental:** Registered users can rent physical items, open online books, and subscribe to newsletters, with penalties for overdue items.
  
- **Rental Management:** Upon login, users receive warnings for overdue returns and can view rented items.
  
- **Newsletter Subscription:** Users can subscribe to and read paid newsletters like the NY Times within the app, with the ability to cancel subscriptions.
  
- **Book Search and Recommendations:** Users can search for books and receive recommendations for similar titles.
  
- **Faculty Course Management:** Faculty users can track courses taught and receive notifications for new textbook editions.
  
- **Virtual Textbook Copies:** For students, virtual textbook copies are available for the duration of a course.
  
- **Book Request System:** Users can request new books (system uses a prioritization scheme).
  
- **Discounted Purchases:** The system reflects any special agreements with publishers and allows discounted purchases, with payment options like debit, credit, and mobile wallet.
  
- **Database Storage:** System data is stored in a database, simulated using Csv/Excel files.


### ⚙️ Installation & Running 
Instructions for installing and running the project   

### ✨ Improvements  <a name="-improvements"></a>
To accomodate larger volumes of data, our data could be moved to a dedicated Database Management System (DBMS) in the future. This transition will improve performance, provide better data organization, and enhance data security. 

### Authors   
Aiden Ruivo, Funmbi Akande, Sukaina Habib, Yasmine Thandi, Adeola Omojola
