# Java Reachable Goof

This is an intentionally vulnerable application. It was purposely designed to demonstrate the capabilities of Snyk's Reachable
Vulnerabilities feature and includes a direct data flow to a vulnerable function.

## What is vulnerable
An exploit is using a vulnerability called [ZipSlip](https://snyk.io/research/zip-slip-vulnerability), which was discovered 
at Snyk. It is a critical vulnerability, which typically results in remote command execution. A special zip archive is 
crafted (attached as `malicious_file.zip`). When this file is extracted by the vulnerable function, it will create a file 
called `good.txt` in the folder `unzipped`, but it will also create a file called `evil.txt` in the `/tmp/` folder. 
This example is not dangerous, but imagine overwriting `.ssh/authorized_keys` or another sensitive file.

## How to run the demo 
1. Checkout the repository (`git checkout git@github.com:snyk/java-reachable-goof.git`)
2. Install all the dependencies (`mvn install`)
3. Compile the project (`mvn compile`)
4. Run the main class (`mvn exec:java -Dexec.mainClass=Unzipper`); the application should throw an exception saying `Malicious file /tmp/evil.txt was created`.
5. Run the snyk test command with Reachable Vulnerabilities feature (`snyk test --reachable-vulns`); you should see the vulnerability `SNYK-JAVA-ORGND4J-72550` marked as reachable
