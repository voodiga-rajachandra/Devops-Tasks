### Sonar Analysis with Java Application
### Repo:https://github.com/Rahuldepp/Java-Blog.git
#### Step1:create a EC2 server for Javascript application

#### Step2:connect the server using ssh

#### Step3: clone the git repository
git clone https://github.com/Rahuldepp/Java-Blog.git

### Step4: Install Java and maven
#### apt update 
#### apt install openjdk-17-jre-headless -y
#### apt install maven 

### Step5: create build using below command
#### mvn package

### Step6: Create Another EC2 Server for SonarQube and connect with ssh

### Step7: Install Java and Sonarqube

#### sudo apt update 
#### sudo apt install unzip
#### sudo apt install openjdk-17-jre-headless 

#### wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-25.4.0.105899.zip
#### unzip sonarqube-25.4.0.105899.zip

### Step8: start and check the status of Sonar Server
#### cd sonarqube-25.4.0.105899/bin/linux-x86-64
#### ./sonar.sh start
#### ./sonar.sh status

### Step9: Use publicip:9000 to open sonarqube server and reset the password

### Step10: create a local project in the name of java with necessary configuration a code will be generated

### Step11: Paste the code in javascript server and sonar will execute the analysis

### Output:

![](images/output.png)




