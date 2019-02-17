# Floor Evacuation Simulator

Floor Evacuation Simulator is a 2019 HackNYU project. It can be used to analyze floor plan images and give the shorest evacuation path to the exit. It can also simulate fire occupying some areas and dynamically change evacuation plan.
 
### Prerequisites

Java SE 8
Eclipse


### Installing and Running

Download/Clone the project and unzip. Open the project in Eclipse and run the Viewer class.

If you successfully execute the program, you will a window says:
FileChoseDemo with two Buttons "Open a File" and "Load Image"

Click "Open a File" and select a floor plan image(JPEG or PNG) to convert the floor plan to a matrix
After the image is processed, click "Load Image" to see a constructed map.
Double click to create an object. First three objects are exits(Green). Then double click the place you want to start at. A path in yellow will guide you to the closest exit.

Click right bottom to switch mode between fire mode and safe mode. When the button is red (fire mode), click and drag to create fire(Red). Clicking the button again will trun it back to green, in which double click a spot will start from there. If the fire blocks the path, the algorithm will find a new path. If no paths are available, a message of "Dead!" will display. 

## Real World Application
This can simulate an evacuation plan in a building and would help fire inspection evaluation.
Also, it helps firefighter to determine the dead-corner in the building when they know the location of the fire.


## Built With

* Java

## Authors

* **Mingkuan Xu** 
* **Yunwei Zhang**
* **Hongbo Sun**
* **Chengyu Benton Li**





## Acknowledgments

* 2019 HackNYU
