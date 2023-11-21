# alibs - A quality of life library for kotlin

Alibs brings you everything you need for a basic kotlin app.

## Getting started

To access the library use the repository and dependency below:
<details>
  <summary>Gradle + Groovy</summary>
  ```gradle
    repositories {
     maven { url 'https://repo.arcver.se/releases/' }
    }
    dependencies {
      implementation("se.arcver:alibs:1.0.2")
    }
  ```
</details>
<details>
  <summary>Gradle + Kotlin</summary>
  ```kt
    repositories {
     maven("https://repo.arcver.se/releases/")
    }
    dependencies {
      implementation("se.arcver:alibs:1.0.2")
    }
  ```
</details>
<details>
  <summary>Maven</summary>
  ```xml
    <repository>
      <id>arcverse-repo</id>
      <url>https://repo.arcver.se/releases/</url>
    </repository>
    <dependency>
      <groupId>se.arcver</groupId>
      <artifactId>alibs</artifactId>
      <version>1.0.2</version>
      <scope>provided</scope>
    </dependency>
  ```
</details>

## Features
<details>
<summary>Includes jetbrains exposed ORM</summary>

The [**jetbrains exposed**](https://github.com/JetBrains/Exposed) orm is built for kotlin and includes many great
functionalities.
Usable drivers with our inbuilt DatabaseManager are:

- mysql
- mariadb
- postgres
</details>
<details>
<summary>Includes flyway migrations</summary>

Flyway migrations combines flawlessly with exposed and offers a great way to manage your database migrations.
</details>
<details>
<summary>Includes TheFruxz Ascend kotlin library</summary>

[**TheFruxz's Ascend**](https://github.com/TheFruxz/Ascend) kotlin library is great and brings many utility classes to kotlin.
</details>
<details>
<summary>Includes the Ktor library</summary>
Ktor is a great kotlin library for creating web applications. It also delivers a fully functional http client api using okhttp.
</details>
<details>
<summary>Includes KotlinX Coroutines and Serialization</summary>

KotlinX Coroutines and Serialization are great kotlin libraries for async programming and json serialization.
</details>
<details>
<summary>Includes GSON</summary>

GSON is a great json serialization library.
</details>
<details>
<summary>Includes lombok</summary>

Lombok is a great library for reducing boilerplate code.
</details>
<details>
<summary>Includes dotenv-kotlin</summary>

dotenv-kotlin is a great library for loading environment variables from a .env file.
</details>
<details>
<summary>Includes jedis client</summary>

Jedis is great for accessing a redis server and caching there.
</details>
