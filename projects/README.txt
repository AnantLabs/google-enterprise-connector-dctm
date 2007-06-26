This readme file presents how to configure the development environment for the Documentum connector.

REQUIRED FILES to compile and run the tests
�	dfc.jar (in <Documentum  root folder>/Shared)
�	dfcbase.jar (in <Documentum  root folder>/Shared)
�	xml-apis.jar (in <Documentum  root folder>/Shared)
�	log4j.jar (in <Documentum  root folder>/Shared)
�	commons-httpclient-3.0.1.jar (in dctm_third_party/lib folder of the project)
�	commons-codec-1.3.jar (in dctm_third_party/lib folder of the project)
�	json.jar (in thirdp_arty/lib folder of the project)
�	jcr-1.0.jar
�	junit.jar (in third_party/lib folder of the project)
�	spring.jar (in third_party/lib folder of the project)
�	connector_spi.jar (to copy in third_party/lib folder of the project)
�	connector.jar (to copy in third_party/lib folder of the project
�	connector_tests.jar (to copy in third_party/lib folder of the project


To build and run the junit tests for the connector manager, you must download 
jcr-1.0.jar and put it in the directory third_party/lib.  This jar is part of JSR-170.  
To get this jar, go to the official JSR-170 site, and follow the links from there:
http://jcp.org/aboutJava/communityprocess/review/jsr170/index.html


CONFIGURATION
Open Eclipse and create a java project from the existing source "connector-dctm"
Add to the classpath of the project all the jars that are in third_party\lib and dctm_third_party\lib
Also, add to the classpath 3 Class folders linked to the folder 
�	google_enterprise_connector_dctm/i18n
�	google_enterprise_connector_dctm/config
�	<Documentum root folder>/config
