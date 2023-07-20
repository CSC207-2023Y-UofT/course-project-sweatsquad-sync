# Project Design Outline
## Revised Outline:
### Frameworks and Drivers
_Accepts input and displays output._
- UI.java (should cover 2.1.1, 3.1.1, 4.1.1)
    - [User Interface] DefaultUI
    - [User Interface] AdminUI (5.1.3)
    - [User Interface] InstructorUI (6.1.3)
		
- Database.java
    - [Database] UserData (5.1.1)
    - [Database] RoomData (1.1.1)
    - [Database] ClassData (6.1.2, 6.1.3, 6.1.4, 6.1.7)

### Interface Adapters 
_Translates betwwwn outer and inner layers + prepares data display in UI._
- Controller.java
    - [Controller] UserController
    - [Controller] RoomController (1.1.1, 5.1.3)
    - [Controller] ClassController (6.1.3, 6.1.4, 6.1.7)

- Presenter.java
    - [Presenter] UserPresenter
    - [Presenter] RoomPresenter (1.1.1)
    - [Presenter] ClassPresenter (6.1.1, 6.1.2, 6.1.6)

### Application Business Rules
_Manages the application use cases._
- UseCase.java
    - [UseCase] UserUseCase (2.1.1, 3.1.1, 4.1.1, 5.1.1)
    - [UseCase] RoomUseCase (5.1.3)
    - [UseCase] ClassUseCase (6.1.2, 6.1.3, 6.1.4, 6.1.7)

### Enterprise Business Rules
_Core business entities._
- Gym.java
    - [Entity] User
    - [Entity] Room (1.1.1)
    - [Entity] Class (6.1.2, 6.1.3, 6.1.4, 6.1.7)
 
*Note: _All dependencies are expected to point inward._

*Note: _Each layer is expected to only interact with adjacent layers, layers cannot be skipped._


## Former Feature Centric Design

### User, RegisteredUser, Instructor, and GymAdmin Classes
##### ƒoundational classes for user interactions.
- #### User Class 
##### Contains methods that store user information (username, passcode, etc.) and relevant methods for login.

[Feature 1.1.1] User Attributes:

users have corresponding username and passcode attributes that can be used to login / access the user

[Feature 1.1.2] Setting Username for User Object:

setUsername method (String input) allows for a user to set their account username, given that it doesn’t exist already for a different user

[Feature 1.1.3] Getting Username for User Object:

getUsername method (no input) returns the username associated with a User object

[Feature 1.1.4] Setting Passcode for User Object:

setUsername method (String input) allows for a user to set their account passcode with certain constraints (8 character length minimum)

[Feature 1.1.5]  Getting Passcode for User Object:

getPasscode method (no input) returns the passcode associated with a User object


- #### RegisteredUser Class (extends User class)
##### Contains methods to register for classes and to view personal schedules.

[Feature 1.2.1] RegisteredUser Attributes:

inherits attributes from User class and has an additional list to store Workout classes that the RegisteredUser is enrolled in

[Feature 1.2.2] Registering a RegisteredUser for a Class:

registerClass method (WorkoutClass input) registers a RegisteredUser for a class that has space

[Feature 1.2.3] View Schedule for User Object:

viewSchedule method (no input) returns information for all classes that the RegisteredUser is enrolled in


- #### Instructor Class (extends RegisteredUser class)
##### Has additional responsibilities than a RegisteredUser -namely tracking certifications and classes being taught.

[Feature 1.3.1] Instructor Attributes:

inherits attributes (and methods) from User class and has 2 additional lists, one to store Workout classes that the Instructor teaches, and another to store certificates that the instructor currently holds

[Feature 1.3.2] Adding Certificates for Instructor Object:

addCertificate method (String input) allows for certificates to be added to an instructor’s list of certificates

[Feature 1.3.3] Removing Certificates for Instructor Object:

delCertificate method (String input) allows for certificates to be removed from an instructor’s list of certificates

[Feature 1.3.4] Getting Certificates for Instructor Object:

getCertificate method (no input) returns list of certificates that an Instructor currently holds

[Feature 1.3.4] View Schedule for Instructor Object:

viewSchedule method (no input) is overridden to return information for all classes that the Instructor is teaching


- #### GymAdmin Class (extends RegisteredUser class)
##### Has additional methods than a RegisteredUser to create and schedule classes, add new instructors + rooms, etc.

[Feature 1.4.1] GymAdmin Attributes:
inherits attributes (and methods) from User class and would have access to additional methods by means of the GUI that the Instructor and RegisteredUser objects wouldn’t (e.g., createClass, scheduleClass, addInstructor, addRoom)

### Workout Class
##### Class that manages the gym’s offerings / schedule.
- #### Workout Class
##### A data structure that holds class information, accessible by methods that get / set class details, and has certificate validation

[Feature 2.1.1] Workout Attributes:

workouts have corresponding DateTime objects, name attributes, list of certificate strings required to teach the class, a maximum capacity, an instructor list, and a room assignment.

[Feature 2.1.2] Set Timing for Workout Object:

setDateTime method (DateTime object input) allows for DateTime objects to be associated with a Workout object

[Feature 2.1.3] Get Timing for Workout Object:

getDateTime method (DateTime object input) returns DateTime object that is associated with a Workout object

[Feature 2.1.4] Set Name for Workout Object:

setName method (String input) allows for a Workout to be assigned a string

[Feature 2.1.5] Get Name for Workout Object:

getName method (String input) returns the name string attribute associated with a Workout
		
[Feature 2.1.6] Add Requirement for Workout Object:

requireCert method (String input) adds a certificate to the list associated with a Workout that is required by an instructor to teach the class

[Feature 2.1.7] Remove Requirement for Workout Object:

deleteCert method (String input) deletes an existing certificate from the list associated with a Workout (the one that outlines certificates an instructor is required to possess to teach the class)

[Feature 2.1.8] Get Certificates for Workout Object:

getCerts method (no input) returns list of certificates required by an instructor to teach the Workout

[Feature 2.1.9] Validate Certificates for Workout Object:

validateCert method (Instructor object input) returns boolean indicating whether or not the Instructor object’s certificates are sufficient to teach the class (contains all certificate strings in the Workout object’s certificate attributes)


### DateTime Class
##### Class that helps orient the gym’s schedule in terms of time.
- #### DateTime Class
##### Contains methods to manage dates and times for a gym, checks the week of a given date, and handle all other datetime related operations.

[Feature 3.1.1] DateTime Attributes:

DateTime objects have corresponding date and time attributes. Dates are stored as calendar dates, and the time is stored as a 24-hour time in 30-minute intervals

[Feature 3.1.2] Set Date for DateTime Object:

setDate method (Date input) allows for the date to be set for a DateTime object

[Feature 3.1.3] Get Date for DateTime Object:

getDate method (no input) returns the date associated with a DateTime object

[Feature 3.1.4] Set Time for DateTime Object:

setTime method (Time input) lets the time be set for a DateTime object, with the constraint that it is set in 30-minute intervals (e.g., 7:00 PM, 8:30 PM, 5:30 AM, 12:00 AM)

[Feature 3.1.5] Getting Time for DateTime Object:

getTime method (no input) returns time associated with a DateTime object

[Feature 3.1.6] Compare DateTime for DateTime Object:

compareDateTime method (DateTime input) returns whether the input DateTime is before, after, or equal to the DateTime object (for scheduling and sorting tasks)

[Feature 3.1.7] Time Difference Between DateTime Objects:

getTimeDifference method (DateTime input) returns the time difference in 30-minute intervals between the current DateTime object and another DateTime object, (for checking the duration of classes and breaks)

[Feature 3.1.8] Convert to String for DateTime Object:

toString method (no input) returns a string representation of the DateTime object, (for printing and displaying schedules)

[Feature 3.1.9] Add Time to DateTime Object:

addTime method (int input) allows for a specific number of 30-minute intervals to be added to the DateTime object, (for scheduling class end times based on class start times and class duration)

[Feature 3.1.10] Subtract Time from DateTime Object:

subtractTime method (int input) allows for a specific number of 30-minute intervals to be subtracted from the DateTime object, (to prevent conflicting classes)


### Gym Class
##### Class that representing the gym physical location and a schedule.
- #### Gym Class
##### A comprehensive class encapsulating the physical features and the main schedule record.

[Feature 4.1.1] Gym Attributes
   - Instructors: refer to Instructor objects
   - Classes: refer to Workout objects
   - Rooms -created as their own aggregate class (composition)
   - Schedule -created as their own class

[Feature 4.1.2] Creating a class:

createClass method (Instructor input) adds an instructor pursuant to Class’ constructor

[Feature 4.1.3] Deleting a class:

deleteClass method (no input) deletes a class from the record

[Feature 4.1.4] Scheduling a class:

scheduleClass method (DateTime input) schedules schedule a class at a specific time

[Feature 4.1.5] Adding an user:

addUser method (User input) takes a user and adds it to the record

[Feature 4.1.6] Deleting an user:

deleteUser method (User input) deletes a user from the record and removes any lingering references in the record

[Feature 4.1.7] Adding a instructor:

addInstructor method (Instructor input) takes an instructor pursuant to Instructor’s constructor and adds it to the record

[Feature 4.1.8] Deleting an instructor:

deleteInstructor method (Instructor input) deletes an instructor from the record and removes any lingering references in the rest of the record

[Feature 4.1.9] Adding a room:

addRoom method adds a room specifying constraints (max # of people)

[Feature 4.1.10] Deleting a room:

deleteRoom method deletes a room from the record and removes any lingering references in the rest of the record (delete classes that are scheduled in this room)

[Feature 4.1.11] Serialize + Save:

serializeAndSave method (String input) serializes the current state of the Gym object and saves it to the specified file path (input), allowing the current state of user accounts to be stored persistently and retrieved later

[Feature 4.1.12] Deserialize + Load:

deserializeAndLoad method (String input) loads the serialized Gym object from the specified file path (input), allowing a previously stored state of user accounts to be loaded into the system


### UserAccountManager Class
##### Class that manipulates information related to user accounts.
- #### UserAccountManager Class
  ##### Creation, storage, and management of all user account interactions in the SweatSquad Sync system.

[Feature 5.1.1] UserAccountManager Attributes:

The UserAccountManager holds a list of all users (including instances of User, RegisteredUser, Instructor, and GymAdmin).

[Feature 5.1.2] Creating a User Account:

createUserAccount method (String input ×2; user + pass) creates a new User object + adds it to the list of users (and validates the username to ensure it doesn't already exist)

[Feature 5.1.3] Creating a RegisteredUser Account:

createRegisteredUserAccount method (String input ×2; user + pass) creates a new User object + adds it to the list of users (and validates the username to ensure it doesn't already exist)

[Feature 5.1.4] Removing a User Account:

removeUserAccount method (User input) removes the specified User object from the list of users

[Feature 5.1.5] Checking User Authentication:

checkAuthentication method (String input ×2; user + pass) verifies the username and passcode match a stored User object in the list of users

[Feature 5.1.6] Getting a User Account:

getUserAccount method (String input) returns the User object corresponding to the input username

[Feature 5.1.7] Toggling User to Registered User:

toggleToRegisteredUser method (User user) changes a User object to a RegisteredUser object (this method is only accessed by a GymAdmin object)

[Feature 5.1.8] Toggling RegisteredUser to User:

toggleToUser method (RegisteredUser input) changes a RegisteredUser object to a User object (this method can only be accessed by a GymAdmin object)

[Feature 5.1.9] Serialize and Save:

serializeAndSave method (String input) serializes the current state of the UserAccountManager object and saves it to the specified file path (input), allowing the current state of user accounts to be stored persistently and retrieved later

[Feature 5.1.10] Deserialize and Load:

deserializeAndLoad method (String input) loads the serialized UserAccountManager object from the specified file path (input), allowing a previously stored state of user accounts to be loaded into the system

### GUI Class
##### Class responsible for presenting and interaction.
- #### GUI Class
  ##### The main interface through which users interact with the system.

[Feature 6.1.1] GUI Attributes:

holds references to the Gym object and UserAccountManager object, which allows the GUI to directly interact with and manipulate objects as appropriate

[Feature 6.1.2] User Authentication:

authenticateUser method (String input ×2; user + pass) interacts with UserAccountManager to check user authentication, receiving username and passcode input from the user, which is then passed to the checkAuthentication method in UserAccountManager for further processing (authentication)

[Feature 6.1.3] User Class Registration:

registerUserForClass method (RegisteredUser input and WorkoutClass input) handles the process of a registered user signing up for a class, where input from the user about which class they want to join, interacts with the RegisteredUser, WorkoutClass, and Gym objects to complete the registration

[Feature 6.1.4] Display User Schedule:

displayUserSchedule method (User input) creates a view of a user's schedule by retrieving the schedule from the RegisteredUser/Instructor object and presenting it in a user-friendly manner

[Feature 6.1.5] Display Workout Class Schedule:

displayClassSchedule method (no input) creates a view for the entire workout class schedule by receiving class information from the Gym object + presenting it in a user-friendly manner

[Feature 6.1.6] Update Gym Features:

updateGymFeatures method (GymAdmin input) provides GymAdmin with the ability to update gym features like adding new classes, instructors or rooms. This method provides an interface for the GymAdmin to interact with the Gym object

[Feature 6.1.7] Handle User Account Creation:

handleUserAccountCreation method (String input ×2; user + pass) provides an interface for users to create their own accounts. It interacts with the UserAccountManager to create new user accounts based on input received.

[Feature 6.1.8] Handle User Account Deletion:

handleUserAccountDeletion method (String input) provides an interface for the GymAdmin to delete a user account. It interacts with the UserAccountManager to delete user accounts based on input (username) received.

[Feature 6.1.9] Toggle User Status:

toggleUserStatus method (String input) provides an interface for the GymAdmin to toggle a user between User and RegisteredUser status (interacts with the UserAccountManager to toggle –delete and recreate– different users based on input received)
