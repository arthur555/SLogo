API_CELLSOCIETY
---
Examine one API from a previous Cell Society project and categorize each method from following perspectives:

the method should not be part of the API (i.e., it should not be public)
the method should be part of the external API because it helps people on the team working in other parts write their code
the method should be part of the internal API because it helps future coders within its part extend it
A simplified view of just the public methods for each team is available here.

author
---
* rr202 - Rahul Ramesh
* sz165 - Arthur Zhao
* hw186 - Haotian Wang
* ih33  - Inchan Hwang


Project Walkthrough
---
We looked at team #10's cell society


### For simulation API:
* Public Methods
* Internal API
    * Simulation interface implements what is necessary fdepending on neighbors, next color, etc.
    * other classes for different models extended the general simulation class. Each has update rule. Cell receives update rule and uses that to update its state
* External API
    * Cell class is a general class, and stands by self. Receives Simluation model
* Should not be in API - There are too many unnecessary public methods; Everything that's outside Simulator<T> should be package-private instead of being public.


### For visualization:
* Public Methods
* Internal API
    * different hierarchy, different parts of panel control different information in simulation
* External API
* Should not be in API
* 4 main aspects
    * model specific panel
    * simulation panel
    * simulation control panel
    * statistics panel
* each had their own class
* additional functionality of choosing neighbors
* Should not be in API
* Different classes for different parameters


### For configuration (looking at team #05):
* Public Methods
* Internal API
* External API
    * XML reader general class has methods to input file, and get parameter class instantiation with all necessary information (parameter class handles error checking)

### Example
```
package Parameters;
public class SegregationParameters {
      public SegregationParameters(String val)
    public HashMap<String, String> getParameters()
    public double getTolerance()
}
```

```
package XML;
public class Reader {
      public Reader()xmlDocument = null;}
    public boolean setUpFile(File xml)
    public String getFilename()
    public GeneralParameters readGeneral()
    public WaTorParameters readCell_WaTor()
    public FireParameters readCell_Fire()
    public SegregationParameters readCell_Segregation()
    public LangstonsLoopsParameters readCell_LangstonsLoops()
    public AntsParameters readCell_Ants()
    public RockPaperScissorsParameters readCell_RockPaperScissors()
}
```