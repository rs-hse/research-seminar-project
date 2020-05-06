## APACHE HADOOP INSTALLATION GUIDE

### Dev team: Almaz

### How to run the module?

- install pdsh 

    `sudo brew install pdsh`
- download Apache Hadoop Distribution 3.2.1

    `wget http://mirror.linux-ia64.org/apache/hadoop/common/hadoop-3.2.1/hadoop-3.2.1.tar.gz`
- unzip Apache Hadoop Distribution

    `tar -xzf hadoop-3.2.1.tar.gz `
- change hadoop-mode to Pseudo-Distributed Mode

`vim etc/hadoop/core-site.xml`

```xml 
    <configuration>
        <property>
            <name>fs.defaultFS</name>
            <value>hdfs://localhost:9000</value>
        </property>
    </configuration>
```

`vim etc/hadoop/hdfs-site.xml`

```xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
```  
- format HDFS

    `bin/hdfs namenode -format`
- Start NameNode daemon and DataNode daemon

    `sbin/start-dfs.sh`

- Check NameNode on:

    `http://localhost:9870/`
    
- Export HADOOP_HOME variable

    `export HIVE_HOME=$(pwd)`
    
    `export PATH=$HIVE_HOME/bin:$PATH`