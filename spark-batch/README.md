## BATCH LAYER

- HDFS + SPARK BATCH + HIVE
- Read tweets from hdfs
- Compute statistics (aka features)
- Save as hive table

## APACHE SPARK INSTALLATION GUIDE

### Dev team: Almaz

### How to run the module?

- download Apache Spark

    `wget http://mirror.linux-ia64.org/apache/spark/spark-3.0.0-preview2/spark-3.0.0-preview2-bin-hadoop3.2.tgz`
- unzip Apache Hive

    `tar -xzvf spark-3.0.0-preview2-bin-hadoop3.2.tgz`
    `cd spark-3.0.0-preview2-bin-hadoop3.2`
- copy processing_batch.scala script

    `export HIVE_HOME=$(pwd)`
    `export PATH=$HIVE_HOME/bin:$PATH`
    
- run batch process
    `spark-shell -i file.scala`
        
- Try to see result in hive
    `$HIVE\bin\hive`