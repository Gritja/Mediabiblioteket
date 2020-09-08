# Mediabiblioteket 

En applikation skriven i Java programmeringsspråket som fungerar som ett digitalt bibliotekssystem för utlån och återlämning 
av olika slags media. Den är byggd med Java Swing som är Javas officiella API för att konstruera grafiska användargränssnitt.  

&nbsp;

## Instruktioner för att kunna köra applikationen

#### Steg 1 (Installera Eclipse) 

Ladda ner Eclipse IDE ifrån:

https://www.eclipse.org/downloads/packages/release/2019-06/r/eclipse-ide-java-developers 

OBS! Se till att välja rätt version för din dator och följ instruktionerna för nedladdningen. 

&nbsp;

#### Steg 2 (Installera JDK)

Ladda ner JDK (Java Development Kit) ifrån: 

https://www.oracle.com/technetwork/java/javase/downloads/index.html

Välj den senaste versionen och följ installationsanvisningarna. 

&nbsp;

#### Steg 3 (Importera applikationen till Eclipse)

Först behöver du ladda ner mappen med samtliga källkodsfiler. 
 

Öppna sedan upp Eclipse och öppna huvudmenyn _File_ -> _Import_ -> _General_ -> _Projects from Folder or Archive_. 
Sedan bläddrar du fram mappen med filerna i genom att trycka på _Directory..._ och välja den och därefter trycka på _Finish_. 

OBS! Tänk på att du efter importen behöver flytta över mappen _files_ (som innehåller nödvändiga datafiler) under Mediabibliotek till Huvudkatalogen för projektet för att applikationen ska kunna hitta den.

&nbsp;

#### Steg 4 (Köra applikationen)

När du väl har importerat applikationen i Eclipse så kan testköra applikationen genom att gå till huvudklassen _GUI.java_,
högerklicka i filen och välja _Run As_ -> _Java Application_. 



OBS! För att kunna logga in i programmet vid körning finns det förvalda användare med personnummer i filen _Lantagare.txt_
(CSV-fil) där du väljer ett personnummer kopplat till en viss person i listan och loggar in med detta nummer. 
De olika medier som går att söka på finns i en annan CSV-fil, _Media.txt_. 



Sedan är det bara att prova sig fram!










