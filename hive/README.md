## APACHE HIVE INSTALLATION GUIDE

### Dev team: Almaz

### How to run the module?

- download Apache Hive Distribution 3.1.2

    `wget https://apache-mirror.rbc.ru/pub/apache/hive/hive-3.1.2/apache-hive-3.1.2-bin.tar.gz`
- unzip Apache Hive

    `tar -xzvf apache-hive-3.1.2-bin.tar.gz`
    `cd apache-hive-3.1.2-bin`
- make environment variables

    `export HIVE_HOME=$(pwd)`
    `export PATH=$HIVE_HOME/bin:$PATH`
    
- create hive-folders

    `$HADOOP_HOME/bin/hadoop fs -mkdir       /tmp`
    `$HADOOP_HOME/bin/hadoop fs -mkdir -p    /user/hive/warehouse`
    `$HADOOP_HOME/bin/hadoop fs -chmod g+w   /tmp`
    `$HADOOP_HOME/bin/hadoop fs -chmod g+w   /user/hive/warehouse`
    
- fix problem with guava-uncomparable verions

    `cp $HADOOP_HOME/share/hadoop/common/lib/guava-27.0-jre.jar ./lib/`
    
- Try to run hive

    `$HIVE\bin\hive`