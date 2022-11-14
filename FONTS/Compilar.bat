javac Domini/*.java ;
javac Domini/*.java Drivers/*.java ;
javac -cp ./lib/junit-4.13.1.jar:./lib/hamcrest-core-1.3.jar Domini/*.java Test/*.java
mv ./Domini/*.class ../EXE/CLASS/FONTS/Domini;
mv ./Drivers/*.class ../EXE/CLASS/FONTS/Drivers;
mv ./Test/*.class ../EXE/CLASS/FONTS/Test;