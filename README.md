# kmp_feasability
Zach and Josh @ Cru Kotlin feasability testing repository

## [Helpful repo for libraries](https://github.com/AAkira/Kotlin-Multiplatform-Libraries?tab=readme-ov-file)

### Problems:
- Xcode and MacOS is required to fully utilize Kotlin Multiplatform Compose development.

### Recommendations:
- "In general, write your implementation as common code whenever possible instead of duplicating functionality in platform-specific source sets."
- "Generally the consensus seems to be that you use Jetpack Compose for your Android UI layer, swift UI for your iOS UI layer, and then Kotlin Multiplatform for your data layer and potentially your view models as well if you want to share code between the two."

### Version documentation
Kotlin Multiplatform Mobile
- 0.8.2(233)-8 JetBrains

Kotlin
- 2.0.0

Device Emulator (Subject to change)
- Pixel 3a API 34
- Android 14.0 ("UpsideDownCake") | x86_64

Gradle
- 8.5.0

## Major dependencies:
- Atlas Device SDK (Realm) for Sync Database and user authentication/management.
- Ktor for performing the API call to KnowGod.com.
- Arrow for functionally handling exceptions in API call (not necessary but a useful library).
- Kermit for quick and useful debug logging.
- Voyager for screen navigation. Kotlin Multiplatform has a default library for screen navigation, we decided not to implement it due to preference. As far as we know, there are no issues with the default library.
- __NOTE:__ Ktorfit was not used due to time constraints, but it is a seemingly decent alternative to Retrofit.

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
- Large changes being made constantly (good and bad fact).
- Active and seemingly growing community of developers.
- User auth is doable both through Atlas Device SDK and the open source Firebase SDK. Both behave very similarly and are very easy to implement.
- Atlas Device SDK is surprisingly fleshed out and works well. The documentation, however, is not. It offers a multitude of services, like Realm, device to cloud syncing, user management/auth, and decent analytics on operations. Definitely recommend.


### Negatives
- Platform specific requirements must be heavily understood before taking on projects
- There is still a possibility you will need to write platform specific UI code, especially if you want your app to look native to the device you are intending the UI for.
- The KMP community is currently smaller compared to Flutter, which may affect the availability of libraries, third party tools, and the responsiveness of community support.
- Cross-platform desktop UIs have been neglected for quite a while. Swing has been a standard for some time, but it’s old and unmaintained. JetBrains is hoping to bring Compose fully to Desktop and Web
- Compose UI is apparently 'technically usable for iOS' but it's still in beta
	- SwiftUI is the alternative for it
	- More fully fledged for Android
	- Despite this, we found it works quite well and is practically fully functional.
- Compose for Web is in alpha, may be unstable.
- Navigation across application screens is still in an experimental phase, deep links are not supported by the default navigation library. Also apparently cannot override default back handling (what button or gesture to use for going to previous Composable), but this makes sense since it is highly dependent on OS.
- Generally a lack of documentation, mostly on the business logic side of things.
- Permissions still need to be managed on the individual platforms (you can create a package to handle the permissions across the different platforms yourself but it is an initial time consuming process).
- Dependency injection is a bit of a pain it seems but not terrible. A good library for it is Koin. @Inject annotation would not work from our attempts, but this isn't really an issue.
- Because everything is "bleeding edge," a lot of dependencies do not work well together, and it can be very time consuming trying to manage dependencies that are updating very often and causing Gradle issues. The chance of one dependency breaking a project seems quite high.
- You will be the first to face an issue in a lot of cases, so there is not a lot of support online or forums/threads to find where you can quickly solve issues.


### Thoughts on KMP and Compose Multiplatform
- Compose Multiplatform is a great library that is very similar to Jetpack Compose, which is easy to learn and use. The downside is that it does have more of an Android-like feel.
- It has good flexibility in how much code you want to share between platforms. You can put some or all of the code in the common code shared between platforms and write the rest in platform-specific code.
- Some features are lacking in Kotlin multiplatform and compose because it is still new and not all libraries are fully built out and up to date. It is good for smaller projects at the moment but you could run into problems on bigger projects with more features.
- The future of KMP and Compose Multiplatform appears to be really good and the support is growing, so libraries will continue to grow and be developed which will make it better and better in the future. 

