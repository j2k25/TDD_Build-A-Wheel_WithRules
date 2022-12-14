# TDD_Build-A-Wheel_WithRules
Test Driven Development using Design Principles and Patterns #4


You're asked to create a GUI application that will help design a bicycle wheel. Your application will allow the user to start with a 
preset wheel,of some size, with a select number of spokes. The user can add and remove spokes and change various properties.
There are some restrictions:

1. At least eight spokes are required.
2. Spokes have to be spread out evenly.
3. If the hub size is changed, the spokes size changes accordingly.
4. If the wheel size is changed, the spokes size changes accordingly.
5. Diameter of the hub can't be bigger than one forth the wheel's diameter.

The program will save the design in a file and can reopen it at a later time.

After you complete the code, answer the following questions:

What design principles did you use in the program?

DRY, YAGNI, OPACITY:
Throughout the project, we used the design principle DRY whenever there is opportunity for extracting repeated processes into methods.
At points when we were thinking too far ahead, we encountered the YAGNI principle such as when we considered creating a rim class. We
applied OPACITY constantly when giving variable names that tell a story. 

In the last hours, we began to discover ways to realize SRP in our logic. By making Hub an abstract class, we were finally able to use
its functionality without it being dependent on Wheel. This revealed a path to extend the wheel allowing it to have other properties. 

DIP:
For example, following the DIP principle, we can make Hub and Spoke implement an interface called WheelComponent. WheelComponent does
nothing but setting and providing its property value. If we decide to add another property called Tire, we would simple make Tire
implement WheelComponent.

In addition, working on our UI also gave us many opportunities to use design principles and patterns that will be listed below.


SRP, OCP:
The MemoryManager and WheelManager play a role in SRP and OCP. Although the WheelManager seems to "load" a wheel object from a saved
design and to "extract" a wheel to a saving format, WheelManager should not directly handle with file reading and writing process (SRP).
By delegating the "saveToMemory" and "readFromMemory" tasks to the MemoryManager, we first reduce the complexity of WheelManager, and we
second provide a Memory interface to the WheelManager. If we decide to change the saving format from txt to some other database format,
we would still keep the same code for WheelManager and only modify the implementation of MemoryManager (OCP).


What design patterns did you use in the program?

BEHAVIOR PATTERN:
a. Command pattern: encapsulate the actions or the trigger within one object. As we started to decouple dependencies, we realized that
it makes more sense to have a component in the UI to send all the requests on updating and another component in the Logic who listens 
to all the requests and response back accordingly. For example, when the UI sends a command called "Wheel diameter increase???, the logic
does its work and then tell the UI to update the wheel diameter with the updated value. Clearly, there is a send-response relationship
between the UI and the Logic. With that in mind, we extract two main classes from the AppPanel that play the big roles in this 
send-response relationship: UtilityButton and WheelActionListener. 

UtilityButton is the only guy who is in charge of sending all the requests to the logic. We see the SRP principle emerges here from the 
design. By isolating the responsibility, the AppPanel can freely render those buttons along with other UI components without worrying
about what happens when the buttons are being clicked. 

STRUCTURAL PATTERN
a. Front controller: centralized control for all the requests. The big picture here is the WheelActionListener, who is on the other 
side of the send-response relationship. More details on WheelActionListener below.

CREATIONAL PATTERN:
a. Singleton: only one object to exist. First thought to come to mind when drawing the wheel is to have a wheel object within the
GraphicPanel class because it needs the wheel's properties. At the same time, that same wheel object is being shared with the 
PropertyPanels because they are rendering the properties values of the wheel. Should we pass the object around? We could, but the more 
efficient solution is to let the WheelActionListener manage that wheel object, then when a request is received, the WheelActionListener
works with the wheel object and then send back responses with what to update and where to update. The Singleton design pattern comes into
picture here because only one wheel object is supposed to exist throughout the program.

b. Factory method: abstraction of creating a single object without the known of class to instantiate. We do not instantiate the Wheel
object directly. Rather we delegate the task to the wheel rule because the wheel needs to follow a set of rules from the WheelRules and
that is where the wheel should be coming from.

Total [100]: 93
Program runs and produces the expected results [20]:
All tests pass [20]:
Test quality [10]:
Code coverage [10]:
Design quality [20]: -5
The Hub should be concreate but can send a notification to the wheel rule when its diamater changes. Then
the wheel rule can notify wheel to change the spokes length. This will decouple hub from wheel or spokes.
Code quality [10]:
Response to questions [10]: -2
More patterns.
