package net.minecraft.server;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit; // PaperSpigot

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// CraftBukkit start
import java.io.PrintStream;
import org.apache.logging.log4j.Level;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.LoggerOutputStream;
import org.bukkit.craftbukkit.SpigotTimings; // Spigot
import org.bukkit.event.server.ServerCommandEvent;
// CraftBukkit end

import fr.maxlego08.kronos.Kronos;

public class DedicatedServer extends MinecraftServer implements IMinecraftServer {

    private static final Logger i = LogManager.getLogger();
    private final List j = Collections.synchronizedList(new ArrayList());
    private RemoteStatusListener k;
    private RemoteControlListener l;
    public PropertyManager propertyManager; // CraftBukkit - private -> public
    private EULA n;
    private boolean generateStructures;
    private EnumGamemode p;
    private boolean q;

    // CraftBukkit start - Signature changed
    public DedicatedServer(joptsimple.OptionSet options) {
        super(options, Proxy.NO_PROXY);
        // super(file1, Proxy.NO_PROXY);
        // CraftBukkit end
        new ThreadSleepForever(this, "Server Infinisleeper");
    }

    protected boolean init() throws java.net.UnknownHostException { // CraftBukkit - throws UnknownHostException
        ThreadCommandReader threadcommandreader = new ThreadCommandReader(this, "Server console handler");

        threadcommandreader.setDaemon(true);
        threadcommandreader.start();

        // CraftBukkit start - TODO: handle command-line logging arguments
        java.util.logging.Logger global = java.util.logging.Logger.getLogger("");
        global.setUseParentHandlers(false);
        for (java.util.logging.Handler handler : global.getHandlers()) {
            global.removeHandler(handler);
        }
        global.addHandler(new org.bukkit.craftbukkit.util.ForwardLogHandler());

        final org.apache.logging.log4j.core.Logger logger = ((org.apache.logging.log4j.core.Logger) LogManager.getRootLogger());
        for (org.apache.logging.log4j.core.Appender appender : logger.getAppenders().values()) {
            if (appender instanceof org.apache.logging.log4j.core.appender.ConsoleAppender) {
                logger.removeAppender(appender);
            }
        }

        new Thread(new org.bukkit.craftbukkit.util.TerminalConsoleWriterThread(System.out, this.reader)).start();

        System.setOut(new PrintStream(new LoggerOutputStream(logger, Level.INFO), true));
        System.setErr(new PrintStream(new LoggerOutputStream(logger, Level.WARN), true));
        // CraftBukkit end

        /*
         * Maxlego08
         * */
        
        Kronos kronos = new Kronos();
        kronos.init();
        i.info("Lancement de la class principale Kronos");
        
        
        i.info("Lancement du serveur en version 1.7.10");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            i.warn("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        i.info("Chargement du fichier server.properties");
        this.propertyManager = new PropertyManager(this.options); // CraftBukkit - CLI argument support
        // PaperSpigot start - Display a EULA warning then continue
        File EULALock = new File( ".eula-lock");
        // Spigot Start
        boolean eulaAgreed = Boolean.getBoolean( "com.mojang.eula.agree" );
        if ( eulaAgreed )
        {
            System.err.println( "You have used the Spigot command line EULA agreement flag." );
            System.err.println( "By using this setting you are indicating your agreement to Mojang's EULA (https://account.mojang.com/documents/minecraft_eula)." );
            System.err.println( "If you do not agree to the above EULA please stop your server and remove this flag immediately." );
            // Spigot End
        } else {
            if (!EULALock.exists()) {
                System.err.println("WARNING: By using this server you are indicating your agreement to Mojang's EULA (https://account.mojang.com/documents/minecraft_eula)");
                System.err.println("If you do not agree to the above EULA please stop this server and remove it from your system immediately.");
                System.err.println("The server will start in 5 seconds, you will only see this message and have to wait this one time.");
                try {
                    EULALock.createNewFile();
                } catch (IOException e1) {
                    System.err.println("Unable to create EULA lock file");
                    e1.printStackTrace();
                }
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException ex) {
                }
            }
                // PaperSpigot end
            if (this.N()) {
                this.c("127.0.0.1");
            } else {
                this.setOnlineMode(this.propertyManager.getBoolean("online-mode", false));
                this.c(this.propertyManager.getString("server-ip", ""));
            }

            this.setSpawnAnimals(this.propertyManager.getBoolean("spawn-animals", true));
            this.setSpawnNPCs(this.propertyManager.getBoolean("spawn-npcs", true));
            this.setPvP(this.propertyManager.getBoolean("pvp", true));
            this.setAllowFlight(this.propertyManager.getBoolean("allow-flight", false));
            this.setTexturePack(this.propertyManager.getString("resource-pack", ""));
            this.setMotd(this.propertyManager.getString("motd", "A Minecraft Server"));
            this.setForceGamemode(this.propertyManager.getBoolean("force-gamemode", false));
            this.setIdleTimeout(this.propertyManager.getInt("player-idle-timeout", 0));
            if (this.propertyManager.getInt("difficulty", 1) < 0) {
                this.propertyManager.setProperty("difficulty", Integer.valueOf(0));
            } else if (this.propertyManager.getInt("difficulty", 1) > 3) {
                this.propertyManager.setProperty("difficulty", Integer.valueOf(3));
            }

            this.generateStructures = this.propertyManager.getBoolean("generate-structures", true);
            int gamemode = this.propertyManager.getInt("gamemode", EnumGamemode.SURVIVAL.getId()); // CraftBukkit - Unique name to avoid stomping on logger

            this.p = WorldSettings.a(gamemode); // CraftBukkit - Use new name
            i.info("Default game type: " + this.p);
            InetAddress inetaddress = null;

            if (this.getServerIp().length() > 0) {
                inetaddress = InetAddress.getByName(this.getServerIp());
            }

            if (this.L() < 0) {
                this.setPort(this.propertyManager.getInt("server-port", 25565));
            }
            // Spigot start
            this.a((PlayerList) (new DedicatedPlayerList(this)));
            org.spigotmc.SpigotConfig.init();
            org.spigotmc.SpigotConfig.registerCommands();
            // Spigot end
            // PaperSpigot start
            org.github.paperspigot.PaperSpigotConfig.init();
            org.github.paperspigot.PaperSpigotConfig.registerCommands();
            // PaperSpigot stop

            i.info("Generating keypair");
            this.a(MinecraftEncryption.b());
            i.info("Lancement du serveur minecraft avec l'ip:  " + (this.getServerIp().length() == 0 ? "localhost" : this.getServerIp()) + ":" + this.L());

        if (!org.spigotmc.SpigotConfig.lateBind) {
            try {
                this.ai().a(inetaddress, this.L());
            } catch (Throwable ioexception) { // CraftBukkit - IOException -> Throwable
                i.warn("**** Une erreur est apparu lors de l'attribution du port !");
                i.warn("The exception was: {}", new Object[] { ioexception.toString()});
                i.warn("Perhaps a server is already running on that port?");
                return false;
            }
        }

            // Spigot Start - Move DedicatedPlayerList up and bring plugin loading from CraftServer to here
            // this.a((PlayerList) (new DedicatedPlayerList(this))); // CraftBukkit
            server.loadPlugins();
            server.enablePlugins(org.bukkit.plugin.PluginLoadOrder.STARTUP);
            // Spigot End

            if (!this.getOnlineMode()) {
                i.warn("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
                i.warn("The server will make no attempt to authenticate usernames. Beware.");
                i.warn("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
                i.warn("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
            }

            if (this.aE()) {
                this.getUserCache().c();
            }

            if (!NameReferencingFileConverter.a(this.propertyManager)) {
                return false;
            } else {
                // this.a((PlayerList) (new DedicatedPlayerList(this))); // CraftBukkit - moved up
                this.convertable = new WorldLoaderServer(server.getWorldContainer()); // CraftBukkit - moved from MinecraftServer constructor
                long j = System.nanoTime();

                if (this.O() == null) {
                    this.k(this.propertyManager.getString("level-name", "world"));
                }

                String s = this.propertyManager.getString("level-seed", "");
                String s1 = this.propertyManager.getString("level-type", "DEFAULT");
                String s2 = this.propertyManager.getString("generator-settings", "");
                long k = (new Random()).nextLong();

                if (s.length() > 0) {
                    try {
                        long l = Long.parseLong(s);

                        if (l != 0L) {
                            k = l;
                        }
                    } catch (NumberFormatException numberformatexception) {
                        k = (long) s.hashCode();
                    }
                }

                WorldType worldtype = WorldType.getType(s1);

                if (worldtype == null) {
                    worldtype = WorldType.NORMAL;
                }

                this.at();
                this.getEnableCommandBlock();
                this.l();
                this.getSnooperEnabled();
                this.c(this.propertyManager.getInt("max-build-height", 256));
                this.c((this.getMaxBuildHeight() + 8) / 16 * 16);
                this.c(MathHelper.a(this.getMaxBuildHeight(), 64, 256));
                this.propertyManager.setProperty("max-build-height", Integer.valueOf(this.getMaxBuildHeight()));
                i.info("Preparing level \"" + this.O() + "\"");
                this.a(this.O(), this.O(), k, worldtype, s2);
                long i1 = System.nanoTime() - j;
                String s3 = String.format("%.3fs", new Object[] { Double.valueOf((double) i1 / 1.0E9D)});

                Bukkit.getConsoleSender().sendMessage("??8[??cKronos??8] ??eLancement du serveur termin?? ! Lancement effectu?? en ??6 " + s3 + " ??e!");
                
                if (this.propertyManager.getBoolean("enable-query", false)) {
                    i.info("Starting GS4 status listener");
                    this.k = new RemoteStatusListener(this);
                    this.k.a();
                }

                if (this.propertyManager.getBoolean("enable-rcon", false)) {
                    i.info("Starting remote control listener");
                    this.l = new RemoteControlListener(this);
                    this.l.a();
                    this.remoteConsole = new org.bukkit.craftbukkit.command.CraftRemoteConsoleCommandSender(); // CraftBukkit
                }

                // CraftBukkit start
                if (this.server.getBukkitSpawnRadius() > -1) {
                    i.info("'settings.spawn-radius' in bukkit.yml has been moved to 'spawn-protection' in server.properties. I will move your config for you.");
                    this.propertyManager.properties.remove("spawn-protection");
                    this.propertyManager.getInt("spawn-protection", this.server.getBukkitSpawnRadius());
                    this.server.removeBukkitSpawnRadius();
                    this.propertyManager.savePropertiesFile();
                }
                // CraftBukkit end

        if (org.spigotmc.SpigotConfig.lateBind) {
            try {
                this.ai().a(inetaddress, this.L());
            } catch (Throwable ioexception) { // CraftBukkit - IOException -> Throwable
                i.warn("**** FAILED TO BIND TO PORT!");
                i.warn("The exception was: {}", new Object[] { ioexception.toString()});
                i.warn("Perhaps a server is already running on that port?");
                return false;
            }
        }
                return true;
            }
        }
        return true; // PaperSpigot
    }

    // CraftBukkit start
    public PropertyManager getPropertyManager() {
        return this.propertyManager;
    }
    // CraftBukkit end

    public boolean getGenerateStructures() {
        return this.generateStructures;
    }

    public EnumGamemode getGamemode() {
        return this.p;
    }

    public EnumDifficulty getDifficulty() {
        return EnumDifficulty.getById(this.propertyManager.getInt("difficulty", 1));
    }

    public boolean isHardcore() {
        return this.propertyManager.getBoolean("hardcore", false);
    }

    protected void a(CrashReport crashreport) {}

    public CrashReport b(CrashReport crashreport) {
        crashreport = super.b(crashreport);
        crashreport.g().a("Is Modded", (Callable) (new CrashReportModded(this)));
        crashreport.g().a("Type", (Callable) (new CrashReportType(this)));
        return crashreport;
    }

    protected void t() {
        System.exit(0);
    }

    public void v() { // CraftBukkit - protected -> public (decompile error?)
        super.v();
        this.aB();
    }

    public boolean getAllowNether() {
        return this.propertyManager.getBoolean("allow-nether", true);
    }

    public boolean getSpawnMonsters() {
        return this.propertyManager.getBoolean("spawn-monsters", true);
    }

    public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
        mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(this.aC().getHasWhitelist()));
        mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(this.aC().getWhitelisted().length));
        super.a(mojangstatisticsgenerator);
    }

    public boolean getSnooperEnabled() {
        return this.propertyManager.getBoolean("snooper-enabled", true);
    }

    public void issueCommand(String s, ICommandListener icommandlistener) {
        this.j.add(new ServerCommand(s, icommandlistener));
    }

    public void aB() {
        SpigotTimings.serverCommandTimer.startTiming(); // Spigot
        while (!this.j.isEmpty()) {
            ServerCommand servercommand = (ServerCommand) this.j.remove(0);

            // CraftBukkit start - ServerCommand for preprocessing
            ServerCommandEvent event = new ServerCommandEvent(this.console, servercommand.command);
            this.server.getPluginManager().callEvent(event);
            servercommand = new ServerCommand(event.getCommand(), servercommand.source);

            // this.getCommandHandler().a(servercommand.source, servercommand.command); // Called in dispatchServerCommand
            this.server.dispatchServerCommand(this.console, servercommand);
            // CraftBukkit end
        }
        SpigotTimings.serverCommandTimer.stopTiming(); // Spigot
    }

    public boolean X() {
        return true;
    }

    public DedicatedPlayerList aC() {
        return (DedicatedPlayerList) super.getPlayerList();
    }

    public int a(String s, int i) {
        return this.propertyManager.getInt(s, i);
    }

    public String a(String s, String s1) {
        return this.propertyManager.getString(s, s1);
    }

    public boolean a(String s, boolean flag) {
        return this.propertyManager.getBoolean(s, flag);
    }

    public void a(String s, Object object) {
        this.propertyManager.setProperty(s, object);
    }

    public void a() {
        this.propertyManager.savePropertiesFile();
    }

    public String b() {
        File file1 = this.propertyManager.c();

        return file1 != null ? file1.getAbsolutePath() : "No settings file";
    }

    public void aD() {
        ServerGUI.a(this);
        this.q = true;
    }

    public boolean ak() {
        return this.q;
    }

    public String a(EnumGamemode enumgamemode, boolean flag) {
        return "";
    }

    public boolean getEnableCommandBlock() {
        return this.propertyManager.getBoolean("enable-command-block", false);
    }

    public int getSpawnProtection() {
        return this.propertyManager.getInt("spawn-protection", super.getSpawnProtection());
    }

    public boolean a(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (world.worldProvider.dimension != 0) {
            return false;
        } else if (this.aC().getOPs().isEmpty()) {
            return false;
        } else if (this.aC().isOp(entityhuman.getProfile())) {
            return false;
        } else if (this.getSpawnProtection() <= 0) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = world.getSpawn();
            int l = MathHelper.a(i - chunkcoordinates.x);
            int i1 = MathHelper.a(k - chunkcoordinates.z);
            int j1 = Math.max(l, i1);

            return j1 <= this.getSpawnProtection();
        }
    }

    public int l() {
        return this.propertyManager.getInt("op-permission-level", 4);
    }

    public void setIdleTimeout(int i) {
        super.setIdleTimeout(i);
        this.propertyManager.setProperty("player-idle-timeout", Integer.valueOf(i));
        this.a();
    }

    public boolean m() {
        return this.propertyManager.getBoolean("broadcast-rcon-to-ops", true);
    }

    public boolean at() {
        return this.propertyManager.getBoolean("announce-player-achievements", true);
    }

    protected boolean aE() {
        server.getLogger().info( "**** Beginning UUID conversion, this may take A LONG time ****"); // Spigot, let the user know whats up!
        boolean flag = false;

        int i;

        for (i = 0; !flag && i <= 2; ++i) {
            if (i > 0) {
                // CraftBukkit - Fix decompiler stomping on field
                DedicatedServer.i.warn("Encountered a problem while converting the user banlist, retrying in a few seconds");
                this.aG();
            }

            flag = NameReferencingFileConverter.a((MinecraftServer) this);
        }

        boolean flag1 = false;

        for (i = 0; !flag1 && i <= 2; ++i) {
            if (i > 0) {
                // CraftBukkit - Fix decompiler stomping on field
                DedicatedServer.i.warn("Encountered a problem while converting the ip banlist, retrying in a few seconds");
                this.aG();
            }

            flag1 = NameReferencingFileConverter.b((MinecraftServer) this);
        }

        boolean flag2 = false;

        for (i = 0; !flag2 && i <= 2; ++i) {
            if (i > 0) {
                // CraftBukkit - Fix decompiler stomping on field
                DedicatedServer.i.warn("Encountered a problem while converting the op list, retrying in a few seconds");
                this.aG();
            }

            flag2 = NameReferencingFileConverter.c((MinecraftServer) this);
        }

        boolean flag3 = false;

        for (i = 0; !flag3 && i <= 2; ++i) {
            if (i > 0) {
                // CraftBukkit - Fix decompiler stomping on field
                DedicatedServer.i.warn("Encountered a problem while converting the whitelist, retrying in a few seconds");
                this.aG();
            }

            flag3 = NameReferencingFileConverter.d((MinecraftServer) this);
        }

        boolean flag4 = false;

        for (i = 0; !flag4 && i <= 2; ++i) {
            if (i > 0) {
                // CraftBukkit - Fix decompiler stomping on field
                DedicatedServer.i.warn("Encountered a problem while converting the player save files, retrying in a few seconds");
                this.aG();
            }

            flag4 = NameReferencingFileConverter.a(this, this.propertyManager);
        }

        return flag || flag1 || flag2 || flag3 || flag4;
    }

    private void aG() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException interruptedexception) {
            ;
        }
    }

    public PlayerList getPlayerList() {
        return this.aC();
    }

    static Logger aF() {
        return i;
    }
}
