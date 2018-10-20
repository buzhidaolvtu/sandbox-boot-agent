#!/usr/bin/env bash

#sandbox-boot-maven-plugin的逻辑大概就是下面的样子
mvn clean package
cp -R sandbox target/
cd target
jar xvf sandbox-boot-agent-1.0-SNAPSHOT-jar-with-dependencies.jar
if [ -f "sandbox-boot-agent.jar" ]; then
    mv sandbox-boot-agent.jar sandbox-boot-agent.jar.bak
fi
jar -0cvfm  sandbox-boot-agent.jar META-INF/MANIFEST.MF META-INF org sandbox