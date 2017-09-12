Technica 2
==========

Technica is an indev Minecraft mod that attempts to resemble industrial processes.
Most of the content in this mod has a counterpart in real life but,
in order to create a satisfying gameplay experience many things within this mod
only resemble real life up to a certain point.

## License
This mod is licensed under the
[**Don't Be a Jerk License**](https://github.com/Busti/Technica2/blob/master/license.md)
created by CoFH.

## Building

1. Clone this repository via 
  - SSH `git clone git@github.com:Busti/Technica2.git` or 
  - HTTPS `git clone https://github.com/Busti/Technica2.git`
2. Setup workspace 
  - Decompiled source `gradlew setupDecompWorkspace`
  - Obfuscated source `gradlew setupDevWorkspace`
  - CI server `gradlew setupCIWorkspace`
3. Build `gradlew build`. Jar will be in `build/libs`
4. For core developer: Setup IDE
  - IntelliJ: Import into IDE, execute `gradlew genIntellijRuns` and refresh the gradle project
  - Eclipse: execute `gradlew eclipse`
5. For add-on developer: Core-Mod Detection
  - Maven repository coming soon...

## Contribution

Before you want to add major changes, you might want to discuss them with us first, before wasting your time.
If you are still willing to contribute to this project, you can contribute via [Pull-Request](https://help.github.com/articles/creating-a-pull-request).

Here are a few things to keep in mind that will help get your PR approved.

* A PR should be focused on content. Any PRs where the changes are only syntax will be rejected. (With the exception of **major** bug fixes)
* Use the file you are editing as a style guide.
* Consider your feature.
  - Is your suggestion already possible using Vanilla + AE2?
  - Make sure your feature isn't already in the works, or hasn't been rejected previously.
  - Does your feature simplify another feature of AE2? These changes will not be accepted.
  - If your feature can be done by any popular mod, discuss with us first.
  

**Getting Started**

1. Fork this repository
2. Clone the fork via
  * SSH `git clone git@github.com:<your username>/Technica2.git` or 
  * HTTPS `git clone https://github.com/<your username>/Technica2.git`
3. Change code base
4. Add changes to git `git add -A`
5. Commit changes to your clone `git commit -m "<summery of made changes>"`
6. Push to your fork `git push`
7. Create a Pull-Request on GitHub
8. Wait for review
9. Squash commits for cleaner history

If you are only doing single file pull requests, GitHub supports using a quick way without the need of cloning your fork. Also read up about [synching](https://help.github.com/articles/syncing-a-fork) if you plan to contribute on regular basis.


## Technica2 Localization

### English Text

`en_us` is included in this repository, fixes to typos are welcome.

### Encoding

Files must be encoded as UTF-8.

### New or updated Translations

The language files are located in `/src/main/resources/assets/technica2/lang/` and use the [appropriate locale code](http://minecraft.gamepedia.com/Language) as name and `.lang` as extension.

To update an translation edit the corresponding file and improve/correct the existing entry. Or copy any entries from `en_US.lang` for missing translation.

To create a new translation, copy the contents of `en_us.lang`, create a new file with appropriate filename, and translate it to your language.

Please keep in mind that we use [String format](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html) to pass additional data to the text for displaying.
Therefore you should preserve parts like `%s` or `%1$d%%`, which allows us to replace them with the correct values while you still have the option to change their order for match the rules of grammar.
This might not be possible for some languages. Should this be the case, please contact us.

Newly submitted languages and updates will be checked using translator services before merging. 