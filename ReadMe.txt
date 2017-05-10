

			Trello File Upload project 
			
--------------------- Requiremnt : -----------------------
Machine requirment: Windows 7
Browser requirement : Chrome Browser
Software requirement
1. Eclipse
2. Setup Java Environment (JRE 1.8)
3. Setup Ant Environment

------------------------

Precondition: Java must be install on computer to run test

1)tools.Jar file is available at : Base directory\com.trello.project\JavaTools
Paste tools.jar in C:\Program Files (x86)\Java\jre1.8.0_45\lib
2)Ant path variable must be set on computer
This files are in folder: Base directory\com.trello.project\apache-ant-1.9.6
**********How to set Ant path variable *********

Go to Start > My computer > Left click > Proerties > Advanced System settings >Environmet variabled
Set below path there
ANT_Home: Base directory\com.trello.project\apache-ant-1.9.6
Path: Base directory\com.trello.project\apache-ant-1.9.6\bin 

Also create Java environment variable :
In Path :
C:\Program Files (x86)\Java\jre1.8.0_45\lib 
C:\Program Files (x86)\Java\jre1.8.0_45\lib\bin 
and save it 
More help : http://www.mkyong.com/ant/how-to-install-apache-ant-on-windows/

------------------------------------------------------------------
To run script on client computer: 
1. Download project from Git URL: 
2. Import project into eclipse editor
3. Configure Build path (External Jars  and TestNG library)
   -  Configure External jars available in the lib Folder to the project
   -  Configure TestNG Libaray to the project
4. Open src folder of project 
5. Right Click BaseClass > Run with >  TestNG 


Total Test cases 5:
Testcases - Priority 1
1. Test_File_Upload
2. Test_File_Upload_delete
3. Test_File_Upload_delete_From_Popup

Testcases - Priority 2
1. Test_createBoard
2. Test_DeleteBoard
