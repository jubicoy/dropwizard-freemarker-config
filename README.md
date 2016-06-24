# dropwizard-freemarker-config

Library for using `freemarker` templates as configuration files.
Inspiration for the library stems from Ruby on Rails framework that
allows loading `config/database.yml` as an `erb` template.

## Usage

Add dependency.

```xml
<dependency>
    <groupId>fi.jubic</groupId>
    <artifactId>dropwizard-freemarker-config</artifactId>
    <version>0.1</version>
</dependency>
```

Set `ConfigurationFactoryFactory` during application bootstrapping.

```java
@Override
public void initialize (Bootstrap<Configuration> bootstrap) {

    bootstrap.setConfigurationFactoryFactory(
            new FtlConfigurationFactoryFactory<>(
                    new FtlDefaultEnvProvider()
            )
    );

}
```

Configuration specified when launching the packaged application jar
can contain `freemarker` notation and has environment variables
injected as `env` field of the model.

## Examples

Database credentials with fallback values:

```yaml
database:
  user: ${(env.DB_USER)!"fallback"}
  password: ${(env.DB_PASSWORD)!"fallback"}
```

Conditional sections. Toggling Hibernate SQL pretty printing on in
development env:

```yaml
database:
  properties:
  <#if (env.DEPLOYMENT_ENVIRONMENT)?? & env.DEPLOYMENT_ENVIRONMENT == "development">
    hibernate.format_sql: true
  </#if>
```
