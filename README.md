# Floor Plan Analysis

By using this program, we can analyze a floor plan and develop an evacuation plan


### Prerequisites

Java SE 8


### Installing and Running

Download/Clone the project and unzip.
Copy the path of the folder.
 For instance "user/john/download/⁨floor-plan-analysis⁩"

Then run the followeing scripts in terminal
```
cd [path of the folder]
```

```
cd ⁨src⁩/⁨main⁩/⁨java⁩ java Viewer
```
If you successfully execute the program, you will a window says:
FileChoseDemo with two Buttons "Open a File" and "Load Image"

Click "Open a File" and select a floor plan image(JPEG or PNG) to convert the floor plan to a matrix
After the image is processed, click "Load Image" to see a constructed map.
Double click to create an object. First three objects are exits(Green). Then double click the place you want to start at. A path in yellow will guide you to the closest exit.

Click right bottom to change create fire(Red). When after the color is red, double click to create a fire. If the fire blocks the path, the algorithm will find a new path. If no paths are available, a message of "Dead!" will display. 

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

* HackNYU
