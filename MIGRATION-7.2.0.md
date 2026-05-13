# IOM Test Framework 7.2.0 Migration Guide

## Dependency Upgrades

### SLF4J 1.7.x → 2.0.17

**What changed:** SLF4J was upgraded from `1.7.30` to `2.0.17`.

**Why:** HikariCP 7.0.2 requires SLF4J 2.x. Using SLF4J 1.7.x alongside
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

### Flyway 9.5.0 → 12.6.0

**What changed:** Flyway was upgraded from `9.5.0` to `12.6.0`.
Two new dependencies were added:

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-database-postgresql</artifactId>
    <version>12.6.0</version>
</dependency>
<dependency>
    <groupId>tools.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>3.1.1</version>
</dependency>
```

**Why:**

- Since Flyway 10, database-specific support was extracted into separate modules.
  PostgreSQL support requires `flyway-database-postgresql`.
- Flyway 12.x internally uses Jackson 3.x (`tools.jackson` package namespace).
  Without this dependency, `NoClassDefFoundError: tools.jackson.databind.ObjectMapper`
  occurs at runtime. Version `3.1.1` is required to match Flyway 12.6.0's
  transitive dependency — earlier versions (e.g. `3.0.0`) cause
  `NoClassDefFoundError: Could not initialize class tools.jackson.databind.ObjectMapper$PrivateBuilder`
  due to initialization bugs.

**Impact on consumer projects:**

- **No code changes required** if you only use Flyway through iom-test-framework's
  `OMSDbHandler`. The Flyway API for basic migration (`Flyway.configure()...load().migrate()`)
  is backward compatible.
- **Jackson 3.x coexists with Jackson 2.x** — they use different package
  namespaces (`tools.jackson.*` vs `com.fasterxml.jackson.*`), so there are
  no classpath conflicts.
- If you use Flyway's Java-based migrations or callbacks, review the
  [Flyway 10 release notes](https://documentation.red-gate.com/flyway/release-notes-and-older-versions)
  for any API changes.

---

### HikariCP 5.1.0 → 7.0.2

**What changed:** HikariCP was upgraded from `5.1.0` to `7.0.2`.

**Impact on consumer projects:**

- **No code changes required** for typical usage through iom-test-framework.
- HikariCP 7.x requires **Java 11+** (already satisfied by the project's
  Java 17 target).
- HikariCP 7.x requires **SLF4J 2.x** (addressed by the SLF4J upgrade above).

---

## Summary of Required Actions

| If your project...                              | Action                                       |
|--------------------------------------------------|----------------------------------------------|
| Inherits SLF4J from iom-test-framework only      | No action needed                             |
| Declares its own SLF4J version                   | Update to `2.0.17`                           |
| Uses Logback as logging backend                  | Upgrade to Logback `1.4.x+`                  |
| Uses Log4j2 as logging backend                   | Switch to `log4j-slf4j2-impl`                |
| Uses Flyway API directly                         | Review Flyway 10+ migration notes            |
| Uses `simplelogger.properties`                   | No changes needed (format unchanged)         |
