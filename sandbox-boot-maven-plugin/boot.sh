#!/usr/bin/env bash

#sandbox-boot-maven-plugin的逻辑大概就是下面的样子
project=/Users/lvtu/workspace/sandbox-boot-agent

#打包jvm-sandbox
cd ${project}/jvm-sandbox/bin
./sandbox-packages.sh

#打包sandbox-boot-loader
cd ${project}/sandbox-boot-loader
mvn clean package

#打包sandbox-module
cd ${project}/sandbox-module-example
mvn clean package

#archives all together
cd ${project}/sandbox-boot-maven-plugin
mvn clean package
#获取原始jvm-sandbox结构
cp -R ${project}/jvm-sandbox/target/sandbox target/
#获取sandbox-boot-loader
cp ${project}/sandbox-boot-loader/target/sandbox-boot-loader-1.0-SNAPSHOT-jar-with-dependencies.jar target/

cd target
jar xvf sandbox-boot-loader-1.0-SNAPSHOT-jar-with-dependencies.jar
if [ -f "sandbox-boot-agent.jar" ]; then
    mv sandbox-boot-agent.jar sandbox-boot-agent.jar.bak
fi
jar -0cvfm  sandbox-boot-agent.jar META-INF/MANIFEST.MF META-INF net org sandbox