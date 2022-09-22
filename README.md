# General Use Inventor Manager

## About

This program uses javafx for the Graphical User Interface.
</br></br> TODO From the GUI, the controller creates 
an InventoryService object globally. When users 
add inventory to GUI, the data is used to 
instantiate a new Inventory object in which is passed 
using the InventoryService object. So goes for the other
listeners within the to be built gui.
</br></br>For the backend, it uses a lightweight database. The 
database used is SQLite. This choice allows for local
use without the need for servers. 

## IDE

The IDE used was intelliJ. Since paths and set up tend 
to be a bit different, it would be a bit easier to use
intelliJ. The following is my setup followed by sqlite 
setup.

# Setting up IntelliJ IDE (Without Maven)
## JavaFX
Download package to desired location. Good idea to keep all
libs/packages in one directory on computer to be tidy.
https://gluonhq.com/products/javafx/ <br /><br />
### Verify Path variable
Set environment variable in IDE (if not done so already):
- File > Settings > Under Appearance and Behavior, Choose Path Variables
- Click + then add the following for name
```zsh
PATH_TO_FX
```
- Then add the path to javafx lib folder for variable<br /><br />

### Verify javafx lib is set
Verify that javafx lib is added to the project by doing the following:
- File > Project Structure > Under Project Settings choose library
- The lib folder should be in place (for this project)
- If not in place choose the + icon and navigate to javafx lib folder<br /><br />

### Verify project JDK is set
- File > Project Structure > Under Project Settings choose Project
- The SDK for project should be openJDK 18 or JDK of choice.
- If the SDK is colored out such as red and proper JDK is not in dropdown:
- - Click drop down for SDK
- - Add SDK > Download JDK > Then choose openjdk-18 for the project<br /><br />

### Setting to compile
Set VM options by the following:
- Right-click the java file with the main function and click run
  (main must be defined).
- Click drop down in the upper right corner near build options (right faces triangle).
- Select edit configuration.
- On the right, under Build and run, choose the modify options drop down.
- Under java, choose Add VM Options.
- Then add the following to the VM options input text field:
```zsh
--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml
```
Note: The path variable must be set to use the above command.</br>
The java project should be able to now compile and run.

# SQLite setup

Setup is simple. Download jar file from online maven
repository. </br> https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
</br> Choose the version, then download jar file under
files. Add jar file to convenient location where other
packages are kept.

## IntelliJ SQLite setup
File > project structure. Under Project Settings, choose modules.
Then on the right, choose dependencies tab, click the + icon
then choose then navigate and choose the sqlite jar file.
And that is it in order to get java to communicate with
local database.

### Optional Database console setup
You can manipulate the database file without running
the java code. To set up: Right click the DB file in the 
IDE and choose new > add datasource from selection (May be
towards the bottom of selection). Then choose sqlite from the 
driver dropdown. </br> </br>
This will allow a console to display in the editor when
you double click the db file. You then can manipulate the 
db using that console. To test, enter the following:
```zsh
SELECT * FROM INVENTORY;
```
Then click the execute button, right
facing triangle (looks like play button
above the window)

### PS
Will comment the code to explain what is going on when
I feel that it is safe to dedicate time to that.
Also may tidy up the file structure if
time persist.

