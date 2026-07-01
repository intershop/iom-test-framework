# IOM Test Framework 8.0.0 Migration Guide

## Artifact Repository Change

**What changed:** Starting with 8.0.0, `iom-test-framework` is no longer published to Maven Central. Artifacts are now published to the IOM Maven Repository of your Azure DevOps Environment — the same feed that already provides the IOM build artifacts.

**Action required for consumers:**

No new repository needs to be added. The `iom-test-framework` artifacts are published to the same feed as the IOM build artifacts (`iom-maven-artifacts`), which is already configured in your `pom.xml` (see `//repositories/repository[id='iom-maven-artifacts']/url`). The URL is specific to your Azure DevOps Environment; the default from the IOM Project Archetype is:

```
https://pkgs.dev.azure.com/intershop-com/Products/_packaging/iom-maven-artifacts/maven/v1
```

If you have not yet set up access to this feed, follow the instructions in the devenv-4-iom documentation: [Get Access to IOM Maven Repository](https://github.com/intershop/devenv-4-iom/blob/main/doc/03_devops_integration.md#get-access-to-iom-maven-repository).

---

## Breaking Changes

### Java 17 → Java 21

**What changed:** The compiler target was bumped from Java 17 to Java 21.

**Impact on consumer projects:**

- Consumer projects must be compiled and run with **Java 21 or later**.
- No source-level API changes are required for code that compiled under Java 17.

**Action required for consumers:**

1. Update your CI/CD pipeline and local JDK to Java 21.
2. If your project overrides `maven-compiler-plugin`'s `<release>` or `<source>`/`<target>`,
   update to `21`.

---

### Order State Service v1 removed

**What changed:** The Order State Service v1 client (`OMSOrderStateServiceHandlerV1`,
`OMSOrderStateServiceHandlerProviderV1`, and related mapper/deserializer classes) has been
deleted. The v1 handler is no longer registered in `ServiceHandlerFactory`.

**Action required for consumers:**

- If your tests referenced the v1 Order State Service handler explicitly, migrate to the
  v2.0 handler (`OMSOrderStateServiceHandlerProviderV2_0`), which remains the default.

---

## Dependency Upgrades

### SLF4J 1.7.x → 2.0.17

**What changed:** SLF4J was upgraded from `1.7.30` to `2.0.17`.

**Why:** HikariCP 7.x requires SLF4J 2.x. Using SLF4J 1.7.x alongside
HikariCP 7.x causes `NoSuchMethodError` or `IncompatibleClassChangeError`
at runtime.

**Impact on consumer projects (e.g. ci-project):**

- **No code changes required.** The public API (`Logger`, `LoggerFactory`,
  `MDC`) is backward compatible. All existing `import org.slf4j.*` statements
  and `LoggerFactory.getLogger(...)` calls work unchanged.
- **`simplelogger.properties`** — The property format is unchanged in 2.x.
  Existing configuration files (e.g. `org.slf4j.simpleLogger.defaultLogLevel=DEBUG`)
  continue to work as-is.
- **Provider discovery changed internally.** SLF4J 2.x uses `ServiceLoader`
  instead of static binding (`StaticLoggerBinder`). This is transparent to
  application code but means:
  - The old `slf4j-simple-1.7.x.jar` is **not** compatible with `slf4j-api-2.x`.
    Both artifacts must be at the same major version.
  - If your project declares its own SLF4J dependencies (not inherited from
    iom-test-framework), update them to `2.0.17` as well.
- **Other SLF4J bindings:** If you use `logback-classic` instead of `slf4j-simple`,
  upgrade to Logback `1.4.x+` (which supports SLF4J 2.x).

**Action required for consumers:**

1. If you override the SLF4J version in your own POM, change it to `2.0.17`.
2. If you use a different logging backend (e.g. Logback, Log4j2), ensure its
   SLF4J bridge is compatible with SLF4J 2.x:
   - Logback: use `1.4.x` or newer
   - Log4j2: use `log4j-slf4j2-impl` (note the `2` suffix) instead of
     `log4j-slf4j-impl`

---

### Flyway 9.5.0 → 12.8.1

**What changed:** Flyway was upgraded from `9.5.0` to `12.8.1`.
Two new dependencies were added:

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-database-postgresql</artifactId>
    <version>12.8.1</version>
</dependency>
<dependency>
    <groupId>tools.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>3.1.3</version>
</dependency>
```

**Why:**

- Since Flyway 10, database-specific support was extracted into separate modules.
  PostgreSQL support requires `flyway-database-postgresql`.
- Flyway 12.x internally uses Jackson 3.x (`tools.jackson` package namespace).
  Without this dependency, `NoClassDefFoundError: tools.jackson.databind.ObjectMapper`
  occurs at runtime.

**Impact on consumer projects:**

- **No code changes required** if you only use Flyway through iom-test-framework's
  `OMSDbHandler`. The Flyway API for basic migration (`Flyway.configure()...load().migrate()`)
  is backward compatible.
- **Jackson 3.x coexists with Jackson 2.x** — they use different package
  namespaces (`tools.jackson.*` vs `com.fasterxml.jackson.*`), so there are
  no classpath conflicts.
- **Jackson annotations version requirement:** Jackson 3.x depends on
  `com.fasterxml.jackson.core:jackson-annotations:2.21` (Jackson 2.x group ID).
  If your project uses a BOM (e.g. Wildfly) that manages `jackson-annotations`
  to an older version, you must override it to `2.21` in your
  `dependencyManagement`. Otherwise, `ObjectMapper$PrivateBuilder` static
  initialization fails at runtime. Example override:

  ```xml
  <dependencyManagement>
      <dependencies>
          <!-- your existing BOM import -->
          <dependency>
              <groupId>com.fasterxml.jackson.core</groupId>
              <artifactId>jackson-annotations</artifactId>
              <version>2.21</version>
          </dependency>
      </dependencies>
  </dependencyManagement>
  ```

- If you use Flyway's Java-based migrations or callbacks, review the
  [Flyway 10 release notes](https://documentation.red-gate.com/flyway/release-notes-and-older-versions)
  for any API changes.

---

### HikariCP 5.1.0 → 7.1.0

**What changed:** HikariCP was upgraded from `5.1.0` to `7.1.0`.

**Impact on consumer projects:**

- **No code changes required** for typical usage through iom-test-framework.
- HikariCP 7.x requires **Java 11+** (already satisfied by the Java 21 target).
- HikariCP 7.x requires **SLF4J 2.x** (addressed by the SLF4J upgrade above).

---

### Jackson 2.13.x → 2.21.3

**What changed:** All `com.fasterxml.jackson` artifacts were upgraded from `2.13.4`
to `2.21.3`. `jackson-annotations` now has its own version property pinned at `2.21`
(matching Wildfly 40).

**Impact on consumer projects:**

- **No code changes required** for typical usage.
- If your project or BOM manages Jackson versions independently, align to `2.21.3`
  (`jackson-core`, `jackson-databind`) and `2.21` (`jackson-annotations`).

---

### JUnit Jupiter 5.9.1 → 6.1.0

**What changed:** JUnit Jupiter was upgraded from `5.9.1` to `6.1.0`.

**Impact on consumer projects:**

- Review the [JUnit 6 release notes](https://junit.org/junit5/docs/current/release-notes/)
  for any API changes if you extend or customise JUnit lifecycle callbacks.
- Standard `@Test`, `@BeforeEach`, `@AfterEach` and assertion APIs are backward compatible.

---

### Other library updates

| Library                    | 7.1.0     | 8.0.0     |
|----------------------------|-----------|-----------|
| Jersey                     | 3.1.3     | 3.1.11    |
| jose4j                     | 0.9.3     | 0.9.6     |
| PostgreSQL driver          | 42.7.1    | 42.7.11   |
| commons-lang3              | 3.12.0    | 3.20.0    |
| smallrye-config            | 3.3.2     | 3.17.2    |
| MapStruct                  | 1.5.3.Final | 1.6.3   |
| Lombok                     | 1.18.24   | 1.18.46   |
| OpenAPI Generator          | 6.2.0     | 6.4.0     |
| jackson-databind-nullable  | 0.2.3     | 0.2.10    |

No consumer-facing code changes are required for these updates.

---

## Summary of Required Actions

| If your project...                                    | Action                                             |
|-------------------------------------------------------|----------------------------------------------------|
| Runs on Java 17                                       | **Upgrade to Java 21**                             |
| Uses Order State Service v1 handler directly          | Migrate to the v2.0 handler                        |
| Inherits SLF4J from iom-test-framework only           | No action needed                                   |
| Declares its own SLF4J version                        | Update to `2.0.17`                                 |
| Uses Logback as logging backend                       | Upgrade to Logback `1.4.x+`                        |
| Uses Log4j2 as logging backend                        | Switch to `log4j-slf4j2-impl`                      |
| Uses Flyway API directly                              | Review Flyway 10+ migration notes                  |
| Has a BOM managing `jackson-annotations` < 2.21       | Override to `2.21` in `dependencyManagement`       |
| Uses `simplelogger.properties`                        | No changes needed (format unchanged)               |
