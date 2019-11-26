# Intelligent Software Systems Seminar 
Summer term 2019, Technische Universität Berlin 

![](aot-logo.png) <img src="tu_logo.png"  width="200" height="150">

<br>
<br>


## Implementation
Classes 
- __CreateCustomFormatter.java__ for the logger
- __Vertex.java__ defined the properties of the nodes in the game tree (value, cost, name, ID, ...) 
- __Graph.java__ defined the methods of the game tree. Contains the __main__ method to start the simulation. 

<br>
<br>

## Full Game Tree 

The full game tree includes 3 sub-trees: 

* WebApp
* DesktopApp
* MobileApp


<img src="https://github.com/Annabelita/Seminar_SecurityGraph/blob/master/images/game_tree.png" width="500">

<br>
<br>


### Set Parameters
You can set 6 parameters __bevore__ runtime. During runtime params can not be changed. 
Please set the params in the __Graph.java__ class in rows 1134 - 1139 *(Ctr + f and look for "SETUP PARAMETERS")*


<img src="https://github.com/Annabelita/Seminar_SecurityGraph/blob/master/images/settings.png" width="350">

<br>

- **DEFAULT_PROTECTION_LEVEL**: A integer in {1, 2, 3}. Is translated to either L1, L2 & L3, representing the protection level *(see seminar paper page 4 Def. 2)* 
- **DEFAULT_DEFENDER_VALUE**: Initial value of the defenders value function
- **DEFAULT_ATTACKER_VALUE**: Initial value of the attackers value function
- **nodesToProtect**: Integer between 0 and 30 that describes how many nodes can be protected by the defender in each round 
- **smartProtect**: true/false *(see seminar paper page 5 Def. 3)*
- **conditionalNode**: true/false  *(see seminar paper page 4 chapter 5.1.2)* 

<br>

The higher the amount of protected nodes the higher the chances for the defender to protect the application successfully. The following
plot shows that from a total of 30 protectable nodes the likelihod to protectful successful first increases above 20% if the defender
decides to protect more than 15 nodes (meaning more than half of the nodes):

<img src="https://github.com/Annabelita/Seminar_SecurityGraph/blob/master/images/probability.png" width="350">

<br>
<br>

### Starting the Simulation
Run the main method in __Graph.java__. 

The first param is used to choose the exploration algorithm (__ForwardPropagate__ oder __RandomChoice__). 

<br>
<br>


## Simulation Analysis

The results of each simulation round are logged in __logs.txt__. You can read more about the structure of the
logfile in my seminar paper (page 4, chapter 5.2)


<img src="https://github.com/Annabelita/Seminar_SecurityGraph/blob/master/images/table1.png" width="350">

<img src="https://github.com/Annabelita/Seminar_SecurityGraph/blob/master/images/table2.png" width="350">


The best strategy for the defender can be derived using the MiniMax algorithm (more details in the seminar paper):

<img src="https://github.com/Annabelita/Seminar_SecurityGraph/blob/master/images/strategie.png" width="350">
