cd ..
cd CLASS
echo "\ntestDocument: \n"
java -cp :./FONTS/lib/junit-4.13.1.jar:./FONTS/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FONTS.Test.DocumentTest
echo "\ntestExpressio: \n"
java -cp :./FONTS/lib/junit-4.13.1.jar:./FONTS/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FONTS.Test.ExpressioTest
echo "\ntestControladorContingut \n"
java -cp :./FONTS/lib/junit-4.13.1.jar:./FONTS/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FONTS.Test.ConjuntContingutsTest
echo "\ntestControladorDocuments: \n"
java -cp :./FONTS/lib/junit-4.13.1.jar:./FONTS/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FONTS.Test.ControladorDocumentsTest
echo "\ntestControladorDomini: \n"
java -cp :./FONTS/lib/junit-4.13.1.jar:./FONTS/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FONTS.Test.ControladorDominiTest
echo "\ntestControladorExpressions: \n"
java -cp :./FONTS/lib/junit-4.13.1.jar:./FONTS/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore FONTS.Test.ControladorExpressionsTest