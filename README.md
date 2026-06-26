# IOM Test Framework

// todo - please check [the wiki](https://github.com/intershop/iom-test-framework/wiki/User-Guide)

## Artifact Repository

Starting with version 8.0.0, artifacts are published to the internal Azure Artifacts feed instead of Maven Central.

Add the repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>order-iom-releases</id>
        <url>https://pkgs.dev.azure.com/intershop-com/Products/_packaging/order-iom-releases/maven/v1</url>
        <releases><enabled>true</enabled></releases>
        <snapshots><enabled>false</enabled></snapshots>
    </repository>
</repositories>
```

Then add the dependency:

```xml
<dependency>
    <groupId>com.intershop.oms</groupId>
    <artifactId>iom-test-framework</artifactId>
    <version>8.0.0</version>
</dependency>
```

