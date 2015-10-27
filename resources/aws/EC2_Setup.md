EC2 Instance Setup
============

| Application | Version | Directory |
|-------------|---------|-----------|
| JRE | 1.8.0_60 | /usr/lib/jvm/jdk1.8.0_60 |
| Apache HTTPD | 2.2.31-1.6.amzn1 | /etc/httpd |
| Tomcat | 8.0.28 | /opt/apache-tomcat-8.0.28 |
| Alice's Favs - site scraper | | /opt/alicesfavs/site-scrape-batch |

JRE
--------
https://www.digitalocean.com/community/tutorials/how-to-install-java-on-centos-and-fedora<br/>
sudo wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u60-b27/server-jre-8u60-linux-x64.tar.gz"<br/>
sudo alternatives --install /usr/bin/java java /usr/lib/jvm/jdk1.8.0_60/bin/java 1<br/>
sudo alternatives --set java /usr/lib/jvm/jdk1.8.0_60/bin/java<br/>
sudo alternatives --remove java /usr/lib/jvm/jre-1.7.0-openjdk.x86_64/bin/java


Apache HTTPD
--------
http://www.cyberciti.biz/faq/linux-install-and-start-apache-httpd/<br/>
https://alextheedom.wordpress.com/microservices/set-up-tomcat-apache-and-mod_jk-cluster/<br/>
yum install httpd<br/>
and then copy httpd.conf and mod_jk.conf into /etc/httpd/conf directory

sudo service httpd stop/start

Tomcat
--------
https://www.mulesoft.com/tcat/tomcat-linux<br/>
sudo wget http://supergsego.com/apache/tomcat/tomcat-8/v8.0.28/bin/apache-tomcat-8.0.28.tar.gz

Apache - Tomcat
--------
http://www.ntu.edu.sg/home/ehchua/programming/howto/ApachePlusTomcat_HowTo.html<br/>
http://www.avajava.com/tutorials/lessons/how-do-i-connect-apache-to-tomcat-using-the-mod-jk-module.html<br/>
http://community.jaspersoft.com/wiki/connecting-apache-web-server-tomcat-and-writing-re-direct-rules<br/>
<br/>
sudo wget https://archive.apache.org/dist/tomcat/tomcat-connectors/jk/binaries/linux/jk-1.2.31/x86_64/mod_jk-1.2.31-httpd-2.2.x.so<br/>

Route 53
-------
Create an A-type Record Set that assigns to EC instance

