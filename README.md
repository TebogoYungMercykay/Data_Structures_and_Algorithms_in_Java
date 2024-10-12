# Data Structures And Algorithms In Java

This project focuses on implementing various data structures and algorithms in Java. Below are the requirements, installation instructions, and details on how to run the code efficiently using a terminal or an IDE.

---

## Requirements Before Running Code

1. **Java Development Kit (JDK) Installation**  
   You need to install JDK to compile and run Java code. JDK 17 is recommended. Here are some tutorials to help with setup:
   
   - **Install Java JDK 17 on Windows 11:**  
     Follow this YouTube tutorial to install JDK 17:  
     [How to Install Java JDK 17 on Windows 11](https://www.youtube.com/watch?v=ykAhL1IoQUM&t=136s)

   - **Setup WSL (Windows Subsystem for Linux) and Run It from Visual Studio Code:**  
     If you're using WSL, you can run the Ubuntu terminal shell directly from Visual Studio Code. Learn how to set it up using this guide:  
     [Setup WSL and Run from VS Code](https://www.youtube.com/watch?v=fp45HpZuhS8&t=112s)

2. **Installing Oracle JDK on Windows Subsystem for Linux (WSL)**  
   If you're working in a WSL environment, follow these steps to install Oracle JDK:

   - Run the WSL terminal as **Administrator**.
   - Set necessary permissions and variables:
   
     ```bash
     set -ex
     export JDK_URL=http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz
     export UNLIMITED_STRENGTH_URL=http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip
     ```

   - Download the JDK and unzip the archive:

     ```bash
     wget --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" ${JDK_URL}
     tar -xzvf jdk-*.tar.gz
     rm -fr jdk-*.tar.gz
     ```

   - Move the JDK to the correct directory and configure it:

     ```bash
     sudo mkdir -p /usr/lib/jvm
     sudo mv jdk1.8* /usr/lib/jvm/oracle_jdk8
     wget --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" ${UNLIMITED_STRENGTH_URL}
     unzip jce_policy-8.zip
     mv UnlimitedJCEPolicyJDK8/local_policy.jar /usr/lib/jvm/oracle_jdk8/jre/lib/security/
     mv UnlimitedJCEPolicyJDK8/US_export_policy.jar /usr/lib/jvm/oracle_jdk8/jre/lib/security/
     ```

   - Configure alternatives for Java and Javac:

     ```bash
     sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/oracle_jdk8/jre/bin/java 2000
     sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/oracle_jdk8/bin/javac 2000
     ```

   - Set environment variables:

     ```bash
     sudo echo "export J2SDKDIR=/usr/lib/jvm/oracle_jdk8
     export J2REDIR=/usr/lib/jvm/oracle_jdk8/jre
     export PATH=$PATH:/usr/lib/jvm/oracle_jdk8/bin:/usr/lib/jvm/oracle_jdk8/db/bin:/usr/lib/jvm/oracle_jdk8/jre/bin
     export JAVA_HOME=/usr/lib/jvm/oracle_jdk8
     export DERBY_HOME=/usr/lib/jvm/oracle_jdk8/db" | sudo tee -a /etc/profile.d/oraclejdk.sh
     ```

   > **Note**: You can update the links for other JDK versions if needed.

---

## About the Code

- All the code is written by me as part of my practice, assignments, and coding modules in computer science.
- This repository includes implementations of data structures like binary search trees, and various algorithms, all coded in Java.

---

## About Me

- **Tebogo Sello Selepe**  
  Currently studying **Computer Sciences** at the **University of Pretoria** (Tuks).

---

## How to Compile and Run the Code

You can use the provided `Makefile` to compile and run the Java code from the terminal. Follow these steps:

1. **Ensure JDK is installed**: Make sure you have JDK installed and properly set up on your system (refer to the Requirements section above).

2. **Clone or download the project**:  
   Open a terminal and navigate to the project directory.

3. **Use the following commands** to clean, compile, and run the project:

   - **Clean the project**:
     ```bash
     make clean
     ```

   - **Compile the project**:
     ```bash
     make
     ```

   - **Run the project**:
     ```bash
     make run
     ```

## Makefile

Here's the `Makefile` to automate the build process:

```makefile
default:
	javac *.java

run:
	java Main

clean:
	rm -f *.class
	reset
	clear

tar:
	tar -cvz *.* -f Java_Filename.tar.gz
	
untar:
	tar -zxvf *.tar.gz
```

> **Note**: The default command compiles all `.java` files, and the `run` command executes the `Main` class. Modify the class name in the `run` command if the entry point is different.

---

## Contributing

This project is mainly for educational purposes. However, if you have suggestions or improvements, feel free to open an issue or submit a pull request.

---

## License

This project is open source. Please check the repository for more specific license details.
