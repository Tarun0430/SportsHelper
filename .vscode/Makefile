JAVAC = javac
JAVA = java
JAVAFX_LIB = /Users/tarunpalani/Downloads/javafx-sdk-21.0.1/lib # Replace with the actual path

.PHONY: all run clean

all: build

build:
	$(JAVAC) -d bin -cp $(JAVAFX_LIB)/javafx.base.jar:$(JAVAFX_LIB)/javafx.controls.jar:$(JAVAFX_LIB)/javafx.web.jar:$(JAVAFX_LIB)/javafx.swing.jar:$(JAVAFX_LIB)/javafx.graphics.jar:$(JAVAFX_LIB)/javafx.media.jar:$(JAVAFX_LIB)/javafx-swt.jar:$(JAVAFX_LIB)/javafx.fxml.jar src/*.java

run:
	$(JAVA) -cp bin:$(JAVAFX_LIB)/javafx.base.jar:$(JAVAFX_LIB)/javafx.controls.jar:$(JAVAFX_LIB)/javafx.web.jar:$(JAVAFX_LIB)/javafx.swing.jar:$(JAVAFX_LIB)/javafx.graphics.jar:$(JAVAFX_LIB)/javafx.media.jar:$(JAVAFX_LIB)/javafx-swt.jar:$(JAVAFX_LIB)/javafx.fxml.jar OddsFrontEnd

clean:
	rm -rf bin
