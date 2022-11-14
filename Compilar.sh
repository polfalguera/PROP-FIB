javac FONTS/Domini/*.java ;
javac FONTS/Drivers/*.java ;
javac -cp ./FONTS/lib/junit-4.13.1.jar:./FONTS/lib/hamcrest-core-1.3.jar FONTS/Domini/*.java FONTS/Test/*.java
mv ./FONTS/Domini/*.class ./EXE/CLASS/FONTS/Domini;
mv ./FONTS/Drivers/*.class ./EXE/CLASS/FONTS/Drivers;
mv ./FONTS/Test/*.class ./EXE/CLASS/FONTS/Test;