[![][Project Logo]][Website]
SpoutAPI
========
SpoutAPI is an API for the spout framework. Spout is a plugin for Bukkit-based servers and a Minecraft client mod system that exposes new API for other plugins to utilize, in an attempt to bring the Minecraft Multiplayer experience to parity with the single player modding experience.

Copyright &copy; 2011, SpoutDev <dev@getspout.org>  
SpoutAPI is licensed under [GNU LESSER GENERAL PUBLIC LICENSE Version 3][License]

Visit our [website][Website].  
Get support on our [Bukkit forum thread][Forum].  
Track and submit issues and bugs on our [issue tracker][Issues].

Follow SpoutAPI on Twitter [@SpoutDev][Twitter]

Source
------
The latest and greatest source of SpoutAPI can be found on [GitHub].  
Download the latest builds from [Jenkins].  
View the latest [Javadoc].

Compiling
---------
SpoutAPI uses Maven to handle its dependencies.

SpoutAPI requires Bukkit (preferrably the latest version or source).  
* Install [Maven 2 or 3](http://maven.apache.org/download.html)  
* Checkout this repo and run: `mvn clean package`

Coding and Pull Request Formatting
----------------------------------
* Generally follows the Oracle coding standards.
* SpoutAPI uses tabs, no spaces.
* No 80 column limit or midstatement newlines.
* Pull requests must compile and work.
* Pull requests must be formatted properly.
* If you change a Packet or Widget's read/write/number of bytes, be sure to increment the version on both the server and client.

Please follow the above conventions if you want your pull requests accepted.

[Project Logo]: http://assets.craftfire.com/img/logo/spout_327x150.png
[License]: http://www.gnu.org/licenses/lgpl.html
[Website]: http://www.getspout.org
[Forum]: http://bit.ly/getspout
[GitHub]: https://github.com/SpoutDev/SpoutAPI
[Javadoc]: http://jddev.getspout.org/
[Jenkins]: http://ci.getspout.org/view/SpoutDev
[Issues]: https://github.com/SpoutDev/Spout/issues
[Twitter]: http://twitter.com/SpoutDev
