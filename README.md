# FoxClient

FoxClient is a client for [FoxRESTful](https://github.com/lekarto/JavaEE-FoxRESTful) Web service.

##### What you can see:
- All avaliable employees
- All avaliable departments

##### What you can do:
- Create new employee/department
- Update avaliable employee/department
- Delete avaliable employee/department

### Tech:
- Jersey RESTful client (1.19)
- Google Web Toolkit (2.7.0)
- Tomcat 7, 8 (or another container)
- Maven

### Installation

The following steps apply only for **Tomchat** container:

1. Clone repository to your machine.

2. Create user (for example: user="*deployer*", password="*s3cr3t*") for Tomcat in file *%TOMCAT_HOME%\conf\tomcat-users.xml* with roles *"manager-gui,manager-script"*

3. In *settings.xml* for your Maven (you can find this file in *%MAVEN_DIR%\conf\settings.xml* or *%PATH_TO_USER_DOCS%\\.m2\settings.xml*) create new server in section "servers" with your login/password pair:

    	<server>
			<id>MyTomcat</id>
			<username>deployer</username>
			<password>s3cr3t</password>
		</server>

4. If you change *id* section in previous step you must change server for Tomcat Maven Plugin in *pom.xml* of this project (configuration for tomcat maven plugin).

5. If your Tomcat servlet container has a non-standard 8080 port you must change it in *pom.xml* (configuration for tomcat maven plugin).

6. Run FoxRESTful service ([how you can do this](https://github.com/lekarto/JavaEE-FoxRESTful/blob/master/README.md))

7. Start Tomcat servlet container.

8. Open project directory in console.

9. Execute:

        mvn tomcat7:deploy

10. Open in your browser:

- [http://localhost:8080/foxclient](http://localhost:8080/foxclient)

When you open "Employees" or "Departments" inset you must wait until "foxsrestful" service starts.

That's all.

#### License
MIT