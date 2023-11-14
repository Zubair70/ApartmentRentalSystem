# ApartmentRentalSystem
Write a program, that will help developer manage multiple blocks containing finite number of apartments for rent. Each apartment or closed parking space might be rented by any number of people about whom information should be stored (model.Person class objects). But the first person who starts renting the apartment is always the person responsible for the rental fees.

As a part of the apartment rental it is possible (only if person is renting an apartment) to additionally rent a closed parking space, where you can store vehicles, but also various items. Each apartment and parking space has information about its usable area and a unique number thanks to which we can clearly define an object symbolizing an apartment or a parking space.

The tenant can put in and out people of the apartment freely and, if he has parking space, he can put in and take out objects. Each person can have multiple apartments and parking spaces rented as long as the total number of apartments and parking spaces rented by a given person (tenant) is not greater than 5. Each apartment or parking space can have only one tenant at a time. 

It should be ensured that the apartments and parking spaces are identifiers are unique and created automatically when creating an object of the type Apartment and Parking Space. The size of the usable area in the case of both cases should be provided when creating the room object. There are two ways to indicate the size:

1. by specifying the volume in cubic meters 
2. by specifying the dimensions of the room in the form of length, width and height of the room (for simplicity we assume that the room is a cuboid. In this approach, the usable volume of the room is calculated when creating the object based on the provided values)

Apartments and parking spaces also have start and end date of rental. If rental date has expired, a letter is sent (an obejt of the TenantLetter type), which is saved to an object of the model.Person class defining a specific tenant.

The task should also implement a time-passing mechanism via threads. The thread should move the date forward 1 day every 5 seconds, simulating the passage of time. At the same time, rental issues should be checked every 10 seconds to see whether all rooms are still being rented or whether the room has already been rented. Ensure threads are synchronized.

If the lease is renewed or the lease is canceled by the tenant, the debt letter is removed from the persons object.

If the tenant does not renew the lease within 30 days. The room should be cleared, which lease was ended and the TenantLetter stays in the model.Person object. In the case of an apartment, we evict the people living in the apartment.

In the case of a parking space, we first check whether there is a vehicle in the parking space. If so, it is sold due to the expedited decision of the bailiff (from the point of view of the program, we remove the object from the room). If any items are stored on site, they are subject to disposal.

The person object has more than the information mentioned above, such as name, surname, (String) PESEL number, address, date of birth.

 If a person with more than three debts (at least 3 TenantLetter objects) wants to start the lease, there should be a ProblematicTenantException exception should be thrown with the message - "[NAME SURNAME] has rented rooms: [LIST OF ROOMS RENTED BEFORE]"

Every item and vehicle has written information about its name and area it occupies. model.Area it occupies can be provided in two ways, as in case of room objects.

Vehicles are divided into different model.types:
- off-road car
- city car
- boat
- motorcycle
- amphibious car

Each vehicle, apart from its name and surface, must have features characteristic of a given vehicle type (e.g. engine capacity, vehicle type, engine type, etc.). Characteristic features may be repeated for each type of vehicle, but at least one should be different from other vehicles.

When placing any object or vehicle into a room, we must make sure that the room is able to accommodate this object. If this is not the case, a TooManyThingsException is thrown with the message "Remove some old items to insert a new item"

The status of people living in the estate along with all data regarding the person, rented rooms, items, etc. must be saved to the file after selecting the appropriate function from the menu. Information saved in the file should be easily understood by human and according to these rules:

- rooms sorted ascending according to size of the room
- contents of the rooms should be sorted descending according to the size of the object. if its the same then according to the name.

Operations of the program:

in main method create residential area with at least 10 rooms of different model.types and sizes, and at least 5 people with pre-assigned rentals and items placed in parking spaces.

after starting the program, the user should be able to invoke all the functionalities listed in the project via the command console.

-> Also it should include inheritance, collections, interfaces or abstract classes, lambda expressions, generics

Saving to file should be done by using one of: InputStream/OutputStream, Reader/Writer, FileInputStream/FileOutputStream, ObjectInputStream/ObjectOutputStream, BufferedReader/BufferedWriter, PrintWriter

