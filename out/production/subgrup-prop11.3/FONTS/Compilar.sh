javac -cp ../:./lib/forms_rt-7.0.3.jar -d ../EXE/CLASS Presentacio/ControladorPresentacio.java
cp -R ../data ../EXE/CLASS
cp -R ./lib ../EXE/CLASS/FONTS
cp ../data/ControladorPresentacio.mf ../EXE/CLASS
cd ../EXE/CLASS
jar cmf ControladorPresentacio.mf ControladorPresentacio.jar ./FONTS/Presentacio/*.class ./FONTS/Persistencia/*.class ./FONTS/Domini/*.class