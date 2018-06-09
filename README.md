# draincraft

A bukkit plugin that drains all the water in a small lake.

## Requirements

To build the sofware you require:

- Apache [Maven](https://maven.apache.org/download.cgi) installed on the computer 
- JAVA JDK (?)
- A bukkit server (version >= 1.21)


## Build


Clone the repository with 
```bash
git clone https://github.com/carlfranz/draincraft.git
```

Then enter the download folder and run maven package command

```bash
cd draincraft/
mvn package
```

Once finished copy the jar `target/DrainCraft-0.0.1-SNAPSHOT.jar` to the `plugin/` folder of your bukkit server.

## Usage

When in game open the console and type:

```
/dc_drain <blocks>
```
Where blocks is the maximum radius (?). 

or

```
/dc_drain linear
```

To remove only one layer of water.

**Warning!** don't try to drain huge quantities of water or the server will crash. Some server admin will **close your account** and delete your saves. 