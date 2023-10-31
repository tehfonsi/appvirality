# App Virality *Beta* [![Release](https://img.shields.io/github/release/tehfonsi/appvirality.svg?label=Jitpack)](https://jitpack.io/#tehfonsi/appvirality)
Tools to help your android app go viral.

**Warning:** This is very early version, use at your own risk!

# Tools

**1. Rating & feedback**, mimics the behaviour as described in this [blog post by Circa](https://medium.com/circa/the-right-way-to-ask-users-to-review-your-app-9a32fd604fca#.dk4uq4jm7).

It should help you get better ratings and build a better app, using the following elements:
- Dialog
- Embedded View
- Recyclerview adapter

**2. Invite**

Ask the user to invite friends, using the following elements:
- Snackbar

**More coming soon**

##Include in your project
###Using Maven

Add it to your build.gradle with:
```gradle
repositories {
    maven { url "https://jitpack.io" }
}
```
and:

```gradle
dependencies {
    compile 'com.github.tehfonsi:appvirality:{latest version}'
}
```

#Screenshots
##Rating & Feedback
![Image](https://raw.githubusercontent.com/tehfonsi/appvirality/develop/media/rating_dialog_small.png)
![Image](https://raw.githubusercontent.com/tehfonsi/appvirality/develop/media/rating_recyclerview_small.png)

##Invite
![Image](https://raw.githubusercontent.com/tehfonsi/appvirality/develop/media/invite_snackbar_small.png)

#Changelog

0.2.0: Added invite snackbar, moved some files and methods

#Developed By

* Stephan Schober 
 * [Twitter @tehfonsi](https://twitter.com/tehfonsi)

##Already used in the following apps
(feel free to send me new projects)

* [poshpeep](https://play.google.com/store/apps/details?id=com.poshpeep)
* [Sprit Club](https://play.google.com/store/apps/details?id=at.idev.spritpreise)

#License

    Copyright 2016 Stephan Schober

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
