package com.akillisehirapp.ui.map;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u000bH\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00110\u00110\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/akillisehirapp/ui/map/MapFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "<init>", "()V", "_binding", "Lcom/akillisehirapp/databinding/FragmentMapBinding;", "binding", "getBinding", "()Lcom/akillisehirapp/databinding/FragmentMapBinding;", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "ELAZIZ_CENTER", "Lcom/google/android/gms/maps/model/LatLng;", "kavsaklar", "", "Lkotlin/Pair;", "", "locationPermLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "map", "enableMyLocation", "centerOnMyLocation", "onDestroyView", "app_debug"})
public final class MapFragment extends androidx.fragment.app.Fragment implements com.google.android.gms.maps.OnMapReadyCallback {
    @org.jetbrains.annotations.Nullable()
    private com.akillisehirapp.databinding.FragmentMapBinding _binding;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.maps.GoogleMap googleMap;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.maps.model.LatLng ELAZIZ_CENTER = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<kotlin.Pair<java.lang.String, com.google.android.gms.maps.model.LatLng>> kavsaklar = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> locationPermLauncher = null;
    
    public MapFragment() {
        super();
    }
    
    private final com.akillisehirapp.databinding.FragmentMapBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onMapReady(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.GoogleMap map) {
    }
    
    private final void enableMyLocation() {
    }
    
    private final void centerOnMyLocation() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}