----------------------------IMPORTANT LLEGIR AQUEST FITXER ABANS D'USAR EL PROGRAMA!---------------------------------

COM COMPILAR I EXECUTAR:

    Trobaras dins de la carpeta FONTS d'aquest directori un archiu anomenat Compilar.sh o Compilar.bat (depenent de si estas usant Linux, MacOS o Windows hauras 
    d'executar un o l'altre, .sh per Linux i MacOS, .bat per Windows).
    A la terminal s'ha de executar, dins del directori FONTS, ./Compilar.sh si estas a Linux, sh ./Compilar.sh si estas desde MacOS o ./Compilar.bat si estas desde 
    Windows i aixo generara tots els .class per les classes del programa, els drivers i els test amb JUnit. 

    Els fichers creats pel make es guardaran automaticament a la carpeta EXE d'aquest directori.

    EXE conte la carpeta CLASS, on els .class aniran a parar.
    EXE tambe conte altres carpetes, que contenen diferents executables per provar els drivers creats. 
    Si vols executar un driver hauràs d'executar desde la carpeta driver el driver en concret que vols provar.
    Per exemple, des de la carpeta driver prodies executar el fitxer driverControladorDomini.sh si estas a Linux o MacOS i driverControladorDomini.bat si estas a Windows.
    Si vols executar el test hauràs d'executar desde la carpeta test el fitxer testGlobal.sh si estas a Linux o MacOS i testGlobal.bat si estas a Windows.

    L'executable que permetra utilitzar el programa és el driverControladorDomini.sh o driverControladorDomini.bat dins de la carptea drivers d'EXE.

    Per exectuar els fitxers .sh o .bat nomes cal que et coloquis desde la terminal a la carpeta on es troba el fitxer i entris: ./NomFitxer.sh si estas a Linux, sh ./NomFitxer.sh 
    si estas a MacOS i ./NomFitxer.bat. Si no funciona per falta de permisos en Linux o Windows has d'executar a la terminal : chmod +x *.sh o chmod +x *.bat

 
