cd ..
cd ..
cd class
echo "\ntestDocument: \n"
java -cp :./lib/junit-4.13.1.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.DocumentTest
echo "\ntestExpressio: \n"
java -cp :./lib/junit-4.13.1.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.ExpressioTest
echo "\ntestControladorContingut \n"
java -cp :./lib/junit-4.13.1.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.ControladorContingutTest
echo "\ntestControladorDocuments: \n"
java -cp :./lib/junit-4.13.1.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.ControladorDocumentsTest
echo "\ntestControladorDomini: \n"
java -cp :./lib/junit-4.13.1.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.ControladorDominiTest
echo "\ntestControladorExpressions: \n"
java -cp :./lib/junit-4.13.1.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.ControladorExpressionsTest