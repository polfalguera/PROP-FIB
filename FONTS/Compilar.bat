rmdir ..\EXE /s
javac -cp "..;.\lib\forms_rt-7.0.3.jar" -d ..\EXE\CLASS Presentacio\ControladorPresentacio.java
cd ..\EXE\CLASS
mkdir RESOURCES
cd .\FONTS
mkdir lib
cd ..\..\..\FONTS
Xcopy ..\RESOURCES ..\EXE\CLASS\RESOURCES /E/H/C/I
Xcopy .\lib ..\EXE\CLASS\FONTS\lib /E/H/C/I
copy ..\RESOURCES\ControladorPresentacio.mf ..\EXE\CLASS
cd ..\EXE\CLASS
jar cmf ControladorPresentacio.mf ControladorPresentacio.jar .\FONTS\Presentacio\*.class .\FONTS\Persistencia\*.class .\FONTS\Domini\*.class