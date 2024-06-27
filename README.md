# compose_feasability
Zach and Josh @ Cru Kotlin feasability testing repository


### Problems:
- Xcode and MacOS is required to fully utilize Kotlin Multiplatform Compose development.

### Recommendations:
- "In general, write your implementation as common code whenever possible instead of duplicating functionality in platform-specific source sets."
- "Generally the consensus seems to be that you use Jetpack Compose for your Android UI layer, swift UI for your iOS UI layer, and then Kotlin Multiplatform for your data layer and potentially your view models as well if you want to share code between the two."

### Version documentation
Kotlin Multiplatform Mobile
- 0.8.2(233)-8 JetBrains

Kotlin
- 1.9.29

Device Emulator (Subject to change)
- Pixel 3a API 34
- Android 14.0 ("UpsideDownCake") | x86_64

Gradle
- 8.4.1

## Authorization library in use:
Open source Firebase Kotlin SDK + Firebase Authentication
- Another option we had was Atlas Device SDKs by MongoDB (previously known as Realm)

## Positive and negative notes:
### Positives
- The ability to compile to many different targets (for example, Apple watches, iOS, Android, Web, Native)
- emphasis on code reusability
	- share logic across the various platforms, simplifying maintenance 
- You can utilize platform specific UI components in conjunction with Compose UI, leading to performance that is potentially on par with native development
- You still have the ability to tap into platform specific APIs, you are not limited by the APIs and libraries provided by Kotlin Multiplatform
- Kotlin is interoperable with Java and Swift
- Kotlin is statically typed, decreasing chances of bugs in applications
- Broad range of platforms that KMP supports.
- large changes being made constantly
### Negatives
- Platform specific requirements must be heavily understood before taking on projects
- There is still a possibility you will need to write platform specific UI code.
- The Kotlin community is smaller compared to Flutter, which may affect the availability of libraries, third party tools, and the responsiveness of community support.
- Cross-platform desktop UIs have been neglected for quite a while. Swing has been a standard for some time, but it’s old and unmaintained. JetBrains is hoping to bring Compose fully to Desktop and Web
- Compose UI is apparently 'technically usable for iOS' but it's still in beta
	- SwiftUI is the alternative for it
	- More fully fledged for Android
	- Compose for Web is in alpha, may be unstable
- large changes are being made all the time, there is a lot to learn (both good and bad)
- Navigation across application screens is still in an experimental phase, deep links are not supported. Also cannot override default back handling (what button or gesture to use for going to previous Composable)
- generally a lack of documentation
- Permissions still need to be managed on the individual platforms (you can create a package to handle the permissions across the different platforms yourself but it is an initial time consuming process)
- Dependency injection is a bit of a pain it seems but not terrible (a good option is Koin)
- User auth is doable but there is no official library for it as far as we are aware. There is an open source Firebase SDK that offers user auth, and it works well.

