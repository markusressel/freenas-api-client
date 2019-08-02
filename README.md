# freenas-api-client
A library to easily interact with the FreeNAS API.

# Status

| Master       | Beta | Dev               |
|--------------|------|-------------------|
| [![codebeat badge](https://codebeat.co/badges/6138e73e-6b19-4db8-87fb-8b60401539a3)](https://codebeat.co/projects/github-com-markusressel-freenas_rest_apiclient-master) | [![codebeat badge](https://codebeat.co/badges/7ff89df8-4f9c-41b9-af94-228b8cf4f0e4)](https://codebeat.co/projects/github-com-markusressel-freenas_rest_apiclient-beta) | [![codebeat badge](https://codebeat.co/badges/d7538ca3-6706-410e-a5fa-44bf815e6206)](https://codebeat.co/projects/github-com-markusressel-freenas_rest_apiclient-dev) |

# How to use

This repository includes an API client for 
API V1 (not feature complete) as well as API V2 (wip).

FreeNas 11 has introduced a Websocket based API next to the REST API.
**freenas-api-client** uses this Websocket API for V2.

While V1 uses RxJava to minimize threading overhead, V2 uses Kotlin 
coroutines for that.

## Gradle
To use this library just include it in your depencencies using

    repositories {
        ...
        maven { url "https://jitpack.io" }
    }

in your project build.gradle file and

    dependencies {
        // for API V1
        implementation('com.github.markusressel:freenas-api-client:api_v1:1.0.0') {
            transitive = true
        }
        
        // for API V2
        implementation('com.github.markusressel:freenas-api-client:api_v2:1.0.0') {
            transitive = true
        }
    }

in your desired module ```build.gradle``` file.

## Example

### API V1

```kotlin
val client = FreeNasRestApiV1Client()
client.setBaseUrl("https://my.server.com/api")
// optional basic auth setup
client.setBasicAuthConfig(BasicAuthConfig("root", "pw"))

client.getUsers().subscribe({ result ->
    // do something with the result list
}, { error ->
    // handle error
})
```

### API V2

```kotlin
val client = FreeNasRestApiV2Client(
    baseUrl = "wss://my.server.com/websocket",
    // although it is called BasicAuthConfig these values are used
    // as websocket login information
    auth = BasicAuthConfig(
        username = "root", 
        password = "some_pw"
    )
)

runBlocking {
    client.getUsers().fold(
    success = { 
        // ok
    }, failure = { error -> 
        throw  error
    })
}


            

```