package com.akillisehirapp.data.api;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ:\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000f0\u00032\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0003\u0010\u0017\u001a\u00020\u00182\b\b\u0003\u0010\u0019\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u000f0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ*\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0014\b\u0001\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\"H\u00a7@\u00a2\u0006\u0002\u0010#\u00a8\u0006$\u00c0\u0006\u0003"}, d2 = {"Lcom/akillisehirapp/data/api/ApiService;", "", "login", "Lretrofit2/Response;", "Lcom/akillisehirapp/data/model/AuthResponse;", "request", "Lcom/akillisehirapp/data/model/LoginRequest;", "(Lcom/akillisehirapp/data/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/akillisehirapp/data/model/RegisterRequest;", "(Lcom/akillisehirapp/data/model/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentTraffic", "", "Lcom/akillisehirapp/data/model/TrafficData;", "getAirQuality", "Lcom/akillisehirapp/data/model/AirQualityData;", "getAnnouncements", "Lcom/akillisehirapp/data/model/Announcement;", "category", "", "page", "", "limit", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnnouncementDetail", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveIncidents", "Lcom/akillisehirapp/data/model/Incident;", "updateFcmToken", "body", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.akillisehirapp.data.model.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.akillisehirapp.data.model.AuthResponse>> $completion);
    
    @retrofit2.http.POST(value = "auth/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.akillisehirapp.data.model.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.akillisehirapp.data.model.AuthResponse>> $completion);
    
    @retrofit2.http.POST(value = "auth/logout")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.GET(value = "traffic/current")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentTraffic(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.akillisehirapp.data.model.TrafficData>>> $completion);
    
    @retrofit2.http.GET(value = "air-quality/current")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAirQuality(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.akillisehirapp.data.model.AirQualityData>> $completion);
    
    @retrofit2.http.GET(value = "announcements")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAnnouncements(@retrofit2.http.Query(value = "category")
    @org.jetbrains.annotations.Nullable()
    java.lang.String category, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.akillisehirapp.data.model.Announcement>>> $completion);
    
    @retrofit2.http.GET(value = "announcements/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAnnouncementDetail(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.akillisehirapp.data.model.Announcement>> $completion);
    
    @retrofit2.http.GET(value = "incidents/active")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveIncidents(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.akillisehirapp.data.model.Incident>>> $completion);
    
    @retrofit2.http.PUT(value = "users/fcm-token")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateFcmToken(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}