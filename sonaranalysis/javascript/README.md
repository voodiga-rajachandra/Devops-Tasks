### Sonar Analysis with Javascript Application
### Repo:https://github.com/dreamz89/photography-website.git
#### Step1:create a EC2 server for Javascript application

#### Step2:connect the server using ssh

#### Step3: clone the git repository
git clone https://github.com/dreamz89/photography-website.git

### Step4: Install nodejs and npm
#### sudo apt update 
#### sudo apt install nodejs
#### sudo apt install npm
### Step5: create build using below commands
#### npm install
#### npm audit fix
#### npm run build

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

### Step: create a file for running sonar scanner

#### npm install -g @sonar/scan

#### vi sonar-project.properties

#### root@ip-172-31-26-213:~/photography-website# cat sonar-project.properties
#### sonar.projectKey=js
#### sonar.projectName=js
#### sonar.projectVersion=1.0
#### sonar.sources=.
#### sonar.host.url=http://18.232.140.167:9000
#### sonar.login=sqp_ff83de42e0c3931f39f99357a83f4c0fa98efbc6

#### sonar scanner

### Step9: Use publicip:9000 to open sonarqube server and reset the password

### Step10: create a local project in the name of javascript with necessary configuration a code will be generated

### Step11: Paste the code in javascript server and sonar will execute the analysis

### Output:

![](images/output.png)




