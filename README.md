# ARCHIVED

Due to the industry moving towards a docker-centric, kubernetes world and the differences between administrating FreeBSD and Linux I chose to move away from FreeNAS. Altough I really liked developing this api client (and it never really saw the light of day) I don't have a personal interest in developing it anymore.

# FreeNas_REST_ApiClient
FreeNAS REST Api Client written in Kotlin using RxKotlin and Fuel

# Status

| Master       | Beta | Dev               |
|--------------|------|-------------------|
| [![codebeat badge](https://codebeat.co/badges/6138e73e-6b19-4db8-87fb-8b60401539a3)](https://codebeat.co/projects/github-com-markusressel-freenas_rest_apiclient-master) | [![codebeat badge](https://codebeat.co/badges/7ff89df8-4f9c-41b9-af94-228b8cf4f0e4)](https://codebeat.co/projects/github-com-markusressel-freenas_rest_apiclient-beta) | [![codebeat badge](https://codebeat.co/badges/d7538ca3-6706-410e-a5fa-44bf815e6206)](https://codebeat.co/projects/github-com-markusressel-freenas_rest_apiclient-dev) |

# How to use

## Gradle
To use this library just include it in your depencencies using

    repositories {
        ...
        maven { url "https://jitpack.io" }
    }

in your project build.gradle file and

    dependencies {
        compile('com.github.markusressel:Freenas_REST_ApiClient:1.0.0') {
            exclude module: 'app'
            transitive = true
        }
    }

in your desired module ```build.gradle``` file.
