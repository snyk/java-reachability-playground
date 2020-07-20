# Java Reachable Goof

This is an intentionally vulnerable application. It should easily demonstrate purpose of feature called Reachable Vulnerabilities 
by having very straight flow to vulnerable function. To demonstrate "Potentially Reachable" mark, one more vulnerability
without data about vulnerable function is added.

## What is vulnerable
### [Arbitrary File Write via Archive Extraction](https://app.snyk.io/vuln/SNYK-JAVA-ORGND4J-72550)
Exploit is using vulnerability called [ZipSlip](https://snyk.io/research/zip-slip-vulnerability), which was discovered 
at Snyk. It is a critical vulnerability, which typically results in remote command execution. Special zip archive is 
crafted (attached as `malicious_file.zip`). When this file is extracted by vulnerable function, it will created file 
called `good.txt` in the folder `unzipped`, but it will also create a file called `evil.txt` in the `/tmp/` folder. 
This example is not dangerous, but imagine overwriting `.ssh/authorized_keys` or another sensitive file.

### [Deserialization of Untrusted Data](https://app.snyk.io/vuln/SNYK-JAVA-COMMONSCOLLECTIONS-472711)
This vulnerability is not exploited. It demonstrates potentially vulnerable code, for which data about vulnerable functions
are not available.

## How to run
1. Checkout this repository (`git checkout git@github.com:snyk/java-reachable-goof.git`)
2. Install all the dependencies (`mvn install`)
3. Compile the project (`mvn compile`)
4. Run the main class (`mvn exec:java -Dexec.mainClass=Unzipper`); application should throw an exception saying `Malicious file /tmp/evil.txt was created`.
5. Run snyk command with Reachable Vulnerabilities feature (`snyk test --reachable-vulns` or `snyk monitor --reachable-vulns`); you should see vulnerability `SNYK-JAVA-ORGND4J-72550` marked as reachable
and the function call path to the vulnerability

---

*Note: Once java application is run, `malicious_file.zip` will be deleted by it. To run it again, run `git checkout .` prior
to next java run.*

## Screenshots

### CLI
![Snyk CLI Reachable Vulnerabilities](cli_reachable.png)

### Snyk UI
![Snyk UI Reachable Vulnerabilities](UI_reachable.png)

