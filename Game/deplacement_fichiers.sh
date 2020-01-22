#! /bin/bash

#parce que j'ai pas eclipse XD

echo "bin : avant deplacement des fichiers .class"
ls bin/
echo "scr : avant compilation des fichiers .java"
cd src/ 
ls 
echo "debut du compilation des fichiers .java"
javac *.java
echo "fin du compilation des fichiers .java"
echo "scr : apres compilation des fichiers .java"
ls
echo "debut du deplacement des fichiers .class"
mv ./*.class ./../bin/ 
echo "fin du deplacement des fichiers .class"
echo "src : apres deplacement des fichiers .class"
ls
cd ..
echo "bin : apres deplacement des fichiers .class"
ls bin/

