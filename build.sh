#!/bin/bash
rm -r cmp > /dev/null
javac -d cmp src/Main/* src/Controller/* src/Model/* src/View/*
cp -r src/Img/ cmp
jar cvfm Ludo.jar src/META-INF/MANIFEST.MF -C cmp .
