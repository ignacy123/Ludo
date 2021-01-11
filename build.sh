#!/bin/bash
rm -r cmp
javac -d cmp src/Main/* src/Controller/* src/Model/* src/View/*
cp -r src/Img/ cmp
cp -r src/META-INF/ cmp
jar cvfm Ludo.jar src/META-INF/MANIFEST.MF -C cmp .
