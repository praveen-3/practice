Design a Simple Elevator System

Write code for low level design of a simple elevator management system consisting of multiple lifts.
All the lifts are in the same building which has multiple floors.

Lifts are numbered 0 to lifts-1 and floors are numbered 0 to floors -1.
Capacity/number of people that each lift can carry is same, liftsCapacity

Assumptions
- For simplicity lets assume that each lift takes exactly one second (see tick() method below) to go to next floor while coming up or going down.
Also, when a lift stops on a floor, then time taken for one or more people to come in or go out of lift is zero seconds (or 0 tick()).

- Also like real world people can request a lift to their floor but may not ultimately enter it when it reaches their floor.
It may also happen that even though only one person has made a request for the lift but eventually more people enter the lift. e.g. only you pressed up/down button but your friends also enter with you.

- However, one can't change move direction of lift i.e. if it has been summoned to go up then you can't press lower floor button inside lift.

- Also, each person entering the lift presses the destination floor button exactly once. This is done to accurately keep track of number of people inside lift.

You have to code below methods.

METHOD : void init(int floors, int lifts, int liftsCapacity, Helper11 helper) :
- Initialize/reinitialize the elevator system and reset all your instance variables.
- liftsCapacity is the maximum number of people a lift can carry at any time
- use helper.print() and helper.println() to print logs else logs won't be visible to you.

METHOD : int requestLift(int startFloor, char direction) :
- This method is called when on any floor, users push
  either up or down button outside any lift.
- startFloor is the floor user is currently at.
- direction values: 'U'= go up, 'D'= go down
- Your code should return lift index from 0 to lifts-1
or -1 in case none of the lifts can be assigned to user.

The Lift assigned to request must be both ELIGIBLE and MOST OPTIMAL .

ELIGIBLE : Below are the reasons as to when a lift can't be assigned to a request. These are intuitive and are followed by most elevator systems.

Case 1: Lift has already passed the user e.g requestLift(2, 'U) i.e. user is requesting to go up from floor 2 and lift is also going up and is at floor 4 right now.
Case 2: Going down: requestLift(18,'D') i.e. User has requested to go down from floor 18 and lift is also going down and is at floor 16 right now.
In both above cases given lift can't be assigned to requests.

Case 3: There are already requests in opposite direction and assigning lift to new request will increase wait time for people waiting in opposite direction.
e.g. lift is at floor 4, empty and is going up to pick first passenger who is at floor 8 and wants to go down, another person pushes the down button on floor 10.
Although lift's travel direction is same here but still it can't be assigned the second request because going two floors up further will increase travel time for the existing request for person at floor 8.
However, if there is a requestLift(4, 'D') or requestLift(6, 'D') then lift will be considered eligible because they are along the way of existing incoming request at floor 8 and stop time on a floor is assumed 0 seconds.
Case 4: Similar to above case but now lift is at floor 6 and going down to pick first passenger who is at floor 2 and will go Up. Lift will be not eligible for requestLift(1,'U') because it will have to further go down 1 floor .
While it will eligible for requestLift(5,'U') or requestLift(4,'U')

Case 5: If lift is serving/going to serve requests in opposite direction .
Suppose a lift is at floor 4 going up with outgoing requests for floor 8 and 10. This lift can't be assigned to request requestLift(6,'D') or requestLift(12, 'D').
Same goes for lifts serving requests in down direction.
Case 6: A special case of above case, lets assume a lift at floor 6 is going up to pick first passenger who will ultimately go down. Let's assume lift is assigned to requests requestLift(12, 'D') and requestLift(10, 'D'). Now this lift can't be assigned to requestLift(6,'U') because even though lift is going in same direction but the requests assigned to it are in opposite direction i.e. down.

MOST OPTIMAL : Means the lift assigned must take minimum time as compared to other lifts to reach startFloor based on the requests it is currently serving,
If there are multiple such lifts, return the lift at smallest index (0 to lifts-1).

Examples of calculating time:
request(10, 'U') , lift is at floor 8 moving up. Time taken = 2 seconds
request(10, 'D') , lift is at floor 12 moving down. Time taken = 2 seconds
request(10, 'U') , lift is at floor 8 Idle. Time taken = 2 seconds
request(10, 'D') , lift is at floor 8 moving up. Existing requests that lift is assigned to: requestLift(16,'D') and requestLift(12,'D')
Time taken = 16-8+16-10=14 seconds i.e. lift will go up to pick request at 16th floor and then while coming back it will pick both 12th floor and finally 10th floor.
request(6, 'U') , lift is at floor 8 moving down. Existing requests that lift is assigned to: requestLift(7,'U') and requestLift(3,'U')
Time taken = 8-3+6-3=8 seconds i.e. lift will go down to pick request at 3rd floor first and then while coming back up it will pick both 6th floor and 7th floor.


METHOD : void pressFloorButtonInLift(int liftIndex, int floor) :
- This method will be called when user presses destination floor button after entering inside lift.
- input will always be valid for this method i.e. lift with liftIndex will be on that floor and will have capacity for at least 1 person
- Please see Assumptions section at the begining for details.

METHOD : String getLiftState(int liftIndex) :
- liftsIndex will always be valid i.e. between 0 and lifts-1
- returns a string representing current floor, direction and number of people inside lift.

currentFloor-direction-peopleCount
0<= currentFloor < this.floors
direction : U for up, D for down, I for idle

"4-U-10": lift is at floor 4, going UP and has 10 people inside,
"12-D-2": lift is at floor 12, going DOWN and has 2 people inside,
"0-I-0": lift is at floor 0, standing IDLE and has no one inside

METHOD : tick() :
This method is called every second so that you can appropriately update lift states
use this method to track time/passage of each second rather than java.util.Date().time

Note :
You can practice this question in Java or Python
This question will be tested in a SINGLE THREADED environment.
All Lifts will be IDLE at ground floor i.e. 0th floor in the beginning.
2<= lifts <=100
2<= floors <=200
1<=liftsCapacity<=10

Input Example :
Below is a sequence of method calls to help you understand better.
init(floors = 6, lifts=2, liftsCapacity = 2)
requestLift(floor = 0, direction = 'U') returns lift 0
requestLift(floor = 5 direction = 'D') returns lift 1
pressFloorButtonInLift(liftIndex=0, floorIndex=4)
tick()
getLiftState(liftIndex = 0) returns 1-U-1 i.e. Lift 0 is at floor 1, going Up, has 1 people inside.
getLiftState(liftIndex = 1) returns 1-U-0 i.e. Lift 1 is at floor 1, going Up, has 0 people inside.
requestLift(floor = 3, direction = 'D') returns lift 1
requestLift(floor = 6, direction = 'D') returns lift -1
requestLift(floor = 0, direction = 'U') returns lift -1
tick()
tick()
getLiftState(liftIndex = 0) returns 3-U-1 i.e. Lift 0 is at floor 3, going Up, has 1 people inside.
getLiftState(liftIndex = 1) returns 3-U-0 i.e. Lift 1 is at floor 3, going Up, has 0 people inside.
tick()
getLiftState(liftIndex = 0) returns 4-I-0 i.e. Lift 0 is at floor 4, standing Idle, has 0 people inside.
getLiftState(liftIndex = 1) returns 4-U-0 i.e. Lift 1 is at floor 4, going Up, has 0 people inside.
Lift 1 didn't picked up requestLift(floor = 3, direction = 'D') even though it passed 3rd floor.
First it will go to 5th floor for the first request and then while coming it will stop on 3rd floor.