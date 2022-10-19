________________________________________________________________________________________
 
 #              Data_Structure_And_Algorithms_In_Java
________________________________________________________________________________________
- ### Requirements before running codes:
    - Install an IDE that compiles and runs Java codes. Recommendation VS Code
    - How to setup WSL Ubuntu terminal shell and run it from Visual Studio Code: 
         visit: https://www.youtube.com/watch?v=fp45HpZuhS8&t=112s
    - How to Install Java JDK 17 on Windows 11: https://www.youtube.com/watch?v=ykAhL1IoQUM&t=136s
- ### About Codes:
    - The code is all written by myself only
    - I hard code them as part of my practice code, practical and assignemnts for my coding modules.
- ###  About Me: 
    - Tebogo Sello Selepe
    - Computer Sciences at Tuks
________________________________________________________________________________________
 ## Makefile
 ##### NB: A makefile Is Included to compile and run the codes on the terminal commands:=
- make clean
- make
- make run

```java
default:
	javac *.java

run:
	java Main

clean:
	rm -f *.class

zip:
	tar -cvz *.java makefile -f Filename.zip

unzip:
	tar -zxvf *.zip
```
________________________________________________________________________________________
