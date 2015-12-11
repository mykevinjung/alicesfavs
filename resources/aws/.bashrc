# .bashrc

# Source global definitions
if [ -f /etc/bashrc ]; then
	. /etc/bashrc
fi

# User specific aliases and functions
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_51
export CATALINA_HOME=/opt/apache-tomcat-8.0.28


alias start_tomcat="cd $CATALINA_HOME; sudo $CATALINA_HOME/bin/startup.sh >> /dev/null 2>&1"
alias stop_tomcat="cd $CATALINA_HOME; sudo $CATALINA_HOME/bin/shutdown.sh >> /dev/null 2>&1"
alias start_httpd="sudo /etc/init.d/httpd start >> /dev/null 2>&1"
alias stop_httpd="sudo /etc/init.d/httpd stop >> /dev/null 2>&1"
alias restart_httpd="sudo /etc/init.d/httpd restart >> /dev/null 2>&1"

