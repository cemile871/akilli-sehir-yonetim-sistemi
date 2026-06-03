package com.akillisehirapp;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0002\u00a8\u0006\b"}, d2 = {"Lcom/akillisehirapp/AkilliSehirApp;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "createNotificationChannels", "Companion", "app_debug"})
public final class AkilliSehirApp extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_EMERGENCY = "kanal_acil";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ENERGY = "kanal_enerji";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_GENERAL = "kanal_genel";
    @org.jetbrains.annotations.NotNull()
    public static final com.akillisehirapp.AkilliSehirApp.Companion Companion = null;
    
    public AkilliSehirApp() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void createNotificationChannels() {
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/akillisehirapp/AkilliSehirApp$Companion;", "", "<init>", "()V", "CHANNEL_EMERGENCY", "", "CHANNEL_ENERGY", "CHANNEL_GENERAL", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}