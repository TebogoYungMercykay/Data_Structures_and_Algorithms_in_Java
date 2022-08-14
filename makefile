default:
	javac *.java

run:
	java Main

clean:
	rm -f *.class

zip:
	tar -cvz *.java makefile -f Filename.zip

unzip:
	tar -zxvf *.zip