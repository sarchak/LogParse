mkdir -p classes

bin/hadoop fs -rmr /user/shrikar/logparse*
bin/hadoop fs -rmr /user/shrikar/logparseall*
bin/hadoop fs -mkdir /user/shrikar/input
bin/hadoop fs -rmr /user/shrikar/input/logparse.txt
bin/hadoop fs -put logparse.txt /user/shrikar/input/logparse.txt


javac -cp hadoop-core-0.20.203.0.jar -d classes LogParseMapper.java
javac -cp hadoop-core-0.20.203.0.jar -d classes LogParseReducer.java
javac -cp hadoop-core-0.20.203.0.jar  LogParseMapper.java
javac -cp hadoop-core-0.20.203.0.jar  LogParseReducer.java
javac -cp hadoop-core-0.20.203.0.jar -d classes LogParseMapperAll.java
javac -cp hadoop-core-0.20.203.0.jar -d classes LogParseReducerAll.java
javac -cp hadoop-core-0.20.203.0.jar  LogParseMapperAll.java
javac -cp hadoop-core-0.20.203.0.jar  LogParseReducerAll.java
jar -cvf logparsehelper.jar -C classes/ .
javac -cp hadoop-core-0.20.203.0.jar:logparsehelper.jar  -d classes LogParse.java
javac -cp hadoop-core-0.20.203.0.jar:logparsehelper.jar  LogParse.java
jar -cvf logparse.jar -C classes/ .

javac -cp hadoop-core-0.20.203.0.jar:logparsehelper.jar  -d classes LogParseAll.java
javac -cp hadoop-core-0.20.203.0.jar:logparsehelper.jar  LogParseAll.java
jar -cvf logparseall.jar -C classes/ .
bin/hadoop jar logparse.jar LogParse /user/shrikar/input/logparse.txt /user/shrikar/logparse
bin/hadoop jar logparseall.jar LogParseAll /user/shrikar/input/logparse.txt /user/shrikar/logparseall
bin/hadoop fs -cat /user/shrikar/logparse/*
bin/hadoop fs -cat /user/shrikar/logparseall/*

