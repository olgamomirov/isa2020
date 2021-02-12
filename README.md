# isa2020
link ka repozitorijumu za front: https://github.com/VioletaMarceta/isa2020Front

Koriscena PostgreSQL baza. U application.properties promeniti kredencijale za bazu. 
Data-postgres.sql fajl sadrzi test podatke.
Email i lozinka za slanje mejla mogu ostati isti, jer je napravljen novi nalog. Username i password napisani u application.properties.

Back-end deo pokrenuti u Eclips-u i uvezati kao Maven projekat. 
Front-end je odradjen u Visual Studio Code-u kao React aplikacija. U terminal-u VSCode-a smo kucale npm install za instaliranje node modul-a (posto se ne komituju na github), a zatim npm start. 

Otvoriti http://localhost:3000 u pretrazivacu.

Napomena: Pregled se moze zapoceti 5 minuta ranije ili najkasnije 10 minuta od pocetka pregleda, u suprotnom lekaru nece biti omoguceno dugme 'zapocni pregled' i ako lekar zakasni sa zapocinjanjem pregleda, pregled odlazi u neodradjene, ali pacijent u tom slucaju ne dobija penal. 
