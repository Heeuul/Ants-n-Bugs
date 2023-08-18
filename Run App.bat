@ECHO OFF 

set /p mapSize= "Enter cell size: " 
set /p antNo= "Enter number of ants: " 
set /p bugNo= "Enter number of bugs: " 

cd Source 

javac Program.java -d "./Compiled/"
echo Program ready! 
pause 

java -classpath "./Compiled/" Program --bugs %bugNo% --ants %antNo% --size %mapsize% 

rmdir /s /q Compiled 
