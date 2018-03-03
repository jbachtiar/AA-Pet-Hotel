Pet Hotel Simulation

Instructions: 
- Key in the no. of Admin/s
- Key in the no. of Worker/s

Hit 'Enter' in order to start the Day. 
Hit 'Enter' also whenever stated in the program to start the next day's task. 

Explanation: 

Our Pet Hotel is the best pet hotel that any pet would dream of staying in. Our hotel has 10 rooms in total and each room can hold up to 10 dogs. Every room in the hotel can only have dogs of the same size. Every size has a certain of rooms to it. 

(i.e.
Size 	-	Room IDs 
XS 	- 	1, 2, 3
 S 	- 	4, 5, 6, 7
 M 	- 	8, 9
 L 	- 	10)

For the sake of our simulation, we only allow dogs to check in and out our pet hotel. 

The simulation starts off with the user keying in the no. of Admins and the no. of Workers for the Pet Hotel. 
The Admin/s (AdminThread/s) will then start reading a list of the dogs that are checking into the pet hotel for Day 1 in the form of a csv file (e.g. "Dog_Entries_1.csv"). This file will then be passed into the program to process. The Admin/s (AdminThread/s) will process the dogs and convert each line in the csv file into a Dog object. They will then be allocated into the rooms based on its size as well as the availability of the room for that dog's size. 

Once the allocation has been finished, the Admin/s (AdminThread/s) has finished their job for the day. 

Whilst the Admin/s (AdminThread/s) are allocating the dogs into their respective rooms, the Worker/s (WorkerThread/s) are also starting to groom the dogs. They can only start once there are dogs being checked in into our hotel. They will start checking from the Room ID: 1 all the way to Room ID: 10. Each Worker is only allowed to groom 1 dog at a time. They also can only access the room 1 at a time as the door is too small to allow more than 1 worker to enter the room. 

Once the Worker/s (WorkerThread/s) has finished grooming the dog, they will place the dog back into the room and start finding the next ungroomed dog in the hotel. The Worker/s (WorkerThread/s) will finish their work only when all the dogs in the hotel has been groomed or when the day is over. 

(note: 1 day in pet hotel = 1 minute) 

Once the day has ended, a report will be generated showing the duration of the day which should be 1 minute. However, there could be a case whereby the time that the thread enters the loop, the time is like 59s which is still within the 1min. Thus, it will only come out of the loop once it goes through all the code in the loop, which meant that the ending time would be over 1 minute. 

Checks are also done before the user is allowed to move on to start off the 2nd day. If there are discreptancies between what is supposed to be reduced (in terms of inventory) and the number of dogs that should be groomed and what is actually groomed and its current inventory, an error will then be reported and then it will start printing out all the discreptancies between the expected and the actual and then finally it will terminate the program. However, it is able to complete the day's tasks then the program will allow you to start the next day's work or orders. 