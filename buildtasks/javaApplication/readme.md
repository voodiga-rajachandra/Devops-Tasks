## Java Application Build and Deploy

### Git Repository:https://github.com/Rahuldepp/Java-Blog.git

#### Step1:Create a EC2 instance
#### Step2:Connect to terminal using ssh
#### Step3: Enter apt update
#### Step4: install java 
sudo apt-get install openjdk-17-jdk

#### Step5: install maven
 sudo apt install maven

#### Step6: clone the git repository using git clone
git clone https://github.com/Rahuldepp/Java-Blog.git
#### Step7: build the code using mvn package
mvn package
#### Step8: make sure build is success
![](javaimages/input1.png)
#### Step9: install tomcat using linux
wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.102/bin/apache-tomcat-9.0.102.tar.gz
tar -xvf apache
cd apache
Start tomcat - cd bin
./startup.sh

find -name context.xml
sudo vi ./webapps/manager/META-INF/context.xml
sudo vi ./webapps/host-manager/META-INF/context.xml


![](javaimages/input0.png)
![](javaimages/input2.png)
#### Step10: copy the war file from build to webapps folder in tomcat
cp -r Java-Blog/target/*.war apache-tomcat-9.0.102/webapps/

#### Step11:use the public ip address:8080
44.202.65.16:8080/BlogApp-1.0-SNAPSHOT/
#### Step12: Application should be live
![](javaimages/output1.png)
![](javaimages/output2.png)
